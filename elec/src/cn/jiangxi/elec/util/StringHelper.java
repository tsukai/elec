package cn.jiangxi.elec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {
	/**
	 * 将String类型的日期形式转换成日期类型
	 * @param textDate
	 * @return
	 */
	public static Date stringConvertDate(String textDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
		Date result = null;
		try {
			result = format.parse(textDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String stringArrayToString(String[] strArr){
		StringBuffer str = new StringBuffer("");
		for (int i = 0;strArr != null && i < strArr.length; i++) {
			str.append(strArr[i]);
		}
		return str.toString();
	}
}
