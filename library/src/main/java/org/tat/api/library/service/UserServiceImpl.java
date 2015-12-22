package org.tat.api.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tat.api.library.model.User;
import org.tat.api.library.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository dao;

	public void saveUser(User user) {
		dao.saveUser(user);
	}

	public List<User> getUsers() {
		return dao.getUsers();
	}

	public void deleteUser(int id) {
		dao.deleteUser(id);
	}

	public User getUser(int id) {
		return dao.getUser(id);
	}

	public void updateUser(User user) {
		dao.updateUser(user);
	}
}
