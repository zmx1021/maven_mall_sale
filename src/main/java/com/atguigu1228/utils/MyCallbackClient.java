package com.atguigu1228.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;



public class MyCallbackClient implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks)
			throws IOException, UnsupportedCallbackException {
		WSPasswordCallback callback =(WSPasswordCallback) callbacks[0];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = sdf.format(new Date());
		
		
		callback.setIdentifier("invoker"+"_"+format);
		
		String md5 = MD5Util.md5("p2c_sale"+format);
		callback.setPassword(md5); 
	}

}
