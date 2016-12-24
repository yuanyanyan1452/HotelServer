package objects;

import java.rmi.RemoteException;
import java.util.Date;

public class HotelStrategy4 extends HotelStrategy implements Calculate{
	//合作企业客户折扣
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Date nowdate=new Date();
//		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price*=0.8;
//		}
		return price;
	}

}
