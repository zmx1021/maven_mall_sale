package com.atguigu1228.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.p2c.usermanage.service.UserService;
import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;

@Controller
public class RegistController {
	
	@Autowired
	UserService UserService;
	
	@RequestMapping("goto_regist")
	public String goto_regist(){
		
		return"sale_user_regist";
	}
	@ResponseBody
	@RequestMapping("do_regist")
	public Map<String, Object> do_regist(T_MALL_USER_ACCOUNT formUser){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int user_regist = UserService.insert_user_regist(formUser);
		
		if(user_regist==1){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		return resultMap;
	}
	@ResponseBody
	@RequestMapping("check_username")
	public Map<String, Object> check_username(T_MALL_USER_ACCOUNT formUser){
		
		return null;
	}

}
