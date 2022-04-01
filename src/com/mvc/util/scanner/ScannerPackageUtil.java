package com.mvc.util.scanner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.mvc.commons.bean.ControllerRequestMapping;
import com.mvc.util.annotation.ConfigAnnotationPraseUtil;

/**
 * 包扫描工具类
 * */
public class ScannerPackageUtil {
	//全局变量，保存整个项目中全部控制器的访问映射处理
	private static final Map<String,ControllerRequestMapping> Action_Map=new HashMap<>();
	private static final Map<String,Class> By_Name_Map=new HashMap<>();
	private static final Map<Class,Class> By_Type_Map=new HashMap<>();
	private static String baseDir;//公共项目路径
	private ScannerPackageUtil(){
		//不允许该类被实例化，所有的操作通过静态方法来完成
	}
	
	/*
	 * 实现扫描包配置的处理
	 * @param clazz 调用此类的程序类，一般是DispatchServlet
	 * @param Packages 配置扫描包的名称，一般用";"进行多个包之间的分隔
	 * */
	public static void ScannerHandle(Class<?> clazz,String Packages){
		String[] resultPackage=Packages.split(";");
		baseDir=clazz.getResource("/").getPath();//得到一个以"/"开头的服务器物理地址，该地址为项目所在的class目录的完整路径
		baseDir=baseDir.substring(1).replace("/", File.separator);//考虑到在不同的系统中运行该项目，更换目录间的分隔符
		for(String pack : resultPackage){
			String subDir=pack.replace(".", File.separator);//获取了子路径
			listDir(new File(baseDir,subDir));
		}
	}
	
	private static void listDir(File file){
		if(file.isDirectory()){
			//是目录
			File[] result=file.listFiles();
			if(result !=null)
			{
				for(File f : result){
				listDir(f);
			}
			}
		}else{
			//是文件
			if(file.isFile()){
				//假设是文件，那么必然会得到一个形如：E:/.../.../com/action/xxx.class的文件，现在要去掉它前面的父路径，和后面文件的后缀名，只留下完整的类名，即com.action.xxx的形式
				String classname=file.getAbsolutePath().replace(baseDir, "").replace(File.separator, ".").replace(".class", "");//获取完整的类名
				//将类名给到相应的工具类进行解析，然后获取到相应的访问路径和action的映射map
				System.out.println("【解析类名：】"+classname);
				try {
					ConfigAnnotationPraseUtil parseUtil=new ConfigAnnotationPraseUtil(Class.forName(classname));
					Action_Map.putAll(parseUtil.getControllerMapResult());
					By_Name_Map.putAll(parseUtil.getNameAndTypeMap());
					By_Type_Map.putAll(parseUtil.getObjectInterfaceAndClassMap());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Map<String,ControllerRequestMapping> getAction_Map(){
		return Action_Map;
	}

	public static Map<String,Class> getByNameMap() {
		return By_Name_Map;
	}

	public static Map<Class,Class> getByTypeMap() {
		return By_Type_Map;
	}
}
