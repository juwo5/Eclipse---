package com.ju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ju.common.JSONData;
import com.ju.entity.User;
import com.ju.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/user/")
@Api(value="用户接口",description="用户接口")
public class UserController {
	//@Resource(name="userService")
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="登录",notes="登录接口描述",httpMethod="POST")
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<User> login(String username,String password,HttpSession session) {
		JSONData data = userService.login(username, password);
		if(data.getCode()==0) {
			session.setAttribute("current_user", data.getData());
		}
		return data;
	}
	
	@ApiOperation(value="注册",notes="注册接口描述",httpMethod="POST")
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> register(User user) {
		JSONData data = userService.register(user);
		return data;
	}
	
	@ApiOperation(value="检查用户名",notes="检查用户名描述",httpMethod="POST")
	@RequestMapping(value="checkUserName",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> checkUserName(String username) {
		return userService.checkUserName(username);
	}
	@ApiOperation(value="检查邮箱",notes="检查邮箱描述",httpMethod="POST")
	@RequestMapping(value="checkEmail",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> checkEmail(String email) {
		return userService.checkEmail(email);
	}
	
	
	@ApiOperation(value="退出登录",notes="退出登录描述",httpMethod="GET")
	@RequestMapping(value="logout",method=RequestMethod.GET)
	@ResponseBody
	public JSONData<String> logout(HttpSession session) {
		session.removeAttribute("current_user");
		return JSONData.buildSuccess("退出成功");
	}
	
	@ApiOperation(value="未登录",notes="未登录描述",httpMethod="POST")
	@RequestMapping(value="getLoginUser",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<User> getLoginUser(HttpSession session) {
		User user = (User) session.getAttribute("current_user");
		if(user!=null) {
			return JSONData.buildSuccess(user);
		}
		return JSONData.buildError("用户未登录");
	}
	
	@ApiOperation(value="提示问题",notes="提示问题描述",httpMethod="POST")
	@RequestMapping(value="getUserQuestion",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> getUserQuestion(String username) {
		return userService.getUserQuestion(username);
	}
	@ApiOperation(value="回答答案",notes="答案描述",httpMethod="POST")
	@RequestMapping(value="checkUserAnswer",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> checkUserAnswer(String username,String answer) {
		return userService.checkUserAnswer(username, answer);
	}
	@ApiOperation(value="重置密码",notes="重置密码描述",httpMethod="POST")
	@RequestMapping(value="resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<String> resetPassword(String username,String newpassword) {
		return userService.resetPassword(username, newpassword);
	}
	
	@ApiOperation(value="通过ID获取用户",notes="通过ID获取用户描述",httpMethod="POST")
	@RequestMapping(value="getUserById",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<User> getUserById(Integer id){
		return userService.getUserById(id);
	}
	@ApiOperation(value="获取所有用户",notes="获取所有用户描述",httpMethod="POST")
	@RequestMapping(value="getAllUser",method=RequestMethod.POST)
	@ResponseBody
	public JSONData<List<User>> getAllUser(){
		return userService.getAllUser();
	}
	
	
	

}
