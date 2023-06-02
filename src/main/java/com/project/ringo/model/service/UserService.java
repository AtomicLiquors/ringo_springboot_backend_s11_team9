package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.User;

public interface UserService {

	User login(User userDto) throws SQLException;

	boolean registUser(User user) throws SQLException;

	boolean updateUser(User user) throws SQLException;

	void deleteUser(int user_no) throws SQLException;

	User getUserById(String id) throws SQLException;

	List<User> getUserList() throws SQLException;

	User getUserByNo(int user_no) throws SQLException;

	User getUserByIdAndPw(String user_id, String user_pw);

	void saveRefreshToken(String user_id, String refreshToken) throws Exception;

	Object getRefreshToken(String user_id) throws Exception;

	void deleRefreshToken(String user_id) throws Exception;

}