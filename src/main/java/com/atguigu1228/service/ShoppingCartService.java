package com.atguigu1228.service;

import java.util.List;

import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCartService {

	int insert_cart_to_db(T_MALL_SHOPPINGCAR formcart);

	List<T_MALL_SHOPPINGCAR> get_shopping_cart_by_user_id(int id);

	int update_cart_from_db(T_MALL_SHOPPINGCAR formcart);

	int delete_cart_by_sku_id(int sku_id);

}
