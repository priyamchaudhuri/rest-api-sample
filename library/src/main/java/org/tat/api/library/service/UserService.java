package org.tat.api.library.service;

import java.util.List;

import org.tat.api.library.model.User;
 
public interface UserService {
 
	void saveUser(User user);

	List<User> getUsers();

	void deleteUser(int id);

	User getUser(int id);

	void updateUser(User user);
}