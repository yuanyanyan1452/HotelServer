package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Hotel;
import objects.HotelWorker;
import objects.ObjectChange;
import objects.ResultMessage;
import po.HotelPO;
import po.HotelWorkerPO;
import po.RoomPO;
import service.VOChange;
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
	VOChange vochange =new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage hotelworker_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelVO hotel_checkInfo(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		HotelPO po=vochange.hotelvo_to_hotelpo(vo);
		ResultMessage result=hoteldataservice.update(po);
		return result;
	}

	@Override
	public ResultMessage hotel_importRoom(RoomVO room) {
		// TODO Auto-generated method stub
		RoomPO po=vochange.roomvo_to_roompo(room);
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
		HotelWorkerPO po=objectchange.changetohotelworkerpo(worker);
		ResultMessage result=hotelworkerdataservice.insert(po);
		return result;
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorkerPO hotelworkerpo=hotelworkerdataservice.find(hotelid);
		
		return null;
	}

	@Override
	public ResultMessage updateHotelWokerInfo( HotelWorker worker) {
		// TODO Auto-generated method stub
		HotelWorkerPO po=objectchange.changetohotelworkerpo(worker);
		ResultMessage result=hotelworkerdataservice.update(po);
		return result;
	}

	

}
