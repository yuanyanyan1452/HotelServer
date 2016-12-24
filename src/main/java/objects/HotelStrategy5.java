package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelStrategy5 extends HotelStrategy implements Calculate {
	// 双十一折扣
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		Date nowdate=new Date();
		try {
			start_time=fmt.parse(" 2016-11-11 00:00:00");
			end_time=fmt.parse("2016-11-11 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price *= 0.7;
		}
		return price;
	}

}
