package com.maker.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码图片
 * */
public class VerifyCodeImage {
	private String code;
	private static final int CAPTCHA_HEIGHT=35;
	private static final int CAPTCHA_WIDTH=100;
	private int fontHeight=32;
	public VerifyCodeImage(String code){
		this.code=code;
	}
	
	public BufferedImage createImage(){
		BufferedImage bimage=new BufferedImage(this.CAPTCHA_WIDTH,this.CAPTCHA_HEIGHT,BufferedImage.TYPE_INT_BGR);
		Graphics gd = bimage.getGraphics();
		//创建一个随机数生成器
		Random random=new Random();
		//将图像填充为白色
		gd.setColor(Color.white);
		gd.fillRect(0, 0, this.CAPTCHA_WIDTH, this.CAPTCHA_HEIGHT);
		//创建字体,字体大小根据图片的高度来定
		Font font=new Font("Arial",Font.BOLD,this.fontHeight);
		//设置字体
		gd.setFont(font);

		//画边框
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, this.CAPTCHA_WIDTH-1, this.CAPTCHA_HEIGHT-1);
		//产生干扰线，使图中的验证码不易被其它程序探测到
		for(int i=0;i<20;i++){
			int x=random.nextInt(this.CAPTCHA_WIDTH);
			int y=random.nextInt(this.CAPTCHA_HEIGHT);
			int x1=random.nextInt(12);
			int y1=random.nextInt(12);
			gd.drawLine(x, y, x+x1, y+y1);
		}
		
		//绘制验证码
		int red,green,blue=0;
		for(int i=0;i<this.code.length();i++){
			//随机生成字体颜色
			red=random.nextInt(200);
			green=random.nextInt(200);
			blue=random.nextInt(200);
			gd.setColor(new Color(red,green,blue));
			//绘制验证码
			//gd.drawString(this.code.charAt(i)+"", i*this.CAPTCHA_WIDTH/this.code.length(),this.CAPTCHA_HEIGHT);
			gd.drawString(this.code.charAt(i)+"", i*this.CAPTCHA_WIDTH/this.code.length(), this.CAPTCHA_HEIGHT-2);
			System.out.println(this.code.charAt(i)+"#####");
		}
		
	
		return bimage;
	}
}
