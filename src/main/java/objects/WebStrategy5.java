package objects;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;

public class WebStrategy5 extends WebStrategy implements Calculate{
	//商圈折扣
	ClientBLService clientblservice=new ClientBLServiceImpl();
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Client client=clientblservice.checkClientInfo(clientid);
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
		double min_price=pricelist.get(0);
		for(int i=1;i<pricelist.size();i++){
			if(pricelist.get(i)<min_price){
				min_price=pricelist.get(i);
			}
		}
		
		return min_price;
	}

}
