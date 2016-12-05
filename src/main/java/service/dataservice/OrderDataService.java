package service.dataservice;

import java.util.ArrayList;

import po.*;
import objects.*;

public interface OrderDataService {

	//通过订单号寻找相应订单信息；
	public OrderPO findByid (int id) ;
	
	//通过客户id寻找订单；
	public ArrayList<OrderPO> findByClientid (int clientid) ;
	
	//分别浏览正常异常撤销与已执行未执行订单
	public ArrayList<OrderPO> findByStatus(int clientid,String state,boolean execute);
	
	//通过酒店id寻找订单；
	public ArrayList<OrderPO> findByHotelid (int hotelid) ;
	
	//插入一条订单记录
	public ResultMessage insert(OrderPO po);
	
	//删除一条订单记录
	public ResultMessage delete(OrderPO po);
	
	//更新一条订单记录
	public ResultMessage update(OrderPO po);
	
//	//返回酒店是否被预定过
//	public Boolean isBooked(int hotelid);
	
}