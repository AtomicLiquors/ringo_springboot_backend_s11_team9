package com.project.ringo.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.User;
@Mapper	
@Repository
public interface UserDAO {

	// Bcrypt는 컨트롤러 단으로 옮겨야 한다.
	User login(User user) throws SQLException;

	User getUserById(String id) throws SQLException;
	
	User getUserByNo(int user_no) throws SQLException;
	
	List<User> getUserList() throws SQLException;

	boolean insertUser(User user) throws SQLException;

	boolean updateUser(User user) throws SQLException;

	void deleteUser(int user_no) throws SQLException;
	
	User getUserByIdAndPw(@Param("user_id") String userId, @Param("user_pw") String userPw);
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	Object getRefreshToken(String user_id) throws SQLException;
	
	void deleRefreshToken(Map<String, String> map) throws SQLException;

}