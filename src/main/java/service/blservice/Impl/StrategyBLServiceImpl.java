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
		ArrayList<HotelStrategyVO> hotelstrategyvo_list=new ArrayList<HotelStrategyVO>();
		for(int i=0;i<hotelstrategypo_list.size();i++){
			HotelStrategyVO hotelstrategyvo=hotelstrategypo_list.get(i).changetohotelstrategyvo();
			hotelstrategyvo_list.add(hotelstrategyvo);
		}
		return hotelstrategyvo_list;
	}

	@Override
	public ArrayList<WebStrategyVO> getStrategy(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyPO> webstrategypo_list=strategydataservice.showlist_web();
		ArrayList<WebStrategyVO> webstrategyvo_list=new ArrayList<WebStrategyVO>();
		for(int i=0;i<webstrategypo_list.size();i++){
			WebStrategyVO webstrategyvo=webstrategypo_list.get(i).changetowebstrategyvo();
			webstrategyvo_list.add(webstrategyvo);
		}
		return webstrategyvo_list;
	}

}
