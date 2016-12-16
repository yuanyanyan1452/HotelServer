package objects;

import java.rmi.RemoteException;

public class HotelStrategy1 extends HotelStrategy implements Calculate{
	//开业酬宾
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		price*=0.9;
		return price;
	}

}
