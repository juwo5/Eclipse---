package com.ju.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ju.entity.User;
import com.ju.entity.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);//注册

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    
    
    
    List<User> selectAll();
    
    
    //登录
    User login(@Param("username")String username,@Param("password") String password);
    	//用户名判断
    	int checkUserName(String username);
    //注册int insert(User record);
    	//用户名和邮箱判断
    	int checkEmail(String email);
    	
    String getUserQuestion(String username);
    int checkUserAnswer(@Param("username")String username,@Param("answer")String answer);
    int resetPassword(@Param("username")String username,@Param("newpassword")String newpassword);

    User getUserById(Integer id);
    
}