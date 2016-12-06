package service.blservice.Impl;

import objects.Client;
import objects.HotelWorker;
import objects.ObjectChange;
import objects.ResultMessage;
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
import vo.WebMarketVO;

public class ManageBLServiceImpl implements ManageBLService {
	ManageDataService managedataservice=new ManageDataServiceImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	ClientBLService clientblservice=new ClientBLServiceImpl();
	VOChange vochange=new VOChange();
	ObjectChange objectchange=new ObjectChange();
	@Override
	public ClientVO manage_searchClient(int clientid) {
		// TODO Auto-generated method stub
		Client client= clientblservice.checkClientInfo(clientid);
		ClientVO clientvo=objectchange.changetoclientvo(client);
		return clientvo;
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) {
		// TODO Auto-generated method stub
		Client client
		ResultMessage result=clientblservice.updateClientInfo(client);
		return result;
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) {
		// TODO Auto-generated method stub
		Hotel hotel
		ResultMessage result=hotelblservice.addHotel(hotel);
		
		return null;
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO w) {
		// TODO Auto-generated method stub
		HotelWorker hotelworker
		ResultMessage result=hotelblservice.addHotelWorker(hotelworker);
		
		return null;
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorker hotelworker=hotelblservice.searchHotelWorker(hotelid);
		HotelWorkerVO hotelworkervo=objectchange.changetohotelworkervo(hotelworker);
		return null;
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO w) {
		// TODO Auto-generated method stub
		HotelWorker hotelworker
		ResultMessage result=hotelblservice.updateHotelWokerInfo(hotelid, hotelworker);
		
		return null;
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO webmarketvo) {
		// TODO Auto-generated method stub
		WebMarketPO po=vochange.marketvo_to_marketpo(webmarketvo);
		ResultMessage result=managedataservice.insertWebMarket(po);
		return null;
	}

	@Override
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid) {
		// TODO Auto-generated method stub
		WebMarketPO webmarketpo=managedataservice.findWebMarket(marketWorkerid);
		return null;
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) {
		// TODO Auto-generated method stub
		WebMarketPO po=vochange.marketvo_to_marketpo(mw);
		ResultMessage result=managedataservice.updateWebMarket(po);
		return result;
	}
	

}
