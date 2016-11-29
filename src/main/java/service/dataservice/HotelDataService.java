
package service.dataservice;

import java.util.ArrayList;
import po.*;
import objects.*;

public interface HotelDataService {
	
	public HotelPO find(int hotelid);
	
	
	public HotelPO find(String hotelname);
	
	public ResultMessage insert(HotelPO po);
	
	public ResultMessage update(HotelPO po);
	
	public ResultMessage delete(HotelPO po);
	
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
	public HotelWorkerPO findHotelWorker(String hotelname);
	
	public ResultMessage insertHotelWorker(String hotelname,HotelWorkerPO po);
	
	public ResultMessage updateHotelWorker(String hotelname,HotelWorkerPO po);
	
	public ResultMessage deleteHotelWorker(String hotelname,HotelWorkerPO po);
	
}

