package com.atguigu1228.service;

import java.util.List;
import java.util.Map;

import com.atguigu1228.bean.OBJECT_T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_ORDER_PAHSE;

public interface OrderService {

	List<Integer> insert_order(List<OBJECT_T_MALL_ORDER> list_order);

	void update_order(List<OBJECT_T_MALL_ORDER> list_order) throws Exception;

	List<OBJECT_T_MALL_ORDER> get_orderpage_by_user_id(Map<String, Object> paramMap);

	List<T_MALL_ORDER_PAHSE> select_jdmsh();

	OBJECT_T_MALL_ORDER check_order_detail(int id);
}
