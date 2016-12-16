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
import service.dataservice.ClientDataService;
import service.dataservice.HotelDataService;
import service.dataservice.HotelWorkerDataService;
import service.dataservice.ManageDataService;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import service.dataservice.Impl.HotelDataServiceImpl;
import service.dataservice.Impl.HotelWorkerDataServiceImpl;
import service.dataservice.Impl.ManageDataServiceImpl;
import service.dataservice.Impl.OrderDataServiceImpl;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

public class ManageBLServiceImpl implements ManageBLService {
	ManageDataService managedataservice=new ManageDataServiceImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	ClientBLService clientblservice=new ClientBLServiceImpl();
	ClientDataService clientdataservice= new ClientDataServiceImpl();
	HotelDataService hoteldataservice=new HotelDataServiceImpl();
	HotelWorkerDataService hotelworkerdataservice=new HotelWorkerDataServiceImpl();
	OrderDataService orderdataservice=new OrderDataServiceImpl();
	VOChange vochange=new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage webmanager_login(String username, String password) throws RemoteException {
		ResultMessage result=managedataservice.checkWebManager(username, password);
		return result;
	}

	@Override
	public ResultMessage webmarket_login(String username, String password) throws RemoteException {
		ResultMessage result=managedataservice.checkWebMarket(username, password);
		return result;
	}
	
	@Override
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		WebMarketPO webmarketpo=managedataservice.getwebmarketpo(username, oldpassword);
		webmarketpo.setpassword(newpassword);
		ResultMessage result=managedataservice.updateWebMarket(webmarketpo);
		return result;
	}

	@Override
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		WebManagerPO webmanagerpo=managedataservice.getwebmanagerpo(username, oldpassword);
		webmanagerpo.setpassword(newpassword);
		ResultMessage result=managedataservice.updateWebManager(webmanagerpo);
		return result;
	}
	
	@Override
	public ArrayList<ClientVO> getallclientvo() throws RemoteException {
		ArrayList<ClientPO> clientpo_list=clientdataservice.getallclientPO();
		ArrayList<ClientVO> clientvo_list=new ArrayList<ClientVO>();
		for(int i=0;i<clientpo_list.size();i++){
			ClientVO clientvo=clientpo_list.get(i).changetoclientvo();
			clientvo_list.add(clientvo);
		}
		return clientvo_list;
	}

	@Override
	public ArrayList<HotelVO> getallhotelvo() throws RemoteException {
		ArrayList<HotelPO> hotelpo_list=hoteldataservice.getAllHotelPO();
		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
		for(int i=0;i<hotelpo_list.size();i++){
			HotelVO hotelvo=hotelpo_list.get(i).changetohotelvo();
			hotelvo_list.add(hotelvo);
		}
		return hotelvo_list;
	}

	@Override
	public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException {
		ArrayList<HotelWorkerPO> hotelworkerpo_list=hotelworkerdataservice.getallhotelworkerPO();
		ArrayList<HotelWorkerVO> hotelworkervo_list=new ArrayList<HotelWorkerVO>();
		for(int i=0;i<hotelworkerpo_list.size();i++){
			HotelWorkerVO hotelworkervo=hotelworkerpo_list.get(i).changetohotelworkervo();
			hotelworkervo_list.add(hotelworkervo);
		}
		return hotelworkervo_list;
	}

	@Override
	public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException {
		ArrayList<WebMarketPO> webmarketpo_list=managedataservice.getallwebmarketPO();
		ArrayList<WebMarketVO> webmarketvo_list=new ArrayList<WebMarketVO>();
		for(int i=0;i<webmarketpo_list.size();i++){
			WebMarketVO webmarketvo=webmarketpo_list.get(i).changetowebmarketvo();
			webmarketvo_list.add(webmarketvo);
		}
		return webmarketvo_list;
	}
	
	@Override
	public int getordernumber() throws RemoteException{
		int num=orderdataservice.getAllOrder();
		return num;
	}
	
	@Override
	public ClientVO manage_searchClient(int clientid) throws RemoteException{
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
	public HotelWorkerVO manage_searchHotelWorker(int hotelid) throws RemoteException{
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
		ResultMessage result=managedataservice.insertWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid) {
		WebMarketPO webmarketpo=managedataservice.findWebMarket(marketWorkerid);
		WebMarketVO webmarketvo=webmarketpo.changetowebmarketvo();		return webmarketvo;
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) {
		WebMarketPO po=vochange.marketvo_to_marketpo(mw);
		ResultMessage result=managedataservice.updateWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO webmarket_getvo(String username) throws RemoteException {
		int id=managedataservice.findWebMarketIDbyUsername(username);
		WebMarketPO webmarketpo=managedataservice.findWebMarket(id);
		WebMarketVO vo=webmarketpo.changetowebmarketvo();
		return vo;
	}

	@Override
	public WebManagerVO webmanager_getvo(String username) throws RemoteException {
		int id=managedataservice.findWebManagerIDbyUsername(username);
		WebManagerPO webmanagerpo=managedataservice.findWebManager(id);
		WebManagerVO vo=webmanagerpo.changetowebmanagervo();
		return vo;
	}

	

	

	
	

}
