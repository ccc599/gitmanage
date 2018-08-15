package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServlet;

public class connectPool   {
	private Vector<connect> list = new Vector<connect>();	
	public connectPool(int count) {
		for(int i=0;i<count;i++){
			list.add(new connect());
		}
	}	
	public connect getFreeConnection(){
		for(int i=0;i<list.size();i++){
			if(list.get(i).status==1)continue;
			if(list.get(i).conn==null){
				try {
					list.get(i).conn=getConnection();
				} catch (Exception e) {
				}
			}
			list.get(i).status=1;
			System.out.println("Á¬½ÓÐòºÅ£º"+i);
			return list.get(i);
		}
		return null;
	}	
	public void closeAll(){
		try{
		for(int i=0;i<list.size();i++){
			list.get(i).conn.close();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String url;
	private static String name;
	private static String pass;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("db.properties"));
		} catch (Exception e) {
		}
		
		url=p.getProperty("url", "jdbc:mysql://127.0.0.1:3306/ccc20180720?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true");
		name=p.getProperty("name", "root");
		pass=p.getProperty("pass", "058222");
	}
	private static Connection getConnection()throws Exception{
		return DriverManager.getConnection(url,name,pass);
	}
	
}
