package com.maker.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML文件的创建
 * 	在内存中创建dom树
 * 		利用的类，仍然是org.w3c.dom中的相关类
 * 
 * 	将内存中的dom树输出到xml文件中
 * 		Source和Result接口
 * 			Source接口定义了要转换内容的来源，而Result就是定义了转换的输出目标，这两个结构本身全都是接口，所以要依靠其子类完成
 * 			除了处理接口标准之外，实际上还要考虑一些工具类
 * 			Outputkeys
 * 			Transformer
 * 			TransformerFactory
 * 		此时的输出时将
 * 		
 * 
 * */
public class XML_create {
	public static void main(String[] args)throws Exception{
		//获得XML工具实例
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		//创建一个空白的Document文档(dom树）
		Document doc=builder.newDocument();
		//设置存储的数据
		String[] ids={"xia","jun","jie"};
		String[] names={"夏","俊","杰"};
		int[] ages={22,24,28};
		//创建根节点
		Element root=doc.createElement("members");
		for(int i=0;i<ids.length;i++){
			//创建member元素，并设置member元素中的text元素值
			Element ele=doc.createElement("member");
			ele.setAttribute("id", ids[i]);
			Element nameEle=doc.createElement("name");
			nameEle.appendChild(doc.createTextNode(names[i]));
			ele.appendChild(nameEle);
			Element ageEle=doc.createElement("age");
			ageEle.appendChild(doc.createTextNode(String.valueOf(ages[i])));
			ele.appendChild(ageEle);
			root.appendChild(ele);
		}
		doc.appendChild(root);
		//将内存的DOM树（Source）的内容，转到Result中
		TransformerFactory transformer=TransformerFactory.newInstance();
		Transformer trans=transformer.newTransformer();
		//设置输出属性
		trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//将内存中的DOM树输出为xml，参数1：转换的数据来源、参数2：输出的目标文件
		trans.transform(new DOMSource(doc), new StreamResult(new File("D:"+File.separator+"temp"+File.separator+"test.xml")));
	}
}
