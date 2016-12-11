package service.dataservice;

import java.util.ArrayList;

import po.*;
import objects.*;

public interface StrategyDataService {
	public HotelStrategyPO find_hotel(String name) ;
	
	public ResultMessage insert_hotel(HotelStrategyPO po);
	
	public ResultMessage delete_hotel(HotelStrategyPO po);
	
	public ResultMessage update_hotel(HotelStrategyPO po);
	
	public ArrayList<HotelStrategyPO> showlist_hotel(int hotelid) ;
	
	public WebStrategyPO find_web(String name);
	
	public ResultMessage insert_web(WebStrategyPO po);
	
	public ResultMessage delete_web(WebStrategyPO po);
	
	public ResultMessage update_web(WebStrategyPO po);
	
	public ArrayList<WebStrategyPO> showlist_web();
}
