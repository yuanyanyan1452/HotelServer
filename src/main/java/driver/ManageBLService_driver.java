package driver;



import java.util.ArrayList;
import vo.*;
import service.*;
import service.blservice.*;
import objects.*;
import objects.VIPInfo.VIPType;



public class ManageBLService_driver {

	public void drive(ManageBLService manageBLService){

		//����manage_searchClient
		ClientVO vo = manageBLService.manage_searchClient(1);
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

		

		//����manage_updateClient
		ArrayList<String> credit_record = new ArrayList<String>();
		VIPInfo info1 = new VIPInfo(VIPType.NORMAL,"2000/01/01");
		ClientVO vo1 = new ClientVO(1, "����", "11111111111", 0, credit_record, info1);
		ResultMessage resultMessage = manageBLService.manage_updateClient(vo1);
		if(resultMessage==ResultMessage.Success){
			System.out.println("���¿ͻ���Ϣ�ɹ�");
		}

		else
			System.out.println("���¿ͻ���Ϣʧ��");

		

		//����manage_addHotel
		resultMessage = manageBLService.manage_addHotel(new HotelVO(1,"1","1","1","1","1","1","1",1,1,"1","1"));
		if(resultMessage==ResultMessage.Success)
			System.out.println("��ӾƵ�ɹ�");
		else
			System.out.println("��ӾƵ�ʧ��");

		

		//����manage_addHotelWorker
		resultMessage = manageBLService.manage_addHotelWorker(new HotelWorkerVO(1,"lucy", "11111111111", "���ֺ�̩"));
		if(resultMessage==ResultMessage.Success)
			System.out.println("��ӾƵ깤����Ա�ɹ�");
		else
			System.out.println("��ӾƵ깤����Աʧ��");

		

		//����manage_searchHotelWorker
		HotelWorkerVO hWorkerVO = manageBLService.manage_searchHotelWorker(1);
		System.out.println(hWorkerVO.gethotel());
		System.out.println(hWorkerVO.getname());
		System.out.println(hWorkerVO.getcontact());

		

		//����manage_updateHotelWorker
		resultMessage = manageBLService.manage_updateHotelWorker(new HotelWorkerVO(1,"1","1","1"));
		if(resultMessage==ResultMessage.Success)
			System.out.println("���¾Ƶ깤����Ա��Ϣ�ɹ�");
		else
			System.out.println("���¾Ƶ깤����Ա��Ϣʧ��");

		

		//����manage_addMarketWorker
		resultMessage = manageBLService.manage_addMarketWorker(new WebMarketVO(1,"jack", "11111111111"));
		if(resultMessage==ResultMessage.Success)
			System.out.println("�������Ӫ����Ա�ɹ�");
		else
			System.out.println("�������Ӫ����Աʧ��");

		

		//����manage_searchMarketWorker
		WebMarketVO webMarketVO = manageBLService.manage_searchMarketWorker(1);
	    System.out.println(webMarketVO.getname());
		System.out.println(webMarketVO.getcontact());

		

		//����manage_updateMarketWorker
		resultMessage = manageBLService.manage_updateMarketWorker(new WebMarketVO(1,"tom", "11111111111"));
		if(resultMessage==ResultMessage.Success)
			System.out.println("��������Ӫ����Ա��Ϣ�ɹ�");
		else
			System.out.println("��������Ӫ����Ա��Ϣʧ��");

	}
}