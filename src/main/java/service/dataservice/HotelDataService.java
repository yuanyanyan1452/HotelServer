
package service.dataservice;

import java.util.ArrayList;
import po.*;
import objects.*;

public interface HotelDataService {

	//网管查看所有酒店
	public ArrayList<HotelPO> getAllHotelPO();
	
	//明确地址商圈后查看酒店
	public ArrayList<HotelPO> show_hotel_list(String address,String business_address);
	
	//通过酒店id查找酒店
	public HotelPO findByid(int hotelid);
	
	//通过酒店名称查找酒店
	public ArrayList<HotelPO> findByName(String hotelname);
	
	//根据星级搜索酒店
	public ArrayList<HotelPO> findByStar(String star);
	
	//新增一个酒店
	public ResultMessage insert(HotelPO po); 
	
	//更新酒店信息
	public ResultMessage update(HotelPO po);
	
	//删除酒店
	public ResultMessage delete(HotelPO po);

	//显示客户预定过的酒店
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
}

