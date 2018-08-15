package entity;

public class Cla {
	public  static String [] clastatus={"״̬1","״̬2","״̬3","״̬4","״̬5","״̬6"};
	
	String name;
	int status;
	int clatypeid;
	int id;
	public String getName() {
		return "���ƣ�"+name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getClatypeid() {
		return clatypeid;
	}
	public void setClatypeid(int clatypeid) {
		this.clatypeid = clatypeid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
