package com.ju.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ju.common.JSONData;
import com.ju.entity.User;
import com.ju.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value="/manager/user",description="后台管理员接口")
@RestController
@RequestMapping("/manager/user/")
public class ManagerUserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="管理员登录",notes="管理员登录接口描述",httpMethod="POST")
	@RequestMapping(value="login",method=RequestMethod.POST)
	public JSONData<User> login(String username,String password,HttpSession session){
		JSONData<User> data = userService.login(username, password);
		if(data.getCode()==0) {//登录成功
			if(data.getData().getRole()==1) {//管理员登录
				session.setAttribute(username, password);
			}else {
				return JSONData.buildError("不是管理员，无法登录");
			}
		}
		return data;
	}
	@ApiOperation(value="所有管理员",notes="所有管理员描述",httpMethod="POST")
	@RequestMapping(value="selectAll",method=RequestMethod.POST)
	public JSONData<List<User>> selectAll(HttpSession session){
		JSONData<List<User>> allUser = userService.getAllUser();
		return allUser;
	}
	@ApiOperation(value="管理员删除用户",notes="管理员删除用户描述",httpMethod="POST")
	@RequestMapping(value="delUserById",method=RequestMethod.POST)
	public JSONData<List<User>> delUserById(Integer id){
		//未做
		return null;
	}
}
