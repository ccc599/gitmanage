package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {
	public static Connection  SqlConnection() {
        // TODO Auto-generated method stub
        Connection conn = null;
        // 椹卞姩寮曟搸
        String jd = "com.mysql.jdbc.Driver";
        // 杩炴帴MySQL鐨勮繛鎺�
        String url = "jdbc:mysql://localhost:3306/ccc20180720?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true\r\n" ;
        // MySQL鐨勭敤鎴峰悕
        String user = "root";
        // MySQL鐨勫瘑鐮�
        String password = "058222";
        try {
            // 鍔犺浇椹卞姩
             Class.forName(jd);
            // 杩炴帴鏁版嵁搴撳璞�
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("寮曟搸澶辫触");
            e.printStackTrace();
        }       
    	//棰勫鐞�
//		PreparedStatement a= conn.prepareStatement("insert into class(name) values(?)");
//		a.setString(1, "as");
		
		//鎵ц绯荤粺澶勭悊,瀛樺偍杩囩▼
//		java.sql.CallableStatement st2 = conn.prepareCall("call fun(?,?)");
//		st2.setString(1, "");
//		st2.getString(2);

        return conn;
    }	
	public static ResultSet getUserData(String sql) throws ClassNotFoundException, SQLException{
		Statement statement=SqlConnection().createStatement();
		ResultSet set=statement.executeQuery(sql);
		return set;
		}
	public static void createDate(String table ,String row , String vales) throws ClassNotFoundException, SQLException{
		String [] sp=vales.split(",");
		vales=" ";
		for(int i=0;i<sp.length;i++)
		{
			if(i==sp.length-1)
			vales+="'"+sp[i]+"'";
			else {
				vales+="'"+sp[i]+"', ";
			}
		}
		
		Statement statement=SqlConnection().createStatement();
		statement.executeUpdate("insert into "+table + "(" + row +")" +"values" + "(" +vales +")");
	}
	public static void alterData(String table ,String row , String value , String keyvalue) throws ClassNotFoundException, SQLException{
		Statement statement=SqlConnection().createStatement();
		statement.executeUpdate("update "+ table +" set "+row+ "='"+value+"' where id= '"+keyvalue+"'");
	}

}

