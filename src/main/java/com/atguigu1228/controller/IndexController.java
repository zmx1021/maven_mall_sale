package com.atguigu1228.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu1228.service.IndexServiceInf;

@Controller
public class IndexController {
	@Autowired
	private IndexServiceInf indexServiceInf;

	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap map) {
		return "sale_index";
	}

}
