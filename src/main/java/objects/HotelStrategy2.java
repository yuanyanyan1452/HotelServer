package objects;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;

public class HotelStrategy2 extends HotelStrategy implements Calculate{
	//生日特惠折扣
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
	ClientBLService clientblservice=new ClientBLServiceImpl();
	
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		try {
			start_time=fmt.parse("2000-01-01 00:00:00 ");
			end_time=fmt.parse("2100-12-30 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ClientVO client=clientblservice.client_checkInfo(clientid);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		Date date=new Date();
		if(date.after(start_time)&&date.before(end_time)){
		String time=format.format(date);
		if(client.getvipinfo()!=null){
			String [] vipinfo=client.getvipinfo().getInfo().split(",");
			if(!vipinfo[1].equals("")&&vipinfo[1].equals(time)){
				price*=0.5;
			}
		}
		}
		return price;
	}

}
