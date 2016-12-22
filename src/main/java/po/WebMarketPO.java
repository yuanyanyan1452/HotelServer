package po;

import java.io.Serializable;

import objects.WebMarket;
import vo.WebMarketVO;

public class WebMarketPO implements Serializable{
	int webmarketid;
	String name;
	String contact;
	String username;
	String password;
	boolean logged;
	
	public WebMarketPO(){
		webmarketid = 0;
		name=null;
		contact=null;
		username=null;
		password=null;
		logged=false;
	}
	
	public WebMarketPO(int id,String n,String c,String u,String p){
		webmarketid = id;
		name=n;
		contact=c;
		username=u;
		password=p;
		logged=false;
	}

	public int getwebmarketid() {
		return webmarketid;
	}

	public void setwebmarketid(int webmarketid) {
		this.webmarketid = webmarketid;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact = contact;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public boolean getlogged() {
		return this.logged;
	}

	public void setlogged(boolean logged) {
		this.logged = logged;
	}
	
	public WebMarket changetowebmarket(){
		WebMarket webmarket=new WebMarket();
		webmarket.setwebmarketid(this.webmarketid);
		webmarket.setname(this.name);
		webmarket.setcontact(this.contact);
		webmarket.setusername(this.username);
		webmarket.setpassword(this.password);
		webmarket.setlogged(this.logged);
		return webmarket;
	}
	
	public WebMarketVO changetowebmarketvo(){
		WebMarketVO vo=new WebMarketVO();
		vo.setwebmarketid(this.webmarketid);
		vo.setname(this.name);
		vo.setcontact(this.contact);
		vo.setusername(this.username);
		vo.setpassword(this.password);
		vo.setlogged(this.logged);
		return vo;
	}
	
	
}
