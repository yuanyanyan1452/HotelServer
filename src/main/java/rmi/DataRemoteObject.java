package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import objects.VIPInfo;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.blservice.ManageBLService;
import service.blservice.OrderBLService;
import service.blservice.StrategyBLService;
import service.blservice.Impl.ClientBLServiceImpl;
import service.blservice.Impl.HotelBLServiceImpl;
import service.blservice.Impl.ManageBLServiceImpl;
import service.blservice.Impl.OrderBLServiceImpl;
import service.blservice.Impl.StrategyBLServiceImpl;
import vo.AccommodationVO;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelStrategyVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.OrderVO;
import vo.RoomOrderVO;
import vo.RoomVO;
import vo.WebManagerVO;
import vo.WebMarketVO;
import vo.WebStrategyVO;

public class DataRemoteObject extends UnicastRemoteObject 
 	implements ClientBLService,HotelBLService,ManageBLService,OrderBLService,StrategyBLService{
	
	private static final long serialVersionUID = 4029039744279087114L;
	private ClientBLService clientbl;
	private HotelBLService hotelbl;
	private ManageBLService managebl;
	private OrderBLService orderbl;
	private StrategyBLService strategybl;
	protected DataRemoteObject() throws RemoteException {
		super();
		clientbl=new ClientBLServiceImpl();
		hotelbl=new HotelBLServiceImpl();
		managebl=new ManageBLServiceImpl();
		orderbl=new OrderBLServiceImpl();
		strategybl=new StrategyBLServiceImpl();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.hotelstrategy_make(strategyvo);
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.hotelstrategy_update(strategyvo);
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.webstrategy_make(strategyvo);
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.webstrategy_update(strategyvo);
	}

	@Override
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.getHotelStrategy(hotelid);
	}

	@Override
	public ArrayList<WebStrategyVO> getWebStrategy(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return strategybl.getWebStrategy(clientid);
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_client_browse(clientid);
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, String state) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_client_browse(clientid, state);
	}

	@Override
	public ArrayList<OrderVO> order_client_browse(int clientid, boolean isExecute) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_client_browse(clientid, isExecute);
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_hotel_browse(hotelid);
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, String state) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_hotel_browse(hotelid, state);
	}

	@Override
	public ArrayList<OrderVO> order_hotel_browse(int hotelid, boolean isExecute) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_hotel_browse(hotelid, isExecute);
	}

	@Override
	public ResultMessage order_client_cancel(int clientid, int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_client_cancel(clientid, orderid);
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_client_generate(vo);
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_hotel_execute(orderid);
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_market_browseUnfilled();
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_market_cancelAbnormal(orderid);
	}

	@Override
	public int calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.calculateTotalwithoutStrategy(roomlist,hotelid);
	}

	@Override
	public int calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid,int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.calculateTotalwithStrategy(roomlist, hotelid,clientid);
	}

	@Override
	public ResultMessage updateActualLeaveTime(int orderid, Date leaveTime) throws RemoteException{
		// TODO Auto-generated method stub
		return orderbl.updateActualLeaveTime(orderid, leaveTime);
	}
	
	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_checkin(info, orderid);
	}
	
	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return orderbl.order_findbyid(orderid);
	}
	
	@Override
	public ResultMessage webmanager_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmanager_login(username, password);
	}

	@Override
	public ResultMessage webmarket_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmarket_login(username, password);
	}
	
	@Override
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmarket_change_password(username, oldpassword, newpassword);
	}



	@Override
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmanager_change_password(username, oldpassword, newpassword);
	}

	
	@Override
	public ClientVO manage_searchClient(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_searchClient(clientid);
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_updateClient(clientvo);
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_addHotel(hotelvo);
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO w) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_addHotelWorker(w);
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorker(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_searchHotelWorker(hotelid);
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO w) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_updateHotelWorker(w);
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO mw) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_addMarketWorker(mw);
	}

	@Override
	public WebMarketVO manage_searchMarketWorker(int marketWorkerid) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_searchMarketWorker(marketWorkerid);
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.manage_updateMarketWorker(mw);
	}

	@Override
	public ResultMessage hotelworker_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotelworker_login(username, password);
	}
	
	@Override
	public ResultMessage hotelworker_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotelworker_change_password(username, oldpassword, newpassword);
	}

	@Override
	public HotelVO hotel_checkInfo(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotel_checkInfo(hotelid);
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotel_updateInfo(vo);
	}

	@Override
	public ResultMessage hotel_importRoom(RoomVO room) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotel_importRoom(room);
	}

	@Override
	public ResultMessage hotel_updateAccomodation(AccommodationVO info,int orderid) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotel_updateAccomodation(info,orderid);
	}

	@Override
	public Hotel searchHotel(int hotelid) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.searchHotel(hotelid);
	}

	@Override
	public ArrayList<Hotel> previousHotel(int clientid) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.previousHotel(clientid);
	}

	@Override
	public ResultMessage addHotel(Hotel hotel) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.addHotel(hotel);
	}

	@Override
	public ResultMessage addHotelWorker(HotelWorker worker) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.addHotelWorker(worker);
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.searchHotelWorker(hotelid);
	}

	@Override
	public ResultMessage updateHotelWokerInfo( HotelWorker worker) throws RemoteException{
		// TODO Auto-generated method stub
		return hotelbl.updateHotelWokerInfo(worker);
	}
	
	@Override
	public ArrayList<HotelVO> searchHotelBylocation(String address, String business_address) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list,String hotelname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list,String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list,String star) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public ResultMessage evalutehotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage client_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_login(username, password);
	}

	@Override
	public ResultMessage client_register(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_register(username, password);
	}
	
	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_change_password(username, oldpassword, newpassword);
	}


	@Override
	public ClientVO client_checkInfo(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_checkInfo(clientid);
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_updateInfo(vo);
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_getpreviousHotelList(clientid);
	}

	@Override
	public int client_checkCredit(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_checkCredit(clientid);
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_checkCreditList(clientid);
	}

//	@Override
//	public ArrayList<HotelVO> client_searchHotelByaddress(String address,String business_address) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelByaddress(address,business_address);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByname(ArrayList<HotelVO> list,String hotelname) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelByname(list,hotelname);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBytype(ArrayList<HotelVO> list,String type) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelBytype(list,type);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelByprice(list,lowprice, highprice);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelBytime(list,inTime, leaveTime);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBystar(ArrayList<HotelVO> list,String star) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelBystar(list,star);
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore) throws RemoteException {
//		// TODO Auto-generated method stub
//		return clientbl.client_searchHotelByscore(list,lowscore, highscore);
//	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_checkHotelInfo(hotelid);
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_evaluateHotel(e, clientid,hotelid);
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_enrollVIP(info, clientid);
	}

	@Override
	public ResultMessage updateClientCredit(int clientId, int value, int tag) throws RemoteException{
		// TODO Auto-generated method stub
		return clientbl.updateClientCredit(clientId, value, tag);
	}

	@Override
	public Client checkClientInfo(int clientid) throws RemoteException{
		// TODO Auto-generated method stub
		return clientbl.checkClientInfo(clientid);
	}

	@Override
	public ResultMessage updateClientInfo(Client client) throws RemoteException{
		// TODO Auto-generated method stub
		return clientbl.updateClientInfo(client);
	}



	@Override
	public WebMarketVO webmarket_getvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmarket_getvo(username);
	}



	@Override
	public WebManagerVO webmanager_getvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return managebl.webmanager_getvo(username);
	}



	@Override
	public HotelWorkerVO hotelworker_getvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelbl.hotelworker_getvo(username);
	}



	@Override
	public ClientVO client_getclientvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return clientbl.client_getclientvo(username);
	}



	




	



	


	


	

	
	



	



	

}
