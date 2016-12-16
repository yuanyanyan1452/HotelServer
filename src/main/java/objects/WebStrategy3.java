package objects;

import java.rmi.RemoteException;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;

public class WebStrategy3 extends WebStrategy implements Calculate{
	//会员等级与商圈折扣
	ClientBLService clientblservice=new ClientBLServiceImpl();
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		Client client=clientblservice.checkClientInfo(clientid);
		String[] vipinfo=client.getvipinfo().getInfo().split(",");
		String viplevel=vipinfo[0];
		price=calculatebyviplevel(price, viplevel);
		return 0;
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
