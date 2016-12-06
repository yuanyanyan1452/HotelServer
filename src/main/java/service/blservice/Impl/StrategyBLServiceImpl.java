package service.blservice.Impl;

import java.util.ArrayList;

import objects.HotelStrategy;
import objects.ResultMessage;
import objects.WebStrategy;
import po.HotelStrategyPO;
import po.WebStrategyPO;
import service.VOChange;
import service.blservice.StrategyBLService;
import service.dataservice.StrategyDataService;
import service.dataservice.Impl.StrategyDataServiceImpl;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public class StrategyBLServiceImpl implements StrategyBLService {
	StrategyDataService strategydataservice=new StrategyDataServiceImpl();
	VOChange vochange=new VOChange();
	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		HotelStrategyPO po=vochange.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=strategydataservice.insert_hotel(po);
		return null;
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		HotelStrategyPO po=vochange.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=strategydataservice.update_hotel(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		WebStrategyPO po=vochange.webstrategyvo_to_webstrategypo(strategyvo);
		ResultMessage result=strategydataservice.insert_web(po);
		return null;
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) {
		// TODO Auto-generated method stub
		WebStrategyPO po=vochange.webstrategyvo_to_webstrategypo(strategyvo);
		ResultMessage result=strategydataservice.update_web(po);
		return null;
	}

	@Override
	public ArrayList<HotelStrategyVO> getStrategy(int hotelid, int clientid) {
		// TODO Auto-generated method stub
		ArrayList<HotelStrategyPO> hotelstrategypo_list=strategydataservice.showlist_hotel();
		
		return null;
	}

	@Override
	public ArrayList<WebStrategyVO> getStrategy(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyPO> hotelstrategypo_list=strategydataservice.showlist_web();
		
		return null;
	}

}
