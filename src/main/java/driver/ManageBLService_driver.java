package driver;



import java.rmi.RemoteException;
import java.util.ArrayList;
import vo.*;
import service.blservice.*;
import objects.*;
import objects.VIPInfo.VIPType;



public class ManageBLService_driver {

	ResultMessage resultMessage=ResultMessage.Fail;
	public void drive(ManageBLService manageBLService){

		//webmanager_login
		try {
			resultMessage = manageBLService.webmanager_login("sdad", "0000");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		
		//webmarket_login
		try {
			resultMessage = manageBLService.webmarket_login("beauty", "23333");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		
		//webmarket_change_password
		try {
			resultMessage = manageBLService.webmarket_change_password("beauty", "23333", "233");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("密码修改成功");
		}else{
			System.out.println("密码修改失败");
		}
		
		//webmanager_change_password
		try {
			resultMessage=manageBLService.webmanager_change_password("sdad", "0000", "000");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("密码修改成功");
		}else{
			System.out.println("密码修改失败");
		}
		
		//webmarket_getvo
		WebMarketVO market = null;
		try {
			market = manageBLService.webmarket_getvo("beauty");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(market.getwebmarketid());
		System.out.println(market.getname());
		System.out.println(market.getcontact());
		System.out.println(market.getusername());
		
		//webmanager_getvo
		WebManagerVO manager = null;
		try {
			manager = manageBLService.webmanager_getvo("sdad");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(manager.getwebmanagerid());
		System.out.println(manager.getname());
		System.out.println(manager.getcontact());
		System.out.println(manager.getusername());
		
		//getallclientvo
		ArrayList<ClientVO> allclient = null;
		try {
			allclient = manageBLService.getallclientvo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(allclient);
		
		//getallhotelvo
		ArrayList<HotelVO> allhotel = null;
		try {
			allhotel = manageBLService.getallhotelvo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(allhotel);
		
		//getallhotelworkervo
		ArrayList<HotelWorkerVO> allhotelWorker = null;
		try {
			allhotelWorker = manageBLService.getallhotelworkervo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(allhotelWorker);
		
		//getallwebmarketvo
		ArrayList<WebMarketVO> allwebMarket = null;
		try {
			allwebMarket = manageBLService.getallwebmarketvo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(allwebMarket);
		
		//getordernumber
		int ordernum = 0;
		try {
			ordernum = manageBLService.getordernumber();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ordernum);
		
		//manage_searchClientByClientid
		ClientVO searchClientByid = null;
		try {
			searchClientByid = manageBLService.manage_searchClientByClientid(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(searchClientByid.getclientid());
		System.out.println(searchClientByid.getclient_name());
		System.out.println(searchClientByid.getcontact());
		System.out.println(searchClientByid.getusername());
		
		//manage_updateClient
		ArrayList<String> update_creditrecord=new ArrayList<String>();
		update_creditrecord.add("2016-11-12 12:05:39,1,订单执行，1050,2500");
		update_creditrecord.add("2016-11-21 07:00:00,3,订单执行，2800,4800");
		update_creditrecord.add("2016-12-01 10:00:00,9,订单执行，500,5300");
		update_creditrecord.add("2016-12-25 20:05:38,16,订单自动置为异常,-3613,1187");
		update_creditrecord.add("2016-12-26 18:56:25,15,订单自动置为异常,-2466,-1279");
		VIPInfo updateInfo=new VIPInfo();
		updateInfo.setType(VIPType.Enterprise);
		updateInfo.setInfo("update_creditrecord");
		ClientVO updateclient=new ClientVO(1,"xijinping","89",-1279,update_creditrecord,updateInfo,"relate","chinese");
		try {
			resultMessage = manageBLService.manage_updateClient(updateclient);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("更新客户信息成功");
		}else{
			System.out.println("更新用户信息失败");
		}
		
		//manage_addHotel
		ArrayList<String> add_evalu=new ArrayList<String>();
		add_evalu.add("nice");
		add_evalu.add("good");
		ArrayList<Integer>add_book=new ArrayList<Integer>();
		add_book.add(2);
		add_book.add(6);
		HotelVO addHotel=new HotelVO(22,"新增酒店","上海市陆家嘴","陆家嘴","五星级假日酒店","wifi,餐饮,停车,洗浴","五星级","4.6,36",add_evalu,add_book);
		try {
			resultMessage = manageBLService.manage_addHotel(addHotel);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("增加酒店成功");
		}else{
			System.out.println("增加酒店失败");
		}
		
		//manage_addHotelWorker
		HotelWorkerVO addWorker=new HotelWorkerVO(18,"222","222","222","222");
		try {
			resultMessage = manageBLService.manage_addHotelWorker(addWorker);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("增加酒店工作人员成功");
		}else{
			System.out.println("增加酒店工作人员失败");
		}
		
		//manage_searchHotelWorkerByHotelid
		try {
			HotelWorkerVO searchByhotelid=manageBLService.manage_searchHotelWorkerByHotelid(1);
			System.out.println(searchByhotelid.getname());
			System.out.println(searchByhotelid.getcontact());
			System.out.println(searchByhotelid.getusername());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage_updateHotelWorker
		HotelWorkerVO updateWorker=new HotelWorkerVO(1,"John","1111","Matin","ddl");
		try {
			resultMessage=manageBLService.manage_updateHotelWorker(updateWorker);
			if(resultMessage==ResultMessage.Success){
				System.out.println("更新酒店工作人员信息成功");
			}else{
				System.out.println("更新酒店工作人员信息失败");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage_addMarketWorker
		WebMarketVO addMarket=new WebMarketVO(5,"555","555","555","555");
		try {
			resultMessage=manageBLService.manage_addMarketWorker(addMarket);
			if(resultMessage==ResultMessage.Success){
				System.out.println("增加网站营销人员信息成功");
			}else{
				System.out.println("增加网站营销人员信息失败");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage_searchMarketWorkerByWebmarketid
		try {
			WebMarketVO searchMarketByid=manageBLService.manage_searchMarketWorkerByWebmarketid(1);
			System.out.println(searchMarketByid.getname());
			System.out.println(searchMarketByid.getcontact());
			System.out.println(searchMarketByid.getusername());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//manage_updateMarketWorker
		WebMarketVO updateMarket=new WebMarketVO(5,"666","555","555","555");
		try {
			resultMessage=manageBLService.manage_updateMarketWorker(updateMarket);
			if(resultMessage==ResultMessage.Success){
				System.out.println("更新网站营销人员信息成功");
			}else{
				System.out.println("更新网站营销人员信息失败");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}