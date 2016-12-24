package service.datafactory;

import service.dataservice.ClientDataService;
import service.dataservice.HotelDataService;
import service.dataservice.HotelWorkerDataService;
import service.dataservice.ManageDataService;
import service.dataservice.OrderDataService;
import service.dataservice.RoomDataService;
import service.dataservice.StrategyDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import service.dataservice.Impl.HotelDataServiceImpl;
import service.dataservice.Impl.HotelWorkerDataServiceImpl;
import service.dataservice.Impl.ManageDataServiceImpl;
import service.dataservice.Impl.OrderDataServiceImpl;
import service.dataservice.Impl.RoomDataServiceImpl;
import service.dataservice.Impl.StrategyDataServiceImpl;

public class datafactoryImpl implements datafactory{

	@Override
	public ClientDataService getClientDataService() {
		// TODO Auto-generated method stub
		ClientDataService client=new ClientDataServiceImpl();
		return client;
	}

	@Override
	public HotelDataService getHotelDataService() {
		// TODO Auto-generated method stub
		HotelDataService hotel=new HotelDataServiceImpl();
		return hotel;
	}

	@Override
	public HotelWorkerDataService getHotelWorkerDataService() {
		// TODO Auto-generated method stub
		HotelWorkerDataService hotelworker=new HotelWorkerDataServiceImpl();
		return hotelworker;
	}

	@Override
	public ManageDataService getManageDataService() {
		// TODO Auto-generated method stub
		ManageDataService manage=new ManageDataServiceImpl();
		return manage;
	}

	@Override
	public OrderDataService getOrderDataService() {
		// TODO Auto-generated method stub
		OrderDataService order=new OrderDataServiceImpl();
		return order;
	}

	@Override
	public RoomDataService getRoomDataService() {
		// TODO Auto-generated method stub
		RoomDataService room=new RoomDataServiceImpl();
		return room;
	}

	@Override
	public StrategyDataService getStrategyDataService() {
		// TODO Auto-generated method stub
		StrategyDataService strategy=new StrategyDataServiceImpl();
		return strategy;
	}

}
