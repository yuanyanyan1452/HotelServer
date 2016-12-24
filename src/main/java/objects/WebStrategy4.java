package objects;

import java.util.Date;

public class WebStrategy4 extends WebStrategy implements Calculate{
	//关门大吉
	
		@Override
		public double calculate(int clientid, int hotelid, double price, int roomnumber) {
			Date nowdate=new Date();
//			if(nowdate.getTime()>start_time.getTime()&&nowdate.getTime()<end_time.getTime()){
			price*=0.3;
//			}
			return price;
		}
		
		
}
