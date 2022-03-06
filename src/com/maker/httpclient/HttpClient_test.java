package com.maker.httpclient;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.NameValuePair;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;



public class HttpClient_test {
	public static void main(String[] args)throws Exception{
		//doget();
		dopost();
		
	}
	/**
	 * 利用HttpClient进行get请求
	 * */
	private static void doget()throws Exception{
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
	/**
	 * 利用HttpClient进行post请求
	 * */
	private static void dopost()throws Exception{
		String requrl="http://localhost:8080/FirstTest/echo.do";
		CloseableHttpClient client=HttpClients.createDefault();
		HttpPost post=new HttpPost(requrl);
		//如果要发送POST请求，那么一定要进行请求参数的配置，需要配置集合
		List<NameValuePair> allParams =new ArrayList<>();
		allParams.add(new BasicNameValuePair("msg","get out of my way"));
		//既然要通过POST发送请求，那么就需要进行编码的配置
		UrlEncodedFormEntity urlEncoded=new UrlEncodedFormEntity(allParams,Charset.forName("UTF-8"));
		//将请求参数配置到post请求当中
		post.setEntity(urlEncoded);
		//获得响应信息
		CloseableHttpResponse response=client.execute(post);
		System.out.println("【响应的数据】"+EntityUtils.toString(response.getEntity()));
		Header[] headers=response.getHeaders();
		for(Header head : headers){
			System.out.println("【头信息】"+head);
		}
		System.out.println("【状态码】"+response.getCode());
	}
}
