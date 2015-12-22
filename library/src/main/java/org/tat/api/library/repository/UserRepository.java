package org.tat.api.library.repository;

import java.util.List;

import org.tat.api.library.model.User;

public interface UserRepository {

	void saveUser(User user);

	List<User> getUsers();

	void deleteUser(int id);

	User getUser(int id);

	void updateUser(User user);
}