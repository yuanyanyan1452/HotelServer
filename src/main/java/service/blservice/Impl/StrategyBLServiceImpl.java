package service.blservice.Impl;

import java.util.ArrayList;

import objects.HotelStrategy;
import objects.ResultMessage;
import objects.WebStrategy;
import po.HotelStrategyPO;
import po.WebStrategyPO;
import service.blservice.StrategyBLService;
import service.dataservice.StrategyDataService;
import service.dataservice.Impl.StrategyDataServiceImpl;

public class StrategyBLServiceImpl implements StrategyBLService {
	StrategyDataService strategydataservice=new StrategyDataServiceImpl();
	
	@Override
	public ResultMessage hotelstrategy_make(HotelStrategy strategy) {
		// TODO Auto-generated method stub
		ResultMessage result=strategydataservice.insert_hotel(po);
		return null;
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategy strategy) {
		// TODO Auto-generated method stub
		ResultMessage result=strategydataservice.update_hotel(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategy strategy) {
		// TODO Auto-generated method stub
		ResultMessage result=strategydataservice.insert_web(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategy strategy) {
		// TODO Auto-generated method stub
		ResultMessage result=strategydataservice.update_web(po);
		return null;
	}

	@Override
	public ArrayList<HotelStrategy> getStrategy(int hotelid, int clientid) {
		// TODO Auto-generated method stub
		ArrayList<HotelStrategyPO> hotelstrategypo_list=strategydataservice.showlist_hotel();
		
		return null;
	}

	@Override
	public ArrayList<WebStrategy> getStrategy(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyPO> hotelstrategypo_list=strategydataservice.showlist_web();
		
		return null;
	}

}
