package vo;

public class HotelBasicInfoVO {
	int hotelid ;
	String name;
	int score; 
	int star;
	public HotelBasicInfoVO(int id,String na,int sc,int m){
		hotelid = id;
		name=na;
		score=sc;
		star=m;
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
	public void setscore(int m){
		score=m;
	}
	public int getcore(){
		return score;
	}
	public void setstar(int m){
		star=m;
	}
	public int getstar(){
		return star;
	}
	
}
