package com.hephec.jdbc;


import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest{

	/**
	 * Java类的属性
	 * Java类的实现通过getter,setter来定义
	 * get(或set)方法取出get(或set)后首字母小写
	 * 即为Java类的属性
	 * 操作Java类属性有一个工具包：beanUtils
	 * 测试一个方法setProperty,getProperty
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * 
	 * 一般情况下，字段名和属性名都一致
	 * 需要加入两个jar包，beanUtils和logging包
	 * 
	 * */
	
	@Test
	public void testSetProperty() throws IllegalAccessException, Exception{
		Object obj=new Student();
		BeanUtils.setProperty(obj, "idCard", "52532");
		System.out.println(obj);
		String str=BeanUtils.getProperty(obj, "idCard");
		System.out.println(str);
	}
}
