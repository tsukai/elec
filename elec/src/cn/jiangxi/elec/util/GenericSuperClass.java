package cn.jiangxi.elec.util;

import java.lang.reflect.ParameterizedType;

public class GenericSuperClass {
	
	/**
	 * 泛类转换，转换成对应的对象
	 * @param class1 泛类
	 * @return
	 */
	public static Class getClass(Class class1) {
		//泛型转换
		ParameterizedType pt = (ParameterizedType)class1
				.getGenericSuperclass();
		Class entity = (Class)pt.getActualTypeArguments()[0];
		return entity;
	}
}
