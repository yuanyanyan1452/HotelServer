package service.blservice.Impl;

import java.rmi.RemoteException;

import objects.Client;
import objects.Hotel;
import objects.HotelWorker;
import objects.ObjectChange;
import objects.ResultMessage;
import po.WebManagerPO;
import po.WebMarketPO;
import service.VOChange;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.blservice.ManageBLService;
import service.dataservice.ManageDataService;
import service.dataservice.Impl.ManageDataServiceImpl;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

public class ManageBLServiceImpl implements ManageBLService {
	ManageDataService managedataservice=new ManageDataServiceImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	ClientBLService clientblservice=new ClientBLServiceImpl();
	VOChange vochange=new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage webmanager_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage result=managedataservice.checkWebManager(username, password);
		return result;
	}

	@Override
	public ResultMessage webmarket_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage result=managedataservice.checkWebMarket(username, password);
		return result;
	}
	
	@Override
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		WebMarketPO webmarketpo=managedataservice.getwebmarketpo(username, oldpassword);
		webmarketpo.setpassword(newpassword);
		ResultMessage result=managedataservice.updateWebMarket(webmarketpo);
		return result;
	}

	@Override
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		WebManagerPO webmanagerpo=managedataservice.getwebmanagerpo(username, oldpassword);
		webmanagerpo.setpassword(newpassword);
		ResultMessage result=managedataservice.updateWebManager(webmanagerpo);
		return result;
	}
	
	@Override
	public ClientVO manage_searchClient(int clientid) throws RemoteException{
		// TODO Auto-generated method stub
		Client client= clientblservice.checkClientInfo(clientid);
		ClientVO clientvo=objectchange.changetoclientvo(client);
		return clientvo;
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException{
		// TODO Auto-generated method stub
		Client client=vochange.clientvo_to_client(clientvo);
		ResultMessage result=clientblservice.updateClientInfo(client);
		return result;
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException{
		// TODO Auto-generated method stub
		Hotel hotel=vochange.hotelvo_to_hotel(hotelvo);
		ResultMessage result=hotelblservice.addHotel(hotel);
		return result;
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO workervo) throws RemoteException{
		// TODO Auto-generated method stub
		HotelWorker hotelworker=vochange.hotelworkervo_to_hotelworker(workervo);
		ResultMessage result=hotelblservice.addHotelWorker(hotelworker);
		return result;
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorker(int hotelid) throws RemoteException{
		// TODO Auto-generated method stub
		HotelWorker hotelworker=hotelblservice.searchHotelWorker(hotelid);
		HotelWorkerVO hotelworkervo=objectchange.changetohotelworkervo(hotelworker);
		return hotelworkervo;
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO worker) throws RemoteException{
		// TODO Auto-generated method stub
		HotelWorker hotelworker=vochange.hotelworkervo_to_hotelworker(worker);
		ResultMessage result=hotelblservice.updateHotelWokerInfo( hotelworker);
		return result;
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO webmarketvo) {
		// TODO Auto-generated method stub
		WebMarketPO po=vochange.marketvo_to_marketpo(webmarketvo);
		ResultMessage result=managedataservice.insertWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid) {
		// TODO Auto-generated method stub
		WebMarketPO webmarketpo=managedataservice.findWebMarket(marketWorkerid);
		WebMarketVO webmarketvo=webmarketpo.changetowebmarketvo();		return webmarketvo;
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) {
		// TODO Auto-generated method stub
		WebMarketPO po=vochange.marketvo_to_marketpo(mw);
		ResultMessage result=managedataservice.updateWebMarket(po);
		return result;
	}

	@Override
	public WebMarketVO webmarket_getvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		int id=managedataservice.findWebMarketIDbyUsername(username);
		WebMarketPO webmarketpo=managedataservice.findWebMarket(id);
		WebMarketVO vo=webmarketpo.changetowebmarketvo();
		return vo;
	}

	@Override
	public WebManagerVO webmanager_getvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		int id=managedataservice.findWebManagerIDbyUsername(username);
		WebManagerPO webmanagerpo=managedataservice.findWebManager(id);
		WebManagerVO vo=webmanagerpo.changetowebmanagervo();
		return vo;
	}

	

	
	

}
