package com.maker.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maker.json.vo.Dept;

/**
 * 目前用来处理JSON对象的jar包用的是FastJSON
 * 	JSON依托于Map结构实现，操作与Map类似
 * 
 * 在FastJSON中，处理的核心类有两个：1、JSONObject、2、JSONArray
 * 
 * 使用FastJSON组件进行JSON数据的生成，最重要的一个目的是为了便于开发者实现JSON文本与VO对象之间的转换操作，
 * 
 * */
public class JSON_Obj {
	public static void main(String[] args){
		//jsonObj();
		//jsonArry();
		//VotoJson();
		ListtoJSON();
	}
	
	/*
	 * JSONObject的使用
	 * */
	private static void jsonObj(){
		JSONObject jsonobj=new JSONObject();
		jsonobj.put("name", "xia");//像map一样保存数据
		System.out.println(jsonobj.toJSONString());
		//将文本转为json
		String jsonData="{\"name\":\"xia\",\"age\":20,\"family\":[\"father\",\"mother\"]}";
		JSONObject jo=JSON.parseObject(jsonData);
		//由于JSONObject本质是实现了Map接口的一个类，所以可以将其视为一个MAP，于是可以用MAP的方式来进行遍历
		for(Map.Entry<String, Object> entry : jo.entrySet()){
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
		
	}
	
	/*
	 * JSONArray的使用
	 * 	常用方法：
	 * 		add(Jsonobj)：增加一个JSONObject对象
	 * 		get(int index)：获取某一索引下标的JSONObject
	 * 
	 * */
	private static void jsonArry(){
		JSONArray jarray=new JSONArray();
		String[] names={"xia","jun","jie"};
		String[] depts={"信息部","办公室","人事部"};
		//用JSON数组存放JSON对象
		for(int i=0;i<names.length;i++){
			JSONObject jobj=new JSONObject();
			jobj.put("name", names[i]);
			jobj.put("dept", depts[i]);
			jarray.add(jobj);
		}
		System.out.println(jarray.toJSONString());
		//JSON数据的解析
		String jsondata="[{\"player\":\"Ronaldinho\",\"position\":\"AMF\"},{\"player\":\"Ronaldo\",\"position\":\"CF\"},{\"player\":\"Messi\",\"position\":\"SS\"}]";
		
		JSONArray Jarray=JSONArray.parseArray(jsondata);
		for(int i=0;i<Jarray.size();i++){
			JSONObject obj=Jarray.getJSONObject(i);
			System.out.println("Player is "+obj.getString("player")+",Position is "+obj.getString("position"));
		}
	}
	
	/*
	 * JSON和VO对象的转换
	 * */
	private static void VotoJson(){
		Dept dept=new Dept();
		dept.setDeptname("部门A");
		dept.setDeptno(1001L);
		dept.setLoc("武汉");
		JSONObject obj=new JSONObject();
		obj.put("dept", dept);
		System.out.println(obj.toJSONString());
		
		String jsondata="{\"dept\":{\"deptname\":\"部门B\",\"deptno\":1101,\"loc\":\"武汉\"}}";
		JSONObject jobj=JSONObject.parseObject(jsondata);
		Dept mydept=jobj.getObject("dept", Dept.class);//整个处理机制是基于反射机制实现的
		System.out.println("部门信息："+mydept.toString());
	}
	
	/*
	 * List集合和JSON对象之间的转换
	 * 
	 * */
	private static void ListtoJSON(){
		//将集合对象转成JSON形式的字符串
		List<Dept> deptlist=new ArrayList<>();
		Long[] deptsno={2001L,2101L,2201L};
		String[] deptnames={"部门X","部门Y","部门Z"};
		String[] deptlocs={"武汉","香港","东京"};
		for(int i=0;i<deptsno.length;i++){
			Dept e=new Dept();
			e.setDeptno(deptsno[i]);
			e.setDeptname(deptnames[i]);
			e.setLoc(deptlocs[i]);
			deptlist.add(e);
		}
		String data=JSON.toJSONString(deptlist);
		System.out.println(data);
		
		//通过JSON形式的字符串直接转成集合对象
		String jsondata="[{\"deptname\":\"部门J\",\"deptno\":3001,\"loc\":\"武汉\"},{\"deptname\":\"部门Q\",\"deptno\":3101,\"loc\":\"香港\"},{\"deptname\":\"部门K\",\"deptno\":3201,\"loc\":\"东京\"}]";
		List<Dept> list=JSON.parseArray(jsondata, Dept.class);
		for(Dept d : list){
			System.out.println(d);
		}
	}

}
