package objects;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.experimental.theories.Theories;

public class HotelStrategy1 implements Calculate{
	//开业酬宾
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
	
	@Override
	
	
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		try {
			start_time=fmt.parse(" 2016-10-14 00:00:00");
			end_time=fmt.parse(" 2016-10-20 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date nowdate=new Date();
		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price*=0.9;
		}
		return price;
	}

}
