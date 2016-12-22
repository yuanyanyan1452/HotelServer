package po;

import java.io.Serializable;

import objects.HotelWorker;
import vo.HotelWorkerVO;

public class HotelWorkerPO implements Serializable{
	int hotelid;
	String name;
	String contact;
	String username;
	String password;
	boolean logged;
	
	public HotelWorkerPO(){
		hotelid=0;
		name=null;
		contact=null;
		username=null;
		password=null;
		logged=false;
	}
	
	public HotelWorkerPO(int id,String n,String c,String u,String p){
		hotelid=id;
		name=n;
		contact=c;
		username=u;
		password=p;
		logged=false;
	}
	
	public int gethotelid() {
		return hotelid;
	}
	public void sethotelid(int hotelid) {
		this.hotelid = hotelid;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public String getname() {
		return name;
	}
	
	public void setcontact(String contact) {
		this.contact = contact;
	}
	public String getcontact() {
		return contact;
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
	
	public HotelWorker changetohotelworker(){
		HotelWorker hotelworker=new HotelWorker();
		hotelworker.sethotelid(this.hotelid);
		hotelworker.setname(this.name);
		hotelworker.setcontact(this.contact);
		hotelworker.setusername(this.username);
		hotelworker.setpassword(this.password);
		hotelworker.setlogged(this.logged);
		return hotelworker;
	}
	
	public HotelWorkerVO changetohotelworkervo(){
		HotelWorkerVO vo=new HotelWorkerVO();
		vo.sethotelid(this.hotelid);
		vo.setname(this.name);
		vo.setcontact(this.contact);
		vo.setusername(this.username);
		vo.setpassword(this.password);
		vo.setlogged(this.logged);
		return vo;
	}
	
}
