package com.maker.xml;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.maker.database.DB_Util;

/**
 * 通过上传xml文件，实现导入数据到数据库
 * */
public class Import_xml {
	private String filepath;//文件路径
	public  Import_xml(String filepath){
		this.filepath=filepath;
	}
	
	public void saveData()throws Exception{
		PreparedStatement pstmt=null;
		try{
			DB_Util.getConn().setAutoCommit(false);//关闭自动提交，因为考虑使用批量导入数据库，所以要关闭自动提交
			String sql="Insert into logs(device,intime,status) values(?,?,?)";
			pstmt=DB_Util.getConn().prepareStatement(sql);
			SAXReader saxReader=new SAXReader();
			Document doc=saxReader.read(new FileInputStream(new File(this.filepath)));
			Element infos=doc.getRootElement();
			List<Element> infolist=infos.elements("info");
			//System.out.println("Test@@@@"+infolist.size());
			for(Element info : infolist){
				pstmt.setString(1, info.elementText("device"));
				pstmt.setTimestamp(2, new java.sql.Timestamp(Long.parseLong(info.elementText("intime"))));;
				pstmt.setInt(3, Integer.parseInt(info.elementText("status")));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			DB_Util.getConn().commit();//由于测试数据较少，所以是一次性写入，如果数据较多，可以几百条写入一次
		}catch(Exception e){
			
				DB_Util.getConn().rollback();
				e.printStackTrace();
				throw e;
		}finally{
			DB_Util.closeCon();
		}
	}
}
