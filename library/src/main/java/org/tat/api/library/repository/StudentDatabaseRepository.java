package org.tat.api.library.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.tat.api.library.model.Address;
import org.tat.api.library.model.Student;

@Repository("StudentRepository")
public class StudentDatabaseRepository extends AbstractRepository implements
		StudentRepository {

	public void saveStudent(Student student) {
		persist(student);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudents() {
		Criteria criteria = getSession().createCriteria(Student.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	public void updateStudent(Student student) {
		getSession().update(student);
	}

}