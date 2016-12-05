package po;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPO implements Serializable{
	int hotelid ;
	String name;
	String address;
	String bussiness_address;
	String introduction;
	String service;
	String star;
	int score;
	String hotel_evaluation;
	int min_price;
	ArrayList<Integer> book_clientid;
	
	public HotelPO(){
		hotelid=0;
		address=null;
		bussiness_address=null;
		name=null;
		introduction=null;
		service=null;
		star=null;
		score=0;
		hotel_evaluation=null;
		book_clientid=null;
	}
	
	public HotelPO(int id,String na,String a,String ba,String in,String s,String star,int score,String e,ArrayList<Integer>book){
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
	
	public void setscore(int score){
		this.score = score;
	}
	public int getscore(){
		return score;
	}
	
	public void setevaluation(String evalu){
		hotel_evaluation = evalu;
	}
	public String getevaluation(){
		return hotel_evaluation;
	}
	
	public void setmin_price(int p){
		min_price=p;
	}
	public int getmin_price(){
		return min_price;
	}
 
	public void setbook_clientid(ArrayList<Integer>book){
		book_clientid=book;
	}
	public ArrayList<Integer> getbook_clientid(){
		return book_clientid;
	}
}
