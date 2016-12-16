package objects;

import java.rmi.RemoteException;

public class HotelStrategy4 extends HotelStrategy implements Calculate{
	//合作企业客户折扣
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		price*=0.8;
		return price;
	}

}
