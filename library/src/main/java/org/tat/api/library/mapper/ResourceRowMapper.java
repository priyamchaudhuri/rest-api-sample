package org.tat.api.library.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.tat.api.library.model.Resource;

public class ResourceRowMapper implements RowMapper<Resource> {

	@Override
	public Resource mapRow(ResultSet rs, int line) throws SQLException {
		ResultSetMetaData metadata = rs.getMetaData();
		Resource resource = new Resource();

		for (int i = 1; i <= metadata.getColumnCount(); i++) {
			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_RESOURCE_ID")) {
				resource.setId(rs.getLong("LIBRARY_RESOURCE_RESOURCE_ID"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_RESOURCE_NAME")) {
				resource.setName(rs.getString("LIBRARY_RESOURCE_RESOURCE_NAME"));
			}

			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_AUTHOR")) {
				resource.setAuthor(rs.getString("LIBRARY_RESOURCE_AUTHOR"));
			}

			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_PUBLICATION")) {
				resource.setPublication(rs.getString("LIBRARY_RESOURCE_PUBLICATION"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_RESOURCE_YEAR")) {
				resource.setYear(rs.getInt("LIBRARY_RESOURCE_RESOURCE_YEAR"));
			}
			
			if (metadata.getColumnLabel(i).equalsIgnoreCase("LIBRARY_RESOURCE_RESOURCE_TYPE")) {
				resource.setType(rs.getString("LIBRARY_RESOURCE_RESOURCE_TYPE"));
			}
			
		}

		return resource;
	}

	}


