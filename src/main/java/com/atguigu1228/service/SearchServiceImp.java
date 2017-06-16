package com.atguigu1228.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.bean.OBJECT_T_MALL_ATTR;
import com.atguigu1228.bean.OBJECT_T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU;
import com.atguigu1228.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu1228.bean.T_MALL_TRADE_MARK;
import com.atguigu1228.mapper.SearchMapper;
import com.atguigu1228.utils.FlushRedisUtil;
import com.atguigu1228.utils.MyJedisConnectionUtil;
import com.atguigu1228.utils.MyJsonUtil;

import redis.clients.jedis.Jedis;

@Service
public class SearchServiceImp implements SearchServiceInf {

	@Autowired
	SearchMapper searchMapper;

	/***
	 * 根据二级分类id查询分类属性对象集合
	 */
	public List<OBJECT_T_MALL_ATTR> get_attr_value_by_class_2_id(
			int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr = searchMapper
				.select_attr_list_by_class_2_id(class_2_id);
		return list_attr;
	}

	/***
	 * 根据分类属性查询商品sku集合
	 * 
	 * @param pp_id
	 */
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id,
			int pp_id, List<T_MALL_SKU_ATTR_VALUE> list_attr_value) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		List<OBJECT_T_MALL_SKU> list_object_sku = new ArrayList<>();

		// 获取redis连接
		Jedis jedis = MyJedisConnectionUtil.getJedis();

		String[] keys = new String[list_attr_value.size()];

		StringBuffer sbBuffer = new StringBuffer("attr_value_" + class_2_id);
		for (int i = 0; i < list_attr_value.size(); i++) {
			keys[i] = "attr_value_" + class_2_id
					+ list_attr_value.get(i).getShxm_id() + "_"
					+ list_attr_value.get(i).getShxzh_id();
			sbBuffer.append("_" + list_attr_value.get(i).getShxm_id() + "_"
					+ list_attr_value.get(i).getShxzh_id());
		}
		String desKey = sbBuffer.toString();
		Set<String> zrange = jedis.zrange(desKey, 0, -1);

		if (zrange.size() != 0 && zrange != null) {

			check_redis_by_class_2_id(list_object_sku, zrange);

		} else {
			jedis.zinterstore(desKey, keys);
			Set<String> zrange2 = jedis.zrange(desKey, 0, -1);
			if (zrange2.size() != 0 && zrange2 != null) {
				check_redis_by_class_2_id(list_object_sku, zrange2);
			} else {
				StringBuffer sbf = new StringBuffer();

				sbf.append(" and sku.id in ");
				sbf.append(" ( ");
				sbf.append(" select sku_0.sku_id from ");
				for (int i = 0; i < list_attr_value.size(); i++) {
					sbf.append(
							" (Select sku_id from t_mall_sku_attr_value where shxm_id ="
									+ list_attr_value.get(i).getShxm_id()
									+ " and shxzh_id="
									+ list_attr_value.get(i).getShxzh_id()
									+ ") sku_" + i + " ");
					if (i < (list_attr_value.size() - 1)) {
						sbf.append(" , ");
					}
				}

				if (list_attr_value.size() > 1) {
					sbf.append(" where ");
				}

				for (int i = 0; i < list_attr_value.size(); i++) {
					if (i < (list_attr_value.size() - 1)) {
						sbf.append("sku_" + i + ".sku_id=sku_" + (i + 1)
								+ ".sku_id");
					}

					if (i < (list_attr_value.size() - 2)) {
						sbf.append(" and ");
					}
				}
				sbf.append(" ) ");

				paramMap.put("sql", sbf.toString());

				paramMap.put("pp_id", pp_id);

				paramMap.put("class_2_id", class_2_id);

				list_object_sku = searchMapper
						.query_sku_page_by_class_2_id_attr_value(paramMap);
				if (list_object_sku != null && list_object_sku.size() != 0) {
					for (int i = 0; i < list_object_sku.size(); i++) {
						String object_to_json = MyJsonUtil
								.object_to_json(list_object_sku.get(i));
						jedis.zadd(desKey, i, object_to_json);
					}
				}
			}
		}
		return list_object_sku;
	}

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id(
			Map<String, Object> paramMap) {

		List<OBJECT_T_MALL_SKU> list_object_sku = new ArrayList<>();

		int class_2_id = (int) paramMap.get("class_2_id");
		// 根据2级分类编号id 从redis缓存中查询
		// 获取redis连接
		Jedis jedis = MyJedisConnectionUtil.getJedis();

		String key = "class_2_id_" + class_2_id;

		Set<String> zrange = jedis.zrange(key, 0, -1);

		if (zrange.size() != 0 && zrange != null) {
			// 如果缓存中不为空
			check_redis_by_class_2_id(list_object_sku, zrange);
		} else {
			// 如果缓存没有 从mysql中查询

			list_object_sku = searchMapper
					.query_sku_page_by_class_2_id(paramMap);
			// 将查询结果 刷新到redis中
			FlushRedisUtil.flush_Redis_by_class_2_id(list_object_sku,
					class_2_id);
		}

		return list_object_sku;
	}

	public List<T_MALL_TRADE_MARK> get_trade_mark_by_class_2_id(
			Map<String, Object> paramMap) {
		return searchMapper.query_trade_mark_by_class_2_id(paramMap);
	}

	public OBJECT_T_MALL_SKU get_sku_detail_by_sku_spu_id(int sku_id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("sku_id", sku_id);
		return searchMapper.select_sku_detail_by_sku_spu_id(paramMap);
	}

	public List<OBJECT_T_MALL_SKU> get_other_sku_by_spu_id(int spu_id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("spu_id", spu_id);
		return searchMapper.select_other_sku_by_spu_id(paramMap);
	}

	private void check_redis_by_class_2_id(
			List<OBJECT_T_MALL_SKU> list_object_sku, Set<String> zrange) {
		Iterator<String> iterator = zrange.iterator();

		while (iterator.hasNext()) {

			String next = iterator.next();

			OBJECT_T_MALL_SKU string_to_object = MyJsonUtil
					.string_to_object(next, OBJECT_T_MALL_SKU.class);

			list_object_sku.add(string_to_object);
		}
	}

}
