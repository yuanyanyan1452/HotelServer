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
import service.blservice.OrderBLService;
import service.dataservice.HotelDataService;
import service.dataservice.HotelWorkerDataService;
import service.dataservice.RoomDataService;
import service.dataservice.Impl.HotelDataServiceImpl;
import service.dataservice.Impl.HotelWorkerDataServiceImpl;
import service.dataservice.Impl.RoomDataServiceImpl;
import vo.AccommodationVO;
import vo.EvaluationVO;
import vo.HotelVO;
import vo.RoomVO;

public class HotelBLServiceImpl implements HotelBLService {
	HotelDataService hoteldataservice=new HotelDataServiceImpl();
	HotelWorkerDataService hotelworkerdataservice=new HotelWorkerDataServiceImpl();		
	RoomDataService roomdataservice=new RoomDataServiceImpl();
	OrderBLService orderblservice= new OrderBLServiceImpl();
	VOChange vochange =new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage hotelworker_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultMessage hotelworker_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelVO hotel_checkInfo(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		HotelVO hotelvo=hotelpo.changetohotelvo(hotelpo);
		return hotelvo;
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
	public ResultMessage hotel_updateAccomodation(AccommodationVO info,int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage result=orderblservice.order_checkin(info, orderid);
		return result;
	}

	@Override
	public Hotel searchHotel(int hotelid) {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		Hotel hotel=hotelpo.changetohotel(hotelpo);
		return hotel;
	}

	@Override
	public ArrayList<Hotel> previousHotel(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> previoushotelpo_list =hoteldataservice.showClientHotels(clientid);
		ArrayList<Hotel> previoushotel_list=new ArrayList<Hotel>();
		for(int i=0;i<previoushotelpo_list.size();i++){
			Hotel hotel=previoushotelpo_list.get(i).changetohotel(previoushotelpo_list.get(i));
			previoushotel_list.add(hotel);
		}
		return previoushotel_list;
	}
	
//
	@Override
	public ResultMessage addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		HotelPO po=objectchange.changetohotelpo(hotel);
		ResultMessage result=hoteldataservice.insert(po);
		return result;
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
		HotelWorker hotelworker=hotelworkerpo.changetohotelworker();
		return hotelworker;
	}

	@Override
	public ResultMessage updateHotelWokerInfo( HotelWorker worker) {
		// TODO Auto-generated method stub
		HotelWorkerPO po=objectchange.changetohotelworkerpo(worker);
		ResultMessage result=hotelworkerdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<Hotel> searchHotelBylocation(String address, String business_address) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> hotelpo_list=hoteldataservice.show_hotel_list(address, business_address);
		ArrayList<Hotel> hotel_list=new ArrayList<Hotel>();
		for(int i=0;i<hotelpo_list.size();i++){
			Hotel hotel=hotelpo_list.get(i).changetohotel(hotelpo_list.get(i));
			hotel_list.add(hotel);
		}
		return hotel_list;
	}

	@Override
	public ArrayList<Hotel> searchHotelByname(String hotelname) {
		// TODO Auto-generated method stub
//		ArrayList<HotelPO> hotelpo_list=hoteldataservice.findByName(hotelname);
//		ArrayList<Hotel> hotel_list=new ArrayList<Hotel>();
//		for(int i=0;i<hotelpo_list.size();i++){
//			Hotel hotel=hotelpo_list.get(i).changetohotel(hotelpo_list.get(i));
//			hotel_list.add(hotel);
//		}
//		return hotel_list;
		return null;
	}

	@Override
	public ArrayList<Hotel> searchHotelByroom(String type) {
		// TODO Auto-generated method stub
//		ArrayList<HotelPO> hotelpo_list=roomdataservice.
//		ArrayList<Hotel> hotel_list=new ArrayList<Hotel>();
//		for(int i=0;i<hotelpo_list.size();i++){
//			Hotel hotel=hotelpo_list.get(i).changetohotel(hotelpo_list.get(i));
//			hotel_list.add(hotel);
//		}
//		return hotel_list;
		return null;
	}

	@Override
	public ArrayList<Hotel> searchHotelByprice(int lowprice, int highprice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Hotel> searchHotelBytime(String inTime, String leaveTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Hotel> searchHotelBystar(String star) {
		// TODO Auto-generated method stub
		ArrayList<HotelPO> hotelpo_list=hoteldataservice.findByStar(star);
		ArrayList<Hotel> hotel_list=new ArrayList<Hotel>();
		for(int i=0;i<hotelpo_list.size();i++){
			Hotel hotel=hotelpo_list.get(i).changetohotel(hotelpo_list.get(i));
			hotel_list.add(hotel);
		}
		return hotel_list;
	}

	@Override
	public ArrayList<Hotel> searchHotelByscore(double lowscore, double highscore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage evalutehotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		String score=hotelpo.getscore();
		String [] scorelist=score.split(",");
		double average_score=Double.parseDouble(scorelist[0]);
		int numofpeople=Integer.parseInt(scorelist[1]);
		int new_numofpeople=numofpeople+1;
		double new_average_score=((average_score*numofpeople)+e.getScore())/new_numofpeople;
		String newscore=String.valueOf(new_average_score)+","+String.valueOf(new_numofpeople);
		hotelpo.setscore(newscore);
		ArrayList<String> newcomment=hotelpo.gethotel_evaluation();
		newcomment.add(e.getComments());
		hotelpo.sethotel_evaluation(newcomment);
		ResultMessage result=hoteldataservice.update(hotelpo);
		return result;
	}


	

	

}
