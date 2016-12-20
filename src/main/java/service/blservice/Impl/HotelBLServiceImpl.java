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
import vo.HotelWorkerVO;
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
		ResultMessage result=hotelworkerdataservice.check(username, password);
		return result;
	}
	
	@Override
	public ResultMessage hotelworker_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		HotelWorkerPO hotelworkerpo=hotelworkerdataservice.gethotelworkerpo(username, oldpassword);
		hotelworkerpo.setpassword(newpassword);
		ResultMessage result=hotelworkerdataservice.update(hotelworkerpo);
		return result;
	}

	@Override
	public HotelVO hotel_checkInfo(int hotelid) {
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		HotelVO hotelvo=hotelpo.changetohotelvo();
		return hotelvo;
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) {
		HotelPO po=vochange.hotelvo_to_hotelpo(vo);
		ResultMessage result=hoteldataservice.update(po);
		return result;
	}

	@Override
	public ResultMessage hotel_importRoom(RoomVO room) {
		RoomPO po=vochange.roomvo_to_roompo(room);
		po.setavailable_num(po.gettotal_num());
		ResultMessage result=roomdataservice.insert(po);
		return result;
	}
	
	@Override
	public ResultMessage hotel_updateRoom(RoomVO room) throws RemoteException {
		ArrayList<RoomPO> roomlist=roomdataservice.find(room.gethotelid());
		RoomPO roompo=new RoomPO();
		for(int i=0;i<roomlist.size();i++){
			if(roomlist.get(i).getroom_type().equals(room.getroom_type())){
				roompo=roomlist.get(i);
			}
		}
		if(roompo.gettotal_num()<room.getavailable_num()){
			roompo.settotal_num(room.getavailable_num());
			roompo.setavailable_num(room.getavailable_num());
		}
		ResultMessage result=roomdataservice.update(roompo);
		return result;
	}

	@Override
	public ResultMessage hotel_updateAccomodation(AccommodationVO info,int orderid) throws RemoteException {
		ResultMessage result=orderblservice.order_checkin(info, orderid);
		return result;
	}

	@Override
	public Hotel searchHotel(int hotelid) {
		HotelPO hotelpo=hoteldataservice.findByid(hotelid);
		Hotel hotel=hotelpo.changetohotel(hotelpo);
		return hotel;
	}

	@Override
	public ArrayList<Hotel> previousHotel(int clientid) {
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
		HotelPO po=objectchange.changetohotelpo(hotel);
		po.setscore(po.getscore()+",1");
		ResultMessage result=hoteldataservice.insert(po);
		return result;
	}

	@Override
	public ResultMessage addHotelWorker(HotelWorker worker) {
		HotelWorkerPO po=objectchange.changetohotelworkerpo(worker);
		HotelWorkerPO hotelworkerpo=hotelworkerdataservice.find(po.gethotelid());
		if(hotelworkerpo.getname().equals(null)){
			ResultMessage result=hotelworkerdataservice.insert(po);
			return result;
		
		}
		else{
			return ResultMessage.Fail;
		}
		
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) {
		HotelWorkerPO hotelworkerpo=hotelworkerdataservice.find(hotelid);
		HotelWorker hotelworker=hotelworkerpo.changetohotelworker();
		return hotelworker;
	}

	@Override
	public ResultMessage updateHotelWokerInfo( HotelWorker worker) {
		HotelWorkerPO po=objectchange.changetohotelworkerpo(worker);
		ResultMessage result=hotelworkerdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<HotelVO> searchHotelBylocation(String address, String business_address) {
		ArrayList<HotelPO> hotelpo_list=hoteldataservice.show_hotel_list(address, business_address);
		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
		for(int i=0;i<hotelpo_list.size();i++){
			HotelVO hotelvo=hotelpo_list.get(i).changetohotelvo();
			hotelvo_list.add(hotelvo);
		}
		return hotelvo_list;
	}
	
	@Override
	public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list,String hotelname) {
		ArrayList<HotelVO> newlist=new ArrayList<HotelVO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getname().contains(hotelname)){
				newlist.add(list.get(i));
			}
		}
		return newlist;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list,String type) {
		ArrayList<HotelVO> newlist=new ArrayList<HotelVO>();
		for(int i=0;i<list.size();i++){
			ArrayList<RoomPO> roomlist=roomdataservice.find(list.get(i).getid());
			for(int j=0;j<roomlist.size();j++){
				if(roomlist.get(j).getroom_type().equals(type)){
					newlist.add(list.get(i));
					break;
				}
			}
		}
		return newlist;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice) {
		ArrayList<HotelVO> newlist=new ArrayList<HotelVO>();
		for(int i=0;i<list.size();i++){
			ArrayList<RoomPO> roomlist=roomdataservice.find(list.get(i).getid());
			for(int j=0;j<roomlist.size();j++){
				if(roomlist.get(j).getprice()<=highprice&&roomlist.get(j).getprice()>=lowprice){
					newlist.add(list.get(i));
					break;
				}
			}
		}
		return newlist;
	}

	@Override
	public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime) {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list,String star) {
		ArrayList<HotelVO> newlist=new ArrayList<HotelVO>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getstar().equals(star)){
				newlist.add(list.get(i));
			}
		}
		return newlist;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore) {
		ArrayList<HotelVO> newlist=new ArrayList<HotelVO>();
		for(int i=0;i<list.size();i++){
			String score=list.get(i).getscore();
			String [] scorelist=score.split(",");
			double average_score=Double.parseDouble(scorelist[0]);
			if(average_score>=lowscore&&average_score<=highscore){
				newlist.add(list.get(i));
			}
		}
		return newlist;
	}

	@Override
	public ResultMessage evalutehotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
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

	@Override
	public HotelWorkerVO hotelworker_getvo(String username) throws RemoteException {
		int id=hotelworkerdataservice.findhotelid_of_hotelworkerbyUsername(username);
		HotelWorkerPO po=hotelworkerdataservice.find(id);
		HotelWorkerVO vo=po.changetohotelworkervo();
		return vo;
	}

	@Override
	public ArrayList<RoomVO> getallroom(int hotelid) throws RemoteException {
		ArrayList<RoomVO> allroom=new ArrayList<RoomVO>();
		ArrayList<RoomPO> roomlist=roomdataservice.find(hotelid);
		for(int i=0;i<roomlist.size();i++){
			allroom.add(roomlist.get(i).changetoroomvo());
		}
		return allroom;
	}



//	public static void main(String[]args){
//		HotelBLServiceImpl hotel=new HotelBLServiceImpl();
//		ArrayList<HotelVO> hotelvo_list=hotel.searchHotelBylocation("南京","新街口");
//		ArrayList<HotelVO> testhotelvo_list=hotel.searchHotelByroom(hotelvo_list, "大床房");
//		System.out.print(testhotelvo_list.size());
//	}
	

	

}
