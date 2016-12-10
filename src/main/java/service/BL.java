package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.OrderPO;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.OrderDataServiceImpl;

public class BL {
	
//	public Date get_current_time(){
//		Date date=new Date();
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time=format.format(date);
//		return time;
//	}
	
	public void auto_change_ordersgtate(){
		OrderDataService orderdataservice=new OrderDataServiceImpl();
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByExecute(false);
		Date date=new Date();
		for(int i=0;i<orderpo_list.size();i++){
			OrderPO orderpo=orderpo_list.get(i);
			Date latest_execute_time=orderpo.getlatest_execute_time();
			boolean compare=date.after(latest_execute_time);
			if(compare){
				orderpo.setstate("ABNORMAL");
				orderdataservice.update(orderpo);
			}
		}
	}
}
