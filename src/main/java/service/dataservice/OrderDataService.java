package service.dataservice;

import java.util.ArrayList;

import po.*;
import objects.*;

public interface OrderDataService {
	//通过订单号寻找相应订单信息；
	public OrderPO findByid (int id) ;
	
	//通过客户id寻找订单；
	public ArrayList<OrderPO> findByClientid (int clientid) ;
	
	//通过酒店id寻找订单；
	public ArrayList<OrderPO> findByHotelid (int hotelid) ;
	
	//��Ӷ���
	public ResultMessage insert(OrderPO po);
	
	//ɾ������
	public ResultMessage delete(OrderPO po);
	
	//���¶���
	public ResultMessage update(OrderPO po);

	
}
