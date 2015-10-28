package com.hephec.jdbc;


import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest{

	/**
	 * Java�������
	 * Java���ʵ��ͨ��getter,setter������
	 * get(��set)����ȡ��get(��set)������ĸСд
	 * ��ΪJava�������
	 * ����Java��������һ�����߰���beanUtils
	 * ����һ������setProperty,getProperty
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * 
	 * һ������£��ֶ�������������һ��
	 * ��Ҫ��������jar����beanUtils��logging��
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
