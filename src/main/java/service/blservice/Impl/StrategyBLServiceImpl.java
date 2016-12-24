package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelStrategyPO;
import po.WebStrategyPO;
import service.VOChange;
import service.blservice.StrategyBLService;
import service.datafactory.datafactory;
import service.datafactory.datafactoryImpl;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public class StrategyBLServiceImpl implements StrategyBLService {
	datafactory datafactory=new datafactoryImpl();
	VOChange vochange=new VOChange();
	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) {
		HotelStrategyPO po=vochange.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=datafactory.getStrategyDataService().insert_hotel(po);
		return result;
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) {
		HotelStrategyPO po=vochange.hotelstrategyvo_to_hotelstrategypo(strategyvo);
		ResultMessage result=datafactory.getStrategyDataService().update_hotel(po);
		return result;
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) {
		WebStrategyPO po=vochange.webstrategyvo_to_webstrategypo(strategyvo);
		ResultMessage result=datafactory.getStrategyDataService().insert_web(po);
		return result;
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) {
		WebStrategyPO po=vochange.webstrategyvo_to_webstrategypo(strategyvo);
		ResultMessage result=datafactory.getStrategyDataService().update_web(po);
		return result;
	}

	@Override
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid) {
		ArrayList<HotelStrategyPO> hotelstrategypo_list=datafactory.getStrategyDataService().showlist_hotel(hotelid);
		ArrayList<HotelStrategyVO> hotelstrategyvo_list=new ArrayList<HotelStrategyVO>();
		for(int i=0;i<hotelstrategypo_list.size();i++){
			HotelStrategyVO hotelstrategyvo=hotelstrategypo_list.get(i).changetohotelstrategyvo();
			hotelstrategyvo_list.add(hotelstrategyvo);
		}
		return hotelstrategyvo_list;
	}

	@Override
	public ArrayList<WebStrategyVO> getWebStrategy() {
		ArrayList<WebStrategyPO> webstrategypo_list=datafactory.getStrategyDataService().showlist_web();
		ArrayList<WebStrategyVO> webstrategyvo_list=new ArrayList<WebStrategyVO>();
		for(int i=0;i<webstrategypo_list.size();i++){
			WebStrategyVO webstrategyvo=webstrategypo_list.get(i).changetowebstrategyvo();
			webstrategyvo_list.add(webstrategyvo);
		}
		return webstrategyvo_list;
	}

	@Override
	public HotelStrategyVO gethotelstrategybyname(String name) throws RemoteException {
		HotelStrategyPO hotelstrategypo=datafactory.getStrategyDataService().find_hotel(name);
		HotelStrategyVO hotelstrategyvo=hotelstrategypo.changetohotelstrategyvo();
		return hotelstrategyvo;
	}

	@Override
	public WebStrategyVO getwebstrategybyname(String name) throws RemoteException {
		WebStrategyPO webstrategypo=datafactory.getStrategyDataService().find_web(name);
		WebStrategyVO webstrategyvo=webstrategypo.changetowebstrategyvo();
		return webstrategyvo;
		
	}

	@Override
	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo) throws RemoteException{
		HotelStrategyPO hotelstrategypo=vochange.hotelstrategyvo_to_hotelstrategypo(hotelstrategyvo);
		ResultMessage result=datafactory.getStrategyDataService().delete_hotel(hotelstrategypo);
		return result;
	}

	@Override
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo) throws RemoteException{
		WebStrategyPO webstrategypo=vochange.webstrategyvo_to_webstrategypo(webstrategyvo);
		ResultMessage result=datafactory.getStrategyDataService().delete_web(webstrategypo);
		return result;
	}

}
