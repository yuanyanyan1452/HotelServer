
package service.dataservice;

import java.util.ArrayList;
import po.*;
import objects.*;

public interface HotelDataService {

	//明确地址商圈后查看酒店
	public ArrayList<HotelPO> show_hotel_list(String address,String business_address);
	
	//通过酒店id查找酒店
	public HotelPO findByid(int hotelid);
	
	//通过酒店名称查找酒店
	public HotelPO findByName(String hotelname);

	//通过价格区间查找酒店
	public HotelPO findByPrice(String price);
	
	//新增一个酒店
	public ResultMessage insert(HotelPO po);
	
	//更新酒店信息
	public ResultMessage update(HotelPO po);
	
	//删除酒店
	public ResultMessage delete(HotelPO po);

	//显示客户预定过的酒店
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
}

