package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebStrategy4 extends WebStrategy implements Calculate{
	//关门大吉
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
		@Override
		public double calculate(int clientid, int hotelid, double price, int roomnumber) {
			Date nowdate=new Date();
			try {
				start_time=fmt.parse("2000-01-01 00:00:00");
				end_time=fmt.parse("2000-12-30 00:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(nowdate.getTime()>start_time.getTime()&&nowdate.getTime()<end_time.getTime()){
			price*=0.3;
			}
			return price;
		}
		
		
}
