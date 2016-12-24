package objects;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelStrategy4 extends HotelStrategy implements Calculate{
	//合作企业客户折扣
	
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Date nowdate=new Date();
		try {
			start_time=fmt.parse("2000-01-01 00:00:00");
			end_time=fmt.parse(" 2100-12-30 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price*=0.8;
		}
		return price;
	}

}
