package service.blservice.Impl;

import java.util.ArrayList;

import objects.AccommodationInfo;
import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import objects.Room;
import po.HotelPO;
import po.HotelWorkerPO;
import service.blservice.HotelBLService;
import service.dataservice.HotelDataService;
import service.dataservice.Impl.HotelDataServiceImpl;
import vo.HotelVO;

public class HotelBLServiceImpl implements HotelBLService {
	HotelDataService hoteldataservice=new HotelDataServiceImpl();
			
	@Override
	public HotelVO hotel_checkInfo(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.find(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		ResultMessage result=hoteldataservice.update(po);
		
		return null;
	}

	@Override
	public ResultMessage hotel_importRoom(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage hotel_updateAccomodation(AccommodationInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hotel searchHotel(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.find(hotelid);
		
		return null;
	}

	@Override
	public ArrayList<Hotel> previousHotel(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> previoushotel_list =hoteldataservice.showClientHotels(clientid);
		
		return null;
	}

	@Override
	public ResultMessage addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		ResultMessage result=hoteldataservice.insert(po);
		
		return null;
	}

	@Override
	public ResultMessage addHotelWorker(HotelWorker worker) {
		// TODO Auto-generated method stub
		ResultMessage result=hoteldataservice.insertHotelWorker(hotelid, po);
		
		return null;
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorkerPO hotelworkerpo=hoteldataservice.findHotelWorker(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage updateHotelWokerInfo(int hotelid, HotelWorker worker) {
		// TODO Auto-generated method stub
		ResultMessage result=hoteldataservice.updateHotelWorker(hotelid, po);
		
		return null;
	}

}
