package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BL {
	
	public String get_current_time(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	
	public void auto_change_ordersgtate(){
//		ArrayList<OrderPO> orderpo_list=
	}
}
