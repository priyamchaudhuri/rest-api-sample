package org.tat.api.library.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.tat.api.library.model.Student;

public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int line) throws SQLException {

		ResultSetMetaData metadata = rs.getMetaData();
		Student student = new Student();

		for (int i = 1; i <= metadata.getColumnCount(); i++) {
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_USER_ID")) {
				student.setId(rs.getLong("USR_USER_ID"));
			}

			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_FIRST_NAME")) {
				student.setFname(rs.getString("USR_FIRST_NAME"));
			}

			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_LAST_NAME")) {
				student.setLname(rs.getString("USR_LAST_NAME"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_JOINING_DATE")) {
				student.setJoiningDate(rs.getDate("USR_JOINING_DATE"));
			}
			
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_ADDRESS_LINE_1")) {
				student.setAddressLine1(rs.getString("USR_ADDRESS_LINE_1"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_ADDRESS_LINE_2")) {
				student.setAddressLine2(rs.getString("USR_ADDRESS_LINE_2"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_ADDRESS_LINE_3")) {
				student.setAddressLine3(rs.getString("USR_ADDRESS_LINE_3"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_CITY")) {
				student.setCity(rs.getString("USR_CITY"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_COUNTRY")) {
				student.setCountry(rs.getString("USR_COUNTRY"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("USR_STATE")) {
				student.setState(rs.getString("USR_STATE"));
			}
			if (metadata.getColumnLabel(i).equalsIgnoreCase("STUDENT_ROLL_NO")) {
				student.setRollNo(rs.getLong("STUDENT_ROLL_NO"));
			}
		}

		return student;
	}

}