package org.tat.api.core.sql;

/**
 * Represents the sort applied to a field
 *
 */
public class Sort extends Criteria {

	private Order order;

	public Sort(Order order, Field field) {
		super(field);
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	/**
	 * @return the sort string for the criteria
	 */
	public String getSort() {
		StringBuffer sort = new StringBuffer();
		sort = sort.append(this.getField().getQualifiedName())
				.append(SPACE).append(this.getOrder().name());
		return sort.toString();
	}
}
