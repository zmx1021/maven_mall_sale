package com.atguigu1228.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCartMapper {

	int add_cart_to_db(T_MALL_SHOPPINGCAR formcart);

	List<T_MALL_SHOPPINGCAR> select_shopping_cart_by_user_id(int id);

	int update_cart_from_db(T_MALL_SHOPPINGCAR formcart);

	int delete_cart_by_sku_id(int sku_id);
}
