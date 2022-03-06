package com.maker.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * w3c组织标准中，仅仅是规定了DOM的解析形式，但是在使用DOM操作的时候，有一个最为严重的问题：
 * 	所有的XML数据如果想进行解析处理，就必须将其在内存中形成DOM树，但是千万不要忘记了，内存的
 * 	容量是有限的，尤其是在高并发的情况下，更希望节约内存空间，DOM处理实际上无法处理较大的XML文件
 * 
 * 为了解决大文件XML的读取操作，提供可一个新的处理标准-SAX解析
 * 	SAX（Simple APIs for XML）是一种基于顺序式读取操作的XML解析模式，在使用SAX解析处理过程中
 * 	不会将一个XML文件中的全部内容读取到内存中，而是会根据读取到的XML中的不同组成结构来进行事件响应，
 * 	这样开发者就可以根据事件产生的内容实现相应数据信息的获取
 * 	
 * 	在整个SAX处理里面，是依据文档的结构从头到尾的顺序式向下读取，而后每当读取到某个数据节点的时候就会产生有一个
 * 	处理事件，那么用户就可以根据这样的事件来获取相应的操作，例如：如果读取到元素开始的节点，那么就有可能读取到元素中的属性内容
 * 	如果要是文本事件，那么就可以获取到元素的具体文字信息
 * 
 * 	SAX是一个民间的处理标准，它只能对XML文件进行读取，但是对比DOM，它的优势在于可以读取较大的XML文件
 * 	在使用SAX解析XML文件的过程中，会存在大量的事件监听方法，这些方法都在“org.xml.sax.helpers.DefaultHandler”类中有所定义
 * 	开发者只需要根据自身的需要，在其子类中覆写相应的方法即可
 * 
 * 	SAX仅仅是一个读取的标准，如果要对该标准进行使用，那么需要借助一些辅助的工具类
 * 		SAXParserFactory
 * 		SAXParser
 * 	
 * 	SAX解析模型
 * 		解析xml文件的目的是获取xml文件中的数据内容，那么仅仅依靠这种顺序式的方式，如果想要获取里面的数据内容
 * 		就需要做一些额外的开发
 * */
public class XML_sax_test {
	
	public static void main(String[] args)throws Exception{
		File xmlfile=new File("D:"+File.separator+"temp"+File.separator+"test.xml");
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		MembersSaxHelper helper=new MembersSaxHelper();
		parser.parse(xmlfile,helper);
		System.out.println(helper.getMemberlist());
		
	}
}

/**
 * 定义一个xml文件的sax解析器类
 * */
class MembersSaxHelper extends DefaultHandler{
	private List<Member> memberlist;
	private String qName;//保存当前元素的名称
	private Member member;
	
	/*
	 * 读取到文档开始时的监听事件
	 * */
	@Override
	public void startDocument() throws SAXException {
		
		//System.out.println("开始读取xml文档：<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
		//初始化List
		memberlist=new ArrayList<>();
	}
	/*
	 * 文档结束时的监听事件
	 * */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("文档读取完毕");
	}
	
	/*
	 * 元素开始时监听事件
	 * */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
	//System.out.println("元素读取开始，元素标签名："+qName+"，id属性值："+attributes.getValue("id"));
	//保存当前的标签名
	this.qName=qName;
	if("member".equals(qName)){
		//读取到member标签
		member=new Member();
		member.setId(attributes.getValue("id"));
	}
	}
	
	/*
	 *元素结束时监听事件 
	 * */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		//System.out.println(qName+"元素读取结束");
		//将Member元素添加到集合中
		if("member".equals(qName)){
			this.memberlist.add(this.member);
			this.member=null;
		}
	}

	/*
	 * 文本标签元素监听事件
	 * */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//System.out.println("文本节点内容："+new String(ch,start,length));
		//读取到文本内容
		String val=new String(ch,start,length).trim();
		if(!("".equals(val)||val==null)){
			//有数据
			if("name".equals(this.qName)){
				this.member.setName(val);
			}
			if("age".equals(this.qName)){
				this.member.setAge(Integer.parseInt(val));
			}
			if("url".equals(this.qName)){
				this.member.setUrl(val);
			}
			
		}
	}
	public List<Member> getMemberlist() {
		return memberlist;
	}
	
}

/**
 * SAX解析模型
 * 	由于测试的xml文件中，数据主要是一组member，在java中，可以用集合的形式来进行member数据的存放
 * */
class Member{
	private String id;
	private String name;
	private int age;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"-"+this.name+"-"+this.age+"-"+this.url;
	}
}