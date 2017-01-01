package logiccontroller;


import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.*;
import service.blservice.*;
import objects.*;

public class HotelController implements HotelBLService{
	//酒店信息参数
		int hotelid=1;
		String hotel_name="天丰大酒店";
		String hotel_address="南京市白下区洪武路26号";
		String hotel_business_address="新街口";
		String hotel_introduction="just so so,bad";
		String hotel_service="wifi,餐饮,停车场";
		String star="四星级";
		String score="4.2,20";
		ArrayList<String>hotel_evaluation=new ArrayList<String>();
		ArrayList<Integer>book_clientid=new ArrayList<Integer>();
		
	//酒店工作人员的信息参数
		String hotelWorker_name="John";
		String hotelWorker_contact="1111";
		String username="Matin";
		String password="ddm";
		boolean logged=false;

		@Override
		//酒店工作人员更新酒店信息
		public ResultMessage hotel_updateInfo(HotelVO vo) {
			// TODO Auto-generated method stub
			if(vo!=null)
				return ResultMessage.Success;
			else return ResultMessage.Fail;
		}

		@Override
		public Hotel searchHotel(int hotelid) {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid){
				return new Hotel(1,"天丰大酒店","南京市白下区洪武路26号","新街口","just so so,bad",
						"wifi,餐饮,停车场","四星级","4.2,20",hotel_evaluation,book_clientid);
			}
			return new Hotel();
		}
		
		@Override
		public ResultMessage addHotel(Hotel hotel) {
			// TODO Auto-generated method stub
			if(hotel_name=="天丰大酒店")
				return ResultMessage.Success;
			else return ResultMessage.Fail;
		}

		@Override
		public ResultMessage addHotelWorker(HotelWorker worker) {
			// TODO Auto-generated method stub
			if(worker!=null&&worker.gethotelid()!=this.hotelid)
				return ResultMessage.Success;
			else return ResultMessage.Fail;
		}

		@Override
		public HotelWorker searchHotelWorker(int hotelid) {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid){
				return new HotelWorker(1,hotelWorker_name,hotelWorker_contact,username,password);
			}
			return new HotelWorker();
		}

		@Override
		public ResultMessage hotelworker_login(String username, String password) throws RemoteException {
			// TODO Auto-generated method stub
			if(username==this.username&&password==this.password){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}


		@Override
		public HotelWorkerVO hotelworker_getvo(String username) throws RemoteException {
			// TODO Auto-generated method stub
			if(username==this.username){
				return new HotelWorkerVO(1,hotelWorker_name,hotelWorker_contact,username,password);
			}
			return null;
		}


		@Override
		public ResultMessage hotelworker_change_password(String username, String oldpassword, String newpassword)
				throws RemoteException {
			// TODO Auto-generated method stub
			if(username.equals(this.username)&&oldpassword.equals(this.password)&&newpassword!=null){
				this.password=newpassword;
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}


		@Override
		public HotelVO hotel_getInfo(int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid){
				return new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","just so so,bad",
						"wifi,餐饮,停车场","四星级","4.2,20",hotel_evaluation,book_clientid);
			}
			return null;
		}


		@Override
		public ResultMessage hotel_importRoom(RoomVO room) throws RemoteException {
			// TODO Auto-generated method stub
			if(room.gethotelid()==this.hotelid){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}


		@Override
		public ResultMessage hotel_updateRoom(RoomVO room) throws RemoteException {
			// TODO Auto-generated method stub
			if(room!=null&&room.gethotelid()==this.hotelid){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}


		@Override
		public ArrayList<HotelVO> searchHotelBylocation(String address, String business_address)
				throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> list=new ArrayList<HotelVO>();
			if(address.equals(this.hotel_address)&&business_address.equals(this.hotel_business_address)){
				list.add(new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","just so so,bad",
						"wifi,餐饮,停车场","四星级","4.2,20",hotel_evaluation,book_clientid));
			}
			return list;
		}


		@Override
		public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list, String hotelname) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> searchByname=new ArrayList<HotelVO>();
			for(int i=0;i<list.size();i++){
				if(hotelname.equals(this.hotel_name)){
					searchByname.add(list.get(i));
				}
			}
			return searchByname;
		}


		@Override
		public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list, String type) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> searchByroom=new ArrayList<HotelVO>();
			for(int i=0;i<list.size();i++){
				if(type=="标准间"||type=="大床房"||type=="双人房"){
					searchByroom.add(list.get(i));
				}
			}
			return searchByroom;
		}


		@Override
		public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list, int lowprice, int highprice)
				throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> searchByprice=new ArrayList<HotelVO>();
			for(int i=0;i<list.size();i++){
				if(lowprice<=350&&highprice>=540){
					searchByprice.add(list.get(i));
				}
			}
			return searchByprice;
		}


		@Override
		public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list, String inTime, String leaveTime)
				throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list, String star) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO>searchBystar=new ArrayList<HotelVO>();
			for(int i=0;i<list.size();i++){
				if(star.equals(this.star)){
					searchBystar.add(list.get(i));
				}
			}
			return searchBystar;
		}


		@Override
		public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list, double lowscore, double highscore)
				throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> searchByscore=new ArrayList<HotelVO>();
			double s=Double.parseDouble(this.score.split(",")[0]);
			for(int i=0;i<list.size();i++){
				if(lowscore<=s&&highscore>=s){
					searchByscore.add(list.get(i));
				}
			}
			return searchByscore;
		}


		@Override
		public ArrayList<HotelVO> searchpreviousHotelList(ArrayList<HotelVO> hotelvolist, int clientid)
				throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<HotelVO> searchPre=new ArrayList<HotelVO>();
			for(int i=0;i<hotelvolist.size();i++){
				ArrayList<Integer>book=hotelvolist.get(i).getbook_clientid();
				for(int j=0;j<book.size();j++){
					if(clientid==book.get(j)){
						searchPre.add(hotelvolist.get(i));
						break;
					}
				}
			}
			return searchPre;
		}


		@Override
		public ResultMessage evalutehotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid){
				String [] scorelist=score.split(",");
				double average_score=Double.parseDouble(scorelist[0]);
				int numofpeople=Integer.parseInt(scorelist[1]);
				int new_numofpeople=numofpeople+1;
				double new_average_score=((average_score*numofpeople)+e.getScore())/new_numofpeople;
				String newscore=String.valueOf(new_average_score)+","+String.valueOf(new_numofpeople);
				this.score=newscore;
				hotel_evaluation.add(e.getComments());
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}


		@Override
		public ArrayList<RoomVO> getallroom(int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<RoomVO> allroom=new ArrayList<RoomVO>();
			if(hotelid==this.hotelid){
				allroom.add(new RoomVO(1,hotelid,"标准间",22,4,350));
				allroom.add(new RoomVO(2,hotelid,"大床房",8,6,480));
				allroom.add(new RoomVO(3,hotelid,"双人房",20,12,540));
			}
			return allroom;
		}


		@Override
		public ArrayList<Hotel> getpreviousHotel(int clientid) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<Hotel> getPre=new ArrayList<Hotel>();
			for(int i=0;i<book_clientid.size();i++){
				if(clientid==book_clientid.get(i)){
					getPre.add(new Hotel(1,"天丰大酒店","南京市白下区洪武路26号","新街口","just so so,bad",
						"wifi,餐饮,停车场","四星级","4.2,20",hotel_evaluation,book_clientid));
				}
			}
			return getPre;
		}


		@Override
		public ResultMessage updateHotelWokerInfo(HotelWorker worker) throws RemoteException {
			// TODO Auto-generated method stub
			if(worker.gethotelid()==this.hotelid){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
}
