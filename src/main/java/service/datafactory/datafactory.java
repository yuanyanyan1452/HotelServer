package service.datafactory;

import service.dataservice.ClientDataService;
import service.dataservice.HotelDataService;
import service.dataservice.HotelWorkerDataService;
import service.dataservice.ManageDataService;
import service.dataservice.OrderDataService;
import service.dataservice.RoomDataService;
import service.dataservice.StrategyDataService;

public interface datafactory {
	
	public ClientDataService getClientDataService();
	
	public HotelDataService getHotelDataService();
	
	public HotelWorkerDataService getHotelWorkerDataService();
	
	public ManageDataService getManageDataService();
	
	public OrderDataService getOrderDataService();
	
	public RoomDataService getRoomDataService();
	
	public StrategyDataService getStrategyDataService();
	
}
