package logiccontroller;


import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import objects.ResultMessage;
import service.blservice.OrderBLService;
import vo.AccommodationVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;

public class OrderController implements OrderBLService{
	SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
	//订单信息
		int id=1;
		int clientid=1;
		int hotelid=1;
		String state="正常";
		Date cancel_time=null;
		boolean execute=true;
		String start_time="2017-01-01 00:00:00";
		String end_time="2017-01-05 00:00:00";
		String latest_execute_time="2017-01-02 00:00:00";
		ArrayList<RoomOrderVO>room_order=new ArrayList<RoomOrderVO>();
		int price=1800;
		int expect_number_of_people=2;
		boolean havechild=false;
		String evaluation="nice";
		
		//订单的房间信息
		RoomOrderVO roomorder1=new RoomOrderVO("标准间",1,5);
		RoomOrderVO roomorder2=new RoomOrderVO("双人房",1,3);
		RoomOrderVO roomorder3=new RoomOrderVO("大床房",2,4);
		
		//房间信息
		RoomVO room1=new RoomVO(1,1,"标准间",22,4,350);
		RoomVO room2=new RoomVO(2,1,"大床房",8,6,480);
		RoomVO room3=new RoomVO(3,1,"双人房",20,12,540);
		
		
		@Override
		public ArrayList<OrderVO> findorderByClientid(int clientid) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findByClientid=new ArrayList<OrderVO>();
			if(clientid==this.clientid){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findByClientid.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
							fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
							expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findByClientid;
		}
		@Override
		public ArrayList<OrderVO> findorderBy_Clientid_State(int clientid, String state) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findBy_clientid_state=new ArrayList<OrderVO>();
			if(clientid==this.clientid&&state.equals(this.state)){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findBy_clientid_state.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
								fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
								expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findBy_clientid_state;
		}
		@Override
		public ArrayList<OrderVO> findorderBy_Clientid_Execute(int clientid, boolean isExecute) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findBy_clientid_execute=new ArrayList<OrderVO>();
			if(clientid==this.clientid&&isExecute==this.execute){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findBy_clientid_execute.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
								fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
								expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findBy_clientid_execute;
		}
		@Override
		public ArrayList<OrderVO> findorderByHotelid(int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findBy_hotelid=new ArrayList<OrderVO>();
			if(hotelid==this.hotelid){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findBy_hotelid.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
								fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
								expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findBy_hotelid;
		}
		@Override
		public ArrayList<OrderVO> findorderBy_Hotelid_State(int hotelid, String state) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findBy_hotelid_state=new ArrayList<OrderVO>();
			if(hotelid==this.hotelid&&state.equals(this.state)){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findBy_hotelid_state.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
								fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
								expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findBy_hotelid_state;
		}
		@Override
		public ArrayList<OrderVO> findorderBy_Hotelid_Execute(int hotelid, boolean isExecute) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO>findBy_hotelid_execute=new ArrayList<OrderVO>();
			if(hotelid==this.hotelid&&isExecute==this.execute){
				try {
					room_order.add(roomorder1);
					room_order.add(roomorder2);
					room_order.add(roomorder3);
					findBy_hotelid_execute.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
								fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
								expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return findBy_hotelid_execute;
		}
		@Override
		public ResultMessage order_client_cancel(int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(orderid!=0){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage order_client_generate(OrderVO vo) throws RemoteException {
			// TODO Auto-generated method stub
			if(vo!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage order_hotel_execute(int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(orderid==this.id){
				execute=true;
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ArrayList<OrderVO> order_market_browseUnfilled() throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO> market_browse=new ArrayList<OrderVO>();
			try {
				room_order.add(roomorder1);
				room_order.add(roomorder2);
				room_order.add(roomorder3);
				market_browse.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
									fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
									expect_number_of_people,havechild,evaluation));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return market_browse;
		}
		@Override
		public ResultMessage order_market_cancelAbnormal(int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(orderid==this.id){
				state="已撤销";
				cancel_time=new Date();
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid)
				throws RemoteException {
			// TODO Auto-generated method stub
			double price = 0;
			ArrayList<RoomVO> roomlistttt=new ArrayList<RoomVO>();
			roomlistttt.add(room1);
			roomlistttt.add(room2);
			roomlistttt.add(room3);
			for(int i=0;i<roomlist.size();i++){
				RoomOrderVO roomvo=roomlist.get(i);
				for(int j=0;j<roomlistttt.size();j++){
					if(roomvo.getroom_type().equals(roomlistttt.get(j).getroom_type())){
						price+=roomvo.getnum_of_days()*roomvo.getroom_number()*roomlistttt.get(j).getprice();
						break;
					}
				}
			}
			
			return price;
		}
		@Override
		public double calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid, int clientid)
				throws RemoteException {
			// TODO Auto-generated method stub
			double price=1800;
			return price;
		}
		@Override
		public OrderVO order_findbyid(int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(orderid==this.id){
				room_order.add(roomorder1);
				room_order.add(roomorder2);
				room_order.add(roomorder3);
				try {
					return new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
										fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
										expect_number_of_people,havechild,evaluation);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		@Override
		public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(info!=null&&orderid==this.id){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage order_checkout(int orderid) throws RemoteException {
			// TODO Auto-generated method stub
			if(orderid==this.id){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage offline_checkin(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid&&room_order!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ResultMessage offline_checkout(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
			// TODO Auto-generated method stub
			if(hotelid==this.hotelid&&room_order!=null){
				return ResultMessage.Success;
			}
			return ResultMessage.Fail;
		}
		@Override
		public ArrayList<OrderVO> get_client_hotel_order(int clientid, int hotelid) throws RemoteException {
			// TODO Auto-generated method stub
			ArrayList<OrderVO> client_hotel_order=new ArrayList<OrderVO>();
			if(clientid==this.clientid&&hotelid==this.hotelid){
				room_order.add(roomorder1);
				room_order.add(roomorder2);
				room_order.add(roomorder3);
				try {
					client_hotel_order.add(new OrderVO(id,clientid,hotelid,state,cancel_time,execute,
										fmt.parse(start_time),fmt.parse(end_time),fmt.parse(latest_execute_time),room_order,price,
										expect_number_of_people,havechild,evaluation));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return client_hotel_order;
			}
			return null;
		}
		
		
		
}
