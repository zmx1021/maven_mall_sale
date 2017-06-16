package com.atguigu1228.utils;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;
import com.atguigu1228.utils.MyCallbackClient;

public class MyWebServiceClientFactorybean<T> implements FactoryBean<T> {
	
	private String url;
	private Class<T> t;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	@Override
	public T getObject() throws Exception {
		
		JaxWsProxyFactoryBean jpFactoryBean = new JaxWsProxyFactoryBean();
		
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		hashMap.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		hashMap.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallbackClient.class.getName());
		hashMap.put(WSHandlerConstants.USER, "username");
		
		WSS4JOutInterceptor oit = new WSS4JOutInterceptor(hashMap);
		
		jpFactoryBean.getOutInterceptors().add(oit);
		
		jpFactoryBean.setAddress(this.url);
		T createBean = jpFactoryBean.create(this.t);
		return createBean;
	}

	@Override
	public Class<?> getObjectType() {
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		
		return false;
	}

}
