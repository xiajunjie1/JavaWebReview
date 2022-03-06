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
import org.w3c.dom.NodeList;

/**
 * XML文件元素的添加和删除
 * 
 * 在大部分的开发情况中，修改xml文件的操作并不常见，因为xml的体积都比较大，而dom是需要内存支持的
 * 如果xml文件很大的时候，用dom修改内存会比较紧张
 * 
 * 只有dom的处理标准支持xml文件的修改
 * */
public class XML_add_delete {
	public static void main(String[] args)throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document doc=builder.parse(new File("D:"+File.separator+"temp"+File.separator+"test.xml"));
		//add(doc);
		delete(doc);
	}
	
	private static void add(Document doc)throws Exception{
		//获取到member元素的集合
		NodeList memberlist=doc.getElementsByTagName("member");
		for(int i=0;i<memberlist.getLength();i++){
			Element member=(Element) memberlist.item(i);
			Element url=doc.createElement("url");
			NodeList namelist=member.getElementsByTagName("name");
			String urlcontent=namelist.item(0).getTextContent()+".com";
			url.appendChild(doc.createTextNode(urlcontent));
			member.appendChild(url);
		}
		TransformerFactory tfactory=TransformerFactory.newInstance();
		Transformer transformer=tfactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.transform(new DOMSource(doc), new StreamResult(new File("D:"+File.separator+"temp"+File.separator+"test.xml")));
		
	}
	
	/**
	 * XML节点的删除
	 * 	与XML修改的处理操作类似的就是XML节点的删除，在进行数据删除操作的时候，所有的元素长度都是动态获取的
	 * 	如果想删除一个指定的元素，那么一般需要获取删除元素的父节点，然后使用remove()方法进行删除
	 * 
	 * 
	 * 
	 * */
	private static void delete(Document doc)throws Exception{
		NodeList urlist=doc.getElementsByTagName("url");//获取要删除的标签
		int len = urlist.getLength();//urlist的长度，还随着元素的删除而动态发生变化，所以这里提前获取
		for(int i=0;i<len;i++){
			//在此处，每次都取集合中的第一个元素，取的次数和初始化的url标签个数相同
			//因为该集合会随着元素的删除，长度发生动态的变化，所以元素的索引也会动态的发生变化，在此处每次就只取最上面的元素
			Element urlele=(Element) urlist.item(0);
			//找到当前节点的父元素，调用remove方法删除当前节点
			urlele.getParentNode().removeChild(urlele);
		}
		TransformerFactory tfactory=TransformerFactory.newInstance();
		Transformer transformer=tfactory.newTransformer();
		transformer.transform(new DOMSource(doc), new StreamResult(new File("D:"+File.separator+"temp"+File.separator+"test2.xml")));
	}
}
