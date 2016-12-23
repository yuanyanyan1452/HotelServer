package objects;

import java.rmi.RemoteException;
import java.util.Date;

public class HotelStrategy3 extends HotelStrategy implements Calculate{
	//三间及以上预订优惠
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Date nowdate=new Date();
//		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		if(roomnumber>=3){
		price*=0.9;
		}
//		}
		return price;
	}

}
