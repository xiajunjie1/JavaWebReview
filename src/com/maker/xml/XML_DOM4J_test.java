package com.maker.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * DOM4J工具
 * 	DOM for Java：基于DOM和SAX的结构新封装的一个组件
 * 	基本上可以见到的Java开发框架，80%都存在有DOM4J的应用
 * 
 * org.dom4j.Node:dom4j的核心类
 * 	String getName()
 * 	Element getParent()
 * 	String getText()
 * 	void setParent(Element parent)
 * 
 * DOM4j可以直接创建节点并设置节点关系
 * 	
 * 	
 * */
public class XML_DOM4J_test {
	public static void main(String[] args) throws Exception{
		//create();
		Parase();
	}
	private static void create()throws Exception{
		//需要存储的数据
		String[] ids ={"leo","chris"};
		String[] names={"梅西","罗纳尔多"};
		int[] ages={22,23};
		//创建文档对象
		Document doc=DocumentHelper.createDocument();
		//创建根元素，并将其添加到文档中
		Element members=doc.addElement("members");
		for(int i=0;i<ids.length;i++){
			//创建根元素下的子元素
			Element member=members.addElement("member");
			//设置元素属性
			member.addAttribute("id", ids[i]);
			//设置member元素下的name元素和age元素
			Element name=member.addElement("name");
			Element age=member.addElement("age");
			name.addText(names[i]);
			age.addText(String.valueOf(ages[i]));
		}
		//将生成的DOM树输出到文件当中,默认输出模式为紧凑输出模式
		XMLWriter writer=new XMLWriter(new FileOutputStream(new File("D:"+File.separator+"temp"+File.separator+"dom4j_out.xml")));
		//创建输出格式，创建为完美输出，即它会自动对xml进行排版，默认为OutputFormat.createCompactFormat()紧凑型输出方式
		OutputFormat format=OutputFormat.createPrettyPrint();
		
		format.setEncoding("UTF-8");
		//完美输出
		XMLWriter writer2=new XMLWriter(new FileOutputStream(new File("D:"+File.separator+"temp"+File.separator+"dom4j_prettyout.xml")),format);
		writer.write(doc);
		writer2.write(doc);
		writer.close();
		writer2.close();
		//一般而言，数据传输的时候，默认的紧凑型xml效率要高于完美型的xml
	}
	
	/*
	 * 解析xml文件
	 * 	dom4j解析xml，核心用的是sax的解析
	 * */
	private static void Parase()throws Exception{
		//找到xml文件
		File file=new File("D:"+File.separator+"temp"+File.separator+"test.xml");
		//创建SAX解析器
		SAXReader reader = new SAXReader();
		Document doc=reader.read(file);
		//获取根元素
		Element rootEle=doc.getRootElement();
		//获取根元素下的子元素
		List<Element> memlist=rootEle.elements("member");
		//遍历获取到的子元素
		Iterator<Element> it=memlist.iterator();
		while(it.hasNext()){
			//获取道每个member元素
			Element ele=it.next();
			String id=ele.attributeValue("id");
			//获取member下Text标签元素的值
			String name=ele.elementText("name");
			String age=ele.elementText("age");
			String url=ele.elementText("url");
			System.out.println("id："+id+",name："+name+",age："+age+"url："+url);
		}
		
	}
}
