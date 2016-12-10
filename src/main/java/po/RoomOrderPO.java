package po;

import objects.RoomOrder;
import vo.RoomOrderVO;

public class RoomOrderPO {
	String room_type;
	int room_number;
	int num_of_days;
	
	public RoomOrderPO(){
		room_type=null;
		room_number=0;
		num_of_days=0;
	}
	
	public RoomOrderPO(String t,int n,int num){
		room_type=t;
		room_number=n;
		num_of_days=num;
	}
	
	public void setroom_type(String t){
		room_type=t;
	}
	public String getroom_type(){
		return room_type;
	}
	
	public void setroom_number(int n){
		room_number=n;
	}
	public int getroom_number(){
		return room_number;
	}
	
	public void setnum_of_days(int num){
		num_of_days=num;
	}
	public int getnum_of_days(){
		return num_of_days;
	}
	
	public RoomOrder changetoroomorder(){
		RoomOrder roomorder = new RoomOrder();
		roomorder.setroom_number(this.room_number);
		roomorder.setroom_type(this.room_type);
		roomorder.setnum_of_days(this.num_of_days);
		return roomorder;
	}
	
	public RoomOrderVO changetoroomordervo(){
		RoomOrderVO vo = new RoomOrderVO();
		vo.setroom_number(this.room_number);
		vo.setroom_type(this.room_type);
		vo.setnum_of_days(this.num_of_days);
		return vo;
		
	}
}
