package po;

import java.io.Serializable;

public class HotelPO implements Serializable{
	int hotelid ;
	String address;
	String bussiness_address;
	String name;
	String introduction;
	String service;
	String room_state;
	String room_type;
	int room_number;
	int room_price;
	String order;
	String evaluation;
	
	public HotelPO(int id,String a,String ba,String na,String in,String s,String rs,String rt,int rn,int rp,String o,String e){
		hotelid = id;
		address=a;
		bussiness_address=ba;
		name=na;
		introduction=in;
		service=s;
		room_state=rt;
		room_type=rt;
		room_number=rn;
		room_price=rp;
		order=o;
		evaluation=e;
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
	
	public void setroom_state(String state){
		room_state = state;
	}
	public String getroom_state(){
		return room_state;
	}
	
	public void setroom_type(String type){
		room_type = type;
	}
	public String getroom_type(){
		return room_type;
	}
	
	public void setroom_number(int number){
		room_number = number;
	}
	public int getroom_number(){
		return room_number;
	}
	
	public void setroom_price(int price){
		room_price = price;
	}
	public int getroom_price(){
		return room_price;
	}
	
	public void setorder(String hotel_order){
		order = hotel_order;
	}
	public String getorder(){
		return order;
	}
	
	public void setevaluation(String evalu){
		evaluation = evalu;
	}
	public String getevaluation(){
		return evaluation;
	}
}
