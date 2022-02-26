package com.maker.httpclient;

import org.apache.hc.client5.http.classic.methods.HttpGet;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class HttpClient_test {
	public static void main(String[] args)throws Exception{
		String requrl="http://localhost:8080/FirstTest/echo.do?msg=Ronaldinho";
		CloseableHttpClient client=HttpClients.createDefault();//创建一个可以关闭的HttpClient对象，及该对象发完请求，连接就释放了
		//准备以Get模式向服务器发送http请求
		HttpGet httpget=new HttpGet(requrl);
		//发送http请求
		CloseableHttpResponse response=client.execute(httpget);
		//获取响应数据
		System.out.println("【响应的数据】"+EntityUtils.toString(response.getEntity()));
		Header[] headers=response.getHeaders();
		for(Header head : headers){
			System.out.println("【头信息】"+head);
		}
		System.out.println("【状态码】"+response.getCode());
		
	}
}
