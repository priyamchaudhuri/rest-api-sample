package org.tat.api.core.sql;

public class Pagination {

	private int offset;
	private int limit;
	private boolean all;

	static final String PAGE_WRAPPER = "SELECT * FROM (SELECT X.*, ROWNUM as ID FROM ({entity.query}) X ) WHERE ID BETWEEN :offset AND (:offset + :limit - 1)";

	public Pagination(int offset, int limit, boolean all) {
		super();
		this.offset = offset;
		this.limit = limit;
		this.all = all;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public boolean isAll() {
		return all;
	}

	/**
	 * @param query
	 * @return the final query after applying pagination
	 */
	public String getPageQuery(String query) {
		String pageQuery;
		if (!all)
			pageQuery = PAGE_WRAPPER.replace("{entity.query}", query);
		else
			pageQuery = query;
		return pageQuery;
	}

}
