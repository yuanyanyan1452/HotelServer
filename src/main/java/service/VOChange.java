package service;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.HotelStrategy;
import objects.HotelWorker;
import objects.Manage;
import objects.Order;
import objects.RoomOrder;
import objects.VIPInfo;
import objects.WebMarket;
import objects.WebStrategy;
import po.ClientPO;
import po.HotelPO;
import po.HotelStrategyPO;
import po.HotelWorkerPO;
import po.OrderPO;
import po.RoomOrderPO;
import po.RoomPO;
import po.WebManagerPO;
import po.WebMarketPO;
import po.WebStrategyPO;
import vo.ClientVO;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;
import vo.VIPInfoVO;
import vo.VIPInfoVO.VIPType;
import vo.WebManagerVO;
import vo.WebMarketVO;
import vo.WebStrategyVO;

public class VOChange {
	
	public ClientPO clientvo_to_clientpo(ClientVO clientvo){
		ClientPO clientpo=new ClientPO();
		clientpo.setclientid(clientvo.getclientid());
		clientpo.setusername(clientvo.getusername());
		clientpo.setpassword(clientvo.getpassword());
		clientpo.setclient_name(clientvo.getclient_name());
		clientpo.setcontact(clientvo.getcontact());
		clientpo.setcredit(clientvo.getcredit());
		clientpo.setcredit_record(clientvo.getcredit_record());
		clientpo.setvipinfo(clientvo.getvipinfo());
		return clientpo;
	}
	
	public Client clientvo_to_client(ClientVO clientvo){
		Client client=new Client();
		client.setclientid(clientvo.getclientid());
		client.setusername(clientvo.getusername());
		client.setpassword(clientvo.getpassword());
		client.setclient_name(clientvo.getclient_name());
		client.setcontact(clientvo.getcontact());
		client.setcredit(clientvo.getcredit());
		client.setcredit_record(clientvo.getcredit_record());
		client.setvipinfo(clientvo.getvipinfo());
		return client;
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
		hotelpo.setscore(hotelvo.getscore());
		hotelpo.sethotel_evaluation(hotelvo.gethotel_evaluation());
		hotelpo.setbook_clientid(hotelvo.getbook_clientid());
		return hotelpo;
		
	}
	
	public Hotel hotelvo_to_hotel(HotelVO hotelvo){
		Hotel hotel=new Hotel();
		hotel.setid(hotelvo.getid());
		hotel.setname(hotelvo.getname());
		hotel.setaddress(hotelvo.getaddress());
		hotel.setbussiness_address(hotelvo.getbussiness_address());
		hotel.setintroduction(hotelvo.getintroduction());
		hotel.setservice(hotelvo.getservice());
		hotel.setstar(hotelvo.getstar());
		hotel.setevaluation(hotelvo.gethotel_evaluation());
		hotel.setbook_clientid(hotelvo.getbook_clientid());
		hotel.setbook_clientid(hotelvo.getbook_clientid());
		//评价,评分没写
		return hotel;
		
	}
	
	public OrderPO ordervo_to_orderpo(OrderVO ordervo){
		OrderPO orderpo=new OrderPO();
		orderpo.setid(ordervo.getid());
		orderpo.setclientid(ordervo.getclientid());
		orderpo.sethotelid(ordervo.gethotelid());
		orderpo.setstate(ordervo.getstate());
		orderpo.setcancel_time(ordervo.getcancel_time());
		orderpo.setexecute(ordervo.getexecute());
		orderpo.setstart_time(ordervo.getstart_time());
		orderpo.setend_time(ordervo.getend_time());
		orderpo.setlatest_execute_time(ordervo.getlatest_execute_time());
		ArrayList<RoomOrderPO> roomorderpo_list=new ArrayList<RoomOrderPO>();
		ArrayList<RoomOrderVO> roomordervo_list=ordervo.getroom_order();
		for(int i=0;i<roomordervo_list.size();i++){
			RoomOrderPO roomorderpo=this.roomordervo_to_roomorderpo(roomordervo_list.get(i));
			roomorderpo_list.add(roomorderpo);
		}
		orderpo.setroom_order(roomorderpo_list);
		orderpo.setprice(ordervo.getprice());
		orderpo.setexpect_number_of_people(ordervo.getexpect_number_of_people());
		orderpo.sethave_child(ordervo.gethave_child());
	
		return orderpo;
	}
	
	public Order ordervo_to_order(OrderVO ordervo){
		Order order=new Order();
		order.setid(ordervo.getid());
		order.setclientid(ordervo.getclientid());
		order.sethotelid(ordervo.gethotelid());
		order.setstate(ordervo.getstate());
		order.setcancel_time(ordervo.getcancel_time());
		order.setexecute(ordervo.getexecute());
		order.setstart_time(ordervo.getstart_time());
		order.setend_time(ordervo.getend_time());
		order.setlatest_execute_time(ordervo.getlatest_execute_time());
		ArrayList<RoomOrder> roomorder_list=new ArrayList<RoomOrder>();
		ArrayList<RoomOrderVO> roomordervo_list=ordervo.getroom_order();
		for(int i=0;i<roomordervo_list.size();i++){
			RoomOrder roomorder=this.roomordervo_to_roomorder(roomordervo_list.get(i));
			roomorder_list.add(roomorder);
		}
		order.setroom_order(roomorder_list);
		order.setprice(ordervo.getprice());
		order.setexpect_number_of_people(ordervo.getexpect_number_of_people());
		order.sethave_child(ordervo.gethave_child());
	
		return order;
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
	
	public  HotelStrategy hotelstrategyvo_to_hotelstrategy(HotelStrategyVO vo){
		HotelStrategy hs=new HotelStrategy();
		hs.setid(vo.getid());
		hs.sethotelid(vo.gethotelid());
		hs.setname(vo.getname());
		hs.setcondition(vo.getcondition());
		hs.setstart_time(vo.getstart_time());
		hs.setend_time(vo.getend_time());
		hs.setexecuteway(vo.getexecuteway());
		hs.setsuperposition(vo.getsuperposition());
		return hs;
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
	
	public WebStrategy webstrategyvo_to_webstrategy(WebStrategyVO vo){
		WebStrategy ws=new WebStrategy();
		ws.setid(vo.getid());
		ws.setname(vo.getname());
		ws.setcondition(vo.getcondition());
		ws.setstart_time(vo.getstart_time());
		ws.setend_time(vo.getend_time());
		ws.setexecuteway(vo.getexecuteway());
		ws.setsuperposition(vo.getsuperposition());
		return ws;
	}
	
	public HotelWorkerPO hotelworkervo_to_hotelworkerpo(HotelWorkerVO vo){
		HotelWorkerPO po=new HotelWorkerPO();
		po.sethotelid(vo.gethotelid());
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setcontact(vo.getcontact());
		return po;
	}
	
	public HotelWorker hotelworkervo_to_hotelworker(HotelWorkerVO vo){
		HotelWorker hw=new HotelWorker();
		hw.sethotelid(vo.gethotelid());
		hw.setusername(vo.getusername());
		hw.setpassword(vo.getpassword());
		hw.setname(vo.getname());
		hw.setcontact(vo.getcontact());
		return hw;
	}
	
	public WebManagerPO managervo_to_managerpo(WebManagerVO vo){
		WebManagerPO po=new WebManagerPO();
		po.setwebmanagerid(vo.getwebmanagerid());
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setcontact(vo.getcontact());
		return po;
	}
	
	public Manage managervo_to_manager(WebManagerVO vo){
		Manage wm=new Manage();
		wm.setwebmanagerid(vo.getwebmanagerid());
		wm.setusername(vo.getusername());
		wm.setpassword(vo.getpassword());
		wm.setname(vo.getname());
		wm.setcontact(vo.getcontact());
		return wm;
	}
	
	public WebMarketPO marketvo_to_marketpo(WebMarketVO vo){
		WebMarketPO po=new WebMarketPO();
		po.setwebmarketid(vo.getwebmarketid());
		po.setusername(vo.getusername());
		po.setpassword(vo.getpassword());
		po.setname(vo.getname());
		po.setcontact(vo.getcontact());
		return po;
	}
	
	public WebMarket marketvo_to_market(WebMarketVO vo){
		WebMarket wm=new WebMarket();
		wm.setwebmarketid(vo.getwebmarketid());
		wm.setusername(vo.getusername());
		wm.setpassword(vo.getpassword());
		wm.setname(vo.getname());
		wm.setcontact(vo.getcontact());
		return wm;
	}
	
	public RoomOrderPO roomordervo_to_roomorderpo(RoomOrderVO vo){
		RoomOrderPO po=new RoomOrderPO();
		po.setroom_type(vo.getroom_type());
		po.setroom_number(vo.getroom_number());
		po.setnum_of_days(vo.getnum_of_days());
		return po;
	}
	
	public RoomOrder roomordervo_to_roomorder(RoomOrderVO vo) {
		// TODO Auto-generated method stub
		RoomOrder order=new RoomOrder();
		order.setroom_type(vo.getroom_type());
		order.setroom_number(vo.getroom_number());
		order.setnum_of_days(vo.getnum_of_days());
		return order;
	}
	
	public RoomPO roomvo_to_roompo(RoomVO vo){
		RoomPO po=new RoomPO();
		po.setid(vo.getid());
		po.sethotelid(vo.gethotelid());
		po.setroom_type(vo.getroom_type());
		po.settotal_num(vo.gettotal_num());
		po.setavailable_num(vo.getavailable_num());
		po.setprice(vo.getprice());
		return po;
		
	}
	
	public VIPInfo vipinfovo_tovioinfo(VIPInfoVO vo){
		VIPInfo vipinfo=new VIPInfo();
		if(vo.getType()==VIPType.Enterprise){
		vipinfo.setType(objects.VIPInfo.VIPType.Enterprise);
		}
		else{
			vipinfo.setType(objects.VIPInfo.VIPType.NORMAL);
		}
		vipinfo.setInfo(vo.getInfo());
		return vipinfo;
	}
	
}
