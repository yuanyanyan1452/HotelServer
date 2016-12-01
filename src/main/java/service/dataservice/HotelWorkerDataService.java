package service.dataservice;

import objects.ResultMessage;
import po.HotelWorkerPO;

public interface HotelWorkerDataService {
	//����id���Ҷ�Ӧ�Ŀͻ�
	public HotelWorkerPO find(int hotelid);
	
	//��ӿͻ�
	public ResultMessage insert(HotelWorkerPO po);
	
	//���¿ͻ���Ϣ
	public ResultMessage update(HotelWorkerPO po);
	
	//ɾ���ͻ�
	public ResultMessage delete(HotelWorkerPO po);
}
