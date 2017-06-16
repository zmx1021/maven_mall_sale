package com.atguigu1228.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;
import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;
import com.atguigu1228.service.ShoppingCartService;
import com.atguigu1228.utils.MyJsonUtil;

import net.sf.json.JSONArray;

@Controller
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping("add_cart")
	public ModelAndView add_cart(T_MALL_SHOPPINGCAR formcart,
			HttpServletRequest req, HttpServletResponse resp,
			RedirectAttributes redirectAttributes) {
		T_MALL_USER_ACCOUNT sessionUser = (T_MALL_USER_ACCOUNT) req.getSession()
				.getAttribute("user");

		formcart.setHj(formcart.getSku_jg()
				.multiply(new BigDecimal(formcart.getTjshl() + "")));

		if (sessionUser == null) {
			List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
			// 用户未登录
			Cookie[] cookies = req.getCookies();
			String cart_cookie = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("list_cart_cookie")) {
					cart_cookie = cookie.getValue();
				}
			}
			if (cart_cookie != "") {
				// cookie中有购物车数据
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				JSONArray fromObject = JSONArray.fromObject(cart_cookie);

				list_cart = (List<T_MALL_SHOPPINGCAR>) fromObject
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);
				List<T_MALL_SHOPPINGCAR> list_cart_cookie = (List<T_MALL_SHOPPINGCAR>) fromObject
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);
				cookie_list_cart_compare_formcart(formcart, resp, list_cart,
						list_cart_cookie);
			} else {
				// cookie中没有购物车数据
				list_cart.add(formcart);
				creat_list_cart_cookie(resp, list_cart);
			}
		} else {
			// 用户已登录
			// 此处 声明两个list_cart 一个用来遍历 一个用来操作
			List<T_MALL_SHOPPINGCAR> list_cart = (List<T_MALL_SHOPPINGCAR>) req
					.getSession().getAttribute("list_cart_session");

			formcart.setYh_id(sessionUser.getId());

			if (list_cart.size() == 0 || list_cart == null) {
				// 数据库中没有购物车数据
				int count = shoppingCartService.insert_cart_to_db(formcart);
				// 同步session
				list_cart.add(formcart);

			} else {
				// 数据库中有购物车的数据
				formcart = db_list_cart_compare_formcart(formcart, list_cart);
				// 同步session
				List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartService
						.get_shopping_cart_by_user_id(sessionUser.getId());
				req.getSession().setAttribute("list_cart_session",
						list_cart_db);
			}
		}
		ModelAndView mv = new ModelAndView(
				"redirect:/add_shopping_cart_success.do");
		// mv.addObject(formcart);
		// redirectAttributes.addAttribute("formcart", formcart);
		mv.addObject("sku_mch", formcart.getSku_mch());
		mv.addObject("sku_jg", formcart.getSku_jg());
		mv.addObject("shp_tp", formcart.getShp_tp());
		mv.addObject("tjshl", formcart.getTjshl());
		mv.addObject("shp_id", formcart.getShp_id());
		mv.addObject("sku_id", formcart.getSku_id());
		return mv;
	}

	protected T_MALL_SHOPPINGCAR db_list_cart_compare_formcart(
			T_MALL_SHOPPINGCAR formcart, List<T_MALL_SHOPPINGCAR> list_cart) {
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getSku_id() == formcart.getSku_id()) {
				// 重复的数据

				list_cart.get(i).setTjshl(
						list_cart.get(i).getTjshl() + formcart.getTjshl());

				list_cart.get(i).setHj(list_cart.get(i).getHj()
						.add(formcart.getSku_jg().multiply(
								new BigDecimal(formcart.getTjshl() + ""))));

				formcart = list_cart.get(i);

				int count = shoppingCartService.update_cart_from_db(formcart);
				break;
			}
			if (i == list_cart.size() - 1) {
				// 新的数据
				int count = shoppingCartService.insert_cart_to_db(formcart);
				// 同步session
			}
		}
		return formcart;
	}

	protected void cookie_list_cart_compare_formcart(
			T_MALL_SHOPPINGCAR formcart, HttpServletResponse resp,
			List<T_MALL_SHOPPINGCAR> list_cart,
			List<T_MALL_SHOPPINGCAR> list_cart_cookie) {
		for (int i = 0; i < list_cart.size(); i++) {

			if (list_cart.get(i).getSku_id() == formcart.getSku_id()) {
				// 老数据
				list_cart_cookie.get(i)
						.setTjshl(list_cart_cookie.get(i).getTjshl()
								+ formcart.getTjshl());
				list_cart_cookie.get(i).setHj(list_cart_cookie.get(i).getHj()
						.add(formcart.getSku_jg().multiply(
								new BigDecimal(formcart.getTjshl() + ""))));
				creat_list_cart_cookie(resp, list_cart_cookie);
				break;
			}
			if (i == list_cart.size() - 1) {
				// 新数据
				list_cart_cookie.add(formcart);
				creat_list_cart_cookie(resp, list_cart_cookie);
			}
		}
	}

	protected void creat_list_cart_cookie(HttpServletResponse resp,
			List<T_MALL_SHOPPINGCAR> list_cart_cookie) {
		if(list_cart_cookie==null||list_cart_cookie.size()==0){
			Cookie cookie = new Cookie("list_cart_cookie", "");
			cookie.setMaxAge(60 * 60 * 24 * 30);
			resp.addCookie(cookie);
		}else{
			String json = MyJsonUtil.object_to_json(list_cart_cookie);
			Cookie cookie = new Cookie("list_cart_cookie", json);
			cookie.setMaxAge(60 * 60 * 24 * 30);
			resp.addCookie(cookie);
		}
	}

	// @RequestParam(value="formcart",required=false)
	@RequestMapping("add_shopping_cart_success")
	public String add_shopping_cart_success(T_MALL_SHOPPINGCAR formcart,
			HttpSession session, ModelMap map) {

		if (session.getAttribute("user") != null) {

			T_MALL_USER_ACCOUNT loginUser = (T_MALL_USER_ACCOUNT) session
					.getAttribute("user");

			List<T_MALL_SHOPPINGCAR> shopping_cart_by_user_id = shoppingCartService
					.get_shopping_cart_by_user_id(loginUser.getId());
			map.put("shoppingcar_list", shopping_cart_by_user_id);

		}
		map.put("formcart", formcart);
		return "add_shopping_cart_success";
	}

	@RequestMapping("goto_shopping_cart")
	public String goto_shopping_cart() {
		return "sale_shopping_cart";
	}

	@RequestMapping("get_cart_page")
	public String get_cart_page(
			@CookieValue("list_cart_cookie") String list_cart_cookie_str,
			HttpSession session, ModelMap map) {

		T_MALL_USER_ACCOUNT loginUser = (T_MALL_USER_ACCOUNT) session
				.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();

		BigDecimal sum_price = new BigDecimal("0.0");
		int count = 0;

		if (loginUser != null  ) {
			// 用户已经登录 先从session中取出 购物车的信息
			list_cart = (List<T_MALL_SHOPPINGCAR>) session
					.getAttribute("list_cart_session");
			// 如果session中没有再去数据库查询
			if (list_cart == null || list_cart.size() == 0) {
				list_cart = shoppingCartService
						.get_shopping_cart_by_user_id(loginUser.getId());
				if (list_cart != null || list_cart.size() != 0) {
					session.setAttribute("list_cart_session", list_cart);
				}
			}
		} else {
			if (list_cart_cookie_str != "" && list_cart_cookie_str != null) {
				// 用户未登录 从cookie中取出购物车的数据转成list集合
				JSONArray fromObject = JSONArray
						.fromObject(list_cart_cookie_str);
				list_cart = (List<T_MALL_SHOPPINGCAR>) fromObject
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);
			}
		}
		// 累加 购物车价格合计 和数量
		for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart) {
			sum_price = sum_price
					.add(new BigDecimal(t_MALL_SHOPPINGCAR.getHj() + ""));
			count += t_MALL_SHOPPINGCAR.getTjshl();
		}

		map.put("cart_hjjg", sum_price);
		map.put("count", count);
		map.put("list_shopping_cart", list_cart);
		return "sale_shoping_cart_inner";
	}

	@RequestMapping("get_mini_cart")
	public String get_mini_cart(HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_str,
			ModelMap map) {

		T_MALL_USER_ACCOUNT loginUser = (T_MALL_USER_ACCOUNT) session
				.getAttribute("user");

		BigDecimal sum_price = new BigDecimal("0.0");
		int count = 0;
		if (loginUser != null) {
			// 用户登录 迷你购物车数据从session中取
			List<T_MALL_SHOPPINGCAR> list_cart_session = (List<T_MALL_SHOPPINGCAR>) session
					.getAttribute("list_cart_session");
			if (list_cart_session == null || list_cart_session.size() == 0) {
				List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartService
						.get_shopping_cart_by_user_id(loginUser.getId());
				if (list_cart_db != null && list_cart_db.size() != 0) {
					session.setAttribute("list_cart_session", list_cart_db);
				}
			}
			for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart_session) {
				sum_price = sum_price
						.add(new BigDecimal(t_MALL_SHOPPINGCAR.getHj() + ""));
				count += t_MALL_SHOPPINGCAR.getTjshl();
			}
			map.put("sum_price", sum_price);
			map.put("count", count);
			map.put("list_cart", list_cart_session);
		} else {
			// 用户没有登录 迷你购物车数据从cookie中取
			if (list_cart_cookie_str != null
					&& !"".equals(list_cart_cookie_str)) {
				JSONArray fromObject = JSONArray
						.fromObject(list_cart_cookie_str);
				List<T_MALL_SHOPPINGCAR> list_cart_cookie = (List<T_MALL_SHOPPINGCAR>) JSONArray
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);
				for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart_cookie) {
					sum_price = sum_price.add(
							new BigDecimal(t_MALL_SHOPPINGCAR.getHj() + ""));
					count += t_MALL_SHOPPINGCAR.getTjshl();
				}

				map.put("list_cart", list_cart_cookie);
			}
			map.put("sum_price", sum_price);
			map.put("count", count);
		}
		return "sale_mini_cart_inner";
	}

	@RequestMapping("add_cart_tjshl")
	public String add_cart_tjshl(
			@CookieValue("list_cart_cookie") String list_cart_cookie_str,
			HttpSession session, ModelMap map, int sku_id, int tjshl,
			HttpServletResponse resp) {

		T_MALL_USER_ACCOUNT sessionUser = (T_MALL_USER_ACCOUNT) session
				.getAttribute("user");

		int count = 0;
		BigDecimal sum_price = new BigDecimal("0.0");

		if (sessionUser != null) {
			// 用户登录
			List<T_MALL_SHOPPINGCAR> list_cart_session = (List<T_MALL_SHOPPINGCAR>) session
					.getAttribute("list_cart_session");

			if (list_cart_session.size() != 0 && list_cart_session != null) {

				if (tjshl == 0) {
					Iterator<T_MALL_SHOPPINGCAR> iterator = list_cart_session
							.iterator();

					while (iterator.hasNext()) {
						if (iterator.next().getSku_id() == sku_id) {
							iterator.remove();
						}
					}
					int cnt = shoppingCartService.delete_cart_by_sku_id(sku_id);

					for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart_session) {
						sum_price = sum_price.add(t_MALL_SHOPPINGCAR.getHj());
						count += t_MALL_SHOPPINGCAR.getTjshl();
					}
				} else {
					for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart_session) {
						if (t_MALL_SHOPPINGCAR.getSku_id() == sku_id) {
							t_MALL_SHOPPINGCAR.setTjshl(tjshl);
							t_MALL_SHOPPINGCAR.setHj(t_MALL_SHOPPINGCAR
									.getSku_jg()
									.multiply(new BigDecimal(
											t_MALL_SHOPPINGCAR.getTjshl()
													+ "")));
							int cnt = shoppingCartService
									.update_cart_from_db(t_MALL_SHOPPINGCAR);

							break;
						}
					}
					for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart_session) {
						sum_price = sum_price.add(t_MALL_SHOPPINGCAR.getHj());
						count += t_MALL_SHOPPINGCAR.getTjshl();
					}

				}

			}

			map.put("list_shopping_cart", list_cart_session);

		} else {
			// 用户未登录
			if (list_cart_cookie_str != null
					&& list_cart_cookie_str.length() != 0) {
				List<T_MALL_SHOPPINGCAR> list_cart_cookie = new ArrayList<>();
				JSONArray fromObject = JSONArray
						.fromObject(list_cart_cookie_str);
				list_cart_cookie = (List<T_MALL_SHOPPINGCAR>) fromObject
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);

				if (tjshl == 0) {
					Iterator<T_MALL_SHOPPINGCAR> iterator = list_cart_cookie
							.iterator();

					while (iterator.hasNext()) {
						if (iterator.next().getSku_id() == sku_id) {
							iterator.remove();
						}
					}
				} else {

					for (T_MALL_SHOPPINGCAR cart : list_cart_cookie) {
						if (cart.getSku_id() == sku_id) {
							cart.setTjshl(tjshl);
							cart.setHj(cart.getSku_jg().multiply(
									new BigDecimal(cart.getTjshl() + "")));
							break;
						}
					}
					for (T_MALL_SHOPPINGCAR cart : list_cart_cookie) {
						sum_price = sum_price.add(cart.getHj());
						count += cart.getTjshl();
					}

				}

				creat_list_cart_cookie(resp, list_cart_cookie);
				map.put("list_shopping_cart", list_cart_cookie);
			}

		}

		map.put("cart_hjjg", sum_price);
		map.put("count", count);

		return "sale_shoping_cart_inner";
	}
}
