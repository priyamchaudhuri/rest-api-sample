package org.tat.api.library.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.tat.api.core.sql.ColumnType;
import org.tat.api.core.sql.Field;
import org.tat.api.core.sql.Query;
import org.tat.api.library.mapper.StudentRowMapper;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;

@Repository("StudentRepository")
public class StudentDatabaseRepository extends AbstractRepository implements
		StudentRepository {

	private static Map<String,Field> studentsFieldMap;
	
	static{
		studentsFieldMap = new HashMap<String, Field>();
		studentsFieldMap.put("id", new Field("USER_ID", "id", ColumnType.LONG, "USR"));
		studentsFieldMap.put("fname", new Field("FIRST_NAME", "fname", ColumnType.STRING, "USR"));
		studentsFieldMap.put("lname", new Field("LAST_NAME", "lname", ColumnType.STRING, "USR"));
		studentsFieldMap.put("joiningDate", new Field("JOINING_DATE", "joiningDate", ColumnType.TIMESTAMP, "USR"));
		studentsFieldMap.put("addressLine1", new Field("ADDRESS_LINE_1", "addressLine1", ColumnType.STRING, "USR"));
		studentsFieldMap.put("addressLine2", new Field("ADDRESS_LINE_2", "addressLine2", ColumnType.STRING, "USR"));
		studentsFieldMap.put("addressLine3", new Field("ADDRESS_LINE_3", "addressLine3", ColumnType.STRING, "USR"));
		studentsFieldMap.put("city", new Field("CITY", "city", ColumnType.STRING, "USR"));
		studentsFieldMap.put("state", new Field("STATE", "state", ColumnType.STRING, "USR"));
		studentsFieldMap.put("country", new Field("COUNTRY", "country", ColumnType.STRING, "USR"));
		studentsFieldMap.put("rollNo", new Field("ROLL_NO", "rollNo", ColumnType.LONG, "STUDENT"));
	}
	
	public static String ALL_STUDENT_QUERY = "SELECT {column.list} FROM USR,STUDENT WHERE USR.USER_ID=STUDENT.STUDENT_USR_ID {filter.criteria} {sort.criteria}";
    public static String SINGLE_STUDENT_QUERY="select {column.list} from usr,student where usr.user_id=student.student_usr_id";
	public static String INSERT_USER="INSERT INTO USR(USER_ID,FIRST_NAME,LAST_NAME,JOINING_DATE,"
			               + " ADDRESS_LINE_1,ADDRESS_LINE_2,ADDRESS_LINE_3,CITY,STATE,COUNTRY) "
			               + "VALUES(USR_SEQUENCE.NEXTVAL, :firstName, :lastName, :joiningDate, :addressLine1,:addressLine2,:addressLine3,:city,:state,:country)";
	public static String INSERT_STUDENT="INSERT INTO STUDENT(STUDENT_USR_ID,ROLL_NO) values(:userId,:rollNo)";
    public static String STUDENT_RESOURCES="select * from library_resource lr,resource_user ru where lr.resource_id=ru.ru_resource_id";
	
	public Student saveStudent(Student student) throws Exception {
		SqlParameterSource userNamedParameters = new MapSqlParameterSource()
		.addValue("firstName", student.getFname())
		.addValue("lastName", student.getLname())
		.addValue("joiningDate", student.getJoiningDate())
		.addValue("addressLine1", student.getAddressLine1())
		.addValue("addressLine2", student.getAddressLine2())
		.addValue("addressLine3", student.getAddressLine3())
		.addValue("city", student.getCity())
		.addValue("state", student.getState())
		.addValue("country", student.getCountry());
		KeyHolder keyHolder = new GeneratedKeyHolder();

		getNamedParameterJdbcTemplate().update(INSERT_USER,
				userNamedParameters, keyHolder,
				new String[] { "USER_ID" });
		Long studentId = keyHolder.getKey().longValue();
		SqlParameterSource studentNamedParameters = new MapSqlParameterSource()
		.addValue("userId", studentId)
		.addValue("rollNo", student.getRollNo());
		getNamedParameterJdbcTemplate().update(INSERT_STUDENT,
				studentNamedParameters);
		return this.getStudent(studentId, null);
		
	}

	public List<Student> getStudents(int offset, int limit, String sorts,
			String fields, boolean all, Map<String, String> searchRequest) throws Exception {
		long startTime = System.currentTimeMillis();
		Query finalQueryObj = getAllEntities(offset, limit, sorts,
				fields,all, searchRequest,studentsFieldMap,ALL_STUDENT_QUERY);
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken to form query object: " + (endTime-startTime));
		startTime = System.currentTimeMillis();
		List<Student> students = getNamedParameterJdbcTemplate().query(
				finalQueryObj.getFinalQuery(), finalQueryObj.getNamedParamsMap(), new StudentRowMapper());
		endTime = System.currentTimeMillis();
		System.out.println("Time taken to query db: " + (endTime-startTime));
		return students == null ? new ArrayList<Student>() : students;
	}

	public void deleteStudent(int id) {
	}

	public Student getStudent(long id,String fields) throws Exception {
		String getStudentQuery = SINGLE_STUDENT_QUERY + " AND usr.USER_ID = :userId";
		getStudentQuery = populateFields(getStudentQuery, fields, studentsFieldMap);
		SqlParameterSource namedParameters = new MapSqlParameterSource("userId", Long.valueOf(id));
		Student student = (Student) getNamedParameterJdbcTemplate().queryForObject(getStudentQuery, namedParameters, new StudentRowMapper());  
		 return student;  
		
	}



	public Resource getStudentResource(int studentId, int resourceId) {
		return null;
	}

	public void updateStudent(Student student) {
	}

}