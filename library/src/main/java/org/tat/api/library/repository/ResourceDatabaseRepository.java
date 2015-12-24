package org.tat.api.library.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tat.api.library.model.Resource;

@Repository("ResourceRepository")
public class ResourceDatabaseRepository extends AbstractRepository implements
		ResourceRepository {

	public void saveResource(Resource Resource) {
		persist(Resource);
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getResources() {
		Criteria criteria = getSession().createCriteria(Resource.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Resource>) criteria.list();
	}

	public void deleteResource(int id) {
		Query query = getSession().createSQLQuery(
				"delete from User where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public Resource getResource(int id) {
		Criteria criteria = getSession().createCriteria(Resource.class);
		criteria.add(Restrictions.eq("id", id));
		return (Resource) criteria.uniqueResult();
	}

	public void updateResource(Resource Resource) {
		getSession().update(Resource);
	}

}