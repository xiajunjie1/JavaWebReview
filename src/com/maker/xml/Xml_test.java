package com.maker.xml;
import java.io.File;

import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



/**
 * Java操作xml
 * 	DOM是一个实现w3c定义的标准，而java实现了这个dom标准，那么就意味着Java可以直接实现XML
 * 	文件的解析及生成的操作
 * 	如果想要在Java中实现xml的处理操作，那么肯定是要使用的是“javax.xml”模块所提供的处理操作
 * 		org.w3c.dom.Node接口
 * 			描述了XML文档中的基本组成单元
 * 		
 * 		org.w3c.dom.document子接口
 * 			所有在xml文件之中出现的元素、属性、文本内容都需要通过Document接口实例来创建
 * 		
 * 		org.w3c.dom.Element子接口
 * 			通过document接口创建的，描述元素的信息
 * 
 * 	在程序开发的过程当中，如果想要生成或者是获取xml数据那么就是最为重要的工作了
 * 		所有xml数据实际上都是字符串文本数据内容，那么对于文本的数据内容，简单的方式就是直接进行字符串的截取操作
 * 		这样的机制可以做，但是存在比较大的问题就是无法方便的修改，为了解决xml的数据操作问题，由w3c定义了一个
 * 		核心的DOM处理标准
 * 	
 * 	DOM（Document Object Model）处理
 * 		将XML文档转换成一个内存中的对象模型集合（通常称为“DOM”树），这样就可以方便的实现XML文档数据的操作，
 * 		同时利用DOM标准方法也可以方便的获取XML中任意部分数据，这种DOM处理机制也被称为随机访问机制
 * 		
 * 		DOM解析
 * 			需要用到javax.xml.parsers包，它可以实现文档的构建
 * 				DocumentBuilder：它可以创建Document实例
 * 				DocumentBuilderFactory：它可以创建DocumentBuilder实例
 * 			
 * 
 * */
public class Xml_test {
public static void main(String[] args)throws Exception{
	//获取DocumentBuilderFactory
	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	//获取DocumentBuilder
	DocumentBuilder builder=factory.newDocumentBuilder();
	//设置要加载的xml文件的路径
	File file=new File("D:"+File.separator+"temp"+File.separator+"xml_test.xml");
	//解析xml文件
	Document documet=builder.parse(file);
	//获取文档中全部的contact元素
	NodeList rootlist=documet.getElementsByTagName("contact");
	
	for(int x=0;x<rootlist.getLength();x++){
		//遍历根元素下的每一个元素，即每一个contact元素
		Element ele=(Element) rootlist.item(x);
		//获取子元素的属性id
		String id=ele.getAttribute("id");
		//获取子元素中的元素标签
		NodeList namelist=ele.getElementsByTagName("name");
		NodeList phonelist=ele.getElementsByTagName("phone");
		NodeList addresslist=ele.getElementsByTagName("address");
		//打印标签中的text标签
		System.out.println("id："+id+",姓名："+namelist.item(0).getTextContent()+",电话："+phonelist.item(0).getTextContent()+",地址："+addresslist.item(0).getTextContent());
			
	}
}
}