package objects;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;

public class HotelStrategy2 extends HotelStrategy implements Calculate{
	//生日特惠折扣
	ClientBLService clientblservice=new ClientBLServiceImpl();
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		ClientVO client=clientblservice.client_checkInfo(clientid);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Date date=new Date();
//		if(date.after(start_time)&&date.before(end_time)){
		String time=format.format(date);
		String [] vipinfo=client.getvipinfo().getInfo().split(",");
		if(vipinfo[1]!=""&&vipinfo[1].equals(time)){
			price*=0.5;
		}
//		}
		return price;
	}

}
