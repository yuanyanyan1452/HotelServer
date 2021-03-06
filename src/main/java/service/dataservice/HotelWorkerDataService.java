package service.dataservice;

import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelWorkerPO;

public interface HotelWorkerDataService {
	public ArrayList<HotelWorkerPO> getallhotelworkerPO();

	public HotelWorkerPO find(int hotelid);
	
	public ResultMessage insert(HotelWorkerPO po);
	
	public ResultMessage update(HotelWorkerPO po);
	
	public ResultMessage delete(int hotelid);
	
	public ResultMessage check(String username,String password);
	
	public HotelWorkerPO gethotelworkerpo(String username,String password);
	
	public int findhotelid_of_hotelworkerbyUsername(String username);
}
