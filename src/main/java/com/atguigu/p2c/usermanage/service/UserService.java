package com.atguigu.p2c.usermanage.service;

import javax.jws.WebService;

import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface UserService {
	
		T_MALL_USER_ACCOUNT query_user_login(T_MALL_USER_ACCOUNT t_MALL_USER_ACCOUNT);
		
		int insert_user_regist(T_MALL_USER_ACCOUNT t_MALL_USER_ACCOUNT);
		
		T_MALL_USER_ACCOUNT query_user_by_username(String loginAccount);
}
