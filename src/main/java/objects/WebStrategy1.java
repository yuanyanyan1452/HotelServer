package objects;

import java.util.Date;

public class WebStrategy1 extends WebStrategy implements Calculate {
	//开业酬宾
	
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		Date nowdate=new Date();
//		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price*=0.9;
//		}
		return price;
	}


}
