package servlet;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	public static String toString(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static <T> T tojson(String s,Class<T> Cla) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(s,Cla);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String jsoninfo(int s,String msg) {
		return toString(new a(s, msg));
	}
	
	public static class a{
		public int c;
		public String msString;
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		public String getMsString() {
			return msString;
		}
		public void setMsString(String msString) {
			this.msString = msString;
		}
		public a(int c, String msString) {
			super();
			this.c = c;
			this.msString = msString;
		}
	}
	
}
