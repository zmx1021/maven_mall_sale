package com.atguigu1228.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;
import com.atguigu1228.mapper.ShoppingCartMapper;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;

	@Override
	public List<T_MALL_SHOPPINGCAR> get_shopping_cart_by_user_id(int id) {
		return shoppingCartMapper.select_shopping_cart_by_user_id(id);
	}

	@Override
	public int insert_cart_to_db(T_MALL_SHOPPINGCAR formcart) {
		return shoppingCartMapper.add_cart_to_db(formcart);
	}

	@Override
	public int update_cart_from_db(T_MALL_SHOPPINGCAR formcart) {
		
		return shoppingCartMapper.update_cart_from_db(formcart);
	}

	@Override
	public int delete_cart_by_sku_id(int sku_id) {
		return shoppingCartMapper.delete_cart_by_sku_id(sku_id);
	}

}
