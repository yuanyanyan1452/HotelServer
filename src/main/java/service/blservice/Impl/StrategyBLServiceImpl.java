package service.blservice.Impl;

import java.rmi.RemoteException;
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
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid) {
		// TODO Auto-generated method stub
		ArrayList<HotelStrategyPO> hotelstrategypo_list=strategydataservice.showlist_hotel(hotelid);
		ArrayList<HotelStrategyVO> hotelstrategyvo_list=new ArrayList<HotelStrategyVO>();
		for(int i=0;i<hotelstrategypo_list.size();i++){
			HotelStrategyVO hotelstrategyvo=hotelstrategypo_list.get(i).changetohotelstrategyvo();
			hotelstrategyvo_list.add(hotelstrategyvo);
		}
		return hotelstrategyvo_list;
	}

	@Override
	public ArrayList<WebStrategyVO> getWebStrategy(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyPO> webstrategypo_list=strategydataservice.showlist_web();
		ArrayList<WebStrategyVO> webstrategyvo_list=new ArrayList<WebStrategyVO>();
		for(int i=0;i<webstrategypo_list.size();i++){
			WebStrategyVO webstrategyvo=webstrategypo_list.get(i).changetowebstrategyvo();
			webstrategyvo_list.add(webstrategyvo);
		}
		return webstrategyvo_list;
	}

	@Override
	public HotelStrategyVO gethotelstrategybyname(String name) throws RemoteException {
		// TODO Auto-generated method stub
		HotelStrategyPO hotelstrategypo=strategydataservice.find_hotel(name);
		HotelStrategyVO hotelstrategyvo=hotelstrategypo.changetohotelstrategyvo();
		return hotelstrategyvo;
	}

	@Override
	public WebStrategyVO getwebstrategybyname(String name) throws RemoteException {
		// TODO Auto-generated method stub
		WebStrategyPO webstrategypo=strategydataservice.find_web(name);
		WebStrategyVO webstrategyvo=webstrategypo.changetowebstrategyvo();
		return webstrategyvo;
		
	}

	@Override
	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo) throws RemoteException{
		// TODO Auto-generated method stub
		HotelStrategyPO hotelstrategypo=vochange.hotelstrategyvo_to_hotelstrategypo(hotelstrategyvo);
		ResultMessage result=strategydataservice.delete_hotel(hotelstrategypo);
		return result;
	}

	@Override
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo) throws RemoteException{
		// TODO Auto-generated method stub
		WebStrategyPO webstrategypo=vochange.webstrategyvo_to_webstrategypo(webstrategyvo);
		ResultMessage result=strategydataservice.delete_web(webstrategypo);
		return result;
	}

}
