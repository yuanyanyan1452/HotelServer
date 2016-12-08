package service.blservice.Impl;

import java.util.ArrayList;

import objects.OrderState;
import objects.ResultMessage;
import objects.RoomType;
import po.OrderPO;
import service.VOChange;
import service.blservice.OrderBLService;
import service.dataservice.OrderDataService;
import service.dataservice.Impl.OrderDataServiceImpl;
import vo.HotelStrategyVO;
import vo.OrderVO;
import vo.WebStrategyVO;

public class OrderBLServiceImpl implements OrderBLService {
	OrderDataService orderdataservice=new OrderDataServiceImpl();
	VOChange vochange =new VOChange();
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
		//ArrayList<OrderPO> orderpo_list1=orderdataservice.findByHotelid(hotelid);
		return null;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage order_client_cancel(int clientid, int orderid) {
		// TODO Auto-generated method stub
		OrderPO orderpo=orderdataservice.findByid(orderid);
		orderpo.setstate("CANCELLED");
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
//		ArrayList<OrderPO> unfilled_order_list=orderdataservice.
		return null;
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) {
		// TODO Auto-generated method stub
		OrderPO po=orderdataservice.findByid(orderid);
		po.setstate("CANCELLED");
		ResultMessage result=orderdataservice.update(po);
		return result;
	}

	@Override
	public int calculateTotalwithoutStrategy(RoomType type, int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateTotalwithStrategy(RoomType type, int num, ArrayList<HotelStrategyVO> list1,
			ArrayList<WebStrategyVO> list2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultMessage updateActualLeaveTime(int orderid, String leaveTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
