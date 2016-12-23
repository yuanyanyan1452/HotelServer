package objects;

import java.util.Date;

public class HotelStrategy5 extends HotelStrategy implements Calculate {
	// 双十一折扣

	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		Date nowdate=new Date();
//		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price *= 0.7;
//		}
		return price;
	}

}
