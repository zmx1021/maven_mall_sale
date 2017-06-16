package com.atguigu1228.service;

import java.util.List;
import java.util.Map;

import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_TRADE_MARK;

public interface SearchServiceInf {
	
	public List<T_MALL_TRADE_MARK> get_trade_mark_by_class_2_id(Map<String, Object> paramMap);

	public List<OBJECT_T_MALL_ATTR> get_attr_value_by_class_2_id(int class_2_id);

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id,int pp_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value);
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id(Map<String, Object> paramMap);

	public OBJECT_T_MALL_SKU get_sku_detail_by_sku_spu_id(int sku_id);

	public List<OBJECT_T_MALL_SKU> get_other_sku_by_spu_id(int spu_id);

}
