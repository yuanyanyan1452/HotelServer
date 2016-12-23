package objects;

import java.rmi.RemoteException;

import service.blservice.ClientBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;

public class WebStrategy3 extends WebStrategy implements Calculate{
	//会员等级与商圈折扣
	ClientBLService clientblservice=new ClientBLServiceImpl();
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) throws RemoteException {
		ClientVO client=clientblservice.client_checkInfo(clientid);
		String vipinfo1=client.getvipinfo().getInfo();
		if(!vipinfo1.equals("")){
		String[] vipinfolist=vipinfo1.split(",");
		String viplevel=vipinfolist[0];
		price=calculatebyviplevel(price, viplevel);
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
