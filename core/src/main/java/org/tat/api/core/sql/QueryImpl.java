package org.tat.api.core.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tat.api.core.exceptions.InvalidFieldNameException;
import org.tat.api.core.exceptions.InvalidSearchInputException;
/**
 * Represents a query
 *
 */
public class QueryImpl implements Query{

	private String finalQuery;
	private Pagination pagination;
	private List<Sort> sortList;
	private List<Search> searchFields;
	private Map<String,Object> namedParamsMap = new HashMap<String, Object>();

	static final String ORDER_BY = " ORDER BY ";
	static final String WHERE = " WHERE ";
	static final String AND = " AND ";
	static final String COMMA = ",";
	static final String SPLIT_REGEX_COMMA = "\\s*,\\s*";
	public String getFinalQuery() {
		return finalQuery;
	}
	public Map<String, Object> getNamedParamsMap() {
		return namedParamsMap;
	}

	/**
	 * @param offset
	 * @param limit
	 * @param sorts the list of sorts to be applied
	 * @param selectFields list of columns to be fetched
	 * @param all if all records are to be fetched
	 * @param searchRequest input for the where clause
	 * @param entityFieldMap map of database fields metadata
	 * @param baseQuery base query 
	 * @throws InvalidSearchInputException
	 * @throws InvalidFieldNameException
	 */
	public void formQueryObject(int offset, int limit, String sorts,
			boolean all, Map<String, String> searchRequest,
			Map<String, Field> entityFieldMap,String baseQuery) throws InvalidSearchInputException, InvalidFieldNameException {
		//Form sort criteria
		baseQuery = baseQuery.replace("{sort.criteria}", formSortCriteria(sorts, entityFieldMap));

		//Form search criteria
		baseQuery = baseQuery.replace("{filter.criteria}", formSearchCriteria(searchRequest, entityFieldMap,baseQuery));

		//Fields to select
		baseQuery = baseQuery.replace("{column.list}", getColumnsList(entityFieldMap));

		//Pagination
		this.pagination = new Pagination(offset, limit, all);
		namedParamsMap.put("offset", offset);
		namedParamsMap.put("limit", limit);

		this.finalQuery = pagination.getPageQuery(baseQuery);
	}

	/**
	 * This method returns a string which contains a list of database columns to be fetched 
	 * @param selectFields list of columns to be fetched
	 * @param entityFieldMap map of database fields metadata
	 * @return string which replaces the column list placeholder in base query
	 * @throws InvalidFieldNameException
	 */
	public String getColumnsList(Map<String,Field> entityFieldMap) throws InvalidFieldNameException{
		StringBuffer fieldsList = new StringBuffer();
		Iterator<Field> fieldIterator = entityFieldMap.values().iterator();
		while(fieldIterator.hasNext()){
			Field field = fieldIterator.next();
			fieldsList.append(field.getQualifiedName());
			fieldsList.append(Criteria.SPACE);
			fieldsList.append(field.getAliasName());
			if(fieldIterator.hasNext()){
				fieldsList.append(COMMA);
			}
		}
		return fieldsList.toString();
	}

	/**
	 * This method builds the sort criteria for the query.
	 * @param sorts list of sorts to be applied
	 * @param entityFieldMap map of database fields metadata
	 * @return string which replaces the sort criteria placeholder in base query
	 * @throws InvalidFieldNameException
	 */
	private String formSortCriteria(String sorts,Map<String,Field> entityFieldMap) throws InvalidFieldNameException{
		if(sorts!=null && sorts.length()>0){
			List<String> list = Arrays.asList(sorts.split(SPLIT_REGEX_COMMA));
			if(list.isEmpty()==false){
				for(String sortStr : list){
					boolean desc = sortStr.trim().startsWith(Order.DESC.getValue());
					String fieldName = desc?sortStr.trim().substring(1):sortStr.trim();
					if(entityFieldMap.containsKey(fieldName)){
						Sort sort;
						if(desc){
							sort = new Sort(Order.DESC, entityFieldMap.get(fieldName));
						}else{
							sort = new Sort(Order.ASC, entityFieldMap.get(fieldName));
						}
						if(sortList==null)
							sortList = new ArrayList<Sort>();
						sortList.add(sort);
					}else{
						throw new InvalidFieldNameException(fieldName);
					}
				}
			}
		}
		if(sortList!=null && sortList.size()>0){
			StringBuffer sortBuff = new StringBuffer(ORDER_BY);
			for(Sort obj : sortList){
				sortBuff.append(obj.getSort());
				if(sortList.indexOf(obj)<sortList.size()-1)
					sortBuff.append(COMMA);
			}

			return sortBuff.toString();
		}else{
			return "";
		}
	}

	/**
	 * This method builds the where clause of the query
	 * @param searchRequest map of fields and values to be filtered
	 * @param entityFieldMap map of database fields metadata
	 * @param query base query
	 * @return string which replaces filter criteria in base query
	 * @throws InvalidFieldNameException
	 * @throws InvalidSearchInputException
	 */
	private String formSearchCriteria(Map<String,String> searchRequest,Map<String,Field> entityFieldMap,String query) throws InvalidFieldNameException, InvalidSearchInputException{
		if(searchRequest!=null && searchRequest.size()>0){
			for(String key : searchRequest.keySet()){
				if(entityFieldMap.containsKey(key)){
					Search searchObj = new Search(key, searchRequest.get(key), entityFieldMap.get(key));
					if(searchFields==null)
						searchFields = new ArrayList<Search>();
					searchFields.add(searchObj);
				}else{
					throw new InvalidFieldNameException(key);
				}
			}
		}
		if(searchFields!=null && searchFields.size()>0){
			StringBuffer searchBuffer = new StringBuffer();
			if(query.contains(WHERE))
				searchBuffer.append(AND);
			else
				searchBuffer.append(WHERE);
			for(Search obj : searchFields){
				searchBuffer.append(obj.getPattern());
				if(searchFields.indexOf(obj)<searchFields.size()-1)
					searchBuffer.append(AND);
				namedParamsMap.putAll(obj.getNamedParams());
			}
			return searchBuffer.toString();
		}else{
			return "";
		}
	}
}
