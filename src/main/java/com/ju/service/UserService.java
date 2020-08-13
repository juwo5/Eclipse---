package com.ju.service;

import com.ju.common.JSONData;
import com.ju.entity.User;

public interface UserService {
	
	JSONData<User> login(String username,String password);
	JSONData<String> register(User user);
	
	JSONData<String> checkUserName(String username);
	JSONData<String> checkEmail(String email);



}
