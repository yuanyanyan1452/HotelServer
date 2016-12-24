package objects;

import java.rmi.RemoteException;
import java.util.Date;

public class HotelStrategy1 extends HotelStrategy implements Calculate{
	//开业酬宾
	@Override
	
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
//		Date nowdate=new Date();
//		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		price*=0.9;
//		}
		return price;
	}

}
