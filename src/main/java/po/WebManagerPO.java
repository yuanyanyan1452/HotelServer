package po;

import java.io.Serializable;

import objects.Manage;
import vo.WebManagerVO;

public class WebManagerPO implements Serializable{
	int webmanagerid;
	String name;
	String contact;
	String username;
	String password;
	
	public WebManagerPO(){
		webmanagerid=0;
		name=null;
		contact=null;
		username=null;
		password=null;
	}
	
	public WebManagerPO(int i,String n,String c,String u,String p){
		webmanagerid = i;
		name=n;
		contact=c;
		username=u;
		password=p;
//		id=i;
	}
	public int getwebmanagerid() {
		return webmanagerid;
	}
	public void setwebmanagerid(int webmanagerid) {
		this.webmanagerid = webmanagerid;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	
	public void setcontact(String contact) {
		this.contact = contact;
	}
	public String getcontact(){
		return contact;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	public String getusername() {
		return username;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	public Manage changetowebmanager(WebManagerPO po){
		Manage webmanager=new Manage();
		webmanager.setwebmanagerid(po.webmanagerid);
		webmanager.setname(po.name);
		webmanager.setcontact(po.contact);
		webmanager.setusername(po.username);
		webmanager.setpassword(po.password);
		return webmanager;
	}
	
	public WebManagerVO changetowebmanagervo(WebManagerPO po){
		WebManagerVO vo=new WebManagerVO();
		vo.setwebmanagerid(po.webmanagerid);
		vo.setname(po.name);
		vo.setcontact(po.contact);
		vo.setusername(po.username);
		vo.setpassword(po.password);
		return vo;
	}
}
