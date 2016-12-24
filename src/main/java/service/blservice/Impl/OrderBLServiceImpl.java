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
import objects.Strategy;
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
import service.datafactory.datafactory;
import service.datafactory.datafactoryImpl;
import vo.AccommodationVO;
import vo.OrderVO;
import vo.RoomOrderVO;

public class OrderBLServiceImpl implements OrderBLService {
	datafactory datafactory=new datafactoryImpl();
	StrategyBLService strategyblservice=new StrategyBLServiceImpl();
	VOChange vochange =new VOChange();
	BL bl=new BL();
	
	@Override
	public ArrayList<OrderVO> findorderByClientid(int clientid) {
		ArrayList<OrderPO> orderpo_list=datafactory.getOrderDataService().findByClientid(clientid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Clientid_State(int clientid, String state) {
		ArrayList<OrderPO> orderpo_list1=datafactory.getOrderDataService().findByStatus(clientid, state, true);
		ArrayList<OrderPO> orderpo_list2=datafactory.getOrderDataService().findByStatus(clientid, state, false);
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
		ArrayList<OrderPO> orderpo_list1=datafactory.getOrderDataService().findByStatus(clientid, "NORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list2=datafactory.getOrderDataService().findByStatus(clientid, "ABNORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list3=datafactory.getOrderDataService().findByStatus(clientid, "CANCELLED", isExecute);
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
		ArrayList<OrderPO> orderpo_list=datafactory.getOrderDataService().findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Hotelid_State(int hotelid, String state) {
		ArrayList<OrderPO> orderpo_list=datafactory.getOrderDataService().findByHotelid(hotelid);
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
		ArrayList<OrderPO> orderpo_list=datafactory.getOrderDataService().findByHotelid(hotelid);
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
		OrderPO orderpo=datafactory.getOrderDataService().findByid(orderid);
		orderpo.setstate("已撤销");
		Date time=new Date();
		orderpo.setcancel_time(time);
		ResultMessage result=datafactory.getOrderDataService().update(orderpo);
//		ArrayList<RoomPO> roomlist=roomdataservice.find(orderpo.gethotelid());
//		ArrayList<RoomOrderPO> roomorderlist=orderpo.getroom_order();
		datafactory.getRoomDataService().add(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) {
		OrderPO po=vochange.ordervo_to_orderpo(vo);
		ResultMessage result=datafactory.getOrderDataService().insert(po);
		HotelPO hotelpo=datafactory.getHotelDataService().findByid(vo.gethotelid());
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
		datafactory.getHotelDataService().update(hotelpo);
		datafactory.getRoomDataService().reduce(po);
		return result;
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) {
		OrderPO po=datafactory.getOrderDataService().findByid(orderid);
		po.setexecute(true);
		ResultMessage result=datafactory.getOrderDataService().update(po);
		return result;
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() {
		ArrayList<OrderPO> unfilled_order_list=datafactory.getOrderDataService().findByState("异常");
		ArrayList<OrderVO> list=new ArrayList<OrderVO>();
		for(int i=0;i<unfilled_order_list.size();i++){
			OrderVO ordervo=unfilled_order_list.get(i).changetoordervo();
			list.add(ordervo);
		}
		return list;
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) {
		OrderPO po=datafactory.getOrderDataService().findByid(orderid);
		po.setstate("撤销");
		Date time=new Date();
		po.setcancel_time(time);
		ResultMessage result=datafactory.getOrderDataService().update(po);
		return result;
	}

	@Override
	public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid) throws RemoteException {
		double price = 0;
		ArrayList<RoomPO> roomlistttt=datafactory.getRoomDataService().find(hotelid);
		
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
		//获得订单本来总价和房间数量
		int roomnumber = 0;
		double price = 0;
		ArrayList<RoomPO> roomlistttt=datafactory.getRoomDataService().find(hotelid);
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
		
		double price1=0;
	
		ArrayList<Double> price_list=new ArrayList<Double>();
//		ArrayList<HotelStrategyVO>  hotelstrategy_list=strategyblservice.getHotelStrategy(hotelid);
//		ArrayList<WebStrategyVO> webstrategy_list=strategyblservice.getWebStrategy();
		price_list.add(price);
		
		//策略模式
		HotelStrategy1 h1=new HotelStrategy1();
		HotelStrategy2 h2=new HotelStrategy2();
		HotelStrategy3 h3=new HotelStrategy3();
		HotelStrategy4 h4=new HotelStrategy4();
		HotelStrategy5 h5=new HotelStrategy5();
		WebStrategy1 w1=new WebStrategy1();
		WebStrategy2 w2=new WebStrategy2();
		WebStrategy3 w3=new WebStrategy3();
		WebStrategy4 w4=new WebStrategy4();
		WebStrategy5 w5=new WebStrategy5();
		Strategy strategy=new Strategy(h1);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(h2);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(h3);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(h4);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(h5);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(w1);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(w2);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(w3);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(w4);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		strategy.setcalculate(w5);
		price1=strategy.getprice(clientid, hotelid, price, roomnumber);
		price_list.add(price1);
		
		double min_price=price;
		if(!price_list.isEmpty()){
		min_price=price_list.get(0);
		for(int i=1;i<price_list.size();i++){
			if(min_price>price_list.get(i)){
				min_price=price_list.get(i);
			}
		}
		}
		return min_price;
	}


	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		OrderPO orderpo=datafactory.getOrderDataService().findByid(orderid);
		orderpo.setstart_time(info.getCheckIn());
		orderpo.setend_time(info.getPlanCheckOut());
		ResultMessage result =datafactory.getOrderDataService().update(orderpo);
		return result;
	}

	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		OrderPO orderpo=datafactory.getOrderDataService().findByid(orderid);
		OrderVO ordervo=orderpo.changetoordervo();
		return ordervo;
	}

	@Override
	public ResultMessage order_checkout(int orderid) {
		Date date= new Date();
		OrderPO orderpo=datafactory.getOrderDataService().findByid(orderid);
		orderpo.setend_time(date);
		ResultMessage result=datafactory.getOrderDataService().update(orderpo);
		datafactory.getRoomDataService().add(orderpo);
		
		return result;
	}

	@Override
	public ResultMessage offline_checkin(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		ArrayList <RoomOrderPO> roomorderpo=new ArrayList<RoomOrderPO>();
		for(int i=0;i<room_order.size();i++){
			roomorderpo.add(vochange.roomordervo_to_roomorderpo(room_order.get(i)));
		}
		ResultMessage result=datafactory.getRoomDataService().reduceOffline(hotelid, roomorderpo);
		return result;
	}

	@Override
	public ResultMessage offline_checkout(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		ArrayList <RoomOrderPO> roomorderpo=new ArrayList<RoomOrderPO>();
		for(int i=0;i<room_order.size();i++){
			roomorderpo.add(vochange.roomordervo_to_roomorderpo(room_order.get(i)));
		}
		ResultMessage result=datafactory.getRoomDataService().addOffline(hotelid, roomorderpo);
		return result;
	}

	@Override
	public ArrayList<OrderVO> get_client_hotel_order(int clientid, int hotelid) throws RemoteException {
		ArrayList<OrderPO> orderpolist=datafactory.getOrderDataService().findByClientid(clientid);
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
