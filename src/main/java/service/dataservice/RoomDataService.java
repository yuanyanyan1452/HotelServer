package service.dataservice;

import java.util.ArrayList;

import objects.ResultMessage;
import po.OrderPO;
import po.RoomOrderPO;
import po.RoomPO;

public interface RoomDataService {

	//通过酒店id寻找房间
	public ArrayList<RoomPO> find(int hotelid);
	
	//插入房间
	public ResultMessage insert(RoomPO po);
	
	//删除房间
	public ResultMessage delete(RoomPO po);
	
	//更新可用房间信息
	public ResultMessage update(RoomPO po);
	
	//退房时房间数量增加
	public ResultMessage add(OrderPO po);
	
	//线下退房时房间数量增加
	public ResultMessage addOffline(int hotelid,ArrayList<RoomOrderPO> room_order);
	
	//每次生成订单时房间数量减少
	public ResultMessage reduce(OrderPO po);
	
	//线下入住房间数量减少
	public ResultMessage reduceOffline(int hotelid,ArrayList<RoomOrderPO> room_order);
}
