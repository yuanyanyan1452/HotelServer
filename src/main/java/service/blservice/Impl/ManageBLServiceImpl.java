package service.blservice.Impl;

import objects.Client;
import objects.HotelWorker;
import objects.ResultMessage;
import po.WebMarketPO;
import service.Change;
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
	Change change=new Change();
	@Override
	public ClientVO manage_searchClient(int clientid) {
		// TODO Auto-generated method stub
		Client client= clientblservice.checkClientInfo(clientid);
		
		return null;
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) {
		// TODO Auto-generated method stub
		ResultMessage result=clientblservice.client_updateInfo(clientvo);
		return result;
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) {
		// TODO Auto-generated method stub
		ResultMessage result=hotelblservice.addHotel(hotel);
		
		return null;
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO w) {
		// TODO Auto-generated method stub
		ResultMessage result=hotelblservice.addHotelWorker(worker);
		
		return null;
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorker hotelworker=hotelblservice.searchHotelWorker(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO w) {
		// TODO Auto-generated method stub
		ResultMessage result=hotelblservice.updateHotelWokerInfo(hotelid, worker);
		
		return null;
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO mw) {
		// TODO Auto-generated method stub
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
		WebMarketPO po=change.marketvo_to_marketpo(mw);
		ResultMessage result=managedataservice.updateWebMarket(po);
		return result;
	}
	

}
