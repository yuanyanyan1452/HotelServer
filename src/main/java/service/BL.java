package service;

import java.util.ArrayList;
import java.util.Date;

import po.OrderPO;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.OrderDataServiceImpl;

public class BL {
	

	
	public void auto_change_ordersgtate(){
		OrderDataService orderdataservice=new OrderDataServiceImpl();
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByExecute(false);
		Date date=new Date();
		for(int i=0;i<orderpo_list.size();i++){
			OrderPO orderpo=orderpo_list.get(i);
			Date latest_execute_time=orderpo.getlatest_execute_time();
			boolean compare=date.after(latest_execute_time);
			if(compare&&orderpo.getstate()=="正常"){
				orderpo.setstate("异常");
				orderdataservice.update(orderpo);
			}
		}
	}
}
