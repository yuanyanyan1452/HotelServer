package driver;


import java.util.ArrayList;
import vo.*;
import service.*;
import service.blservice.*;
import objects.*;



public class HotelBLService_driver {

	public void drive(HotelBLService hotelBLService){

		//hotel_checkInfo
		HotelVO hotelVO = hotelBLService.hotel_getInfo(1);
		System.out.println(hotelVO.getname());
		System.out.println(hotelVO.getaddress());
		System.out.println(hotelVO.getbussiness_address());
		System.out.println(hotelVO.getintroduction());
		System.out.println(hotelVO.getservice());
		System.out.println(hotelVO.gethotel_evaluation());

		

		//hotel_updateInfo
		ResultMessage resultMessage = hotelBLService.hotel_updateInfo(
				new HotelVO(1,"�Ͼ�","�½ֿ�", "���", "��ҾƵ�", "�Ƶ����з���", "����", "˫�˷�", 1, 100, "", "good"));
		if(resultMessage == ResultMessage.Success){
			System.out.println("���¾Ƶ���Ϣ�ɹ�");
		}
		else {
			System.out.println("���¾Ƶ���Ϣʧ��");
		}

		

		//hotel_importRoom
		Room room=new Room();
		resultMessage = hotelBLService.hotel_importRoom(room);
		if (resultMessage==ResultMessage.Success) {
			System.out.println("¼����÷���ɹ�");
		}
		else {
			System.out.println("¼����÷���ʧ��");
		}

		

		//hotel_updateAccomdation
		AccommodationVO ac=new AccommodationVO(null, null, null, null);
		resultMessage = hotelBLService.hotel_updateInfo(ac);
		if (resultMessage == ResultMessage.Success) {
			System.out.println("������ס��Ϣ�ɹ�");
		}
		else {
			System.out.println("������ס��Ϣʧ��");
		}
	}
}


