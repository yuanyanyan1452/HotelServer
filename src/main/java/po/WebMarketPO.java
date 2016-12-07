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
	
	public WebMarketPO(){
		webmarketid = 0;
		name=null;
		contact=null;
		username=null;
		password=null;
	}
	
	public WebMarketPO(int id,String n,String c,String u,String p){
		webmarketid = id;
		name=n;
		contact=c;
		username=u;
		password=p;
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
	
	public WebMarket changetowebmarket(WebMarketPO po){
		WebMarket webmarket=new WebMarket();
		webmarket.setwebmarketid(po.webmarketid);
		webmarket.setname(po.name);
		webmarket.setcontact(po.contact);
		webmarket.setusername(po.username);
		webmarket.setpassword(po.password);
		return webmarket;
	}
	
	public WebMarketVO changetowebmarketvo(WebMarketPO po){
		WebMarketVO vo=new WebMarketVO();
		vo.setwebmarketid(po.webmarketid);
		vo.setname(po.name);
		vo.setcontact(po.contact);
		vo.setusername(po.username);
		vo.setpassword(po.password);
		return vo;
	}
	
	
}
