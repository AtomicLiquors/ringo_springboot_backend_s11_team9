package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.UserDAO;
import com.project.ringo.model.dto.User;



@Service("userService")
public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	public UserServiceImpl(UserDAO userDao) {
		this.userDao = userDao;
	}
	

	@Override
	public User login(User userDto) throws SQLException {
		if(userDto.getUser_id() == null || userDto.getUser_pw() == null)
			return null;
		return sqlSession.getMapper(UserDAO.class).login(userDto);
	}
	

	@Override
	public boolean registUser(User user) throws SQLException {
		return userDao.insertUser(user); 
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		return userDao.updateUser(user); 
	}
	

	@Override
	public void deleteUser(int user_no) throws SQLException {
		userDao.deleteUser(user_no); 
	}
	

	@Override
	public User getUserById(String id) throws SQLException {
		return userDao.getUserById(id); 
	}


	@Override
	public List<User> getUserList() throws SQLException {
		return userDao.getUserList();
	}


	@Override
	public User getUserByNo(int user_no) throws SQLException {
		return userDao.getUserByNo(user_no);
	}
	

	@Override
	public User getUserByIdAndPw(String user_id, String user_pw) {
		return userDao.getUserByIdAndPw(user_id, user_pw);
	}
	
	@Override
	public void saveRefreshToken(String user_id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserDAO.class).saveRefreshToken(map);
	}
	
	@Override
	public Object getRefreshToken(String user_id) throws Exception {
		return sqlSession.getMapper(UserDAO.class).getRefreshToken(user_id);
	}
	
	@Override
	public void deleRefreshToken(String user_id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_id", user_id);
		map.put("token", null);
		sqlSession.getMapper(UserDAO.class).deleRefreshToken(map);
	}
	
}
