package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserDao, User> implements IService<User> {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean loginVerify(String username, String password) {
		if(userDao.selectByUsername(username) > 0) {
			return userDao.selectUser(username, password) > 0;
		}
		return false;
	}
	
	public boolean userRegister(User user) {
		if(!loginVerify(user.getUsername(), user.getPassword())) {
			return userDao.insert(user) > 0;
		}
		return false;
	}
}
