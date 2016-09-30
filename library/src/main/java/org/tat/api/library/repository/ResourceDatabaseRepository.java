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
import org.tat.api.library.mapper.ResourceRowMapper;
import org.tat.api.library.model.Owner;
import org.tat.api.library.model.Resource;

@Repository("ResourceRepository")
public class ResourceDatabaseRepository extends AbstractRepository implements
ResourceRepository {
	private static Map<String,Field> resourceFieldMap;

	static{
		resourceFieldMap = new HashMap<String, Field>();
		resourceFieldMap.put("id", new Field("RESOURCE_ID", "id", ColumnType.LONG, "LIBRARY_RESOURCE"));
		resourceFieldMap.put("name", new Field("RESOURCE_NAME", "name", ColumnType.STRING, "LIBRARY_RESOURCE"));
		resourceFieldMap.put("author", new Field("AUTHOR", "author", ColumnType.STRING, "LIBRARY_RESOURCE"));
		resourceFieldMap.put("publication", new Field("PUBLICATION", "publication", ColumnType.STRING, "LIBRARY_RESOURCE"));
		resourceFieldMap.put("year", new Field("RESOURCE_YEAR", "year", ColumnType.LONG, "LIBRARY_RESOURCE"));
		resourceFieldMap.put("type", new Field("RESOURCE_TYPE", "type", ColumnType.STRING, "LIBRARY_RESOURCE"));
	}

	public static String STUDENT_RESOURCES="select {column.list} from library_resource ,resource_user  where library_resource.resource_id=resource_user.ru_resource_id ";
	public static String ALL_RESOURCE_QUERY = "select {column.list} from library_resource {filter.criteria} {sort.criteria}";
	public static String INSERT_RESOURCE="INSERT INTO LIBRARY_RESOURCE(RESOURCE_NAME,AUTHOR,PUBLICATION,"
			+ " RESOURCE_YEAR,RESOURCE_TYPE) "
			+ "VALUES(:resourceName, :author, :publication, :resourceYear,:resourceType)";

	public Resource saveResource(Resource resource) {
		SqlParameterSource userNamedParameters = new MapSqlParameterSource()
		.addValue("resourceName", resource.getName())
		.addValue("author", resource.getAuthor())
		.addValue("publication", resource.getPublication())
		.addValue("resourceYear", resource.getYear())
		.addValue("resourceType", resource.getType());
		KeyHolder keyHolder = new GeneratedKeyHolder();

		getNamedParameterJdbcTemplate().update(INSERT_RESOURCE,
				userNamedParameters, keyHolder,
				new String[] { "RESOURCE_ID" });
		Long resourceId = keyHolder.getKey().longValue();
		return this.getResource(resourceId);
	}

	public List<Resource> getResources(Integer offset, Integer limit,
			String sorts,boolean all,
			Map<String, String> searchRequest) throws Exception {
		Query query = getAllEntities(offset, limit, sorts, all, searchRequest, resourceFieldMap, ALL_RESOURCE_QUERY);
		List<Resource> resources = getNamedParameterJdbcTemplate().query(query.getFinalQuery(),query.getNamedParamsMap(), new ResourceRowMapper());
		return resources == null ? new ArrayList<Resource>() : resources;
	}

	public void deleteResource(int id) {
	}

	public Resource getResource(long id) {
		String getStudentQuery = ALL_RESOURCE_QUERY + " WHERE resource_id = :resourceId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("resourceId", Long.valueOf(id));
		Resource resource = (Resource) getNamedParameterJdbcTemplate().queryForObject(getStudentQuery, namedParameters, new ResourceRowMapper());  
		return resource;  
	}

	public void updateResource(Resource Resource) {
	}

	public Owner getResourceOwner(int id) {
		String getResUserQuery = "SELECT CASE  Y.OWNER_TYPE  WHEN 'USER' THEN ( SELECT 'STUDENT' AS USR_TYPE FROM USR u JOIN STUDENT s ON u.USER_ID=s.STUDENT_USR_ID WHERE u.user_id=Y.owner_id"
				+ "    UNION  SELECT 'TEACHER' AS USR_TYPE FROM USR u JOIN TEACHER t ON u.USER_ID=t.TEACHER_USR_ID WHERE u.user_id=X.owner_id   UNION"
				+ "    SELECT 'LIBRARIAN' AS USR_TYPE FROM USR u JOIN LIBRARIAN l ON u.USER_ID=l.LIBRARIAN_USER_ID WHERE u.user_id=X.owner_id)  WHEN 'RACK' THEN 'RACK'"
				+ "    END AS OWNER_TYPE, Y.OWNER_ID AS OWNER_ID "
				+ " FROM (select RU_USER_ID as OWNER_ID, 'USER' as OWNER_TYPE from resource_user where RU_RESOURCE_ID=:resourceId UNION select RR_RACK_ID as OWNER_ID, 'RACK' as OWNER_TYPE from resource_rack where resource_rack.RR_RESOURCE_ID=:resourceId)Y";
		SqlParameterSource namedParameters = new MapSqlParameterSource("resourceId", id);
		Map<String, Object> result = getNamedParameterJdbcTemplate().queryForMap(
				getResUserQuery,namedParameters);
		String ownerType = (String)result.get("OWNER_TYPE");
		//User user;
		switch(ownerType){

		case "STUDENT" :

		case "TEACHER" :
		case "LIBRARIAN" :
		case "RACK" :
		}

		return null;
	}

	public List<Resource> getStudentResources(long id,int offset, int limit, String sorts, boolean all, Map<String, String> searchRequest) throws Exception {
		StringBuffer queryBuff = new StringBuffer(STUDENT_RESOURCES);
		queryBuff.append(" AND resource_user.RU_USER_ID = :userId ");
		queryBuff.append(" {filter.criteria} {sort.criteria}");
		Query query = getAllEntities(offset, limit, sorts, all, searchRequest, resourceFieldMap, queryBuff.toString());
		query.getNamedParamsMap().put("userId", Long.valueOf(id));
		List<Resource> resources = (List<Resource>) getNamedParameterJdbcTemplate().query(
				query.getFinalQuery(),query.getNamedParamsMap(), new ResourceRowMapper());
		return resources == null ? new ArrayList<Resource>() : resources;


	}

	public Resource getStudentResource(long studentId, long resourceId) {
		String getResourceQuery = STUDENT_RESOURCES + " AND ru.RU_USER_ID = :userId AND ru.RU_RESOURCE_ID=:resourceId";
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", Long.valueOf(studentId)).addValue("resourceId", Long.valueOf(resourceId));
		Resource resource = (Resource) getNamedParameterJdbcTemplate().queryForObject(
				getResourceQuery,namedParameters, new ResourceRowMapper());
		return resource;
	}


}