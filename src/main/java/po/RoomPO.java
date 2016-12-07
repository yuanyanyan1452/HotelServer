package po;

import objects.Room;
import vo.RoomVO;

public class RoomPO {
	int id;
	int hotelid;
	String room_type;
	int total_num;
	int available_num;
	int price;
	
	public RoomPO(){
		id=0;
		hotelid=0;
		room_type=null;
		total_num=0;
		available_num=0;
		price=0;
	}
	
	public RoomPO(int i,int hid,String type,int total,int available,int p){
		id=i;
		hotelid=hid;
		room_type=type;
		total_num=total;
		available_num=available;
		price=p;
	}
	
	public void setid(int id){
		this.id=id;
	}
	public int getid(){
		return id;
	}
	
	public void sethotelid(int hotelid){
		this.hotelid=hotelid;
	}
	public int gethotelid(){
		return hotelid;
	}
	
	public void setroom_type(String room_type){
		this.room_type=room_type;
	}
	public String getroom_type(){
		return room_type;
	}
	
	public void settotal_num(int total_num){
		this.total_num=total_num;
	}
	public int gettotal_num(){
		return total_num;
	}
	
	public void setavailable_num(int available_num){
		this.available_num=available_num;
	}
	public int getavailable_num(){
		return available_num;
	}
	
	public void setprice(int price){
		this.price=price;
	}
	public int getprice(){
		return price;
	}
	
	public Room changetoroom(RoomPO po){
		Room room=new Room();
		room.setid(po.id);
		room.sethotelid(po.hotelid);
		room.setroom_type(po.room_type);
		room.settotal_num(po.total_num);
		room.setavailable_num(po.available_num);
		room.setprice(po.price);
		return room;
	}
	
	public RoomVO changetoroomvo(RoomPO po){
		RoomVO vo=new RoomVO();
		vo.setid(po.id);
		vo.sethotelid(po.hotelid);
		vo.setroom_type(po.room_type);
		vo.settotal_num(po.total_num);
		vo.setavailable_num(po.available_num);
		vo.setprice(po.price);
		return vo;
	}
}

