package org.tat.api.library.repository;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.tat.api.core.sql.Field;
import org.tat.api.core.sql.Query;
import org.tat.api.core.sql.QueryImpl;

public abstract class AbstractRepository {

	private static final Logger logger = Logger
			.getLogger(AbstractRepository.class);

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	protected Query getAllEntities(int offset, int limit, String sorts,
			String fields, boolean all, Map<String, String> searchRequest,
			Map<String, Field> entityFieldMap, String baseQuery)
					throws Exception {
		Query query = new QueryImpl();
		query.formQueryObject(offset, limit, sorts, fields, all, searchRequest,
				entityFieldMap, baseQuery);
		logger.debug("Final Query : " + query.getFinalQuery());
		return query;
	}

	public String populateFields(String queryStr, String fields,
			Map<String, Field> entityFieldMap) throws Exception {
		Query query = new QueryImpl();
		queryStr = queryStr.replace("{column.list}",
				query.getColumnsList(fields, entityFieldMap));
		return queryStr;
	}
}