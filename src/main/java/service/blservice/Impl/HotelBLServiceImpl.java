package service.blservice.Impl;

import java.util.ArrayList;

import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import po.HotelPO;
import po.HotelWorkerPO;
import po.RoomPO;
import service.Change;
import service.blservice.HotelBLService;
import service.dataservice.HotelDataService;
import service.dataservice.HotelWorkerDataService;
import service.dataservice.RoomDataService;
import service.dataservice.Impl.HotelDataServiceImpl;
import service.dataservice.Impl.HotelWorkerDataServiceImpl;
import service.dataservice.Impl.RoomDataServiceImpl;
import vo.AccommodationVO;
import vo.HotelVO;
import vo.RoomVO;

public class HotelBLServiceImpl implements HotelBLService {
	HotelDataService hoteldataservice=new HotelDataServiceImpl();
	HotelWorkerDataService hotelworkerdataservice=new HotelWorkerDataServiceImpl();		
	RoomDataService roomdataservice=new RoomDataServiceImpl();
	Change change =new Change();
	@Override
	public HotelVO hotel_checkInfo(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		HotelPO po=new HotelPO();
		ResultMessage result=hoteldataservice.update(po);
		
		return null;
	}

	@Override
	public ResultMessage hotel_importRoom(RoomVO room) {
		// TODO Auto-generated method stub
		RoomPO po=change.roomvo_to_roompo(room);
		ResultMessage result=roomdataservice.insert(po);
		return result;
	}

	@Override
	public ResultMessage hotel_updateAccomodation(AccommodationVO info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hotel searchHotel(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		
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
		HotelPO po=new HotelPO();
		ResultMessage result=hoteldataservice.insert(po);
		
		return null;
	}

	@Override
	public ResultMessage addHotelWorker(HotelWorker worker) {
		// TODO Auto-generated method stub
		HotelWorkerPO po=new HotelWorkerPO();
		ResultMessage result=hotelworkerdataservice.insert(po);
		
		return null;
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorkerPO hotelworkerpo=hotelworkerdataservice.find(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage updateHotelWokerInfo(int hotelid, HotelWorker worker) {
		// TODO Auto-generated method stub
		HotelWorkerPO po=new HotelWorkerPO();
		ResultMessage result=hotelworkerdataservice.update(po);
		
		return null;
	}


}
