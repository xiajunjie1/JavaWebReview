package com.maker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ParameterUtil {
 	public static final Long MAX_SIZE=3145728L;//设置最大上传内容为3M
 	public static final Long FILE_MAX_SIZE=1048576L;//设置单个文件上传为3M
 	public static final String TEMP_DIR="/temp/";//设置临时目录
 	public static final String UPLOAD_DIR="/upload/";//设置上传目录
 	public static final String DEFAULT_ENCODING="UTF-8";//设置默认编码
 	
 	private HttpServletRequest request;//请求参数
 	private String uploadFile;//保存上传路径
 	private String tempFile;//保存临时路径
 	private Long maxSize;//最大上传空间
 	private Long filemaxSize;//单个文件最大上传空间
 	private String defaultEncoding;//设置编码
 	
 	private boolean uploadFlag=false;//保存表单是否封装的状态
 	private ServletFileUpload fileupload;//实现文件上传处理的对象
 	private List<String> tempfileNames=new ArrayList<>();//临时文件名称存储集合
 	private Map<String,String[]> paramap=new HashMap<>();//利用FileUpload组件获取表单参数，并将相应的参数和参数值保存在该map中，如果是文件，则先将文件存储到临时目录中，然后将临时文件的路径作为map值存储起来
 	private List<String> tempFilenames=new ArrayList<>();//保存临时文件名称，将来清空临时文件的时候使用
 	
 	public ParameterUtil(HttpServletRequest request){
 		this(request,UPLOAD_DIR,TEMP_DIR,MAX_SIZE,FILE_MAX_SIZE,DEFAULT_ENCODING);
 	}
 	
 	public ParameterUtil(HttpServletRequest request,String uploadFile){
 		this(request,uploadFile,TEMP_DIR,MAX_SIZE,FILE_MAX_SIZE,DEFAULT_ENCODING);
 	}
 	
 	public ParameterUtil(HttpServletRequest request,String uploadFile,String tempFile,Long maxSize,Long filemaxSize,String defaultEncoding){
 		this.request=request;
 		/*
 		 * 保证传入的路径是以/结尾的
 		 * */
 		if(uploadFile.endsWith("/")){
 			this.uploadFile=uploadFile;
 		}else{
 			this.uploadFile=uploadFile+"/";
 		}
 		
 		if(tempFile.endsWith("/")){
 			this.tempFile=tempFile;
 		}else{
 			this.tempFile=tempFile+"/";
 		}
 		
 		
 		
 		this.maxSize=maxSize;
 		this.filemaxSize=filemaxSize;
 		this.defaultEncoding=defaultEncoding;
 		this.handleParam();
 	}
 	/**
 	 * 处理请求参数
 	 * */
 	private void handleParam(){
 		//判断请求表单是否封装,并将给封装状态赋值
 		if(this.uploadFlag=(this.request.getContentType()!=null && this.request.getContentType().startsWith("multipart/form-data"))){
 			DiskFileItemFactory factory=new DiskFileItemFactory();
 			factory.setDefaultCharset(this.defaultEncoding);
 			//设置临时目录
 			factory.setRepository(new File(this.tempFile));
 			
 			this.fileupload=new ServletFileUpload(factory);
 			this.fileupload.setSizeMax(this.maxSize);
 			this.fileupload.setFileSizeMax(this.filemaxSize);
 			if(this.fileupload.isMultipartContent(this.request)){
 				//有文件上传
 				try {
					Map<String,List<FileItem>> map=this.fileupload.parseParameterMap(this.request);
					for(Map.Entry<String, List<FileItem>> entry : map.entrySet()){
						//获取请求参数的名称
						String paraname=entry.getKey();
						List<FileItem> paravalues=entry.getValue();
						//如果发现是普通的文本数据，则直接保存在paramap属性中，如果是上传的文件，则生成一个临时文件的名称，将名称保存在paramap中
						String[] values=new String[paravalues.size()];//根据提交参数的个数来确定字符串数组的大小
						int foot=0;//字符串数组的索引控制
						for(FileItem item : paravalues){
							if(item.isFormField()){
								//普通的文本参数
								values[foot++]=item.getString(this.defaultEncoding);
							}else{//二进制文件
								//创建新的文件名称，同时将文件保存在临时目录中
								String filename=saveTempFile(item);
								values[foot++]=filename;
								this.tempFilenames.add(filename);
							}
						}
						
						this.paramap.put(paraname, values);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 			}
 		}
 	}
 	
 	/**
 	 * 临时文件的存储，并且返回临时文件的名称
 	 * 
 	 * */
 	private String saveTempFile(FileItem fileitem)throws Exception{
 		if(fileitem.getSize()>0){
 			//有上传文件的存在
 			String filename="temp."+getUUidName(fileitem);
 			
 			String filepath=this.request.getServletContext().getRealPath(this.tempFile)+filename;
 			
 			fileitem.write(new File(filepath));
 			return filename;
 		}
 		return null;
 	}
 	
 	
 	public String getUUidName(FileItem item){
 		//生成临时文件名称
 		return UUID.randomUUID()+"."+item.getContentType().substring(item.getContentType().lastIndexOf("/")+1);
 	}
 	
 	/**
 	 * 获取单个参数值
 	 * */
 	public String getParameter(String name){
 		if(this.uploadFlag){
 			//请求表单封装过
 			if(this.paramap.containsKey(name)){
 				//请求值存在,返回该参数的第一个值
 				return this.paramap.get(name)[0];
 			}
 			return null;
 		}
 		else{
 			//非封装表单的请求
 			return this.request.getParameter(name);
 		}
 	}
 	
 	/**
 	 * 根据参数名称，获取一组参数值
 	 * 
 	 * */
 	public String[] getParameterValues(String name){
 		if(this.uploadFlag){
 			if(this.paramap.containsKey(name)){
 				return this.paramap.get(name);
 			}
 			return null;
 		}else{
 			return this.request.getParameterValues(name);
 		}
 	}
 	
 	/**
 	 * 获取所有参数的名称
 	 * */
 	public Set<String> getParameterNames(){
 		if(this.uploadFlag){
 			return this.paramap.keySet();
 		}
 		return this.request.getParameterMap().keySet();
 	}
 	
 	
 	public Map<String,String[]> getParameterMap(){
 		if(this.uploadFlag){
 			return this.paramap;
 		}
 		return this.request.getParameterMap();
 	}
 	
 	public List<String> getUUidNames(String name){
 		List<String> uuidnames=new ArrayList<>();
 		System.out.println(this.paramap);
 		String[] names=this.paramap.get(name);
 		if(names==null||names.length==0){
 			System.out.println("【为空】");
 			return uuidnames;
 		}
 		for(String n : names){
 			
 			if(n!=null){
 				//如果未传文件，直接调用该方法，names的值为[null]
 			uuidnames.add("jayj."+UUID.randomUUID()+"."+n.substring(n.lastIndexOf(".")+1));
 		}
 		}
 		return uuidnames;
 	}
 	
 	public List<String> SaveFile(String paraname)throws Exception{
 		String[] names=this.paramap.get(paraname);
 		List<String> nlist=getUUidNames(paraname);
 		
 		for(int x=0;x<names.length;x++){
 			File srcFile=new File(this.request.getServletContext().getRealPath(this.tempFile)+names[x]);
 			File destFile=new File(this.request.getServletContext().getRealPath(this.uploadFile)+nlist.get(x));
 			FileInputStream in=new FileInputStream(srcFile);
 			FileOutputStream out=new FileOutputStream(destFile);
 			byte[] data=new byte[1024];
 			int len;
 			while((len=in.read(data))!=-1){
 				out.write(data,0,len);
 			}
 			in.close();
 			out.close();
 		}
 		return nlist;
 	}
 	
 	public void SaveFile(String paraname,List<String> nlist)throws Exception{
 		String[] names=this.paramap.get(paraname);
 		
 		
 		for(int x=0;x<names.length;x++){
 			File srcFile=new File(this.request.getServletContext().getRealPath(this.tempFile)+names[x]);
 			File destFile=new File(this.request.getServletContext().getRealPath(this.uploadFile)+nlist.get(x));
 			FileInputStream in=new FileInputStream(srcFile);
 			FileOutputStream out=new FileOutputStream(destFile);
 			byte[] data=new byte[1024];
 			int len;
 			while((len=in.read(data))!=-1){
 				out.write(data,0,len);
 			}
 			in.close();
 			out.close();
 		}
 		
 	}
 	
 	public void clean(){
 		if(this.tempFilenames!=null && tempFilenames.size()>0){
 			for(String fname : tempFilenames){
 				File f=new File(this.request.getServletContext().getRealPath(this.tempFile)+fname);
 				if(f.exists()){
 					f.delete();
 				}
 			}
 		}
 	}
 	
 	public void deletFile(String file){
 		String filepath=request.getServletContext().getRealPath(this.uploadFile)+file;
 		File dfile=new File(filepath);
 		if(dfile.exists()){
 			dfile.delete();
 		}
 	}
}
