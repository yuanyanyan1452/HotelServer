package driver;


import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import vo.*;
import service.blservice.*;
import objects.*;

public class OrderBLService_driver {
	public void drive(OrderBLService orderBLService){
		
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultMessage resultMessage=ResultMessage.Fail;
		
		ArrayList<RoomOrderVO>roomlist=new ArrayList<RoomOrderVO>();
		roomlist.add(new RoomOrderVO("标准间",1,5));
		roomlist.add(new RoomOrderVO("双人房",1,3));
		roomlist.add(new RoomOrderVO("大床房",2,4));
		
		//findorderByClientid
		ArrayList<OrderVO> client_orderlist;
		try {
			client_orderlist = orderBLService.findorderByClientid(1);
			System.out.println(client_orderlist);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//findorderBy_Clientid_State
		try {
			ArrayList<OrderVO>findByState=orderBLService.findorderBy_Clientid_State(1, "正常");
			System.out.println(findByState);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//findorderBy_Clientid_Execute
		ArrayList<OrderVO> findByExecute = null;
		try {
			findByExecute = orderBLService.findorderBy_Clientid_Execute(1, true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findByExecute);
		
		//findorderByHotelid
		ArrayList<OrderVO> findByHotelid = null;
		try {
			findByHotelid = orderBLService.findorderByHotelid(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findByHotelid);
		
		//findorderBy_Hotelid_State
		ArrayList<OrderVO> findByHotel_State = null;
		try {
			findByHotel_State = orderBLService.findorderBy_Hotelid_State(1, "正常");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findByHotel_State);
		
		//findorderBy_Hotelid_Execute
		ArrayList<OrderVO> findByHotel_Exe = null;
		try {
			findByHotel_Exe = orderBLService.findorderBy_Hotelid_Execute(1, true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findByHotel_Exe);
		
		//order_client_cancel
		try {
			resultMessage=orderBLService.order_client_cancel(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("撤销成功");
		}else{
			System.out.println("撤销失败");
		}
		
		//order_client_generate
		ArrayList<RoomOrderVO>generateRoom=new ArrayList<RoomOrderVO>();
		generateRoom.add(new RoomOrderVO("标准间",1,5));
		OrderVO generateOrder = null;
		try {
			generateOrder = new OrderVO(1,1,1,"正常",null,false,fmt.parse("2017-01-01 00:00:00"),
					fmt.parse("2017-01-05 00:00:00"),fmt.parse("2017-01-02 00:00:00"),generateRoom,1800,1,false,"nice");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultMessage=orderBLService.order_client_generate(generateOrder);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("下单成功");
		}else{
			System.out.println("下单失败");
		}
		
		//order_hotel_execute
		try {
			resultMessage=orderBLService.order_hotel_execute(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("订单已执行");
		}else{
			System.out.println("订单未执行");
		}
		
		//order_market_browseUnfilled
		ArrayList<OrderVO> market_browse = null;
		try {
			market_browse = orderBLService.order_market_browseUnfilled();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(market_browse);
		
		//order_market_cancelAbnormal
		try {
			resultMessage=orderBLService.order_market_cancelAbnormal(2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("撤销异常订单成功");
		}else{
			System.out.println("撤销异常订单失败");
		}
		
		//calculateTotalwithoutStrategy
		try {
			double totalWithoutStrate=orderBLService.calculateTotalwithoutStrategy(roomlist, 1);
			System.out.println(totalWithoutStrate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//calculateTotalwithStrategy
		double totalWithStrate = 0;
		try {
			totalWithStrate = orderBLService.calculateTotalwithStrategy(roomlist, 1, 1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(totalWithStrate);
		
		//order_findbyid
		OrderVO findOrderByid = null;
		try {
			findOrderByid = orderBLService.order_findbyid(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(findOrderByid);
		
		//order_checkin
		try {
			AccommodationVO accommodation=new AccommodationVO("314",fmt.parse("2017-01-01 00:00:00"),
					fmt.parse("2017-01-05 00:00:00"),null);
			resultMessage=orderBLService.order_checkin(accommodation, 1);
			if(resultMessage==ResultMessage.Success){
				System.out.println("入住成功");
			}else{
				System.out.println("入住失败");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//order_checkout
		try {
			resultMessage=orderBLService.order_checkout(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("退房成功");
		}else{
			System.out.println("退房失败");
		}
		
		//offline_checkin
		try {
			resultMessage=orderBLService.offline_checkin(1, roomlist);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("线下入住成功");
		}else{
			System.out.println("线下入住失败");
		}
		
		//offline_checkout
		try {
			resultMessage=orderBLService.offline_checkout(1, roomlist);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultMessage==ResultMessage.Success){
			System.out.println("线下退房成功");
		}else{
			System.out.println("线下退房失败");
		}
		
		//get_client_hotel_order
		ArrayList<OrderVO> client_hotel_order = null;
		try {
			client_hotel_order = orderBLService.get_client_hotel_order(1, 1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(client_hotel_order);
		
	}
}
