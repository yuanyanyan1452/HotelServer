package po;

import java.io.Serializable;

public class HotelPO implements Serializable{
	int hotelid ;
	String address;
	String bussiness_address;
	String name;
	String introduction;
	String service;
	String evaluation;
	int mark;
	
	public HotelPO(){
		hotelid=0;
		address=null;
		bussiness_address=null;
		name=null;
		introduction=null;
		service=null;
		evaluation=null;
		mark=0;
	}
	
	public HotelPO(int id,String a,String ba,String na,String in,String s,String e,int m){
		hotelid = id;
		address=a;
		bussiness_address=ba;
		name=na;
		introduction=in;
		service=s;
		evaluation=e;
		mark=m;
	}
	
	public void setid(int id){
		hotelid = id;
	}
	public int getid(){
		return hotelid;
	}
	
	public void setaddress(String addr){
		address = addr;
	}
	public String getaddress(){
		return address;
	}
	
	public void setbussiness_address(String bussi_addr){
		bussiness_address = bussi_addr;
	}
	public String getbussiness_address(){
		return bussiness_address;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	public void setintroduction(String intro){
		introduction = intro;
	}
	public String getintroduction(){
		return introduction;
	}
	
	public void setservice(String service){
		this.service = service;
	}
	public String getservice(){
		return service;
	}
	
	public void setevaluation(String evalu){
		evaluation = evalu;
	}
	public String getevaluation(){
		return evaluation;
	}
	
	public void setmark(int m){
		mark=m;
	}
	public int getmark(){
		return mark;
	}
 
}
