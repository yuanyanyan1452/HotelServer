package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import objects.ResultMessage;
import po.OrderPO;
import service.BL;
import service.VOChange;
import service.blservice.OrderBLService;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.OrderDataServiceImpl;
import vo.AccommodationVO;
import vo.HotelStrategyVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.WebStrategyVO;

public class OrderBLServiceImpl implements OrderBLService {
	OrderDataService orderdataservice=new OrderDataServiceImpl();
	VOChange vochange =new VOChange();
	BL bl=new BL();
	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub\
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstate("CANCELLED");
		Date time=new Date();
		orderpo.setcancel_time(time);
		ResultMessage result=orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) {
		// TODO Auto-generated method stub
		OrderPO po=vochange.ordervo_to_orderpo(vo);
		ResultMessage result=orderdataservice.insert(po);
		return result;
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) {
		// TODO Auto-generated method stub
		OrderPO po=orderdataservice.findByid(orderid);
		po.setexecute(true);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		OrderPO po=orderdataservice.findByid(orderid);
		po.setstate("CANCELLED");
		Date time=new Date();
		po.setcancel_time(time);
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public int calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist) {
		// TODO Auto-generated method stub
		for(int i=0;i<roomlist.size();i++){
			
		}
		return 0;
	}

	@Override
	public int calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist, ArrayList<HotelStrategyVO> list1,
			ArrayList<WebStrategyVO> list2) {
		// TODO Auto-generated method stub
		for(int i=0;i<roomlist.size();i++){
			
		}
		return 0;
	}

	@Override
	public ResultMessage updateActualLeaveTime(int orderid, Date leaveTime) {
		// TODO Auto-generated method stub
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setend_time(leaveTime);
		ResultMessage result=orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstart_time(info.getCheckIn());
		orderpo.setend_time(info.getPlanCheckOut());
		ResultMessage result =orderdataservice.update(orderpo);
		return result;
	}

	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		OrderPO orderpo=orderdataservice.findByid(orderid);
		OrderVO ordervo=orderpo.changetoordervo();
		return ordervo;
	}

}
