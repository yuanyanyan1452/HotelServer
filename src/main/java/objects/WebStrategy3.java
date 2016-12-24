package objects;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;

public class WebStrategy3 extends WebStrategy implements Calculate{
	//会员等级与商圈折扣
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date start_time;
	Date end_time;
	ClientBLService clientblservice=new ClientBLServiceImpl();
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Date nowdate=new Date();
		try {
			start_time=fmt.parse("2000-01-01 00:00:00");
			end_time=fmt.parse("2100-12-30 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(nowdate.after(start_time)&&nowdate.before(end_time)){
		ClientVO client=clientblservice.client_checkInfo(clientid);
		String vipinfo1=client.getvipinfo().getInfo();
		if(!vipinfo1.equals("")){
		String[] vipinfolist=vipinfo1.split(",");
		String viplevel=vipinfolist[0];
		price=calculatebyviplevel(price, viplevel);
		}
		}
		return price;
	}

	public double calculatebyviplevel(double price,String viplevel){
		if(viplevel.equals("一级会员")){
			price*=0.9;
		}
		else if(viplevel.equals("二级会员")){
			price*=0.8;
		}
		else if(viplevel.equals("三级会员")){
			price*=0.7;
		}
		return price;
	}
}
