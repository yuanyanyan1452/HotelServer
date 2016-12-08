package service.dataservice;

import objects.ResultMessage;
import po.ClientPO;
import po.HotelWorkerPO;

public interface HotelWorkerDataService {
	//����id���Ҷ�Ӧ�Ŀͻ�
	public HotelWorkerPO find(int hotelid);
	
	//��ӿͻ�
	public ResultMessage insert(HotelWorkerPO po);
	
	//���¿ͻ���Ϣ
	public ResultMessage update(HotelWorkerPO po);
	
	//ɾ���ͻ�
	public ResultMessage delete(int hotelid);
	
	public ResultMessage check(String username,String password);
	
	public HotelWorkerPO gethotelworkerpo(String username,String password);
	
	public int findhotelid_of_hotelworkerbyUsername(String username);
}
