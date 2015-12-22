package org.tat.api.library.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tat.api.library.model.User;

@Repository("userRepository")
public class UserDatabaseRepository extends AbstractRepository implements
		UserRepository {

	public void saveUser(User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	public void deleteUser(int id) {
		Query query = getSession().createSQLQuery(
				"delete from User where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public User getUser(int id) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return (User) criteria.uniqueResult();
	}

	public void updateUser(User user) {
		getSession().update(user);
	}

}