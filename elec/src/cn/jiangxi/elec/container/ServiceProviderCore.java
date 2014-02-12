package cn.jiangxi.elec.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceProviderCore {
	protected static ApplicationContext ac;
	//加载bean.xml文件
	public static void load(String fileName){
		ac = new ClassPathXmlApplicationContext(fileName);
	}
}
