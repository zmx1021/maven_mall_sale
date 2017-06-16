package com.atguigu1228.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_TRADE_MARK;

public interface SearchMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(@Param("class_2_id") int class_2_id);
	
	List<OBJECT_T_MALL_SKU> query_sku_page_by_class_2_id(Map<String,Object> paramMap);

	List<T_MALL_TRADE_MARK> query_trade_mark_by_class_2_id(Map<String, Object> paramMap);

	List<OBJECT_T_MALL_SKU> query_sku_page_by_class_2_id_attr_value(Map<String, Object> paramMap);

	OBJECT_T_MALL_SKU select_sku_detail_by_sku_spu_id(Map<String, Object> paramMap);

	List<OBJECT_T_MALL_SKU> select_other_sku_by_spu_id(Map<String, Object> paramMap);

}
