package objects;

import java.rmi.RemoteException;

public interface Calculate {
	public double calculate(int clientid,int hotelid,double price,int roomnumber) throws RemoteException;
}
