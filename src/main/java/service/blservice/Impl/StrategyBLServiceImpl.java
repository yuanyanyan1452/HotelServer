package service.blservice.Impl;

import java.util.ArrayList;

import objects.HotelStrategy;
import objects.ResultMessage;
import objects.WebStrategy;
import po.HotelStrategyPO;
import po.WebStrategyPO;
import service.Change;
import service.blservice.StrategyBLService;
import service.dataservice.StrategyDataService;
import service.dataservice.Impl.StrategyDataServiceImpl;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public class StrategyBLServiceImpl implements StrategyBLService {
	StrategyDataService strategydataservice=new StrategyDataServiceImpl();
	Change change=new Change();
	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		HotelStrategyPO po=change.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=strategydataservice.insert_hotel(po);
		return null;
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		HotelStrategyPO po=change.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=strategydataservice.update_hotel(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		WebStrategyPO po=change.webstrategyvo_to_webstrategypo(strategyvo);
		ResultMessage result=strategydataservice.insert_web(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		WebStrategyPO po=change.webstrategyvo_to_webstrategypo(strategyvo);
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
