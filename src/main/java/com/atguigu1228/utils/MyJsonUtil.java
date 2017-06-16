package com.atguigu1228.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import net.sf.json.JSONArray;

public class MyJsonUtil {
	private static Gson gson = new Gson();
	
	
	public static void main(String[] args) {
		
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			list_cart.add(new T_MALL_SHOPPINGCAR());
		}
		String object_to_json = object_to_json(list_cart);
		
		System.out.println(object_to_json);
		
	}

	public static <T> String object_to_json(T t) {
		
		String json = "";
		if (!t.getClass().getSimpleName().equals("String")) {
			json = new String(gson.toJson(t));
		} else {
			json = (String) t;
		}
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return json;
	}
	public static <T> Connection String_to_connection(String str,Class<T> t){
		
		JSONArray fromObject = JSONArray.fromObject(str);
		
		Collection collection = fromObject.toCollection(fromObject, t);
		
		return (Connection) collection;
	}
	

	public static <T> String object_to_json_str(T t) {
		String encode = "";
		if (t != null) {
			String json = gson.toJson(t);
			try {
				encode = URLEncoder.encode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return encode;
	}

	public static <T> T string_to_object(String str, Class<T> t) {

		T fromJson = null;

		if (str != null && !"".equals(str)) {
			String decode;
			try {
				decode = URLDecoder.decode(str, "utf-8");

				fromJson = gson.fromJson(decode, t);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return fromJson;
	}

	public static <T> List<T> list_str_to_collection(String str, Class<T> t) {

		List<T> list = null;

		if (str != null && !"".equals(str)) {
			
			 try {
				str = URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			JSONArray fromObject = JSONArray.fromObject(str);

			list = (List<T>) fromObject.toCollection(fromObject, t);
		}

		return list;

	}

	public static <T> String list_to_list_str(List<T> l) {
		String str = "";
		if (l.size() != 0 && l != null) {
			JSONArray fromObject = JSONArray.fromObject(l);

			str = fromObject.toString();
			
			 try {
				str = URLEncoder.encode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

}
