package com.ju.service.impl;

import java.util.List;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ju.common.JSONData;
import com.ju.dao.UserMapper;
import com.ju.entity.User;
import com.ju.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public JSONData<User> login(String username, String password) {
		int count = userMapper.checkUserName(username);
		if(count==0) {
			return JSONData.buildError("此用户不存在");
		}
		//加密解密
		Hashids hashids = new Hashids("ju");
		password = hashids.encodeHex(password);
		User user = userMapper.login(username, password);
		if(user==null) {
			return JSONData.buildError("密码错误");
		}
		//清空密码
		user.setPassword("");
		return JSONData.buildSuccess(user,"登录成功");
	}

	@Override
	public JSONData<String> register(User user) {
		int count = userMapper.checkUserName(user.getUsername());
		if(count>0) {
			return JSONData.buildError("此用户已存在");
		}
		count = userMapper.checkEmail(user.getEmail());
		if(count>0) {
			return JSONData.buildError("此邮箱已存在");
		}
		
		Hashids hashids = new Hashids("ju");
		String encodeHex = hashids.encodeHex(user.getPassword());
		user.setPassword(encodeHex);
		
		count= userMapper.insert(user);
		if(count==0) {
			return JSONData.buildError("注册失败");
		}
		return JSONData.buildSuccess("注册成功");
	}

	
	
	@Override
	public JSONData<String> checkUserName(String username) {
		int count = userMapper.checkUserName(username);
		if(count>0) {
			return JSONData.buildError("用户名已存在");
		}
		return  JSONData.buildSuccess("用户名不存在，校验成功");
	}
	@Override
	public JSONData<String> checkEmail(String email) {
		int count = userMapper.checkEmail(email);
		if(count>0) {
			return JSONData.buildError("邮箱已存在");
		}
		return  JSONData.buildSuccess("邮箱不存在，校验成功");
	}

	@Override
	public JSONData<String> getUserQuestion(String username) {
		int count = userMapper.checkUserName(username);
		if(count==0) {
			return JSONData.buildError("用户名不存在");
		}
		String question = userMapper.getUserQuestion(username);
		return JSONData.buildSuccess(question);
	}

	@Override
	public JSONData<String> checkUserAnswer(String username, String answer) {
		int count = userMapper.checkUserName(username);
		if(count==0) {
			return JSONData.buildError("用户名不存在");
		}
		count = userMapper.checkUserAnswer(username, answer);
		if(count>0) {
			return JSONData.buildSuccess("答案校验成功");
		}
		return JSONData.buildError("答案有误");
	}

	@Override
	public JSONData<String> resetPassword(String username, String newpassword) {
		//加密
		Hashids hashids = new Hashids("ju");
		newpassword = hashids.encodeHex(newpassword);
		int count = userMapper.resetPassword(username, newpassword);
		if(count>0) {
			return JSONData.buildSuccess("密码重置成功");
		}
		return JSONData.buildError("密码重置失败");
	}

	@Override
	public JSONData<User> getUserById(Integer id) {
		User user = userMapper.getUserById(id);
		if(user==null) {
			return JSONData.buildError("id查找没有用户，操作失败");
		}
		return JSONData.buildSuccess(user);
	}

	@Override
	public JSONData<List<User>> getAllUser() {
		List<User> selectALL = userMapper.selectAll();
		if(selectALL==null) {
			return JSONData.buildError("所有用户信息查询失败");
		}
		return JSONData.buildSuccess(selectALL);
	}
	
	
	
	

}
