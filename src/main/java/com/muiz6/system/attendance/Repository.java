package com.muiz6.system.attendance;

import com.muiz6.system.attendance.dto.NewEmployeeDto;
import com.muiz6.system.attendance.model.AttendanceItemModel;
import com.muiz6.system.attendance.model.EmployeeItemModel;
import com.muiz6.system.attendance.model.EmployeeModel;
import javafx.application.Platform;
import jdk.internal.jline.internal.Nullable;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public abstract class Repository {

	private static final String _PATH_TO_DATA_BASE = "jdbc:sqlite:data.db";
	private static final String _SQL_CREATE_TABLE_EMPLOYEES =
			"CREATE TABLE IF NOT EXISTS employees (\n" +
					" id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
					" name TINYTEXT NOT NULL,\n" +
					" join_date BIGINT NOT NULL,\n" +
					" active BIT NOT NULL DEFAULT (1));";
	private static final String _SQL_CREATE_TABLE_TIME_IN =
			"CREATE TABLE IF NOT EXISTS time_in (\n" +
					" date BIGINT NOT NULL,\n" +
					" id SMALLINT NOT NULL,\n" +
					" monday SMALLINT NOT NULL,\n" +
					" tuesday SMALLINT NOT NULL,\n" +
					" wednesday SMALLINT NOT NULL,\n" +
					" thursday SMALLINT NOT NULL,\n" +
					" friday SMALLINT NOT NULL,\n" +
					" saturday SMALLINT NOT NULL,\n" +
					" sunday SMALLINT NOT NULL,\n" +
					" PRIMARY KEY(date, id));";
	private static final String _SQL_CREATE_TABLE_ATTENDANCE =
			"CREATE TABLE IF NOT EXISTS attendance (\n" +
					" date BIGINT NOT NULL,\n" +
					" id SMALLINT NOT NULL,\n" +
					" time_in SMALLINT NOT NULL,\n" +
					" PRIMARY KEY(date, id));";

	public static void initializeDataBase() {
		final String url = _PATH_TO_DATA_BASE;

		// create data base
		try (final Connection conn =  DriverManager.getConnection(url);
			final Statement stmt = conn.createStatement()) {

			// create tables
			stmt.execute(_SQL_CREATE_TABLE_EMPLOYEES);
			stmt.execute(_SQL_CREATE_TABLE_TIME_IN);
			stmt.execute(_SQL_CREATE_TABLE_ATTENDANCE);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<EmployeeItemModel> getEmployees() {
		final String url = _PATH_TO_DATA_BASE;
		final String sql = "SELECT id, name, join_date FROM employees" +
				" WHERE active=1;";

		try (final Connection conn = DriverManager.getConnection(url);
			 final Statement stmt = conn.createStatement();
			 final ResultSet result = stmt.executeQuery(sql)) {

			final ArrayList<EmployeeItemModel> list = new ArrayList<>();
			while(result.next()) {
				final EmployeeItemModel model = new EmployeeItemModel();
				model.setId(result.getInt("id"));
				model.setName(result.getString("name"));
				model.setJoinDate(result.getLong("join_date"));
				list.add(model);
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// else
		return new ArrayList<>();
	}

	public static void addEmployee(NewEmployeeDto employee,
			OnCompletionCallback callback) {
		new Thread(() -> {
			final String url = _PATH_TO_DATA_BASE;
			final String sql = "INSERT INTO employees (name, join_date) VALUES (?, ?);";
			final String sql2 = "SELECT MAX(id) AS maxId FROM employees;";

			try (final Connection conn = DriverManager.getConnection(url);
				 final Statement stmt = conn.createStatement();
				 final PreparedStatement prepStmt = conn.prepareStatement(sql)) {
				prepStmt.setString(1, employee.getName());
				prepStmt.setLong(2, employee.getJoinDate());
				prepStmt.execute();

				// get id of just inserted employee
				try (final ResultSet rs = stmt.executeQuery(sql2)) {
					rs.next();
					final int id = rs.getInt("maxId");
					_insertTimeInRecord(conn, employee, id);
				}
				Platform.runLater(() -> {
					callback.onCompletion(true);
				});
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				Platform.runLater(() -> {
					callback.onCompletion(false);
				});
			}
		}).start();
	}

	/**
	 * mark absent/holiday of all employees at start of program for today,
	 * except the ones that has already been added to today's attendance
	 * record.
	 */
	public static void markEmployeeAbsentAll() {
		final long epochMilliDate = LocalDate.now()
				.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
		final String sql = "SELECT id FROM employees WHERE active=1;";
		final String sql2 = "SELECT id FROM attendance WHERE date=?;";
		final String sql3 = "INSERT INTO attendance VALUES (?, ?, ?);";

		try (final Connection conn = DriverManager.getConnection(_PATH_TO_DATA_BASE);
			 final Statement stmt = conn.createStatement();
			 final ResultSet rs = stmt.executeQuery(sql);
			 final PreparedStatement prepStmt = conn.prepareStatement(sql2);
			 final PreparedStatement insertStmt = conn.prepareStatement(sql3)) {
			final ArrayList<Integer> regIds = new ArrayList<>();
			while (rs.next()) {
				regIds.add(rs.getInt("id"));
			}

			prepStmt.setLong(1, epochMilliDate);
			final ArrayList<Integer> markedIds =  new ArrayList<>();
			try (ResultSet rs2 = prepStmt.executeQuery()) {
				while (rs2.next()) {
					markedIds.add(rs2.getInt("id"));
				}
			}

			// now only ids that have not been marked for today are left
			regIds.removeAll(markedIds);
			for (final int id: regIds) {
				insertStmt.setLong(1, epochMilliDate);
				insertStmt.setInt(2, id);

				// weekday for column to lookup in time_in table
				final String weekDay = new SimpleDateFormat("EEEE")
						.format(new Date()).toLowerCase();
				final short timeInToday;
				final String sql5 = String
						.format("SELECT %s from time_in WHERE id=%d" +
								" ORDER BY date DESC LIMIT 1;",
						weekDay, id);
				try (final ResultSet rsTimeIn = stmt.executeQuery(sql5)) {
					rsTimeIn.next();
					timeInToday = rsTimeIn.getShort(weekDay);
				}
				if (timeInToday == Constants.TIME_IN_HOLIDAY) {
					insertStmt.setShort(3, Constants.TIME_IN_HOLIDAY);
				}
				else {
					insertStmt.setShort(3, Constants.TIME_IN_ABSENT);
				}
				insertStmt.execute();
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<AttendanceItemModel> getAttendanceList() {
		final String sql = "SELECT id, time_in FROM attendance WHERE date=?;";
		final String sql2 = "SELECT name, join_date FROM employees WHERE id=?;";

		final String url = _PATH_TO_DATA_BASE;
		try (final Connection conn = DriverManager.getConnection(url);
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			final long epochMilliDate = LocalDate.now()
					.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
			stmt.setLong(1, epochMilliDate);
			ArrayList<AttendanceItemModel> list = new ArrayList<>();
			try (ResultSet rs = stmt.executeQuery();
				 PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
				while (rs.next()) {
					AttendanceItemModel model = new AttendanceItemModel();
					final int id = rs.getInt("id");
					model.setId(id);
					model.setTimeIn(rs.getShort("time_in"));

					stmt2.setInt(1, id);
					try (ResultSet rs2 = stmt2.executeQuery()) {
						rs2.next();
						model.setName(rs2.getString("name"));
						model.setJoinDate(rs2.getLong("join_date"));
					}
					list.add(model);
				}
			}
			return list;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Nullable
	public static EmployeeModel getEmployee(int employeeId) {
		final String url = _PATH_TO_DATA_BASE;
		final String sql = "SELECT name, join_date FROM employees WHERE id=?;";
		final String sql2 = "SELECT * FROM time_in WHERE id=?;";

		try (final Connection conn = DriverManager.getConnection(url);
			 final PreparedStatement stmt = conn.prepareStatement(sql);
			 final PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
			stmt.setInt(1, employeeId);
			stmt2.setInt(1, employeeId);
			EmployeeModel employee = new EmployeeModel();
			try (final ResultSet rs = stmt.executeQuery();
				 final ResultSet rs2 = stmt2.executeQuery()) {
				rs.next();
				rs2.next();
				employee.setId(employeeId);
				employee.setName(rs.getString("name"));
				employee.setJoinDate(rs.getLong("join_date"));
				employee.setTimeInMonday(rs2.getShort("monday"));
				employee.setTimeInTuesday(rs2.getShort("tuesday"));
				employee.setTimeInWednesday(rs2.getShort("wednesday"));
				employee.setTimeInThursday(rs2.getShort("thursday"));
				employee.setTimeInFriday(rs2.getShort("friday"));
				employee.setTimeInSaturday(rs2.getShort("saturday"));
				employee.setTimeInSunday(rs2.getShort("sunday"));
				return employee;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void updateEmployee(EmployeeModel employee,
			OnCompletionCallback callback) {
		new Thread(() -> {
			final String url = _PATH_TO_DATA_BASE;
			final String sql = "SELECT date FROM time_in WHERE id=? ORDER BY date DESC LIMIT 1;";
			final String sql2 = "UPDATE time_in" +
					" SET monday=?, tuesday=?, wednesday=?, thursday=?," +
					" friday=?, saturday=?, sunday=?" +
					" WHERE date=? AND id=?;";
			try (final Connection conn = DriverManager.getConnection(url);
				 final PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, employee.getId());
				try (ResultSet rs = stmt.executeQuery()) {
					rs.next();
					long storedDate = rs.getLong("date");
					final long epochMilliToday = LocalDate.now()
							.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
					if (storedDate == epochMilliToday) {
						try (final PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
							stmt2.setShort(1, employee.getTimeInMonday());
							stmt2.setShort(2, employee.getTimeInTuesday());
							stmt2.setShort(3, employee.getTimeInWednesday());
							stmt2.setShort(4, employee.getTimeInThursday());
							stmt2.setShort(5, employee.getTimeInFriday());
							stmt2.setShort(6, employee.getTimeInSaturday());
							stmt2.setShort(7, employee.getTimeInSunday());
							stmt2.setLong(8, epochMilliToday);
							stmt2.setInt(9, employee.getId());
							stmt2.execute();
							_updateEmployeeName(conn, employee.getId(), employee.getName());
						}
					}
					else {
						_insertTimeInRecord(conn, employee, employee.getId());
					}
				}
				Platform.runLater(() -> {
					callback.onCompletion(true);
				});
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
				Platform.runLater(() -> {
					callback.onCompletion(false);
				});
			}
		}).start();
	}

	public static void disableEmployee(int id) {
		new Thread(() -> {
			String url = _PATH_TO_DATA_BASE;
			String sql = "UPDATE employees SET active=0 WHERE id=?;";
			try (final Connection conn = DriverManager.getConnection(url);
				 final PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, id);
				stmt.execute();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}).start();
	}

	public static void markAttendance(int id, short timeIn) {
		new Thread(() -> {
			final String url = _PATH_TO_DATA_BASE;
			final String sql = "UPDATE attendance SET time_in=?" +
					" WHERE date=? AND id=?;";
			try (final Connection conn = DriverManager.getConnection(url);
				 final PreparedStatement stmt = conn.prepareStatement(sql)) {
				final long epochMilliToday = LocalDate.now()
						.atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
				stmt.setShort(1, timeIn);
				stmt.setLong(2, epochMilliToday);
				stmt.setInt(3, id);
				stmt.execute();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}).start();
	}

	public static ArrayList<Long> getHolidayList() {
		final String url = _PATH_TO_DATA_BASE;
		final String sql = "SELECT date FROM attendance WHERE time_in=-1;";
		try (final Connection conn = DriverManager.getConnection(url);
			 final Statement stmt = conn.createStatement();
			 final ResultSet rs =stmt.executeQuery(sql)) {
			ArrayList<Long> result = new ArrayList<>();
			while (rs.next()) {
				result.add(rs.getLong("date"));
			}
			return result;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	private static void _insertTimeInRecord(Connection conn,
			NewEmployeeDto employee, int id) throws SQLException {
		final String sql = "INSERT INTO time_in VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		final PreparedStatement prepStmt = conn.prepareStatement(sql);

		final long epochMilliDate = LocalDate.now()
				.atStartOfDay(ZoneId.systemDefault())
				.toEpochSecond() * 1000;
		prepStmt.setLong(1, epochMilliDate); // today date at 12am
		prepStmt.setInt(2, id);
		prepStmt.setShort(3, employee.getTimeInMonday());
		prepStmt.setShort(4, employee.getTimeInTuesday());
		prepStmt.setShort(5, employee.getTimeInWednesday());
		prepStmt.setShort(6, employee.getTimeInThursday());
		prepStmt.setShort(7, employee.getTimeInFriday());
		prepStmt.setShort(8, employee.getTimeInSaturday());
		prepStmt.setShort(9, employee.getTimeInSunday());
		prepStmt.execute();
		_updateEmployeeName(conn, id, employee.getName());
	}

	private static void _updateEmployeeName(Connection conn, int id, String name)
			throws SQLException {
		final String sql = "UPDATE employees SET name=? WHERE id=?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setInt(2, id);
		stmt.execute();
	}

	/**
	 * used by some methods of Repository to notify if the process was a
	 * success or a failure
	 */
	public interface OnCompletionCallback {
		void onCompletion(boolean success);
	}
}
