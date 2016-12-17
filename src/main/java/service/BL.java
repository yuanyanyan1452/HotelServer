package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.ClientPO;
import po.OrderPO;
import service.dataservice.ClientDataService;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import service.dataservice.Impl.OrderDataServiceImpl;

public class BL {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	public void auto_change_ordersgtate(){
		OrderDataService orderdataservice=new OrderDataServiceImpl();
		ClientDataService clientdataservice=new ClientDataServiceImpl();
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByExecute(false);
		Date date=new Date();
		for(int i=0;i<orderpo_list.size();i++){
			OrderPO orderpo=orderpo_list.get(i);
			Date latest_execute_time=orderpo.getlatest_execute_time();
			boolean compare=date.after(latest_execute_time);
			if(compare&&orderpo.getstate().equals("正常")){
				orderpo.setstate("异常");
				orderdataservice.update(orderpo);
				ClientPO clientpo=clientdataservice.find(orderpo.getclientid());
				int newcredit=clientpo.getcredit()-orderpo.getprice();
				clientpo.setcredit(newcredit);
				String creditinfo=format.format(date)+","+String.valueOf(orderpo.getid())+","+"订单自动置为异常,"+"-"+String.valueOf(orderpo.getprice())+","+String.valueOf(newcredit);
				ArrayList<String> newcreditrecord=clientpo.getcredit_record();
				newcreditrecord.add(creditinfo);
				clientpo.setcredit_record(newcreditrecord);
				clientdataservice.update(clientpo);
				
			}
		}
	}
}
