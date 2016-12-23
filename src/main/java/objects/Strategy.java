package objects;

import java.rmi.RemoteException;

public class Strategy {

	private Calculate calculate;
	
	public Strategy(Calculate calculate){
		this.calculate=calculate;
	}
	
	public void setcalculate(Calculate calculate){
		this.calculate=calculate;
	}
	
	public double getprice(int clientid,int hotelid,double price,int roomnumber){
		double p=price;
		try {
			p=calculate.calculate(clientid, hotelid, price, roomnumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return p;
	}
}
