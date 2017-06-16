package com.atguigu1228.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.p2c.usermanage.service.UserService;
import com.atguigu1228.bean.T_MALL_SHOPPINGCAR;
import com.atguigu1228.bean.T_MALL_USER_ACCOUNT;
import com.atguigu1228.service.ShoppingCartService;
import com.atguigu1228.utils.MyJsonUtil;

import net.sf.json.JSONArray;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping("goto_login1228")
	public String goto_login1228(String err,ModelMap map) {
		
		map.put("err", err);
		return "sale_login";
	}
	
	@RequestMapping("user_logout")
	public String user_logout(HttpSession session){
		session.invalidate();
		return "redirect:/to_index.do";
		
	}
	
	@RequestMapping("to_index")
	public String to_index(){
		return"sale_index";
	}

	@RequestMapping("login1228")
	public ModelAndView login1228(
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_str,
			T_MALL_USER_ACCOUNT user, HttpSession session,
			HttpServletResponse response, ModelMap map) {
		// 本系统，此处调用用户认证的webservice接口
		T_MALL_USER_ACCOUNT user_login = userService.query_user_login(user);

		if (user_login == null) {
			ModelAndView mv = new ModelAndView("redirect:/goto_login1228.do");
			mv.addObject("err", "用户名或者密码错误!");
			return mv;
		} else {
			session.setAttribute("user", user_login);
			String object_to_json = MyJsonUtil
					.object_to_json(user_login.getYh_nch());
			Cookie cookie = new Cookie("atguiguUser", object_to_json);
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);

			// 查询db中的数据
			List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartService
					.get_shopping_cart_by_user_id(user_login.getId());
			session.setAttribute("list_cart_session", list_cart_db);
			if (list_cart_cookie_str == null
					|| ("").equals(list_cart_cookie_str)) {
				// cookie中没有购物车数据

				session.setAttribute("list_cart_session", list_cart_db);
			} else {
				// cookie中有购物车的数据

				JSONArray fromObject = JSONArray
						.fromObject(list_cart_cookie_str);
				// cookie中购物车数据的集合
				List<T_MALL_SHOPPINGCAR> list_cart_cookie = (List<T_MALL_SHOPPINGCAR>) fromObject
						.toCollection(fromObject, T_MALL_SHOPPINGCAR.class);

				if (list_cart_db != null && list_cart_db.size() != 0) {
					// 数据库中有数据
					for (int i = 0; i < list_cart_cookie.size(); i++) {

						for (int j = 0; j < list_cart_db.size(); j++) {

							if (list_cart_db.get(j)
									.getSku_id() == list_cart_cookie.get(i)
											.getSku_id()) {
								// 重复的数据 不操作
								break;
							}
							if (j == list_cart_db.size() - 1) {
								// 新的数据
								list_cart_cookie.get(i)
										.setYh_id(user_login.getId());
								shoppingCartService.insert_cart_to_db(
										list_cart_cookie.get(i));
							}
						}
					}
				} else {
					for (int i = 0; i < list_cart_cookie.size(); i++) {
						list_cart_cookie.get(i).setYh_id(user_login.getId());
						shoppingCartService
								.insert_cart_to_db(list_cart_cookie.get(i));
					}
				}
				List<T_MALL_SHOPPINGCAR> cart_by_user_id = shoppingCartService
						.get_shopping_cart_by_user_id(user_login.getId());
				session.setAttribute("list_cart_session", cart_by_user_id);
			}
			// 如果用户登录 把 存在cookie里的购物车信息置空
			Cookie list_cart_cookie = new Cookie("list_cart_cookie", "");
			cookie.setMaxAge(60 * 60);
			response.addCookie(list_cart_cookie);
			ModelAndView mv = new ModelAndView("redirect:/to_index.do");
			return mv;
		}
	}
	//用户注册
	@ResponseBody
	@RequestMapping("user_regist")
	public Map<String, Object> user_regist(T_MALL_USER_ACCOUNT formUser){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		T_MALL_USER_ACCOUNT t_MALL_USER_ACCOUNT = new T_MALL_USER_ACCOUNT();
		
		t_MALL_USER_ACCOUNT.setYh_mch(formUser.getYh_mch());
		t_MALL_USER_ACCOUNT.setYh_mm(formUser.getYh_mm());
		t_MALL_USER_ACCOUNT.setYh_yx(formUser.getYh_yx());
		t_MALL_USER_ACCOUNT.setYh_shjh(formUser.getYh_shjh());
		
		t_MALL_USER_ACCOUNT.setYh_nch("辣鸡，你好！");
		t_MALL_USER_ACCOUNT.setYh_xm("真实姓名");
	
		//调用webservice接口
		int user_regist = userService.insert_user_regist(t_MALL_USER_ACCOUNT);
		
		if(user_regist==1){
			//注册成功
			resultMap.put("success", true);
			
		}else{
			resultMap.put("success", false);
		}
		return resultMap;
	}
	@ResponseBody
	@RequestMapping("check_user_loginAccount")
	public Map<String, Object> check_user_loginAccount(String loginaccount){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		//调用webservice接口进行用户名查重功能
		T_MALL_USER_ACCOUNT db_user = userService.query_user_by_username(loginaccount);
		
		if(db_user!=null){
			//用户名已经存在
			resultMap.put("success", false);
		}else{
			resultMap.put("success", true);
		}
		
		return resultMap;
		
	}
}
