package objects;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;

public class WebStrategy5 extends WebStrategy implements Calculate{
	//商圈折扣
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
		if(client.getvipinfo()!=null){
			String [] vipinfo=client.getvipinfo().info.split(",");
			ArrayList<Double> pricelist=new ArrayList<Double>();
			for(int i=2;i<vipinfo.length;i++){
				switch(vipinfo[i]){
				case "新街口":
				case "学则路":
					pricelist.add(price*0.9);
					break;
				case "仙林中心":
				case "玄武路":
					pricelist.add(price*0.8);
					break;
				default:
					pricelist.add(price);
				}
			}
		
		
		if(!pricelist.isEmpty()){
			price=pricelist.get(0);
			for(int i=1;i<pricelist.size();i++){
				if(pricelist.get(i)<price){
					price=pricelist.get(i);
				}
			}
		}
		}
		}
		return price;
	}

}
