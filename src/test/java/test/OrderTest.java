package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import mock.MockClient10;
import mock.MockHotel10;
import mock.MockOrder10;
import mock.MockOrder11;
import mock.MockOrder12;
import mock.MockRoom10;
import objects.HotelStrategy;
import objects.OrderState;
import objects.ResultMessage;
import objects.RoomType;
import objects.WebStrategy;
import service.blservice.Impl.OrderBLServiceImpl;
import vo.OrderVO;
import vo.RoomOrderVO;

public class OrderTest {

	OrderBLServiceImpl OrderImpl = new OrderBLServiceImpl();
	
	
	@Test
	public void testorder_client_browse1() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,2));
		roomorderlist.add(new RoomOrderVO("大床房",1,2));
		OrderVO ordervo1 = new OrderVO(2,3,2,"异常",null,
				false,String2Date("2016-11-08 04:18:36"),String2Date("2016-11-10 08:30:41"),String2Date("2016-11-08 12:00:00"),roomorderlist,2500,3,false,"bad");
		expect_list.add(ordervo1);

		ArrayList<OrderVO> orderlist = OrderImpl.findorderByClientid(3);
		assertEquals(expect_list, orderlist);
	}

	@Test

	public void testorder_client_browse2() {
		
		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,2));
		roomorderlist.add(new RoomOrderVO("大床房",1,2));
		OrderVO ordervo1 = new OrderVO(2,3,2,"异常",null,
				false,String2Date("2016-11-08 04:18:36"),String2Date("2016-11-10 08:30:41"),String2Date("2016-11-08 12:00:00"),roomorderlist,2500,3,false,"bad");
		expect_list.add(ordervo1);
		
		ArrayList<OrderVO> orderlist = OrderImpl.findorderBy_Clientid_State(3,"异常");
		assertEquals(expect_list, orderlist);

	}

	@Test

	public void testorder_client_browse3() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,2));
		roomorderlist.add(new RoomOrderVO("大床房",1,2));
		OrderVO ordervo1 = new OrderVO(2,3,2,"异常",null,
				false,String2Date("2016-11-08 04:18:36"),String2Date("2016-11-10 08:30:41"),String2Date("2016-11-08 12:00:00"),roomorderlist,2500,3,false,"bad");
		expect_list.add(ordervo1);
		
		ArrayList<OrderVO> orderlist = OrderImpl.findorderBy_Clientid_Execute(3,false);

		assertEquals(expect_list, orderlist);

	}

	@Test

	public void testorder_hotel_browse1() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,3));
		OrderVO ordervo1 = new OrderVO(1,1,1,"正常",null,
				true,String2Date("2016-11-12 14:00:00"),String2Date("2016-11-15 13:10:25"),String2Date("2016-11-12 16:02:03"),roomorderlist,1050,2,true,"just so so");
		expect_list.add(ordervo1);

		ArrayList<OrderVO> orderlist = OrderImpl.findorderByHotelid(1);
		
		assertEquals(expect_list, orderlist);

	}

	@Test

	public void testorder_hotel_browse2() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,3));
		OrderVO ordervo1 = new OrderVO(1,1,1,"正常",null,
				true,String2Date("2016-11-12 14:00:00"),String2Date("2016-11-15 13:10:25"),String2Date("2016-11-12 16:02:03"),roomorderlist,1050,2,true,"just so so");
		expect_list.add(ordervo1);

		ArrayList<OrderVO> orderlist = OrderImpl.findorderBy_Hotelid_State(1,"正常");

		assertEquals(expect_list, orderlist);

	}

	@Test

	public void testorder_hotel_browse3() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,3));
		OrderVO ordervo1 = new OrderVO(1,1,1,"正常",null,
				true,String2Date("2016-11-12 14:00:00"),String2Date("2016-11-15 13:10:25"),String2Date("2016-11-12 16:02:03"),roomorderlist,1050,2,true,"just so so");
		expect_list.add(ordervo1);

		ArrayList<OrderVO> orderlist = OrderImpl.findorderBy_Hotelid_Execute(1,true);

		assertEquals(expect_list, orderlist);

	}

	@Test

	public void testorder_client_cancel() {

		ResultMessage flag = OrderImpl.order_client_cancel(8);
		assertEquals(ResultMessage.Success, flag);

	}

	@Test

	public void testorder_client_generate() {
		ArrayList<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		OrderVO ordervo = new OrderVO(1,1,1,"1",null,true,null,null,null,list,1,1,false,null);

		ResultMessage flag = OrderImpl.order_client_generate(ordervo);

		assertEquals(ResultMessage.Success, flag);

	}

	@Test

	public void testorder_hotel_execute() {

		ResultMessage flag = OrderImpl.order_hotel_execute(7);

		assertEquals(ResultMessage.Success, flag);

	}

	@Test

	public void testorder_market_browseUnfilled() {

		ArrayList<OrderVO> expect_list = new ArrayList<OrderVO>();
		ArrayList<RoomOrderVO> roomorderlist = new ArrayList<RoomOrderVO>();
		roomorderlist.add(new RoomOrderVO("标准间",1,2));
		roomorderlist.add(new RoomOrderVO("大床房",1,2));
		OrderVO ordervo1 = new OrderVO(2,3,2,"异常",null,
				false,String2Date("2016-11-08 04:18:36"),String2Date("2016-11-10 08:30:41"),String2Date("2016-11-08 12:00:00"),roomorderlist,2500,3,false,"bad");
		expect_list.add(ordervo1);

		assertEquals(expect_list, OrderImpl.order_market_browseUnfilled());

	}

	@Test

	public void testorder_market_cancelAbnormal() {

		ResultMessage flag = OrderImpl.order_market_cancelAbnormal(7);

		assertEquals(ResultMessage.Success, flag);

	}

	@Test

	public void testcalculateTotalwithoutStrategy() throws RemoteException {

		ArrayList<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		list.add(new RoomOrderVO("标准间",1,1));
		
		OrderBLServiceImpl OrderImpl = new OrderBLServiceImpl();

		int total = (int)OrderImpl.calculateTotalwithoutStrategy(list, 1);

		assertEquals(350, total);

	}

	@Test

	public void testcalculateTotalwithStrategy() throws RemoteException {

		ArrayList<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		list.add(new RoomOrderVO("标准间",1,1));

		OrderBLServiceImpl OrderImpl = new OrderBLServiceImpl();

		int total = (int)OrderImpl.calculateTotalwithStrategy(list, 1, 1);

		assertEquals(200, total);

	}

//	@Test
//
//	public void testupdateActualLeaveTime() {
//
//		OrderBLServiceImpl OrderImpl = new OrderBLServiceImpl();
//
//		ResultMessage result = OrderImpl.updateActualLeaveTime(7, String2Date("2016-12-1-12-00-00"));
//
//		assertEquals(ResultMessage.Success, result);
//
//	}
	
	Date String2Date(String str){
		Date date;
		try{  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		    date = sdf.parse(str);  
		    return date;
		} catch (ParseException e)  {  
		    System.out.println(e.getMessage()); 
		    return null;
		}  

	}

}
