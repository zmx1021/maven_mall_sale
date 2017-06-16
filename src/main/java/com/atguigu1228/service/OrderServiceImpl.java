package com.atguigu1228.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.bean.OBJECT_T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_FLOW;
import com.atguigu1228.bean.T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_ORDER_INFO;
import com.atguigu1228.bean.T_MALL_ORDER_PAHSE;
import com.atguigu1228.exception.OverSaleException;
import com.atguigu1228.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;

	@Override
	public List<Integer> insert_order(List<OBJECT_T_MALL_ORDER> list_order) {

		List<Integer> list_cart_id = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		for (OBJECT_T_MALL_ORDER object_T_MALL_ORDER : list_order) {
			orderMapper.add_order(object_T_MALL_ORDER);// 插入order信息 并主键返回

			List<T_MALL_ORDER_INFO> list_order_info = object_T_MALL_ORDER
					.getList_order_info();

			for (T_MALL_ORDER_INFO t_MALL_ORDER_INFO : list_order_info) {
				t_MALL_ORDER_INFO.setDd_id(object_T_MALL_ORDER.getId());
				// 生成需要删除session中存在订单中对应的购物车的id 集合
				list_cart_id.add(t_MALL_ORDER_INFO.getGwch_id());
			}
			paramMap.put("list_order_info",
					object_T_MALL_ORDER.getList_order_info());
			orderMapper.add_order_infos(paramMap);

			// 生成flow
			T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
			t_MALL_FLOW.setYh_id(object_T_MALL_ORDER.getYh_id());
			// t_MALL_FLOW.setMqdd(object_T_MALL_ORDER.getDzh_mch());
			t_MALL_FLOW.setMdd(object_T_MALL_ORDER.getDzh_mch());
			t_MALL_FLOW.setDd_id(object_T_MALL_ORDER.getId());
			orderMapper.add_flow(t_MALL_FLOW);
		}
		orderMapper.delete_cart_by_gwchid_form_order_info(list_cart_id);

		return list_cart_id;

	}

	@Override
	public void update_order(List<OBJECT_T_MALL_ORDER> list_order) throws Exception {

		for (OBJECT_T_MALL_ORDER object_T_MALL_ORDER : list_order) {
			object_T_MALL_ORDER.setJdh(2);
			object_T_MALL_ORDER.setYjsdshj(getYJSDSJ(3));

			// 1 修改订单状态，把订单由提交状态，修改成支付状态
			orderMapper.update_order_status(object_T_MALL_ORDER);

			// 2 修改物流状态，生成物流信息
			T_MALL_FLOW t_mall_flow = new T_MALL_FLOW();

			// psfsh
			// psshj
			// psmsh
			// mqdd
			// mdd
			// ywy
			// lxfsh

			t_mall_flow.setPsshj(getYJSDSJ(1));
			t_mall_flow.setYwy("小丽");
			t_mall_flow.setDd_id(object_T_MALL_ORDER.getId());
			t_mall_flow.setLxfsh("123456");
			t_mall_flow.setPsmsh("别急！");
			t_mall_flow.setMqdd("正在出库");

			orderMapper.update_order_flow_(t_mall_flow);

			// 3 根据订单信息修改库存状态
			// 商品库存量
			// 商品销量

			// 修改之前需要，查询库存量是否大于购买量
			boolean flag = true;

			for (T_MALL_ORDER_INFO t_mall_order_info : object_T_MALL_ORDER
					.getList_order_info()) {

				int cnt = orderMapper
						.select_skukc_by_sku_id(t_mall_order_info.getSku_id());

				if (cnt < t_mall_order_info.getSku_shl()) {
					flag = false;

					break;
				}

			}

			if (flag) {

				Map<String, Object> paramMap = new HashMap<>();

				for (T_MALL_ORDER_INFO t_mall_order_info : object_T_MALL_ORDER
						.getList_order_info()) {

					paramMap.put("sku_id", t_mall_order_info.getSku_id());
					paramMap.put("sku_gmshl", t_mall_order_info.getSku_shl());
					orderMapper.update_sku_kc_xl(paramMap);
				}
			} else {

				throw new OverSaleException("购物数量大于库存！");
			}

		}

	}

	public Date getYJSDSJ(int key) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, key);
		return calendar.getTime();
	}

	@Override
	public List<OBJECT_T_MALL_ORDER> get_orderpage_by_user_id(Map<String, Object> paramMap) {
		List<OBJECT_T_MALL_ORDER> user_order_list = orderMapper.select_orderlist_by_userid_orderstatus(paramMap);
		return user_order_list;
	}

	@Override
	public List<T_MALL_ORDER_PAHSE> select_jdmsh() {
		
		List<T_MALL_ORDER_PAHSE> list_jdmsh = orderMapper.select_jdmsh();
		return list_jdmsh;
	}

	@Override
	public OBJECT_T_MALL_ORDER check_order_detail(int id) {
		
		OBJECT_T_MALL_ORDER object_T_MALL_ORDER = orderMapper.select_order_detail_by_id(id);
		return object_T_MALL_ORDER;
	}

}
