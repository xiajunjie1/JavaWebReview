package com.mvc.commons.bean;

import java.io.File;
/**
 * 所有的上传操作是通过DispatchServlet转发到Action处理类之中的，那么在进行转发的处理过程中
 * 所有的上传文件都保存在临时目录里面，在进行Action处理的时候，还需要获取一些上传文件的额外信息，例如
 * 文件大小，文件的原始名称（上传后，所有的文件名称都是临时名称）、文件的MIME类型
 * 
 * 在java io包中提供了一个File程序类，这个类可以描述文件，但是File类却无法描述更多的信息，所以最佳的做法是
 * 基于已经存在的File类扩充一个新的子类-MultipartFile类
 * 
 * */
public class MultipartFile extends File {
private String contentType;//MIME类型
private String orignalFilename;//文件的原始名称
public MultipartFile(String path){
	super(path);
}

public MultipartFile(File parent,String child){
	super(parent,child);
}

public String getOrignalFilename() {
	return orignalFilename;
}

public void setOrignalFilename(String orignalFilename) {
	this.orignalFilename = orignalFilename;
}

public String getContentType() {
	return contentType;
}

public void setContentType(String contentType) {
	this.contentType = contentType;
}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return "【File】"+super.toString()+"【mime】"+this.contentType+"【name】"+this.orignalFilename;
}

}
