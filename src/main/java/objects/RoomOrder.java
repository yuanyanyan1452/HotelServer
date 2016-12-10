package objects;

public class RoomOrder {
	String room_type;
	int room_number;
	int num_of_days;
	
	public RoomOrder(){
		room_type=null;
		room_number=0;
		num_of_days=0;
	}
	
	public RoomOrder(String t,int n,int num){
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
}
