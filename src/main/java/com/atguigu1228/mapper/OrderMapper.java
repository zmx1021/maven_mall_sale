package com.atguigu1228.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu1228.bean.OBJECT_T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_FLOW;
import com.atguigu1228.bean.T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_ORDER_PAHSE;

public interface OrderMapper {

	void add_order(OBJECT_T_MALL_ORDER object_T_MALL_ORDER);

	void add_order_infos(Map<String, Object> paramMap);

	void add_flow(T_MALL_FLOW t_MALL_FLOW);

	void delete_cart_by_gwchid_form_order_info(List<Integer> list);

	void update_order_status(OBJECT_T_MALL_ORDER object_T_MALL_ORDER);

	void update_order_flow_(T_MALL_FLOW t_mall_flow);

	int select_skukc_by_sku_id(int sku_id);

	void update_sku_kc_xl(Map<String, Object> paramMap);

	List<OBJECT_T_MALL_ORDER> select_orderlist_by_userid_orderstatus(
			Map<String, Object> paramMap);

	List<T_MALL_ORDER_PAHSE> select_jdmsh();

	OBJECT_T_MALL_ORDER select_order_detail_by_id(int id);

}
