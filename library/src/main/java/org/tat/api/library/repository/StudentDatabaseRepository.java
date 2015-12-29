package org.tat.api.library.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tat.api.library.controller.Sort;
import org.tat.api.library.controller.SortType;
import org.tat.api.library.model.Address;
import org.tat.api.library.model.Resource;
import org.tat.api.library.model.Student;

@Repository("StudentRepository")
public class StudentDatabaseRepository extends AbstractRepository implements
		StudentRepository {

	public void saveStudent(Student student) {
		persist(student);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudents(int offset, int limit, List<Sort> sortConfig) {
		Criteria criteria = getSession().createCriteria(Student.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setProjection(Projections.distinct(Projections.property("id")));
		if (sortConfig != null && sortConfig.size() > 0) {
			for (Sort sort : sortConfig) {
				if (sort.getSortType().equals(SortType.ASC)) {
					criteria.addOrder(Order.asc(sort.getField()));
				} else {
					criteria.addOrder(Order.desc(sort.getField()));
				}
			}
		}
		criteria.setFirstResult(offset);
		criteria.setMaxResults(limit);
		return (List<Student>) criteria.list();
	}

	public void deleteStudent(int id) {
		Query query = getSession().createSQLQuery(
				"delete from User where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	public Student getStudent(int id) {
		Criteria criteria = getSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("id", id));
		return (Student) criteria.uniqueResult();
	}
	
	public Address getStudentAddress(int id) {
		Criteria criteria = getSession().createCriteria(Student.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setProjection(Projections.property("address"));
		return (Address) criteria.uniqueResult();
	}
	
	public Set<Resource> getStudentResources(int id) {
		Criteria criteria = getSession().createCriteria(Resource.class, "resource");
		criteria.createAlias("resource.user", "student");
		criteria.add(Restrictions.eq("student.id", id));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		Set<Resource> resources = new HashSet<Resource>((List<Resource>)criteria.list());
		return resources;
	}

	public void updateStudent(Student student) {
		getSession().update(student);
	}

}