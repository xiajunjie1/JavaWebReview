package message.maker.handler;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import message.maker.util.DB_Util;

/**
 * XML导出工具类
 * */
public class ExportHandle {
	private Date beginDate;
	private Date endDate;
	
	public ExportHandle(Date beginDate,Date endDate){
		this.beginDate=beginDate;
		this.endDate=endDate;
	}
	/*
	 * 获取数据库数据，生成xml
	 * */
	public String getData(){
		String str=null;//保存最终的生成的xml的数据
		//在使用DOM4J的时候，一定要数据按照输出流的方式进行输出，那么最佳的做法就是使用字节内存流
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		Connection con=null;
		PreparedStatement pstat=null;
		ResultSet rs=null;
		
		try{
			String sql="Select device,intime,status from record where intime between ? and ?";
			 con=DB_Util.getConn();
			 pstat=con.prepareStatement(sql);
			pstat.setDate(1, new java.sql.Date(this.beginDate.getTime()));
			pstat.setDate(2, new java.sql.Date(this.endDate.getTime()));
			rs=pstat.executeQuery();
			//最终所有的数据内容需要保存到xml文件中
			//创建一个xml的文档
			Document doc=DocumentHelper.createDocument();
			//创建根元素
			Element infosElement=doc.addElement("infos");
			
			while(rs.next()){
				Element info =infosElement.addElement("info");
				Element device=info.addElement("device");
				device.addText(rs.getString("device"));
				Element intime=info.addElement("intime");
				intime.addText(String.valueOf(rs.getTimestamp("intime").getTime()));//将时间转为时间戳存到xml文件中
				Element status=info.addElement("status");
				status.addText(String.valueOf(rs.getInt("status")));
				
			}
			//将生成的xml文档输出到内存输出流中
			XMLWriter writer=new XMLWriter(output);
			writer.write(doc);
			writer.close();
			//内存流中的方法，可以直接将输出的流转成字符串
			str=output.toString();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB_Util.closeConn(rs, pstat, con);
		}
		return str;
	}
}
