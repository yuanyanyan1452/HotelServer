package service.dataservice;

import java.util.ArrayList;

import po.*;
import objects.*;

public interface OrderDataService {
	public OrderPO find (int id) ;
	
	public ArrayList<OrderPO> find (String name) ;
	
	public ResultMessage insert(OrderPO po);
	
	public ResultMessage delete(OrderPO po);
	
	public ResultMessage update(OrderPO po);
	
}
