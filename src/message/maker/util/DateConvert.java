package message.maker.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConvert {//日期转换
	private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private DateConvert(){}//防止外部实例化
	
	public static Date StringToDate(String str){
		LocalDateTime dateTime=LocalDateTime.parse(str);
		ZoneId zoneId=ZoneId.systemDefault();//获取默认时区
		Instant instant=dateTime.atZone(zoneId).toInstant();
		Date date=Date.from(instant);
		return date;
	}
}
