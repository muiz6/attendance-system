package com.muiz6.system.attendance;

import com.muiz6.system.attendance.model.EmployeeModel;

import javax.annotation.Nullable;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public abstract class Repository {

	private static final String _PATH_TO_DATA_BASE = "jdbc:sqlite:data.db";
	private static final String _TABLE_NAME_EMPLOYEES = "employees";
	private static final String _TABLE_NAME_TIME_IN = "time-in";
	private static final String _TABLE_NAME_TIME_OUT = "time-out";
	private static final String _TABLE_NAME_ATTENDANCE = "attendance";
	private static final String _TABLE_NAME_HOLIDAYS = "holidays";
	private static final String _SQL_CREATE_TABLE_EMPLOYEES = MessageFormat
			.format("CREATE TABLE IF NOT EXISTS {0} (\n"
			+ "	id tinyint IDENTITY(0, 1) PRIMARY KEY,\n"
			+ "	name tinytext NOT NULL,\n"
			+ "	join_date bigint NOT NULL\n"
			+ ");", _TABLE_NAME_EMPLOYEES);
	private static final String _SQL_CREATE_TABLE_TIME_IN =
			"CREATE TABLE IF NOT EXISTS time_in (\n"
			+ "	employee_id tinyint NOT NULL,\n"
			+ "	monday smallint,\n"
			+ "	tuesday smallint,\n"
			+ "	wednesday smallint,\n"
			+ "	thursday smallint,\n"
			+ "	friday smallint,\n"
			+ "	saturday smallint,\n"
			+ "	sunday smallint,\n"
			+ " FOREIGN KEY (employee_id) REFERENCES employees (id)\n"
			+ ");";
	private static final String _SQL_CREATE_TABLE_TIME_OUT =
			"CREATE TABLE IF NOT EXISTS time_out (\n"
			+ "	employee_id tinyint NOT NULL,\n"
			+ "	monday smallint,\n"
			+ "	tuesday smallint,\n"
			+ "	wednesday smallint,\n"
			+ "	thursday smallint,\n"
			+ "	friday smallint,\n"
			+ "	saturday smallint,\n"
			+ "	sunday smallint,\n"
			+ " FOREIGN KEY (employee_id) REFERENCES employees (id)\n"
			+ ");";
	private static final String _SQL_CREATE_TABLE_HOLIDAY_DAY =
			"CREATE TABLE IF NOT EXISTS holiday_day (\n"
			+ "	day tinytext PRIMARY KEY,\n"
			+ "	value bit NOT NULL\n"
			+ ");";
	private static final String _SQL_CREATE_TABLE_HOLIDAY_DATE =
			"CREATE TABLE IF NOT EXISTS holiday_date (\n"
			+ "	date bigint PRIMARY KEY\n"
			+ ");";

	public static void initializeDataBase() {
		final String url = _PATH_TO_DATA_BASE;

		// create data base
		try (final Connection conn =  DriverManager.getConnection(url);
			final Statement stmt = conn.createStatement()) {

			// create tables
			stmt.execute(_SQL_CREATE_TABLE_EMPLOYEES);
			stmt.execute(_SQL_CREATE_TABLE_TIME_IN);
			stmt.execute(_SQL_CREATE_TABLE_TIME_OUT);
			stmt.execute(_SQL_CREATE_TABLE_HOLIDAY_DAY);
			stmt.execute(_SQL_CREATE_TABLE_HOLIDAY_DATE);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static ArrayList<EmployeeModel> getEmployees() {
		final String url = _PATH_TO_DATA_BASE;

		final String sql = MessageFormat
				.format("SELECT {0}, {1}, {2} FROM {3}",
				"id", "name", "join_date", _TABLE_NAME_EMPLOYEES);

		try (final Connection conn = DriverManager.getConnection(url);
			final Statement stmt = conn.createStatement();
			final ResultSet result = stmt.executeQuery(sql)) {

			final ArrayList<EmployeeModel> list = new ArrayList<>();
			while(result.next()) {
				final EmployeeModel model = new EmployeeModel();
				model.setId(result.getByte("id"));
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
		// final ArrayList<EmployeeModel> elseList = new ArrayList<>();
		// for (int i = 0; i < 5; i++) {
		// 	EmployeeModel model = new EmployeeModel();
		// 	model.setId((byte) i);
		// 	model.setName("name "+ i);
		// 	model.setJoinDate(i);
		// 	elseList.add(model);
		// }
		// return elseList;
		return new ArrayList<>();
	}
}
