package logiccontroller;


import vo.*;
import service.blservice.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.*;
import objects.VIPInfo.VIPType;

public class ManageController implements ManageBLService{
	//客户信息
		int clientid=3;
		String client_name="glance";
		String client_contact="5656";
		int client_credit=300;
		ArrayList<String>creditrecord=new ArrayList<String>();
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"二级会员,05-13,鼓楼");
		String client_username="sing";
		String client_password="appreciate";
		boolean client_logged=false;
		
		//酒店信息
		int hotelid=1;
		String hotel_name="天丰大酒店";
		String address = "南京市白下区洪武路26号";
		String business_address = "新街口";
		String introduction = "四星级商务酒店";
		String service = "wifi,餐饮,停车";
		String star="四星级";
		String score="4.2,20";
		ArrayList<String>hotel_evaluation=new ArrayList<String>();
		ArrayList<Integer>book_clientid=new ArrayList<Integer>();
		
		//酒店工作人员信息
		String hotelWorker_name="John";
		String hotelWorker_contact="1111";
		String hotelWorker_username="Matin";
		String hotelWorker_password="ddm";
		boolean hotelWorker_logged=false;
		
		//网站营销人员信息
		int webmarketid=1;
		String marketWorker_name="xjw";
		String marketWorker_contact="5678";
		String marketWorker_username="beauty";
		String marketWorker_password="23333";
		boolean marketWorker_logged=false;
		
		//网站管理人员信息
		int managerid=1;
		String manager_name="David";
		String manager_contact="4648";
		String manager_username="sdad";
		String manager_password="0000";
		boolean manager_logged=false;
		
		@Override
		public ResultMessage webmanager_login(String username, String password) throws RemoteException {
			// TODO Auto-generated method stub
			if(username.equals(manager_name)&&password.equals(manager_password)&&!manager_logged){
				return ResultMessage.Success;
			}else{
				return ResultMessage.Fail;
			}
		}
		@Override
		public ResultMessage webmarket_login(String username, String password) throws RemoteException {
			// TODO Auto-generated method stub
			if(username.equals(marketWorker_username)&&password.equals(marketWorker_password)&&!marketWorker_logged){
				return ResultMessage.Success;
			}else{
				return ResultMessage.Fail;
			}
		}
		@Override
		public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
				throws RemoteException {
			// TODO Auto-generated method stub
			if(username.equals(marketWorker_username)&&oldpassword.equals(marketWorker_password)){
				marketWorker_password=newpassword;
				return ResultMessage.Success;
			}else{
				return ResultMessage.Fail;
			}
		}
		@Override
		public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
				throws RemoteException {
			// TODO Auto-generated method stub
			if(username.equals(manager_username)&&oldpassword.equals(manager_password)){
				manager_password=newpassword;
				return ResultMessage.Success;
			}else{
				return ResultMessage.Fail;
			}
		}
		@Override
		public WebMarketVO webmarket_getvo(String username) throws RemoteException {
			// TODO Auto-generated method stub
			WebMarketVO marketVO=new WebMarketVO();
			if(username.equals(marketWorker_username)){
				marketVO=new WebMarketVO(webmarketid,marketWorker_name,marketWorker_contact,
						marketWorker_username,marketWorker_password);
			}
			return marketVO;
		}
		@Override
		public WebManagerVO webmanager_getvo(String username) throws RemoteException {
			// TODO Auto-generated method stub
			WebManagerVO managerVO=new WebManagerVO();
			if(username.equals(manager_username)){
				managerVO=new WebManagerVO(managerid,manager_name,manager_contact,
						manager_username,manager_password);
			}
			return managerVO;
		}
		@Override
		public ArrayList<ClientVO> getallclientvo() throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<ClientVO> allClient=new ArrayList<ClientVO>();
			ClientVO client=new ClientVO(clientid,client_name,client_contact,client_credit,creditrecord,
					info,client_username,client_password);
			allClient.add(client);
			return allClient;
		}
		@Override
		public ArrayList<HotelVO> getallhotelvo() throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> allHotel=new ArrayList<HotelVO>();
			HotelVO hotel=new HotelVO(hotelid,hotel_name,address,business_address,introduction,service,star,score,hotel_evaluation,book_clientid);
			allHotel.add(hotel);
			return allHotel;
		}
		@Override
		public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelWorkerVO>allHotelWorker=new ArrayList<HotelWorkerVO>();
			HotelWorkerVO hotelWorker=new HotelWorkerVO(hotelid,hotelWorker_name,hotelWorker_contact,hotelWorker_username,hotelWorker_password);
			allHotelWorker.add(hotelWorker);
			return allHotelWorker;
		}
		@Override
		public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<WebMarketVO>allWebMarket=new ArrayList<WebMarketVO>();
			WebMarketVO WebMarket=new WebMarketVO(webmarketid,marketWorker_name,marketWorker_contact,marketWorker_username,marketWorker_password);
			allWebMarket.add(WebMarket);
			return allWebMarket;
		}
		@Override
		public int getordernumber() throws RemoteException {
			// TODO Auto-generated method stub
			int ordernumber=14;
			return ordernumber;
		}
		@Override
		public ClientVO manage_searchClientByClientid(int clientid) throws RemoteException {
			// TODO Auto-generated method stub
			if(clientid==3){
				return new ClientVO(clientid,client_name,client_contact,client_credit,creditrecord,info,client_username,client_password);
			}
			return null;
		}
		@Override
		public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException {
			// TODO Auto-generated method stub
			if(clientvo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelvo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage manage_addHotelWorker(HotelWorkerVO hotelworkervo) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelworkervo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public HotelWorkerVO manage_searchHotelWorkerByHotelid(int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid){
				return new HotelWorkerVO(hotelid,hotelWorker_name,hotelWorker_contact,hotelWorker_username,hotelWorker_password);
			}
			return null;
		}
		@Override
		public ResultMessage manage_updateHotelWorker(HotelWorkerVO hotelworkervo) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelworkervo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage manage_addMarketWorker(WebMarketVO webmarketvo) throws RemoteException {
			// TODO Auto-generated method stub
			if(webmarketvo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public WebMarketVO manage_searchMarketWorkerByWebmarketid(int marketWorkerid) throws RemoteException {
			// TODO Auto-generated method stub
			if(marketWorkerid==webmarketid){
				return new WebMarketVO(webmarketid,marketWorker_name,marketWorker_contact,marketWorker_username,marketWorker_password);
			}
			return null;
		}
		@Override
		public ResultMessage manage_updateMarketWorker(WebMarketVO webmarketvo) throws RemoteException {
			// TODO Auto-generated method stub
			if(webmarketvo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}

		
}
