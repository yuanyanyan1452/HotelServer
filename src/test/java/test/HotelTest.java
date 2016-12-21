package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import service.blservice.Impl.HotelBLServiceImpl;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.RoomVO;

public class HotelTest {
	HotelBLServiceImpl hotelblImpl = new HotelBLServiceImpl();
	
	@Test
	public void testhotelworker_login() throws RemoteException{
		ResultMessage result=hotelblImpl.hotelworker_login("Matin", "yyj");
		
		assertEquals(ResultMessage.Success, result);
	}
	
	@Test
	public void testhotelworker_getvo() throws RemoteException{
		HotelWorkerVO hotelworkervo=hotelblImpl.hotelworker_getvo("Matin");
		HotelWorkerVO testhotelworkervo=new HotelWorkerVO(1,"Susan","1111","Matin","yyj");
		
		assertEquals(testhotelworkervo,hotelworkervo);
	}
	
	@Test
	public void testhotelworker_changepassword() throws RemoteException{
		ResultMessage result=hotelblImpl.hotelworker_change_password("Matin","yyj", "newpassword");
		
		assertEquals(ResultMessage.Success, result);
	}
	
	@Test
	public void testhotel_checkInfo(){
		HotelVO hotelVO = hotelblImpl.hotel_getInfo(1);
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testHotel=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		
		assertEquals(testHotel,hotelVO);
	}
	
	@Test
	public void testhotel_updateInfo(){
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testHotel=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		
		assertEquals(ResultMessage.Success,hotelblImpl.hotel_updateInfo(testHotel));
	}
	
	@Test
	public void testhotel_importRoom(){
		RoomVO roomvo=new RoomVO(0,3,"套房",10,10,700);
		
		assertEquals(ResultMessage.Success,hotelblImpl.hotel_importRoom(roomvo));
	}
	
//	@Test
//	public void testhotel_updateAccomodation() throws RemoteException{
//		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		AccommodationVO accomodationvo;
//		try {
//			accomodationvo = new AccommodationVO("314",fmt.parse("2016-11-12 14:00:00"), fmt.parse("2016-11-15 13:10:15"), fmt.parse("2016-11-15 14:00:00"));
//			int orderid =1;
//			
//			assertEquals(ResultMessage.Success,hotelblImpl.hotel_updateAccomodation(accomodationvo,orderid));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	@Test
	public void testsearchHotelByLocation(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testhotelvo=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		
		ArrayList<String>evalu2=new ArrayList<String>();
		evalu2.add("nice");
		evalu2.add("good but a little expensive");
		evalu2.add("nice");
		evalu2.add("nice");
		ArrayList<Integer> book_id2=new ArrayList<Integer>();
		book_id2.add(1);
		HotelVO testhotelvo2=new HotelVO(2,"南京金陵酒店","南京市汉中路2号","新街口","四星级商务酒店",
				"餐饮，住房，洗浴","四星级","4.56,34",evalu,book_id);
		
		testhotelvo_list.add(testhotelvo);
		testhotelvo_list.add(testhotelvo2);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelvo_list);
	}
	
	@Test
	public void testsearchHotelByname(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testhotelvo=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelByname(hotelblImpl.searchHotelBylocation("南京","新街口"),"天丰大酒店");
		assertEquals(testhotelvo_list,hotelvo_list);
	}

	@Test
	public void testsearchHotelByroom(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testhotelvo=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		
		ArrayList<String>evalu2=new ArrayList<String>();
		evalu2.add("nice");
		evalu2.add("good but a little expensive");
		evalu2.add("nice");
		evalu2.add("nice");
		ArrayList<Integer> book_id2=new ArrayList<Integer>();
		book_id2.add(1);
		HotelVO testhotelvo2=new HotelVO(2,"南京金陵酒店","南京市汉中路2号","新街口","四星级商务酒店",
				"餐饮，住房，洗浴","四星级","4.56,34",evalu,book_id);
		testhotelvo_list.add(testhotelvo2);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelblImpl.searchHotelByroom(hotelvo_list, "大床房"));
	}

	@Test
	public void testsearchHotelByprice(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testhotelvo=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		
		ArrayList<String>evalu2=new ArrayList<String>();
		evalu2.add("nice");
		evalu2.add("good but a little expensive");
		evalu2.add("nice");
		evalu2.add("nice");
		ArrayList<Integer> book_id2=new ArrayList<Integer>();
		book_id2.add(1);
		HotelVO testhotelvo2=new HotelVO(2,"南京金陵酒店","南京市汉中路2号","新街口","四星级商务酒店",
				"餐饮，住房，洗浴","四星级","4.56,34",evalu,book_id);
		testhotelvo_list.add(testhotelvo2);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelblImpl.searchHotelByprice(hotelvo_list, 400, 600));
	}

//	@Test
//	public void testsearchHotelBytime(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
//
	@Test
	public void testsearchHotelBystar(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO testhotelvo=new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.2,20",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		
		ArrayList<String>evalu2=new ArrayList<String>();
		evalu2.add("nice");
		evalu2.add("good but a little expensive");
		evalu2.add("nice");
		evalu2.add("nice");
		ArrayList<Integer> book_id2=new ArrayList<Integer>();
		book_id2.add(1);
		HotelVO testhotelvo2=new HotelVO(2,"南京金陵酒店","南京市汉中路2号","新街口","四星级商务酒店",
				"餐饮，住房，洗浴","四星级","4.56,34",evalu,book_id);
		testhotelvo_list.add(testhotelvo2);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelblImpl.searchHotelBystar(hotelvo_list, "四星级"));
	}

	@Test
	public void testsearchHotelByscore(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("nice");
		evalu.add("good but a little expensive");
		evalu.add("nice");
		evalu.add("nice");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(1);
		HotelVO testhotelvo=new HotelVO(2,"南京金陵酒店","南京市汉中路2号","新街口","四星级商务酒店",
				"餐饮，住房，洗浴","四星级","4.56,34",evalu,book_id);
		testhotelvo_list.add(testhotelvo);
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelblImpl.searchHotelByscore(hotelvo_list, 4.3, 4.6));
	}
	
	@Test
	public void testpreviousHotel(){
		ArrayList<Hotel> testHotelList_Client=new ArrayList<Hotel>();
		Hotel hotel=new Hotel();
		testHotelList_Client.add(hotel);
		ArrayList<Hotel> HotelList=hotelblImpl.getpreviousHotel(1);
		
		assertEquals(testHotelList_Client,HotelList);
	}
	
	@Test
	public void testaddHotel(){
		ArrayList<String>evalu=new ArrayList<String>();
		ArrayList<Integer>book_id=new ArrayList<Integer>();
		Hotel hotel = new Hotel(4,"凯撒酒店","徐州市经济开发区","金山桥","四星级商务酒店","wifi,餐饮，洗浴，娱乐","四星级",null,evalu,book_id);
		
		assertEquals(ResultMessage.Success,hotelblImpl.addHotel(hotel));
	}
	
	@Test
	public void testaddHotelWorker(){
		HotelWorker hotelworker=new HotelWorker(1,"Susan","1111","Matin","yyj");
		ResultMessage result=hotelblImpl.addHotelWorker(hotelworker);
		
		assertEquals(ResultMessage.Fail,result);
	}
	
	@Test
	public void testsearchHotelWorker(){
		HotelWorker testhotelworker=new HotelWorker(1,"Susan","1111","Matin","newpassword");
		HotelWorker hotelworker=hotelblImpl.searchHotelWorker(1);
		
		assertEquals(testhotelworker,hotelworker);
	}
	
	@Test
	public void testupdateHotelWokerInfo(){
		HotelWorker hotelworker=new HotelWorker(1,"Susan","1111","Matin","ddm");
		ResultMessage result=hotelblImpl.updateHotelWokerInfo(hotelworker);
		
		assertEquals(ResultMessage.Success,result);
	}
	
	@Test
	public void testevaluatehotel() throws RemoteException{
		EvaluationVO e=new EvaluationVO(4.2,"nice");
		int clientid=1;
		int hotelid=1;
		ResultMessage result=hotelblImpl.evalutehotel(e,clientid,hotelid);
		
		assertEquals(ResultMessage.Success, result);
	}
	
	
	
}
