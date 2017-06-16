package com.atguigu1228.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.p2c.usermanage.service.TestServiceInf;

public class TestWsClient {
	
	
	  public static void main(String[] args) {
		
		  JaxWsProxyFactoryBean  jpFactoryBean = new JaxWsProxyFactoryBean();
		  
		  jpFactoryBean.setAddress("http://www.jdong.com:8282/maven_mall_usermanage/testServiceInf");
		  TestServiceInf create = jpFactoryBean.create(TestServiceInf.class);
		  String ping = create.ping();
		  
		  System.out.println(ping);
		  
		  
	}
	

}
