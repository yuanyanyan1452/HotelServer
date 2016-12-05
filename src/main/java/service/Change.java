package service;
import po.ClientPO;
import po.HotelPO;
import po.HotelStrategyPO;
import po.HotelWorkerPO;
import po.OrderPO;
import po.WebManagerPO;
import po.WebMarketPO;
import po.WebStrategyPO;
import vo.ClientVO;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.OrderVO;
import vo.WebManagerVO;
import vo.WebMarketVO;
import vo.WebStrategyVO;

public class Change {
	
	public ClientPO clientvo_to_clientpo(ClientVO clientvo){
		ClientPO clientpo=new ClientPO();
		clientpo.setClientid(clientvo.getClientid());
		clientpo.setusername(clientvo.getusername());
		clientpo.setpassword(clientvo.getpassword());
		clientpo.setClient_name(clientvo.getClient_name());
		clientpo.setContact(clientvo.getContact());
		clientpo.setCredit(clientvo.getCredit());
		clientpo.setCredit_record(clientvo.getCredit_record());
		clientpo.setVIPInfo(clientvo.getVIPInfo());
		return clientpo;
	}
	
	public HotelPO hotelvo_to_hotelpo(HotelVO hotelvo){
		HotelPO hotelpo=new HotelPO();
		hotelpo.setid(hotelvo.getid());
		hotelpo.setname(hotelvo.getname());
		hotelpo.setaddress(hotelvo.getaddress());
		hotelpo.setbussiness_address(hotelvo.getbussiness_address());
		hotelpo.setintroduction(hotelvo.getintroduction());
		hotelpo.setservice(hotelvo.getservice());
		hotelpo.setstar(hotelvo.getstar());
		hotelpo.setmin_price(hotelvo.getmin_price());
		hotelpo.setbook_clientid(hotelvo.getbook_clientid());
		//评价没写
		return hotelpo;
		
	}
	
	public OrderPO ordervo_to_orderpo(OrderVO ordervo){
		OrderPO orderpo=new OrderPO();
		orderpo.setid(ordervo.getid());
		orderpo.setclientid(ordervo.getclientid());
		orderpo.sethotelid(ordervo.gethotelid());
		orderpo.setstate(ordervo.getstate());
		orderpo.setexecute(ordervo.getexecute());
		orderpo.setstart_time(ordervo.getstart_time());
		orderpo.setend_time(ordervo.getend_time());
		orderpo.setlatest_execute_time(ordervo.getlatest_execute_time());
		orderpo.setroom_order(ordervo.getroom_order());
		orderpo.setprice(ordervo.getprice());
		orderpo.setexpect_number_of_people(ordervo.getexpect_number_of_people());
		orderpo.sethave_child(ordervo.gethave_child());
		//策略没写
		return orderpo;
	}
	
	public  HotelStrategyPO hotelstrategyvo_to_hotelstrategypo(HotelStrategyVO vo){
		HotelStrategyPO po=new HotelStrategyPO();
		po.setid(vo.getid());
		po.sethotelid(vo.gethotelid());
		po.setname(vo.getname());
		po.setcondition(vo.getcondition());
		po.setstart_time(vo.getstart_time());
		po.setend_time(vo.getend_time());
		po.setexecuteway(vo.getexecuteway());
		po.setsuperposition(vo.getsuperposition());
		return po;
	}
	
	public WebStrategyPO webstrategyvo_to_webstrategypo(WebStrategyVO vo){
		WebStrategyPO po=new WebStrategyPO();
		po.setid(vo.getid());
		po.setname(vo.getname());
		po.setcondition(vo.getcondition());
		po.setstart_time(vo.getstart_time());
		po.setend_time(vo.getend_time());
		po.setexecuteway(vo.getexecuteway());
		po.setsuperposition(vo.getsuperposition());
		return po;
	}
	
	public HotelWorkerPO hotelworkervo_to_hotelworkerpo(HotelWorkerVO vo){
		HotelWorkerPO po=new HotelWorkerPO();
		po.sethotelid(vo.gethotelid());
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setContact(vo.getcontact());
		po.sethotel(vo.gethotel());
		return po;
	}
	
	public WebManagerPO managervo_to_managerpo(WebManagerVO vo){
		WebManagerPO po=new WebManagerPO();
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setContact(vo.getcontact());
		return po;
	}
	
	public WebMarketPO marketvo_to_marketpo(WebMarketVO vo){
		WebMarketPO po=new WebMarketPO();
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setContact(vo.getcontact());
		return po;
	}
	
	
}
