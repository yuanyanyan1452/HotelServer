package objects;

import java.rmi.RemoteException;

public class HotelStrategy3 extends HotelStrategy implements Calculate{
	//三间及以上预订优惠
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		// TODO Auto-generated method stub
		if(roomnumber>=3){
		price*=0.9;
		}
		return price;
	}

}
