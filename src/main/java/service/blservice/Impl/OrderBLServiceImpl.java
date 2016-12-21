package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import objects.HotelStrategy1;
import objects.HotelStrategy2;
import objects.HotelStrategy3;
import objects.HotelStrategy4;
import objects.HotelStrategy5;
import objects.ResultMessage;
import objects.WebStrategy1;
import objects.WebStrategy2;
import objects.WebStrategy3;
import objects.WebStrategy4;
import objects.WebStrategy5;
import po.HotelPO;
import po.OrderPO;
import po.RoomOrderPO;
import po.RoomPO;
import service.BL;
import service.VOChange;
import service.blservice.OrderBLService;
import service.blservice.StrategyBLService;
import service.dataservice.ClientDataService;
import service.dataservice.HotelDataService;
import service.dataservice.OrderDataService;
import service.dataservice.RoomDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import service.dataservice.Impl.HotelDataServiceImpl;
import service.dataservice.Impl.OrderDataServiceImpl;
import service.dataservice.Impl.RoomDataServiceImpl;
import vo.AccommodationVO;
import vo.HotelStrategyVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.WebStrategyVO;

public class OrderBLServiceImpl implements OrderBLService {
	OrderDataService orderdataservice=new OrderDataServiceImpl();
	RoomDataService roomdataservice=new RoomDataServiceImpl();
	StrategyBLService strategyblservice=new StrategyBLServiceImpl();
	ClientDataService clientblservice=new ClientDataServiceImpl();
	HotelDataService hoteldataservice=new HotelDataServiceImpl();
	VOChange vochange =new VOChange();
	BL bl=new BL();
	@Override
	public ArrayList<OrderVO> findorderByClientid(int clientid) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByClientid(clientid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Clientid_State(int clientid, String state) {
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, state, true);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, state, false);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list1.size();i++){
			OrderVO ordervo=orderpo_list1.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list2.size();i++){
			OrderVO ordervo=orderpo_list2.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Clientid_Execute(int clientid, boolean isExecute) {
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, "NORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, "ABNORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list3=orderdataservice.findByStatus(clientid, "CANCELLED", isExecute);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list1.size();i++){
			OrderVO ordervo=orderpo_list1.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list2.size();i++){
			OrderVO ordervo=orderpo_list2.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list3.size();i++){
			OrderVO ordervo=orderpo_list3.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderByHotelid(int hotelid) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Hotelid_State(int hotelid, String state) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			if(orderpo_list.get(i).getstate()==state){
				OrderVO ordervo=orderpo_list.get(i).changetoordervo();
				ordervo_list.add(ordervo);
			}
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Hotelid_Execute(int hotelid, boolean isExecute) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			if(orderpo_list.get(i).getexecute()==isExecute){
				OrderVO ordervo=orderpo_list.get(i).changetoordervo();
				ordervo_list.add(ordervo);
			}
		}
		return ordervo_list;
	}

	@Override
	public ResultMessage order_client_cancel( int orderid) {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstate("已撤销");
		Date time=new Date();
		orderpo.setcancel_time(time);
		ResultMessage result=orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) {
		OrderPO po=vochange.ordervo_to_orderpo(vo);
		ResultMessage result=orderdataservice.insert(po);
		HotelPO hotelpo=hoteldataservice.findByid(vo.gethotelid());
		ArrayList<Integer> clientidlist=hotelpo.getbook_clientid();
		int newid=vo.getclientid();
		boolean hh=true;
		for(int i=0;i<clientidlist.size();i++){
			if(newid==clientidlist.get(i)){
				hh=false;
				break;
			}
		}
		if(hh){
			clientidlist.add(newid);
		}
		hotelpo.setbook_clientid(clientidlist);
		return result;
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) {
		OrderPO po=orderdataservice.findByid(orderid);
		po.setexecute(true);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() {
		ArrayList<OrderPO> unfilled_order_list=orderdataservice.findByState("异常");
		ArrayList<OrderVO> list=new ArrayList<OrderVO>();
		for(int i=0;i<unfilled_order_list.size();i++){
			OrderVO ordervo=unfilled_order_list.get(i).changetoordervo();
			list.add(ordervo);
		}
		return list;
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) {
		OrderPO po=orderdataservice.findByid(orderid);
		po.setstate("撤销");
		Date time=new Date();
		po.setcancel_time(time);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid) throws RemoteException {
		double price = 0;
		ArrayList<RoomPO> roomlistttt=roomdataservice.find(hotelid);
		
		for(int i=0;i<roomlist.size();i++){
			RoomOrderVO roomvo=roomlist.get(i);
			for(int j=0;j<roomlistttt.size();j++){
				if(roomvo.getroom_type().equals(roomlistttt.get(j).getroom_type())){
					price+=roomvo.getnum_of_days()*roomvo.getroom_number()*roomlistttt.get(j).getprice();
					break;
				}
			}
		}
		
		return price;
	}

	@Override
	public double calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid,int clientid) throws RemoteException {
		int roomnumber = 0;
		double price = 0;
		ArrayList<RoomPO> roomlistttt=roomdataservice.find(hotelid);
		for(int i=0;i<roomlist.size();i++){
			RoomOrderVO roomvo=roomlist.get(i);
			for(int j=0;j<roomlistttt.size();j++){
				if(roomvo.getroom_type().equals(roomlistttt.get(j).getroom_type())){
					price+=roomvo.getnum_of_days()*roomvo.getroom_number()*roomlistttt.get(j).getprice();
					roomnumber+=roomvo.getroom_number();
					break;
				}
			}
		}
		
		//获得订单本来总价和房间数量
		ArrayList<Double> price_list=new ArrayList<Double>();
		ArrayList<HotelStrategyVO>  hotelstrategy_list=strategyblservice.getHotelStrategy(hotelid);
		ArrayList<WebStrategyVO> webstrategy_list=strategyblservice.getWebStrategy();
		//ArrayList<HotelStrategyVO> adapthotelstrategy=new ArrayList<HotelStrategyVO>();
		//ArrayList<WebStrategyVO> adaptwebstrategy=new ArrayList<WebStrategyVO>();
		
		Date nowdate=new Date();
		for(int i=0;i<hotelstrategy_list.size();i++){
		HotelStrategyVO hotelstrategyvo=hotelstrategy_list.get(i);
		if(nowdate.after(hotelstrategyvo.getstart_time())&&nowdate.before(hotelstrategyvo.getend_time())){
		switch(hotelstrategyvo.getid()){
		case 1:
			HotelStrategy1 hs1=new HotelStrategy1();
			price=hs1.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
		case 2:
			HotelStrategy2 hs2=new HotelStrategy2();
			price=hs2.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
		case 3:
			HotelStrategy3 hs3=new HotelStrategy3();
			price=hs3.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
		case 4:
			HotelStrategy4 hs4=new HotelStrategy4();
			price=hs4.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
		case 5:
			HotelStrategy5 hs5=new HotelStrategy5();
			price=hs5.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
			
		}
		}
		}
		
		
		for(int i=0;i<webstrategy_list.size();i++){
		if(nowdate.after(webstrategy_list.get(i).getstart_time())&&nowdate.before(webstrategy_list.get(i).getend_time())){
		switch(webstrategy_list.get(i).getid()){
		case 1:
			WebStrategy1 ws1=new WebStrategy1();
			price=ws1.calculate(clientid, hotelid, price, roomnumber);
			price_list.add(price);
			break;
		case 2:
			WebStrategy2 ws2=new WebStrategy2();
		   	price=ws2.calculate(clientid, hotelid, price, roomnumber);
		    price_list.add(price);
		    break;
		case 3:
		case 4:
		case 5:
		   WebStrategy3 ws3=new WebStrategy3();
		   price=ws3.calculate(clientid, hotelid, price, roomnumber);
		   WebStrategy5 ws5=new WebStrategy5();
		   ws5.setcondition(webstrategy_list.get(i).getcondition());
		   price=ws5.calculate(clientid, hotelid, price, roomnumber);
		   price_list.add(price);
		   break;
		case 6:
			WebStrategy4 ws4=new WebStrategy4();
		   	price=ws4.calculate(clientid, hotelid, price, roomnumber);
		    price_list.add(price);
		}
		}
		
		}
		
		
		double min_price=price_list.get(0);
		for(int i=1;i<price_list.size();i++){
			if(min_price>price_list.get(i)){
				min_price=price_list.get(i);
			}
		}
		return min_price;
	}


	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstart_time(info.getCheckIn());
		orderpo.setend_time(info.getPlanCheckOut());
		ResultMessage result =orderdataservice.update(orderpo);
//		ArrayList<RoomPO> room_list=roomdataservice.find(orderpo.gethotelid());
//		ArrayList<RoomOrderPO> roomorder=orderpo.getroom_order();
//		for(int i=0;i<roomorder.size();i++){
//			for(int j=0;j<room_list.size();i++){
//				if(roomorder.get(i).getroom_type().equals(room_list.get(i).getroom_type())){
//					RoomPO roompo=room_list.get(i);
//					roompo.setavailable_num(roompo.getavailable_num()-roomorder.get(j).getroom_number());
//					roomdataservice.update(roompo);
//				}
//			}
//		}
		roomdataservice.reduce(orderpo);
		return result;
	}

	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		OrderVO ordervo=orderpo.changetoordervo();
		return ordervo;
	}

	@Override
	public ResultMessage order_checkout(int orderid) {
		Date date= new Date();
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setend_time(date);
		ResultMessage result=orderdataservice.update(orderpo);
		roomdataservice.add(orderpo);
		return result;
	}

	@Override
	public ResultMessage offline_checkin(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		ArrayList <RoomOrderPO> roomorderpo=new ArrayList<RoomOrderPO>();
		for(int i=0;i<room_order.size();i++){
			roomorderpo.add(vochange.roomordervo_to_roomorderpo(room_order.get(i)));
		}
		ResultMessage result=roomdataservice.addOffline(hotelid, roomorderpo);
		return result;
	}

	@Override
	public ResultMessage offline_checkout(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		ArrayList <RoomOrderPO> roomorderpo=new ArrayList<RoomOrderPO>();
		for(int i=0;i<room_order.size();i++){
			roomorderpo.add(vochange.roomordervo_to_roomorderpo(room_order.get(i)));
		}
		ResultMessage result=roomdataservice.reduceOffline(hotelid, roomorderpo);
		return result;
	}

	@Override
	public ArrayList<OrderVO> get_client_hotel_order(int clientid, int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> orderpolist=orderdataservice.findByClientid(clientid);
		ArrayList<OrderVO> ordervolist=new ArrayList<OrderVO>();
		for(int i=0;i<orderpolist.size();i++){
			OrderPO po=orderpolist.get(i);
			if(po.gethotelid()==hotelid){
				ordervolist.add(po.changetoordervo());
			}
		}
		return ordervolist;
	}

}
