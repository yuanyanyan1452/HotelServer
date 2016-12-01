package driver;
import java.util.ArrayList;
import vo.*;
import service.*;
import service.blservice.*;
import objects.*;
import objects.VIPInfo.VIPType;



public class ClientBLService_driver {

	public void drive(ClientBLService clientBLService){
		
		//����client_checkInfo
		ClientVO vo = clientBLService.client_checkInfo(1);
		System.out.println(vo.getClient_name());
		System.out.println(vo.getContact());
		System.out.println(vo.getCredit());
		ArrayList<String> list1 = vo.getCredit_record();
		for(String s:list1){
			System.out.println(list1);
		}
		VIPInfo info = vo.getInfo();
		System.out.println(info.getType());
		System.out.println(info.getInfo());


		//����client_updateInfo
		
		ResultMessage resultMessage = clientBLService.client_updateInfo(vo);
		if(resultMessage==ResultMessage.Success){
			System.out.println("���¿ͻ���Ϣ�ɹ�");
		}
		else {
			System.out.println("���¿ͻ���Ϣʧ��");
		}

		//����client_getpreviousHotelList
		ArrayList<HotelVO> list = clientBLService.client_getpreviousHotelList(1);
		HotelVO vo2 = null;
		if(list.isEmpty()){
			System.out.println("�ͻ�δ��Ԥ�����Ƶ�");
		}
		else{
			for(int i=0;i<list.size();i++){
				vo2 = list.get(i);
				System.out.println(vo2.getname());
				System.out.println(vo2.getevaluation());
			}
		}

		//����client_checkCredit
		int credit_record = clientBLService.client_checkCredit(1);
		System.out.println(credit_record);

		//����client_setLocation
		list = clientBLService.client_setLocation("�Ͼ�");
		for(int i=0;i<list.size();i++){
			vo2 = list.get(i);
			System.out.println(vo2.getname());
			System.out.println(vo2.getevaluation());
		}

		//����client_searchHotel
		list = clientBLService.client_searchHotel("condition");
		for(int i=0;i<list.size();i++){
			vo2 = list.get(i);
			System.out.println(vo2.getname());
			System.out.println(vo2.getevaluation());
		}

		//����client_checkHotelInfo
		vo2 = clientBLService.client_checkHotelInfo(1);
		System.out.println(vo2.getname());
		System.out.println(vo2.getaddress());
		System.out.println(vo2.getbussiness_address());
		System.out.println(vo2.getintroduction());
		System.out.println(vo2.getservice());
		//�˴�ʡ��һ�������
		//������

		//����client_evaluateHotel
		Evaluation e=new Evaluation(4.0,"good");
		resultMessage = clientBLService.client_evaluateHotel(e,1);
		if(resultMessage == ResultMessage.Success){
			System.out.println("���۳ɹ�");
		}
		else
			System.out.println("����ʧ��");

		//����client_enrollVIP
		VIPInfo vip=new VIPInfo(VIPType.NORMAL,"2001/1/1");
 		resultMessage = clientBLService.client_enrollVIP(vip,1);
		if(resultMessage == ResultMessage.Success)
			System.out.println("ע���Ա�ɹ�");
		else
			System.out.println("ע���Աʧ��");
	}

}
