package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import objects.ResultMessage;
import objects.VIPInfo.VIPType;
import po.ClientPO;
import po.OrderPO;
import po.RoomPO;
import service.BL;
import service.VOChange;
import service.blservice.OrderBLService;
import service.blservice.StrategyBLService;
import service.dataservice.ClientDataService;
import service.dataservice.OrderDataService;
import service.dataservice.RoomDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import service.dataservice.Impl.OrderDataServiceImpl;
import service.dataservice.Impl.RoomDataServiceImpl;
import vo.AccommodationVO;
import vo.HotelStrategyVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.WebStrategyVO;

public class OrderBLServiceImpl implements OrderBLService {
	OrderDataService orderdataservice=new OrderDataServiceImpl();
	RoomDataService roomdataservice=new RoomDataServiceImpl();
	StrategyBLService strategyblservice=new StrategyBLServiceImpl();
	ClientDataService clientblservice=new ClientDataServiceImpl();
	VOChange vochange =new VOChange();
	BL bl=new BL();
	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByClientid(clientid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, String state) {
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, state, true);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, state, false);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list1.size();i++){
			OrderVO ordervo=orderpo_list1.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list2.size();i++){
			OrderVO ordervo=orderpo_list2.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute) {
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, "NORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, "ABNORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list3=orderdataservice.findByStatus(clientid, "CANCELLED", isExecute);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list1.size();i++){
			OrderVO ordervo=orderpo_list1.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list2.size();i++){
			OrderVO ordervo=orderpo_list2.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		for(int i=0;i<orderpo_list3.size();i++){
			OrderVO ordervo=orderpo_list3.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return null;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			OrderVO ordervo=orderpo_list.get(i).changetoordervo();
			ordervo_list.add(ordervo);
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, String state) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			if(orderpo_list.get(i).getstate()==state){
				OrderVO ordervo=orderpo_list.get(i).changetoordervo();
				ordervo_list.add(ordervo);
			}
		}
		return ordervo_list;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute) {
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
		ArrayList<OrderVO> ordervo_list=new ArrayList<OrderVO>();
		for(int i=0;i<orderpo_list.size();i++){
			if(orderpo_list.get(i).getexecute()==isExecute){
				OrderVO ordervo=orderpo_list.get(i).changetoordervo();
				ordervo_list.add(ordervo);
			}
		}
		return ordervo_list;
	}

	@Override
	public ResultMessage order_client_cancel(int clientid, int orderid) {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstate("已撤销");
		Date time=new Date();
		orderpo.setcancel_time(time);
		ResultMessage result=orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) {
		OrderPO po=vochange.ordervo_to_orderpo(vo);
		ResultMessage result=orderdataservice.insert(po);
		return result;
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) {
		OrderPO po=orderdataservice.findByid(orderid);
		po.setexecute(true);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() {
		ArrayList<OrderPO> unfilled_order_list=orderdataservice.findByState("ABNORMAL");
		ArrayList<OrderVO> list=new ArrayList<OrderVO>();
		for(int i=0;i<unfilled_order_list.size();i++){
			OrderVO ordervo=unfilled_order_list.get(i).changetoordervo();
			list.add(ordervo);
		}
		return list;
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) {
		OrderPO po=orderdataservice.findByid(orderid);
		po.setstate("已撤销");
		Date time=new Date();
		po.setcancel_time(time);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public int calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid) throws RemoteException {
		int price = 0;
		ArrayList<RoomPO> roomlistttt=roomdataservice.find(hotelid);
		
		for(int i=0;i<roomlist.size();i++){
			RoomOrderVO roomvo=roomlist.get(i);
			for(int j=0;j<roomlistttt.size();j++){
				if(roomvo.getroom_type().equals(roomlistttt.get(j).getroom_type()))
			price+=roomvo.getnum_of_days()*roomvo.getroom_number()*roomlistttt.get(j).getprice();
				break;
			}
		}
		
		return price;
	}

	@Override
	public int calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, int hotelid,int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		int room_number = 0;
		int price = 0;
		ArrayList<RoomPO> roomlistttt=roomdataservice.find(hotelid);
		for(int i=0;i<roomlist.size();i++){
			RoomOrderVO roomvo=roomlist.get(i);
			for(int j=0;j<roomlistttt.size();j++){
				if(roomvo.getroom_type().equals(roomlistttt.get(j).getroom_type()))
			price+=roomvo.getnum_of_days()*roomvo.getroom_number()*roomlistttt.get(j).getprice();
			room_number+=roomvo.getroom_number();
				break;
			}
		}
		ArrayList<Integer> price_list=new ArrayList<Integer>();
		ArrayList<HotelStrategyVO>  hotelstrategy_list=strategyblservice.getHotelStrategy(hotelid);
		ArrayList<WebStrategyVO> webstrategy_list=strategyblservice.getWebStrategy(clientid);
		for(int i=0;i<hotelstrategy_list.size();i++){
			int pp=price;
			HotelStrategyVO hotelstrategyvo=hotelstrategy_list.get(i);
			Date date=new Date();
			if(date.before(hotelstrategyvo.getend_time())&&date.after(hotelstrategyvo.getstart_time())){
				String name=hotelstrategyvo.getname();
				if(name.equals("开业酬宾")){
					pp*=0.9;
				}
				else if(name.equals("生日特惠折扣")){
					ClientPO clientvo=clientblservice.find(clientid);
					if(clientvo.getvipinfo().getType().equals(VIPType.NORMAL)){
//						String[] info=clientvo.getvipinfo().getInfo().split(",");
//						if(info[0].equals("一级会员")){
							pp*=0.5;
//						}
//						else if(info[0].equals("二级会员")){
//							pp*=0.8;
//						}
//						else{
//							pp*=0.7;
//						}
					}
				}
				else if(name.equals("三间及以上预订优惠")&&room_number>=3){
					pp*=0.9;
				}
				else if(name.equals("合作企业客户折扣")){
					ClientPO clientvo=clientblservice.find(clientid);
					if(clientvo.getvipinfo().getType().equals(VIPType.Enterprise)){
						pp*=0.8;
					}
				}
				else if(name.equals("双11活动折扣")&&pp>=500){
					pp*=0.7;
				}
				price_list.add(pp);
			}
		}
		
		//网站没写
		int min_price=price_list.get(0);
		for(int i=1;i<price_list.size();i++){
			if(min_price>price_list.get(i)){
				min_price=price_list.get(i);
			}
		}
		return min_price;
	}

	@Override
	public ResultMessage updateActualLeaveTime(int orderid, Date leaveTime) {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setend_time(leaveTime);
		ResultMessage result=orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstart_time(info.getCheckIn());
		orderpo.setend_time(info.getPlanCheckOut());
		ResultMessage result =orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		OrderPO orderpo=orderdataservice.findByid(orderid);
		OrderVO ordervo=orderpo.changetoordervo();
		return ordervo;
	}

}
