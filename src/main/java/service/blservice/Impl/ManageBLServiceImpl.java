package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.HotelWorker;
import objects.ObjectChange;
import objects.ResultMessage;
import po.ClientPO;
import po.HotelPO;
import po.HotelWorkerPO;
import po.WebManagerPO;
import po.WebMarketPO;
import service.VOChange;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.blservice.ManageBLService;
import service.datafactory.datafactory;
import service.datafactory.datafactoryImpl;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

public class ManageBLServiceImpl implements ManageBLService {
	datafactory datafactory=new datafactoryImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	ClientBLService clientblservice=new ClientBLServiceImpl();
	VOChange vochange=new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage webmanager_login(String username, String password) throws RemoteException {
		ResultMessage result=datafactory.getManageDataService().checkWebManager(username, password);
		return result;
	}

	@Override
	public ResultMessage webmarket_login(String username, String password) throws RemoteException {
		ResultMessage result=datafactory.getManageDataService().checkWebMarket(username, password);
		return result;
	}
	
	@Override
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		WebMarketPO webmarketpo=datafactory.getManageDataService().getwebmarketpo(username, oldpassword);
		webmarketpo.setpassword(newpassword);
		ResultMessage result=datafactory.getManageDataService().updateWebMarket(webmarketpo);
		return result;
	}

	@Override
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		WebManagerPO webmanagerpo=datafactory.getManageDataService().getwebmanagerpo(username, oldpassword);
		webmanagerpo.setpassword(newpassword);
		ResultMessage result=datafactory.getManageDataService().updateWebManager(webmanagerpo);
		return result;
	}
	
	@Override
	public ArrayList<ClientVO> getallclientvo() throws RemoteException {
		ArrayList<ClientPO> clientpo_list=datafactory.getClientDataService().getallclientPO();
		ArrayList<ClientVO> clientvo_list=new ArrayList<ClientVO>();
		for(int i=0;i<clientpo_list.size();i++){
			ClientVO clientvo=clientpo_list.get(i).changetoclientvo();
			clientvo_list.add(clientvo);
		}
		return clientvo_list;
	}

	@Override
	public ArrayList<HotelVO> getallhotelvo() throws RemoteException {
		ArrayList<HotelPO> hotelpo_list=datafactory.getHotelDataService().getAllHotelPO();
		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
		for(int i=0;i<hotelpo_list.size();i++){
			HotelVO hotelvo=hotelpo_list.get(i).changetohotelvo();
			hotelvo_list.add(hotelvo);
		}
		return hotelvo_list;
	}

	@Override
	public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException {
		ArrayList<HotelWorkerPO> hotelworkerpo_list=datafactory.getHotelWorkerDataService().getallhotelworkerPO();
		ArrayList<HotelWorkerVO> hotelworkervo_list=new ArrayList<HotelWorkerVO>();
		for(int i=0;i<hotelworkerpo_list.size();i++){
			HotelWorkerVO hotelworkervo=hotelworkerpo_list.get(i).changetohotelworkervo();
			hotelworkervo_list.add(hotelworkervo);
		}
		return hotelworkervo_list;
	}

	@Override
	public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException {
		ArrayList<WebMarketPO> webmarketpo_list=datafactory.getManageDataService().getallwebmarketPO();
		ArrayList<WebMarketVO> webmarketvo_list=new ArrayList<WebMarketVO>();
		for(int i=0;i<webmarketpo_list.size();i++){
			WebMarketVO webmarketvo=webmarketpo_list.get(i).changetowebmarketvo();
			webmarketvo_list.add(webmarketvo);
		}
		return webmarketvo_list;
	}
	
	@Override
	public int getordernumber() throws RemoteException{
		int num=datafactory.getOrderDataService().getAllOrder();
		return num;
	}
	
	@Override
	public ClientVO manage_searchClientByClientid(int clientid) throws RemoteException{
		Client client= clientblservice.checkClientInfo(clientid);
		ClientVO clientvo=objectchange.changetoclientvo(client);
		return clientvo;
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException{
		Client client=vochange.clientvo_to_client(clientvo);
		ResultMessage result=clientblservice.updateClientInfo(client);
		return result;
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException{
		Hotel hotel=vochange.hotelvo_to_hotel(hotelvo);
		ResultMessage result=hotelblservice.addHotel(hotel);
		return result;
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO workervo) throws RemoteException{
		HotelWorker hotelworker=vochange.hotelworkervo_to_hotelworker(workervo);
		ResultMessage result=hotelblservice.addHotelWorker(hotelworker);
		return result;
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorkerByHotelid(int hotelid) throws RemoteException{
		HotelWorker hotelworker=hotelblservice.searchHotelWorker(hotelid);
		HotelWorkerVO hotelworkervo=objectchange.changetohotelworkervo(hotelworker);
		return hotelworkervo;
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO worker) throws RemoteException{
		HotelWorker hotelworker=vochange.hotelworkervo_to_hotelworker(worker);
		ResultMessage result=hotelblservice.updateHotelWokerInfo( hotelworker);
		return result;
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO webmarketvo) {
		WebMarketPO po=vochange.marketvo_to_marketpo(webmarketvo);
		ResultMessage result=datafactory.getManageDataService().insertWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO manage_searchMarketWorkerByWebmarketid(int marketWorkerid) {
		WebMarketPO webmarketpo=datafactory.getManageDataService().findWebMarket(marketWorkerid);
		WebMarketVO webmarketvo=webmarketpo.changetowebmarketvo();		return webmarketvo;
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) {
		WebMarketPO po=vochange.marketvo_to_marketpo(mw);
		ResultMessage result=datafactory.getManageDataService().updateWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO webmarket_getvo(String username) throws RemoteException {
		int id=datafactory.getManageDataService().findWebMarketIDbyUsername(username);
		WebMarketPO webmarketpo=datafactory.getManageDataService().findWebMarket(id);
		WebMarketVO vo=webmarketpo.changetowebmarketvo();
		return vo;
	}

	@Override
	public WebManagerVO webmanager_getvo(String username) throws RemoteException {
		int id=datafactory.getManageDataService().findWebManagerIDbyUsername(username);
		WebManagerPO webmanagerpo=datafactory.getManageDataService().findWebManager(id);
		WebManagerVO vo=webmanagerpo.changetowebmanagervo();
		return vo;
	}

	

	

	
	

}
