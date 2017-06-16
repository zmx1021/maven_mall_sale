package com.atguigu.p2c.usermanage.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu1228.bean.T_MALL_ADDRESS;
import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressService {
	
	List<T_MALL_ADDRESS> query_address_by_user_id(T_MALL_USER_ACCOUNT user);
}
