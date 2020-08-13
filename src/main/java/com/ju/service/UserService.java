package com.ju.service;

import java.util.List;

import com.ju.common.JSONData;
import com.ju.entity.User;

public interface UserService {
	
	JSONData<User> login(String username,String password);
	JSONData<String> register(User user);
	
	JSONData<String> checkUserName(String username);
	JSONData<String> checkEmail(String email);

	
	JSONData<String> getUserQuestion(String username);
	JSONData<String> checkUserAnswer(String username,String answer);
	JSONData<String> resetPassword(String username,String newpassword);
	JSONData<User> getUserById(Integer id);
	JSONData<List<User>> getAllUser();

}
