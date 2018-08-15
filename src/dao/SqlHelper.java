package dao;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SqlHelper {


	private static connectPool pool = new connectPool(3);
	
	public static int  executeUpdate(String sql,Object... os){
		connect c=pool.getFreeConnection();
		
		try{
		PreparedStatement st=c.conn.prepareStatement(sql);
			for(int i=1;i<=os.length;i++){
				st.setObject(i, os[i-1]);
			}
			return st.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			return -2;
		}finally{
			c.status=0;
		}
		
	}
	
	public static List executeQuery(String sql,Class cla,Object... os) {
		connect c=pool.getFreeConnection();
		ArrayList list = new ArrayList();
		try {
			PreparedStatement st=c.conn.prepareStatement(sql);
			for(int i=1;i<=os.length;i++){
				st.setObject(i, os[i-1]);
			}
			ResultSet rs= st.executeQuery();
			ResultSetMetaData m=rs.getMetaData();
			while(rs.next()){
			Object o=	cla.newInstance();
				for(int i=1;i<=m.getColumnCount();i++){
					Object v=rs.getObject(i);
					String colname=m.getColumnLabel(i);
					try{
					Field f=cla.getDeclaredField(colname);
					setValue(f, o, v);
					}catch (Exception e) {
						continue;
					}
				}
				list.add(o);
			}
			return list;
		}catch (Exception e) {			
			e.printStackTrace();
			return null;
		}finally{
			c.status=0;
		}
	}
	public static void setValue(Field f,Object o,Object v)throws Exception{
		f.setAccessible(true);
		if(f.getType().equals(String.class))			
		f.set(o,v.toString());	
		else if(f.getType().equals(double.class))			
			f.set(o,Double.valueOf( v.toString()));
		else if(f.getType().equals(int.class))			
			f.set(o,Integer.valueOf( v.toString()));
		else 			
			f.set(o, v);
	}
	
	public static ArrayList<HashMap<String, Object>> executeQuery(String sql,Object... os) {
		
		connect c=pool.getFreeConnection();
		ArrayList<HashMap<String ,Object>> list = new ArrayList();
		try {
			PreparedStatement st=c.conn.prepareStatement(sql);
			for(int i=1;i<=os.length;i++){
				st.setObject(i, os[i-1]);
			}
			ResultSet rs= st.executeQuery();
			ResultSetMetaData m=rs.getMetaData();
			while(rs.next()){
				HashMap<String ,Object> map = new  HashMap<String, Object>();
			
				for(int i=1;i<=m.getColumnCount();i++){
					Object v=rs.getObject(i);
					String colname=m.getColumnLabel(i);
					map.put(colname, v);
				}
				list.add(map);
			}
			return list;
		}catch (Exception e) {			
			e.printStackTrace();
			return null;
		}finally{
			c.status=0;
		}
	}
	
	public static void main(String[] args) {
		connect c=pool.getFreeConnection();
		try {
			CallableStatement st=c.conn.prepareCall("call test1(?,?)");
			st.setInt(2, 20);
			ResultSet rs=st.executeQuery();
			rs.next();
			System.out.println(rs.getInt(2));
			System.out.println(st.getInt(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
