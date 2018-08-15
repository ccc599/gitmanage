package dao;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class jsUntil {

	public static String toString(Object o)
	{
	 ObjectMapper mapper=new ObjectMapper();
	 try {
		
		return mapper.writeValueAsString(o);
	}   catch (Exception e) {
		
		 return "";
	}
	
	}

	public static <T> T toObject(String str,Class<T> cla) 
	{
		 ObjectMapper mapper=new ObjectMapper();
		 try {
			
			return mapper.readValue(str, cla);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public static String returnvalues(int status, String text) {
		
		return toString( new jsoninfo(status,text));
	}
	 public static class jsoninfo{
		public int status;
		public String text;
	
		public jsoninfo(int status, String text) {
			super();
			this.status = status;
			this.text = text;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}
