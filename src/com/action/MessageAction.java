package com.action;

import java.util.List;

import com.mvc.commons.annotation.AutoWired;
import com.mvc.commons.annotation.Controller;
import com.mvc.commons.annotation.RequestMapping;
import com.mvc.commons.bean.ServletObject;
import com.mvc.service.ImemberService;
import com.mvc.vo.Member;

/**
 * 对于当前程序，如果想要控制器类被Servlet去使用的话，那么一般会有两种做法。
 * 	第一种：在项目中写一个配置文件，然后在配置文件中去定义所需要的程序类，维护相对麻烦
 * 	第二种：进行包扫描配置，例如：当所有的Action都保存在：com.action中，那么可以在
 * 	DispatchServlet类中进行这个包的配置，这样就会自动扫描当前包中的程序类，得到Class实例化对象
 * 	有了Class基本上就可以实现一切操作了。
 * */

@Controller//表示当前类是一个控制器处理类
@RequestMapping("/pages/message/")//父路径
public class MessageAction {
	//@AutoWired(name="imemberService")//可以设置名字，通过名字进行注入
	@AutoWired//不设置名字，直接通过该属性的类型进行注入
	private ImemberService imemService;
	
	@RequestMapping("add")//真实访问路径/pages/message/add.action
	public void add(){
		System.out.println("【Message.add】增加新的消息内容");
		System.out.println(ServletObject.getReq().getContextPath());
	}
	
	@RequestMapping("list")//真实访问路径/pages/message/list.action
	public void list(){
		System.out.println("【Message.list】显示消息内容");
		try {
			List<Member> mlist=this.imemService.list();
			System.out.println(mlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("remove")//真实访问路径/pages/message/remove.action
	public void remove(){
		System.out.println("【Message.remove】删除消息内容");
	}
	@RequestMapping("edit")//真实访问路径/pages/message/edit.action
	public void edit(){
		System.out.println("【Message.edit】编辑消息内容");
	}
}
/**
 * 以上的真实路径使用.action的原因是为了找到DispatchServlet,而前面的路径是为了进行Action程序类访问映射标记
 * 在后续的开发过程里面，开发者只需要编写Action程序类即可，其他具体实现细节，全部由自定义的MVC框架来实现
 * */
