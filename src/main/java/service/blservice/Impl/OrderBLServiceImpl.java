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
		
		return null;
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, String state) {
		// TODO Auto-generated method stub\
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, state, true);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, state, false);
		
 		return null;
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> orderpo_list1=orderdataservice.findByStatus(clientid, "NORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list2=orderdataservice.findByStatus(clientid, "ABNORMAL", isExecute);
		ArrayList<OrderPO> orderpo_list3=orderdataservice.findByStatus(clientid, "CANCELLED", isExecute);
		
		return null;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> orderpo_list=orderdataservice.findByHotelid(hotelid);
	
		return null;
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, String state) {
		// TODO Auto-generated method stub
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
		return null;
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
		ResultMessage result=orderdataservice.update(po);
		return null;
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
