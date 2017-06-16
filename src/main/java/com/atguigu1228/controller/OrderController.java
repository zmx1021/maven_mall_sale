package com.atguigu1228.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.p2c.usermanage.service.AddressService;
import com.atguigu1228.bean.OBJECT_T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_ADDRESS;
import com.atguigu1228.bean.T_MALL_ORDER;
import com.atguigu1228.bean.T_MALL_ORDER_INFO;
import com.atguigu1228.bean.T_MALL_ORDER_PAHSE;
import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;
import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;
import com.atguigu1228.exception.OverSaleException;
import com.atguigu1228.service.OrderService;

@Controller
@SessionAttributes({ "list_order", "list_cart_session", "user" })
public class OrderController {
	@Autowired
	AddressService addressService;

	@Autowired
	OrderService orderService;
	
	
	@RequestMapping("check_order_detail")
	public String to_order_detail(int id,ModelMap map){
		
		OBJECT_T_MALL_ORDER order_detail = orderService.check_order_detail(id);
		map.put("order_detail", order_detail);
		
		return"sale_order_detail";
	}
	@RequestMapping("to_order_list")
	public String to_order_list(HttpSession session,
			@ModelAttribute("user") T_MALL_USER_ACCOUNT loginUser) {
		// T_MALL_USER_ACCOUNT loginUser = (T_MALL_USER_ACCOUNT)
		// session.getAttribute("user");

		if (loginUser != null) {
			return "sale_order_list";
		} else {
			return "redirect:/goto_login1228.do";
		}

	}

	@RequestMapping("get_myorder_page")
	public String get_myorder_page(
			@ModelAttribute("user") T_MALL_USER_ACCOUNT loginUser,ModelMap map,int jdh) {

		if (loginUser == null) {
			return "redirect:/goto_login1228.do";
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("yh_id", loginUser.getId());
			paramMap.put("jdh", jdh);
			List<OBJECT_T_MALL_ORDER> list_order_by_userid = orderService.get_orderpage_by_user_id(paramMap);
			map.put("list_order", list_order_by_userid);
			return "my_order_list_inner";
		}

	}
	@RequestMapping("get_letf_option")
	@ResponseBody
	public Object get_letf_option(ModelMap map){
		
		List<T_MALL_ORDER_PAHSE> list_jdmsh = orderService.select_jdmsh();
		
		return list_jdmsh;
	}

	@RequestMapping("to_review_order")
	public String to_review_order(HttpSession session, ModelMap map) {

		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session
				.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_cart_session = (List<T_MALL_SHOPPINGCAR>) session
				.getAttribute("list_cart_session");

		List<T_MALL_ADDRESS> addresses = addressService
				.query_address_by_user_id(user);

		Set<String> list_cart_kcdz = new HashSet<>();

		for (T_MALL_SHOPPINGCAR cart : list_cart_session) {
			list_cart_kcdz.add(cart.getKcdz());
		}
		Iterator<String> iterator = list_cart_kcdz.iterator();

		List<OBJECT_T_MALL_ORDER> list_order = new ArrayList<>();

		while (iterator.hasNext()) {
			String KCDZ = iterator.next();

			OBJECT_T_MALL_ORDER t_MALL_ORDER = new OBJECT_T_MALL_ORDER();
			BigDecimal zje = new BigDecimal("0.0");
			t_MALL_ORDER.setJdh(1);
			t_MALL_ORDER.setYh_id(user.getId());
			List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<>();

			for (T_MALL_SHOPPINGCAR cart : list_cart_session) {

				if (KCDZ.equals(cart.getKcdz())) {
					T_MALL_ORDER_INFO order_info = new T_MALL_ORDER_INFO();
					zje = zje.add(cart.getHj());
					order_info.setSku_id(cart.getSku_id());
					order_info.setGwch_id(cart.getId());
					order_info.setShp_tp(cart.getShp_tp());
					order_info.setSku_jg(cart.getSku_jg());
					order_info.setSku_mch(cart.getSku_mch());
					order_info.setSku_shl(cart.getTjshl());
					order_info.setSku_kcdz(cart.getKcdz());

					list_order_info.add(order_info);

				}

			}
			t_MALL_ORDER.setList_order_info(list_order_info);
			t_MALL_ORDER.setZje(zje);

			list_order.add(t_MALL_ORDER);
		}

		map.put("list_order", list_order);
		map.put("list_user_address", addresses);

		return "sale_review_order";
	}

	@RequestMapping("submit_order")
	public ModelAndView submit_order(
			@ModelAttribute("list_order") List<OBJECT_T_MALL_ORDER> list_order,
			T_MALL_ADDRESS address,
			@ModelAttribute("list_cart_session") List<T_MALL_SHOPPINGCAR> list_cart_sessoion) {

		for (OBJECT_T_MALL_ORDER object_t_mall_order : list_order) {
			object_t_mall_order.setDzh_id(address.getId());
			object_t_mall_order.setDzh_mch(address.getYh_dz());
			object_t_mall_order.setShhr(address.getShjr());
		}

		List<Integer> list_cart_id = orderService.insert_order(list_order);

		// 清理session中加入订单的购物车
		Iterator<T_MALL_SHOPPINGCAR> iterator = list_cart_sessoion.iterator();

		while (iterator.hasNext()) {
			if (list_cart_id.contains(iterator.next().getId())) {
				iterator.remove();
			}
		}

		ModelAndView mv = new ModelAndView("redirect:/to_payfor.do");

		return mv;
	}

	@RequestMapping("to_payfor")
	public String to_payfor(
			@ModelAttribute("list_order") List<OBJECT_T_MALL_ORDER> list_order,
			ModelMap map) {

		BigDecimal sum = new BigDecimal("0.0");

		for (OBJECT_T_MALL_ORDER object_T_MALL_ORDER : list_order) {
			sum = sum.add(object_T_MALL_ORDER.getZje());
		}

		map.put("sum", sum);
		map.put("list_order", list_order);

		return "sale_payfor";
	}

	@RequestMapping("submit_pay_for")
	public String submit_pay_for(
			@ModelAttribute("list_order") List<OBJECT_T_MALL_ORDER> list_order,
			ModelMap map) {

		// 调用第三方支付 如果订单支付成功

		// 调用orderservice接口对订单进行操作
		try {
			orderService.update_order(list_order);

		} catch (Exception e) {
			if (e.getMessage().equals("购物数量大于库存！")) {
				return "over_sale_exception";
			} else {
				e.printStackTrace();
			}
		}
		return "redirect:/sale_payfor_success.do";
	}

	@RequestMapping("sale_payfor_success")
	public String sale_payfor_success(
			@ModelAttribute("list_order") List<OBJECT_T_MALL_ORDER> list_order,
			ModelMap map) {

		map.put("list_order", list_order);

		list_order = null;
		return "sale_payfor_success";
	}

}
