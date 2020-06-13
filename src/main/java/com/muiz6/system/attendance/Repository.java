package com.muiz6.system.attendance;

import com.muiz6.system.attendance.dto.NewEmployeeDto;
import com.muiz6.system.attendance.model.EmployeeModel;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public abstract class Repository {

	private static final String _PATH_TO_DATA_BASE = "jdbc:sqlite:data.db";
	private static final String _TABLE_NAME_EMPLOYEES = "employees";
	private static final String _TABLE_NAME_TIME_IN = "time_in";
	private static final String _TABLE_NAME_ATTENDANCE = "attendance";
	private static final String _SQL_CREATE_TABLE_EMPLOYEES = MessageFormat
			.format("CREATE TABLE IF NOT EXISTS {0} (\n"
			+ "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
			+ "	name TINYTEXT NOT NULL,\n"
			+ "	join_date BIGINT NOT NULL,\n"
			+ "	active BIT NOT NULL DEFAULT (1)\n"
			+ ");", _TABLE_NAME_EMPLOYEES);
	private static final String _SQL_CREATE_TABLE_TIME_IN = MessageFormat
			.format("CREATE TABLE IF NOT EXISTS {0} (\n"
			+ " date BIGINT NOT NULL,\n"
			+ "	id SMALLINT NOT NULL,\n"
			+ "	monday SMALLINT NOT NULL,\n"
			+ "	tuesday SMALLINT NOT NULL,\n"
			+ "	wednesday SMALLINT NOT NULL,\n"
			+ "	thursday SMALLINT NOT NULL,\n"
			+ "	friday SMALLINT NOT NULL,\n"
			+ "	saturday SMALLINT NOT NULL,\n"
			+ "	sunday SMALLINT NOT NULL,\n"
			+ " PRIMARY KEY(date, id)"
			+ ");", _TABLE_NAME_TIME_IN);
	private static final String _SQL_CREATE_TABLE_ATTENDANCE = MessageFormat
			.format("CREATE TABLE IF NOT EXISTS {0} (\n"
			+ " date BIGINT NOT NULL,\n"
			+ " id SMALLINT NOT NULL,\n"
			+ " time_in SMALLINT NOT NULL,\n"
			+ " PRIMARY KEY(date, id)\n"
			+ ");", _TABLE_NAME_ATTENDANCE);

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

	public static ArrayList<EmployeeModel> getEmployees() {
		final String url = _PATH_TO_DATA_BASE;
		final String sql = MessageFormat
				.format("SELECT id, name, join_date FROM {0}" +
								" WHERE active=1"
						, _TABLE_NAME_EMPLOYEES);

		try (final Connection conn = DriverManager.getConnection(url);
			final Statement stmt = conn.createStatement();
			final ResultSet result = stmt.executeQuery(sql)) {

			final ArrayList<EmployeeModel> list = new ArrayList<>();
			while(result.next()) {
				final EmployeeModel model = new EmployeeModel();
				model.setId(result.getInt("id"));
				model.setName(result.getString("name"));

				// result.getLong("join_date") not working due to some reason
				String joinDate = result.getString("join_date");
				joinDate = joinDate.replaceAll(",", "");
				model.setJoinDate(Long.parseLong(joinDate));
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
		final String url = _PATH_TO_DATA_BASE;
		final String sql = MessageFormat
				.format("SELECT MAX(id) AS maxId FROM {0}",
						_TABLE_NAME_EMPLOYEES);

		ResultSet result = null;
		try (final Connection conn = DriverManager.getConnection(url);
			 final Statement stmt = conn.createStatement();) {

			final String sql2 = MessageFormat
					.format("INSERT INTO {0} (name, join_date)" +
									" VALUES (''{1}'', ''{2}'');",
							_TABLE_NAME_EMPLOYEES,
							employee.getName(),
							employee.getJoinDate());
			stmt.execute(sql2);

			// get id of just inserted employee
			result = stmt.executeQuery(sql);
			result.next();
			final int id = result.getInt("maxId");

			final String sql3 = MessageFormat
					.format("INSERT INTO {0}" +
									" VALUES (''{1}'', ''{2}'', ''{3}''," +
									" ''{4}'', ''{5}'', ''{6}'', ''{7}''," +
									" ''{8}'', ''{9}'');",
							_TABLE_NAME_TIME_IN,
							System.currentTimeMillis(),
							id,
							employee.getTimeInMonday(),
							employee.getTimeInTuesday(),
							employee.getTimeInWednesday(),
							employee.getTimeInThursday(),
							employee.getTimeInFriday(),
							employee.getTimeInSaturday(),
							employee.getTimeInSunday());
			stmt.execute(sql3);
			callback.onCompletion(true);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			callback.onCompletion(false);
		}
		finally {
			if (result != null) {
				try {
					result.close();
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public interface OnCompletionCallback {
		void onCompletion(boolean success);
	}
}
