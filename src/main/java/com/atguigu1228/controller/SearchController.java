package com.atguigu1228.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Attr;

import com.atguigu1228.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_TRADE_MARK;
import com.atguigu1228.service.SearchServiceImp;
import com.atguigu1228.service.SearchServiceInf;
import com.atguigu1228.utils.FlushRedisUtil;
import com.atguigu1228.utils.MyJedisConnectionUtil;
import com.atguigu1228.utils.MyJsonUtil;
import com.mysql.fabric.xmlrpc.base.Array;

import redis.clients.jedis.Jedis;

@Controller
public class SearchController {

	@Autowired
	SearchServiceInf searchservice;

	/***
	 * 二级分类检索sku结果
	 * 
	 * @param class_2_id
	 * @return
	 */
	@RequestMapping("get_sku_by_class_2/{class_2_id}/{class_2_name}")
	public String get_sku_by_class_2(ModelMap map, @PathVariable int class_2_id,
			@PathVariable String class_2_name) {

		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("class_2_id", class_2_id);

		paramMap.put("pp_id", 0);
		// 根据2级分类编号获取 品牌
		List<T_MALL_TRADE_MARK> list_trade_mark = searchservice
				.get_trade_mark_by_class_2_id(paramMap);
		// 根据2级分类编号 获取 分类属性 属性名 名称
		List<OBJECT_T_MALL_ATTR> list_attr = searchservice
				.get_attr_value_by_class_2_id(class_2_id);
		// 根据2级分类编号获取 sku分页数据
		List<OBJECT_T_MALL_SKU> list_object_sku = searchservice
				.get_sku_by_class_2_id(paramMap);

		map.put("list_attr", list_attr);

		map.put("list_object_sku", list_object_sku);

		map.put("list_trade_mark", list_trade_mark);

		return "sale_search";
	}

	/***
	 * 分类属性/属性值条件检索
	 * 
	 * @param class_2_id
	 * @param list_attr_value
	 * @return
	 */
	@RequestMapping(value = "get_sku_by_class_2_attr_value", method = RequestMethod.POST)
	public String get_sku_by_class_2_attr_value(ModelMap map, int class_2_id,
			int pp_id,
			MODEL_T_MALL_SKU_ATTR_VALUE model_t_mall_sku_attr_value) {// ["1,2","3,4"]
		List<OBJECT_T_MALL_SKU> list_object_sku = new ArrayList<>(20);
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("class_2_id", class_2_id);
		paramMap.put("pp_id", pp_id);

		if (model_t_mall_sku_attr_value.getList_attr_value() == null
				|| model_t_mall_sku_attr_value.getList_attr_value()
						.size() == 0) {
			list_object_sku = searchservice.get_sku_by_class_2_id(paramMap);
		} else {

			list_object_sku = searchservice.get_sku_by_class_2_attr_value(
					class_2_id, pp_id,
					model_t_mall_sku_attr_value.getList_attr_value());

		}
		map.put("list_object_sku", list_object_sku);
		return "sale_search_inner";

	}

	@RequestMapping("get_sku_detail")
	public String get_sku_detail(int sku_id, int spu_id, ModelMap map) {

		OBJECT_T_MALL_SKU sku_detail = searchservice
				.get_sku_detail_by_sku_spu_id(sku_id);

		List<OBJECT_T_MALL_SKU> list_object_sku_other = searchservice
				.get_other_sku_by_spu_id(spu_id);

		map.addAttribute("object_sku", sku_detail);

		map.addAttribute("list_object_sku_other", list_object_sku_other);

		return "sale_sku_detail";
	}

}
