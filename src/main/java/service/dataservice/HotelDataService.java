
package service.dataservice;

import java.util.ArrayList;
import po.*;
import objects.*;

public interface HotelDataService {
<<<<<<< HEAD
	//���ݾƵ������ҾƵ�
	public HotelPO find(int hotelid);
	
	//��ӾƵ�
	public ResultMessage insert(HotelPO po);
	
	//���¾Ƶ������Ϣ
	public ResultMessage update(HotelPO po);
	
	//ɾ���Ƶ�
	public ResultMessage delete(HotelPO po);
	
	//�������б���Ӧ�ͻ�Ԥ�����ľƵ�
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
	//���ض�Ӧ�Ƶ�Ĺ�����Ա
	public HotelWorkerPO findHotelWorker(String hotelname);
	
	//��Ӷ�Ӧ�Ƶ�Ĺ�����Ա
	public ResultMessage insertHotelWorker(String hotelname,HotelWorkerPO po);
	
	//���¶�Ӧ�Ƶ�Ĺ�����Ա��Ϣ
	public ResultMessage updateHotelWorker(String hotelname,HotelWorkerPO po);
	
	//ɾ����Ӧ�Ƶ�Ĺ�����Ա
=======
	public HotelPO find(String hotelname);
	
	public ResultMessage insert(HotelPO po);
	
	public ResultMessage update(HotelPO po);
	
	public ResultMessage delete(HotelPO po);
	
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
	public HotelWorkerPO findHotelWorker(String hotelname);
	
	public ResultMessage insertHotelWorker(String hotelname,HotelWorkerPO po);
	
	public ResultMessage updateHotelWorker(String hotelname,HotelWorkerPO po);
	
>>>>>>> refs/remotes/Four-People/master
	public ResultMessage deleteHotelWorker(String hotelname,HotelWorkerPO po);
	
}

