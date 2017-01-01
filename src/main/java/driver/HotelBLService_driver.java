package driver;


import java.rmi.RemoteException;
import java.util.ArrayList;
import vo.*;
import service.blservice.*;
import objects.*;



public class HotelBLService_driver {

	@SuppressWarnings("null")
	public void drive(HotelBLService hotelBLService){

		//hotelworker_login
		ResultMessage resultMessage1 = null;
		try {
			resultMessage1 = hotelBLService.hotelworker_login("Matin", "ddm");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage1==ResultMessage.Success){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		
		//hotelworker_getvo
		HotelWorkerVO worker = null;
		try {
			worker = hotelBLService.hotelworker_getvo("Matin");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(worker.getname());
		System.out.println(worker.getcontact());
		System.out.println(worker.getusername());
		
		//hotelworker_change_password
		ResultMessage resultMessage2 = null;
		try {
			resultMessage2 = hotelBLService.hotelworker_change_password("Matin", "ddm", "ddl");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage2==ResultMessage.Success){
			System.out.println("修改密码成功");
		}else{
			System.out.println("修改密码失败");
		}
		
		//hotel_getInfo
		HotelVO hotelVO = null;
		try {
			hotelVO = hotelBLService.hotel_getInfo(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(hotelVO.getid());
		System.out.println(hotelVO.getname());
		System.out.println(hotelVO.getaddress());
		System.out.println(hotelVO.getbussiness_address());
		System.out.println(hotelVO.getintroduction());
		System.out.println(hotelVO.getservice());
		System.out.println(hotelVO.getstar());
		System.out.println(hotelVO.getscore());
		System.out.println(hotelVO.gethotel_evaluation());
		System.out.println(hotelVO.getbook_clientid());

		

		//hotel_updateInfo
		ArrayList<String> evaluation=new ArrayList<String>();
		evaluation.add("just so so");
		evaluation.add("bad");
		ArrayList<Integer>book=new ArrayList<Integer>();
		book.add(1);
		book.add(2);
		book.add(3);
		book.add(7);
		ResultMessage resultMessage = null;
		try {
			resultMessage = hotelBLService.hotel_updateInfo(new HotelVO(1,"天丰大酒店","南京市白下区36号", "新街口", "四星级商务酒店", "wifi,餐饮,停车", "四星级", "4.2,20", evaluation,book));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage == ResultMessage.Success){
			System.out.println("酒店信息更新成功");
		}
		else {
			System.out.println("酒店信息更新失败");
		}

		

		//hotel_importRoom
		RoomVO room=new RoomVO(1,1,"商务房",10,5,840);
		try {
			resultMessage = hotelBLService.hotel_importRoom(room);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (resultMessage==ResultMessage.Success) {
			System.out.println("增加房间成功");
		}
		else {
			System.out.println("增加房间失败");
		}

		//hotel_updateRoom
		RoomVO room2=new RoomVO(1,1,"标准间",20,6,350);
		ResultMessage resultMessage3 = null;
		try {
			resultMessage3 = hotelBLService.hotel_updateRoom(room2);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage3==ResultMessage.Success){
			System.out.println("更新可用房间成功");
		}else{
			System.out.println("更新可用房间失败");
		}

		//searchHotelBylocation
		ArrayList<HotelVO> searchByloc = null;
		try {
			searchByloc = hotelBLService.searchHotelBylocation("南京市", "新街口");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByloc);
		
		//searchHotelByname
		ArrayList<HotelVO> searchByname = null;
		try {
			searchByname = hotelBLService.searchHotelByname(searchByloc, "天丰大酒店");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByname);
		
		//searchHotelByroom
		ArrayList<HotelVO> searchByroom = null;
		try {
			searchByroom = hotelBLService.searchHotelByroom(searchByloc, "标准间");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByroom);
		
		//searchHotelByprice
		ArrayList<HotelVO> searchByprice = null;
		try {
			searchByprice = hotelBLService.searchHotelByprice(searchByloc, 300, 700);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByprice);
		
		//searchHotelBytime
		ArrayList<HotelVO> searchBytime = null;
		try {
			searchBytime = hotelBLService.searchHotelBytime(searchByloc, "2016-12-25 00:00:00", "2016-12-28 00:00:00");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchBytime);
		
		//searchHotelBystar
		ArrayList<HotelVO> searchBystar = null;
		try {
			searchBystar = hotelBLService.searchHotelBystar(searchByloc, "四星级");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchBystar);
		
		//searchHotelByscore
		ArrayList<HotelVO> searchByscore = null;
		try {
			searchByscore = hotelBLService.searchHotelByscore(searchByloc, 4.2, 4.7);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByscore);
		
		//searchpreviousHotelList
		ArrayList<HotelVO> searchprevious = null;
		try {
			searchprevious = hotelBLService.searchpreviousHotelList(searchByloc, 1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchprevious);
		
		//evaluateHotel
		EvaluationVO e = null;
		e.setScore(4.2);
		e.setComments("good");
		ResultMessage resultMessage4 = null;
		try {
			resultMessage4 = hotelBLService.evalutehotel(e, 1,1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage4==ResultMessage.Success){
			System.out.println("评价成功");
		}else{
			System.out.println("评价失败");
		}
		
		//getallroom
		ArrayList<RoomVO> roomlist = null;
		try {
			roomlist = hotelBLService.getallroom(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(roomlist);
		
		//searchHotel
		Hotel searchByid = null;
		try {
			searchByid = hotelBLService.searchHotel(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(searchByid.getname());
		 System.out.println(searchByid.getaddress());
		 System.out.println(searchByid.getbussiness_address());
		 System.out.println(searchByid.getintroduction());
		 System.out.println(searchByid.getservice());
		 System.out.println(searchByid.getstar());
		 System.out.println(searchByid.getscore());
		 System.out.println(searchByid.getevaluation());
		 System.out.println(searchByid.getbook_clientid());
		
		 //getprevioushotel
		 ArrayList<Hotel> previousList = null;
		try {
			previousList = hotelBLService.getpreviousHotel(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 System.out.println(previousList);
		 
		 //addhotel
		 ArrayList<String>addevalu=new ArrayList<String>();
		 addevalu.add("very nice");
		 ArrayList<Integer>addbook=new ArrayList<Integer>();
		 addbook.add(1);
		 Hotel add=new Hotel(22,"总统酒店","南京市仙林中心","仙林中心","五星级商务酒店","wifi,餐饮,停车","五星级","4.8,34",addevalu,addbook);
		 ResultMessage resultMessage5 = null;
		try {
			resultMessage5 = hotelBLService.addHotel(add);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if(resultMessage5==ResultMessage.Success){
			 System.out.println("添加成功");
		 }else{
			 System.out.println("添加失败");
		 }
		 
		 //addHotelWorker
		 HotelWorker addworker=new HotelWorker(1,"111","111","111","111");
		 try {
			ResultMessage resultMessage6=hotelBLService.addHotelWorker(addworker);
			if(resultMessage6==ResultMessage.Success){
				System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 //searchHotelWorker
		 try {
			HotelWorker searchworker=hotelBLService.searchHotelWorker(1);
			System.out.println(searchworker.getname());
			System.out.println(searchworker.getcontact());
			System.out.println(searchworker.getusername());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 
		 //updateHotelWokerInfo
		 HotelWorker updateworker=new HotelWorker(1,"222","111","111","111");
		 try {
			ResultMessage resultMessage7=hotelBLService.updateHotelWokerInfo(updateworker);
			if(resultMessage7==ResultMessage.Success){
				System.out.println("更新酒店工作人员信息成功");
			}else{
				System.out.println("更新酒店工作人员信息失败");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
	}
}


