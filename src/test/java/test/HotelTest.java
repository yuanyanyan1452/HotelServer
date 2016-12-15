package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import service.blservice.Impl.HotelBLServiceImpl;
import vo.AccommodationVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.RoomVO;

public class HotelTest {
	HotelBLServiceImpl hotelblImpl = new HotelBLServiceImpl();
	
	@Test
	public void testhotelworker_login() throws RemoteException{
		ResultMessage result=hotelblImpl.hotelworker_login("Matin", "yyj");
		
		assertEquals(ResultMessage.Success, result);;
	}
	
	@Test
	public void testhotelqorker_getvo() throws RemoteException{
		HotelWorkerVO hotelworkervo=hotelblImpl.hotelworker_getvo("Matin");
		HotelWorkerVO testhotelworkervo=new HotelWorkerVO();
		
		assertEquals(testhotelworkervo,hotelworkervo);
	}
	
	@Test
	public void testhotelworker_changepassword() throws RemoteException{
		ResultMessage result=hotelblImpl.hotelworker_change_password("Matin","yyj", "newpassword");
		
		assertEquals(ResultMessage.Success, result);
	}
	
	@Test
	public void testhotel_checkInfo(){
		HotelVO hotelVO = hotelblImpl.hotel_checkInfo(1);
		HotelVO testHotel = new HotelVO();
		
		assertEquals(testHotel,hotelVO);
	}
	
	@Test
	public void testhotel_updateInfo(){
		HotelVO testHotel = new HotelVO();
		
		assertEquals(ResultMessage.Success,hotelblImpl.hotel_updateInfo(testHotel));
	}
	
	@Test
	public void testhotel_importRoom(){
		RoomVO roomvo=new RoomVO();
		
		assertEquals(ResultMessage.Success,hotelblImpl.hotel_importRoom(roomvo));
	}
	
	@Test
	public void testhotel_updateAccomodation() throws RemoteException{
		AccommodationVO accomodationvo=new AccommodationVO(null, null, null, null);
		int orderid =1;
		
		assertEquals(ResultMessage.Success,hotelblImpl.hotel_updateAccomodation(accomodationvo,orderid));
	}
	
	@Test
	public void testsearchHotelByLocation(){
		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
		
		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
		
		assertEquals(testhotelvo_list,hotelvo_list);
	}
	
//	@Test
//	public void testsearchHotelByname(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelByname("");
//		
//	}
//
//	@Test
//	public void testsearchHotelByroom(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
//
//	@Test
//	public void testsearchHotelByprice(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
//
//	@Test
//	public void testsearchHotelBytime(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
//
//	@Test
//	public void testsearchHotelBystar(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
//
//	@Test
//	public void testsearchHotelByscore(){
//		ArrayList<HotelVO> testhotelvo_list=new ArrayList<HotelVO>();
//		HotelVO testhotelvo=new HotelVO();
//		testhotelvo_list.add(testhotelvo);
//		ArrayList<HotelVO> hotelvo_list=hotelblImpl.searchHotelBylocation("南京","新街口");
//		
//	}
	
	@Test
	public void testpreviousHotel(){
		ArrayList<Hotel> testHotelList_Client=new ArrayList<Hotel>();
		Hotel hotel=new Hotel();
		testHotelList_Client.add(hotel);
		ArrayList<Hotel> HotelList=hotelblImpl.previousHotel(1);
		
		assertEquals(testHotelList_Client,HotelList);
	}
	
	@Test
	public void testaddHotel(){
		Hotel hotel = new Hotel();
		
		assertEquals(ResultMessage.Success,hotelblImpl.addHotel(hotel));
	}
	
	@Test
	public void testaddHotelWorker(){
		HotelWorker hotelworker=new HotelWorker();
		ResultMessage result=hotelblImpl.addHotelWorker(hotelworker);
		
		assertEquals(ResultMessage.Success,result);
	}
	
	@Test
	public void testsearchHotelWorker(){
		HotelWorker testhotekworker=new HotelWorker();
		HotelWorker hotelworker=hotelblImpl.searchHotelWorker(1);
		
		assertEquals(testhotekworker,hotelworker);
	}
	
	@Test
	public void testupdateHotelWokerInfo(){
		HotelWorker hotelworker=new HotelWorker();
		ResultMessage result=hotelblImpl.updateHotelWokerInfo(hotelworker);
		
		assertEquals(ResultMessage.Success,result);
	}
	
	@Test
	public void testevaluatehotel() throws RemoteException{
		EvaluationVO e=new EvaluationVO(0, null);
		int clientid=1;
		int hotelid=1;
		ResultMessage result=hotelblImpl.evalutehotel(e,clientid,hotelid);
		
		assertEquals(ResultMessage.Success, result);
	}
	
	
	
	
	
	
	
	
}
