package po;

import java.io.Serializable;

public class WebManagerPO implements Serializable{
	String name;
	String contact;
//	int id;
	
	public WebManagerPO(String n,String c){
		name=n;
		contact=c;
//		id=i;
	}
	public String getname(){
		return name;
	}
	public String getcontact(){
		return contact;
	}
//	public int getid() {
//		// TODO Auto-generated method stub
//		return id;
//	}
}
