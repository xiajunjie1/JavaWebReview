package com.maker.httpclient;
import java.net.URL;
import java.util.Scanner;
/**
 * 利用传统的java.net.URL类对服务器Servlet进行请求，该类默认只能进行GET请求
 * 	如果想要向服务器发送POST甚至更多各种各样的请求，那么需要使用者精通HTTP协议
 * 	在实际的开发中，如果想要简化各种请求，可以使用HttpClient进行操作
 * */
public class AccessServlet {
	public static void main(String[] args)throws Exception{
		String requrl="http://localhost:8080/FirstTest/echo.do?msg=Ronaldinho";
		URL url =new URL(requrl);//发送Get请求
		Scanner sc=new Scanner(url.openStream());//获取返回的数据
		sc.useDelimiter("\n");//以换行作为分隔符
		while(sc.hasNext()){
			System.out.println(sc.next());
			//<h2>Ronaldinho</h2>
		}
		sc.close();
	}
}
