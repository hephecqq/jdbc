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
 * 1.访问数据信息的类,包含对数据的CRUD操作
 * 而不包含任何业务相关的信息
 * 2.实现功能的模块化,更有利于代码的维护和升级
 * 
 * 3.使用JDBC编写DAO可能包含的方法
 * insert,update,delete操作都包含其中
 * void update(String sql,Object ...args);
 * 
 * */
//查询一条记录,返回对应的对象：<T> T get(Class<T> clazz,String sql,Object ...args);
//查询多条记录,返回对应的对象的集合：List<T> getForList(Class<T> clazz,String sql,Object ...args)
//返回某条记录某个字段的值或者某个统计的值<E> E getForValue(String sql,Object ...args);
//DAO可以被子类继承或直接使用

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
			//获取Connection对象
			conn=JDBCTool.getConnection();
			//获取PreparedStatement对象
			ps=conn.prepareStatement(sql);
			//填充占位符
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			//得到ResultSet
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
				//使用反射
				ReflectionUtils.set(entity,key,value);
				//使用BeanUtils赋值
				BeanUtils.setProperty(entity, key, value);
			}
			//若ResultSet中有记录,准备一个Map<String,Object>键，存放列的别名,值，存放列的值
				//由ResultSetMetaData对象得到具体结果集中有多少列,在得到每一列的别名
			
			//由ResultSet得到每一列的值，填充Map对象
			
			//使用反射创建Class对应的对象
			
			//遍历Map对象，使用反射填充对象的属性值,属性名为Map中的key,属性值为Map中的value
			
			//System.out.println(rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, ps, conn);
		}
		
		return entity;
	}
	
	public <T> List<T> getForList(Class<T> clazz,String sql,Object ...args){
		//准备一个List<Map<String,Object>>:key：存放列的别名,值《存放列的值，其中一个Map对象对应着一条记录
		//处理ResultSet，使用while循环
		//把填充好的Map对象方法如准备好的额List中
		//使用反射创建Class对象
		//遍历Map对象，使用反射填充对象的属性值:属性名为Map中key,属性值为Map中的value
		//判断List中是否为空集合，不为空，则得到一个一个Map对象，在把一个Map对象转换为一个Class参数对应的Object对象
		
		
		return null;
		
	}
	public <E> E getForValue(String sql,Object ...args){
		return null;
	}
}
