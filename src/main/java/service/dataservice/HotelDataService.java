
package service.dataservice;

import java.util.ArrayList;
import po.*;
import objects.*;

public interface HotelDataService {

	//
	public HotelPO findByid(int hotelid);
	
	public HotelPO findByName(String hotelname);

	public HotelPO findByPrice(String price);
	
	//
	public ResultMessage insert(HotelPO po);
	
	//
	public ResultMessage update(HotelPO po);
	
	//
	public ResultMessage delete(HotelPO po);

	//
	public ArrayList<HotelPO> showClientHotels(int clientid);
	
	//
	public HotelWorkerPO findHotelWorker(int hotelid);
	
	//
	public ResultMessage insertHotelWorker(int hotelid,HotelWorkerPO po);
	
	//
	public ResultMessage updateHotelWorker(int hotelid,HotelWorkerPO po);

	public ResultMessage deleteHotelWorker(int hotelid,HotelWorkerPO po);
	
}

