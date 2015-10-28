package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSetMetaData;

import org.apache.commons.beanutils.BeanUtils;

import com.hephec.reflection.ReflectionUtils;

/**
 * DAO Data Access Object
 * 1.����������Ϣ����,���������ݵ�CRUD����
 * ���������κ�ҵ����ص���Ϣ
 * 2.ʵ�ֹ��ܵ�ģ�黯,�������ڴ����ά��������
 * 
 * 3.ʹ��JDBC��дDAO���ܰ����ķ���
 * insert,update,delete��������������
 * void update(String sql,Object ...args);
 * 
 * */
//��ѯһ����¼,���ض�Ӧ�Ķ���<T> T get(Class<T> clazz,String sql,Object ...args);
//��ѯ������¼,���ض�Ӧ�Ķ���ļ��ϣ�List<T> getForList(Class<T> clazz,String sql,Object ...args)
//����ĳ����¼ĳ���ֶε�ֵ����ĳ��ͳ�Ƶ�ֵ<E> E getForValue(String sql,Object ...args);
//DAO���Ա�����̳л�ֱ��ʹ��

public class DAO {

	public <T> T update(Class<T> clazz,String sql,Object ...args){
		
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JDBCTool.getConnection();
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			int count=ps.executeUpdate();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, ps, conn);
		}
		
		return null;
	}
	
public <T> T get(Class<T> clazz,String sql,Object ...args){
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		T entity=null;
		ResultSetMetaData meta=null;
		try {
			//��ȡConnection����
			conn=JDBCTool.getConnection();
			//��ȡPreparedStatement����
			ps=conn.prepareStatement(sql);
			//���ռλ��
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			//�õ�ResultSet
			rs =ps.executeQuery();
			meta=rs.getMetaData();
			Map<String,Object> values=new HashMap<String,Object>();
			System.out.println(values);
		
			for (int i = 0; i < meta.getColumnCount(); i++) {
				//...
				String label=meta.getColumnLabel(i+1);
				//System.out.println(label);
				Object value=rs.getObject(i+1);
				values.put(label, value);
			}
			entity=clazz.newInstance();
			for(Map.Entry<String, Object> entry:values.entrySet()){
				String key=entry.getKey();
				Object value=entry.getValue();
				//ʹ�÷���
				ReflectionUtils.set(entity,key,value);
				//ʹ��BeanUtils��ֵ
				BeanUtils.setProperty(entity, key, value);
			}
			//��ResultSet���м�¼,׼��һ��Map<String,Object>��������еı���,ֵ������е�ֵ
				//��ResultSetMetaData����õ������������ж�����,�ڵõ�ÿһ�еı���
			
			//��ResultSet�õ�ÿһ�е�ֵ�����Map����
			
			//ʹ�÷��䴴��Class��Ӧ�Ķ���
			
			//����Map����ʹ�÷��������������ֵ,������ΪMap�е�key,����ֵΪMap�е�value
			
			//System.out.println(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, ps, conn);
		}
		
		return entity;
	}
	
	public <T> List<T> getForList(Class<T> clazz,String sql,Object ...args){
		//׼��һ��List<Map<String,Object>>:key������еı���,ֵ������е�ֵ������һ��Map�����Ӧ��һ����¼
		//����ResultSet��ʹ��whileѭ��
		//�����õ�Map���󷽷���׼���õĶ�List��
		//ʹ�÷��䴴��Class����
		//����Map����ʹ�÷��������������ֵ:������ΪMap��key,����ֵΪMap�е�value
		//�ж�List���Ƿ�Ϊ�ռ��ϣ���Ϊ�գ���õ�һ��һ��Map�����ڰ�һ��Map����ת��Ϊһ��Class������Ӧ��Object����
		
		
		return null;
		
	}
	public <E> E getForValue(String sql,Object ...args){
		return null;
	}
}
