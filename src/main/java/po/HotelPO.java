package po;

import java.io.Serializable;
import java.util.ArrayList;

import objects.Hotel;
import vo.HotelVO;

public class HotelPO implements Serializable{
	int hotelid ;
	String name;
	String address;
	String bussiness_address;
	String introduction;
	String service;
	String star;
	String score;
	ArrayList<String>hotel_evaluation;
	ArrayList<Integer> book_clientid;
	
	public HotelPO(){
		hotelid=0;
		address=null;
		bussiness_address=null;
		name=null;
		introduction=null;
		service=null;
		star=null;
		score=null;
		hotel_evaluation=null;
		book_clientid=null;
	}
	
	public HotelPO(int id,String na,String a,String ba,String in,String s,String star,String score,ArrayList<String>e,ArrayList<Integer>book){
		hotelid = id;
		name=na;
		address=a;
		bussiness_address=ba;
		introduction=in;
		service=s;
		this.star=star;
		this.score=score;
		hotel_evaluation=e;
		book_clientid=book;
	}
	
	public void setid(int id){
		hotelid = id;
	}
	public int getid(){
		return hotelid;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
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
	
	public void setstar(String star){
		this.star = star;
	}
	public String getstar(){
		return star;
	}
	
	public void setscore(String score){
		this.score = score;
	}
	public String getscore(){
		return score;
	}
	
	public void sethotel_evaluation(ArrayList<String>evalu){
		hotel_evaluation = evalu;
	}
	public ArrayList<String> gethotel_evaluation(){
		return hotel_evaluation;
	}
 
	public void setbook_clientid(ArrayList<Integer>book){
		book_clientid=book;
	}
	public ArrayList<Integer> getbook_clientid(){
		return book_clientid;
	}
	public HotelVO changetohotelvo(HotelPO po){
		HotelVO vo = new HotelVO();
		vo.setid(po.getid());
		vo.setname(name);
		vo.setaddress(po.getaddress());
		vo.setbussiness_address(bussiness_address);
		vo.setintroduction(introduction);
		vo.setservice(service);
		vo.setstar(star);
		vo.setscore(score);
		vo.sethotel_evaluation(hotel_evaluation);
		vo.setbook_clientid(book_clientid);
		return vo;
	}

	

	public Hotel changetohotel(HotelPO po){
		Hotel hotel = new Hotel();
		hotel.setid(po.getid());
		hotel.setname(name);
		hotel.setaddress(address);
		hotel.setbussiness_address(bussiness_address);
		hotel.setintroduction(introduction);
		hotel.setservice(service);
		hotel.setstar(star);
		hotel.setscore(score);
		hotel.setevaluation(hotel_evaluation);
		hotel.setbook_clientid(book_clientid);
		return hotel;
	}
}
