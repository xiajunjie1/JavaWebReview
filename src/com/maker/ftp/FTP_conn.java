package com.maker.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * FTP连接类
 * 	想要连接到FTP，Linux系统中的防火墙和selinux都需要关闭
 * 	需要保证vsftpd服务的开启
 * 	以下操作均为临时操作，非永久：
 * 		1、开启ftp服务：systemctl start vsftpd
 * 		2、关闭防火墙操作：systemctl stop firewalld.service
 * 		3、关闭selinux操作：setenforce 0
 * 
 * FTP文件的上传
 * 	如果想要进行上传，那么一般都需要考虑好相应的存储目录，需要注意的是，在当前Linux系统中
 * 	所有上传文件的保存目录为"/var/ftp",那么这个目录是一定存在的，如果在下面创建的子目录，都希望可以通过程序动态处理
 * 
 * 目前遇到的问题：
 * 	根据测试来看，利用FTPClient登上了FTP服务后，客户端无法识别根目录的绝对路径，也可以理解为/对于ftp1用户来说就是/var/ftp/ftp1/
 * 	即使用相对路径来进行操作，如果使用绝对路径，会造成changeWorkingDirectory()失败，同样会造成创建目录失败
 * 	如果切换盘符成功后，依然用父盘/文件名的方式进行上传，也会上传失败，返回false
 * 	因为执行了changeWorkingDirectory(),所以已经进入到父盘当中了，所以在上传的时候，参数为文件名本身和输入流即可，不要带路径
 * 	目前造成该情况的原因，推测为创建ftp用户的时候，设置了-d项的原因
 * 	另一种可能是在配置vsftp服务的时候，配置项目的原因，有待进一步学习
 * 
 * */
public class FTP_conn {
private static final String ftp_server="192.168.0.105";//服务器ip
private static final int ftp_port=21;//服务器端口，若不设置端口，默认使用21端口
private static final String ftp_user="ftp1";//用户名
private static final String ftp_pwd="xia123";//密码
private static final int ftp_timeout=5000;//ftp连接超时时间
private static final String textEncoding="UTF-8";//传输文本时设置文本字符编码
public static FTPClient Getclient() throws Exception{
	FTPClient client=new FTPClient();
	client.connect(ftp_server,ftp_port);//设置连接的服务器和端口号
	client.login(ftp_user, ftp_pwd);//设置连接的登录用户和密码
	client.setConnectTimeout(ftp_timeout);//设置连接等待超时
	client.setControlEncoding(textEncoding);//设置字符编码
	System.out.println("【FTP连接状态码】"+client.getReplyCode());//若能成功连接，会返回代码230
	return client;
}
public static void main(String[] args){
	try {
		FTPClient client=Getclient();
		System.out.println(client.abort());
		//System.out.println("test@@："+client.changeWorkingDirectory(new String("/xia".getBytes("UTF-8"),"ISO-8859-1")));
		//System.out.println("test##："+client.printWorkingDirectory());//
		//FileUpload(client,"/xia");//传输相对路径
		//DownloadFile(client,"/xia","bgx.jpg");
		moveFile(client,"/xia/","/xiabak/","test--bg.jpg");
		client.logout();//注销
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
/**
 * 文件上传方法
 * */
private static void FileUpload(FTPClient client,String filepath)throws Exception{
	
	client.enterLocalPassiveMode();//设置为被动模式（一定要设）
	client.setFileType(FTPClient.BINARY_FILE_TYPE);//设置为二进制传输模式
	client.setKeepAlive(true);
	client.setBufferSize(2048);//设置上传的缓存大小
	boolean flag=client.changeWorkingDirectory(filepath);
	//System.out.println("切换目录"+flag);
	if(!flag){
		client.makeDirectory(new String(filepath.getBytes("UTF-8"),"ISO-8859-1"));//切换到上传的目录，如果目录不存在则创建它
		client.changeWorkingDirectory(filepath);//切换到该目录
	}
	
	File file=new File("D:"+File.separator+"temp"+File.separator+"bg.jpg");
	InputStream input=new FileInputStream(file);//获得要上传的文件的输入流
	String tempname="upload--"+file.getName();
	//System.out.println(tempname);
	String ftpfilename=new String(tempname.getBytes("UTF-8"),"ISO-8859-1");//对文件名进行编码设定，防止上传后文件名乱码
	//String ftpfilename=new String("/xia/bgx.jpg".getBytes("UTF-8"),"ISO-8859-1");//在未执行changeWorkingDirectory的情况下可以创建成功，存储路径为/var/ftp/ftp1/xia/bgx.jpg
	//System.out.println(ftpfilename);
	if(client.storeFile(ftpfilename, input)){
		System.out.println("文件上传成功");
	}else{
		System.out.println("文件上传失败");
	}
	input.close();
	
}

/**
 * FTP文件下载
 * 	下载的前提：下载的文件必须存在
 * 
 * 文件的删除
 * */
private static void DownloadFile(FTPClient client,String dir,String filename)throws Exception{
	client.enterLocalPassiveMode();
	client.setFileType(FTPClient.BINARY_FILE_TYPE);
	client.setBufferSize(2048);
	if(client.changeWorkingDirectory(dir)){
		//指定目录存在，且切换到该目录
		//获取文件列表
		FTPFile[] files=client.listFiles();
		//遍历当前目录下的文件
		for(FTPFile file : files){
			if(file.getName().trim().equalsIgnoreCase(filename)){
				//找到需要下载的文件
				//为了可以区分下载文件，本次进行一个强制性的命名
				File downloadFile=new File("D:"+File.separator+"temp"+File.separator+"download-bg.jpg");
				//为了实现FTP的下载，需要利用输出流，输出写入
				OutputStream out=new FileOutputStream(downloadFile);
				//文件下载，第一个参数为FTP上需要下载的文件的名字，第二个参数为写入到本地的输出流
				boolean flag=client.retrieveFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"), out);
				out.flush();
				out.close();
				System.out.println(flag?"下载成功":"下载失败");
				//删除文件
				//client.deleteFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
				//由以上代码可知道，凡是要与FTP上的文件进行直接操作，如上传，下载，删除等，均需要对文件名称进行转码操作
			}
		}
	}
	
}

private static void moveFile(FTPClient client,String oldpath,String newpath,String filename)throws Exception{
	client.enterLocalPassiveMode();
	client.setFileType(FTPClient.BINARY_FILE_TYPE);
	client.setBufferSize(2048);
	if(!client.changeWorkingDirectory(newpath)){
		client.makeDirectory(newpath);
	}
	
	if(client.changeWorkingDirectory(oldpath)){
		FTPFile[] files=client.listFiles();
		for(FTPFile file : files){
			if(file.getName().equals(filename)){
				//文件的移动
				client.rename(new String(filename.getBytes("UTF-8"),"ISO-8859-1"),new String((newpath+filename).getBytes("UTF-8"),"ISO-8859-1"));
			}
		}
	}
}

}
