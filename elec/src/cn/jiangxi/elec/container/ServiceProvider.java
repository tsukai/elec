package cn.jiangxi.elec.container;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.StringUtils;

public class ServiceProvider {
	public static ServiceProviderCore spc;
	//加载配置文件
	static{
//		System.out.println("加载spring配置文件");
		spc = new ServiceProviderCore();
		spc.load("beans.xml");
	}
	
	public static Object getService(String name){
		if(StringUtils.isBlank(name)){
			throw new RuntimeException("当前服务名称不存在");
		}
		Object obj = null;
		if(spc.ac.containsBean(name)){
			obj = spc.ac.getBean(name);
		}
		if(obj == null){
			throw new RuntimeException("当前服务名称【"+ name +"】下的服务节点不存在");
		}
		return obj;
	}
}
