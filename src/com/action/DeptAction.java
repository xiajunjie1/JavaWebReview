package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mvc.commons.annotation.AutoWired;
import com.mvc.commons.annotation.Controller;
import com.mvc.commons.annotation.RequestMapping;
import com.mvc.commons.bean.MultipartFile;
import com.mvc.commons.bean.ServletObject;
import com.mvc.service.IdeptService;
import com.mvc.view.ModeAndView;
import com.mvc.vo.Dept;

@Controller
@RequestMapping("/dept/")
public class DeptAction extends AbstractAction {
	@AutoWired
	private IdeptService deptservice;
	@RequestMapping("add")
	public String add(){
		System.out.println("新增成功！");
		return "add.jsp";
	}
	@RequestMapping("edit")
	public ModeAndView edit(){
		ModeAndView mav=new ModeAndView("edit.jsp","msg","test");
		System.out.println("修改信息！");
		return mav;
	}
	
	@RequestMapping("remove")
	public void remove(){
		System.out.println("删除信息");
	}
	
	@RequestMapping("split")
	public String split(int curpage,int pagesize,String colum,String keyword){
		System.out.println("分页显示");
		
		try {
			Map<String,Object> map=deptservice.split(curpage, pagesize, colum, keyword);
			Long records=(Long) map.get("allrecord");
			List<Dept> dlist=(List<Dept>) map.get("result");
			System.out.println("【记录数】"+records);
			System.out.println("【数据内容】"+dlist);
			ServletObject.getSession().setAttribute("record",records);
			ServletObject.getSession().setAttribute("result", dlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("分页查询出错");
			e.printStackTrace();
		}
		return "show.jsp";
	}
	
	@RequestMapping("ptest")
	public String test(String param){
		System.out.println("参数接收测试，参数值为："+param);
		ServletObject.getReq().setAttribute("ptest", param);
		return "show.jsp";
	}
	@RequestMapping("vtest")
	public void testVo(Dept dept){
		System.out.println("【vo测试】："+dept);
	}
	@RequestMapping("mix")
	public void mixParam(Dept dept,String[] hobby,MultipartFile photo){
		System.out.println("【Vo】"+dept);
		System.out.println("【数组信息】"+Arrays.toString(hobby));
		System.out.println("【上传的文件信息】"+photo);
		//将接收到的文件进行保存
		try {
			FileInputStream input=new FileInputStream(photo);
			String tfname=UUID.randomUUID()+"."+photo.getContentType().substring(photo.getContentType().lastIndexOf("/")+1);
			File targetFile=new File(ServletObject.getApplication().getRealPath("/upload/")+tfname);
			FileOutputStream output=new FileOutputStream(targetFile);
			byte[] data=new byte[1024];
			int len;
			while((len=input.read(data))!=-1){
				output.write(data,0,len);
			}
			input.close();
			output.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("init")
	public void initDept(){
		try {
			deptservice.addBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
