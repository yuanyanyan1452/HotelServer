package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
	}

	

	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) throws RemoteException {
		return strategybl.hotelstrategy_make(strategyvo);
	}

	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) throws RemoteException {
		return strategybl.hotelstrategy_update(strategyvo);
	}

	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) throws RemoteException {
		return strategybl.webstrategy_make(strategyvo);
	}

	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) throws RemoteException {
		return strategybl.webstrategy_update(strategyvo);
	}

	@Override
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid) throws RemoteException {
		return strategybl.getHotelStrategy(hotelid);
	}

	@Override
	public ArrayList<WebStrategyVO> getWebStrategy() throws RemoteException {
		return strategybl.getWebStrategy();
	}

	@Override
	public HotelStrategyVO gethotelstrategybyname(String name) throws RemoteException {
		return strategybl.gethotelstrategybyname(name);
	}

	@Override
	public WebStrategyVO getwebstrategybyname(String name) throws RemoteException {
		return strategybl.getwebstrategybyname(name);
	}
	
	@Override
	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo) throws RemoteException {
		return strategybl.hotelstrategy_delete(hotelstrategyvo);
	}

	@Override
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo) throws RemoteException{
		return strategybl.webstrategy_delete(webstrategyvo);
	}
	
	@Override
	public ArrayList<OrderVO> findorderByClientid(int clientid) throws RemoteException {
		return orderbl.findorderByClientid(clientid);
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Clientid_State(int clientid, String state) throws RemoteException {
		return orderbl.findorderBy_Clientid_State(clientid, state);
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Clientid_Execute(int clientid, boolean isExecute) throws RemoteException {
		return orderbl.findorderBy_Clientid_Execute(clientid, isExecute);
	}

	@Override
	public ArrayList<OrderVO> findorderByHotelid(int hotelid) throws RemoteException {
		return orderbl.findorderByHotelid(hotelid);
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Hotelid_State(int hotelid, String state) throws RemoteException {
		return orderbl.findorderBy_Hotelid_State(hotelid, state);
	}

	@Override
	public ArrayList<OrderVO> findorderBy_Hotelid_Execute(int hotelid, boolean isExecute) throws RemoteException {
		return orderbl.findorderBy_Hotelid_Execute(hotelid, isExecute);
	}

	@Override
	public ResultMessage order_client_cancel(int orderid) throws RemoteException {
		return orderbl.order_client_cancel( orderid);
	}

	@Override
	public ResultMessage order_client_generate(OrderVO vo) throws RemoteException {
		return orderbl.order_client_generate(vo);
	}

	@Override
	public ResultMessage order_hotel_execute(int orderid) throws RemoteException {
		return orderbl.order_hotel_execute(orderid);
	}

	@Override
	public ArrayList<OrderVO> order_market_browseUnfilled() throws RemoteException {
		return orderbl.order_market_browseUnfilled();
	}

	@Override
	public ResultMessage order_market_cancelAbnormal(int orderid) throws RemoteException {
		return orderbl.order_market_cancelAbnormal(orderid);
	}

	@Override
	public double calculateTotalwithoutStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid) throws RemoteException {
		return orderbl.calculateTotalwithoutStrategy(roomlist,hotelid);
	}

	@Override
	public double calculateTotalwithStrategy(ArrayList<RoomOrderVO> roomlist,int hotelid,int clientid) throws RemoteException {
		return orderbl.calculateTotalwithStrategy(roomlist, hotelid,clientid);
	}
	

	@Override
	public String getstrategyname() throws RemoteException {
		return orderbl.getstrategyname();
	}
	
	@Override
	public ResultMessage order_checkin(AccommodationVO info, int orderid) throws RemoteException {
		return orderbl.order_checkin(info, orderid);
	}
	
	@Override
	public OrderVO order_findbyid(int orderid) throws RemoteException {
		return orderbl.order_findbyid(orderid);
	}
	
	@Override
	public ResultMessage order_checkout(int orderid)throws RemoteException {
		return orderbl.order_checkout(orderid);
	}

	@Override
	public ResultMessage offline_checkin(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		return orderbl.offline_checkin(hotelid, room_order);
	}

	@Override
	public ResultMessage offline_checkout(int hotelid, ArrayList<RoomOrderVO> room_order) throws RemoteException {
		return orderbl.offline_checkout(hotelid, room_order);
	}
	
	@Override
	public ArrayList<OrderVO> get_client_hotel_order(int clientid, int hotelid) throws RemoteException {
		return orderbl.get_client_hotel_order(clientid, hotelid);
	}
	
	@Override
	public ResultMessage webmanager_login(String username, String password) throws RemoteException {
		return managebl.webmanager_login(username, password);
	}

	@Override
	public ResultMessage webmarket_login(String username, String password) throws RemoteException {
		return managebl.webmarket_login(username, password);
	}
	
	@Override
	public ResultMessage webmarket_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		return managebl.webmarket_change_password(username, oldpassword, newpassword);
	}

	@Override
	public ResultMessage webmanager_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		return managebl.webmanager_change_password(username, oldpassword, newpassword);
	}
	
	@Override
	public ArrayList<ClientVO> getallclientvo() throws RemoteException {
		return managebl.getallclientvo();
	}

	@Override
	public ArrayList<HotelVO> getallhotelvo() throws RemoteException {
		return managebl.getallhotelvo();
	}

	@Override
	public ArrayList<HotelWorkerVO> getallhotelworkervo() throws RemoteException {
		return managebl.getallhotelworkervo();
	}

	@Override
	public ArrayList<WebMarketVO> getallwebmarketvo() throws RemoteException {
		return managebl.getallwebmarketvo();
	}
	
	@Override
	public int getordernumber() throws RemoteException {
		return managebl.getordernumber();
	}
	
	@Override
	public ClientVO manage_searchClientByClientid(int clientid) throws RemoteException {
		return managebl.manage_searchClientByClientid(clientid);
	}

	@Override
	public ResultMessage manage_updateClient(ClientVO clientvo) throws RemoteException {
		return managebl.manage_updateClient(clientvo);
	}

	@Override
	public ResultMessage manage_addHotel(HotelVO hotelvo) throws RemoteException {
		return managebl.manage_addHotel(hotelvo);
	}

	@Override
	public ResultMessage manage_addHotelWorker(HotelWorkerVO w) throws RemoteException {
		return managebl.manage_addHotelWorker(w);
	}

	@Override
	public HotelWorkerVO manage_searchHotelWorkerByHotelid(int hotelid) throws RemoteException {
		return managebl.manage_searchHotelWorkerByHotelid(hotelid);
	}

	@Override
	public ResultMessage manage_updateHotelWorker(HotelWorkerVO w) throws RemoteException {
		return managebl.manage_updateHotelWorker(w);
	}

	@Override
	public ResultMessage manage_addMarketWorker(WebMarketVO mw) throws RemoteException {
		return managebl.manage_addMarketWorker(mw);
	}

	@Override
	public WebMarketVO manage_searchMarketWorkerByWebmarketid(int marketWorkerid) throws RemoteException {
		return managebl.manage_searchMarketWorkerByWebmarketid(marketWorkerid);
	}

	@Override
	public ResultMessage manage_updateMarketWorker(WebMarketVO mw) throws RemoteException {
		return managebl.manage_updateMarketWorker(mw);
	}

	@Override
	public ResultMessage hotelworker_login(String username, String password) throws RemoteException {
		return hotelbl.hotelworker_login(username, password);
	}
	
	@Override
	public ResultMessage hotelworker_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		return hotelbl.hotelworker_change_password(username, oldpassword, newpassword);
	}

	@Override
	public HotelVO hotel_getInfo(int hotelid) throws RemoteException {
		return hotelbl.hotel_getInfo(hotelid);
	}

	@Override
	public ResultMessage hotel_updateInfo(HotelVO vo) throws RemoteException {
		return hotelbl.hotel_updateInfo(vo);
	}

	@Override
	public ResultMessage hotel_importRoom(RoomVO room) throws RemoteException {
		return hotelbl.hotel_importRoom(room);
	}
	
	@Override
	public ResultMessage hotel_updateRoom(RoomVO room) throws RemoteException {
		return hotelbl.hotel_updateRoom(room);
	}


	@Override
	public Hotel searchHotel(int hotelid) throws RemoteException{
		return hotelbl.searchHotel(hotelid);
	}

	@Override
	public ArrayList<Hotel> getpreviousHotel(int clientid) throws RemoteException{
		return hotelbl.getpreviousHotel(clientid);
	}

	@Override
	public ResultMessage addHotel(Hotel hotel) throws RemoteException{
		return hotelbl.addHotel(hotel);
	}

	@Override
	public ResultMessage addHotelWorker(HotelWorker worker) throws RemoteException{
		return hotelbl.addHotelWorker(worker);
	}

	@Override
	public HotelWorker searchHotelWorker(int hotelid) throws RemoteException{
		return hotelbl.searchHotelWorker(hotelid);
	}

	@Override
	public ResultMessage updateHotelWokerInfo( HotelWorker worker) throws RemoteException{
		return hotelbl.updateHotelWokerInfo(worker);
	}
	
	@Override
	public ArrayList<HotelVO> searchHotelBylocation(String address, String business_address) throws RemoteException {
		return hotelbl.searchHotelBylocation(address, business_address);
	}
	
	@Override
	public ArrayList<HotelVO> searchHotelByname(ArrayList<HotelVO> list,String hotelname) throws RemoteException {
		return hotelbl.searchHotelByname(list, hotelname);
	}

	@Override
	public ArrayList<HotelVO> searchHotelByroom(ArrayList<HotelVO> list,String type) throws RemoteException {
		return hotelbl.searchHotelByroom(list, type);
	}

	@Override
	public ArrayList<HotelVO> searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice) throws RemoteException {
		return hotelbl.searchHotelByprice(list, lowprice, highprice);
	}

	@Override
	public ArrayList<HotelVO> searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime) throws RemoteException {
		return hotelbl.searchHotelBytime(list, inTime, leaveTime);
	}

	@Override
	public ArrayList<HotelVO> searchHotelBystar(ArrayList<HotelVO> list,String star) throws RemoteException {
		return hotelbl.searchHotelBystar(list, star);
	}

	@Override
	public ArrayList<HotelVO> searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore) throws RemoteException {
		return hotelbl.searchHotelByscore(list, lowscore, highscore);
	}
	
	@Override
	public ArrayList<HotelVO> searchpreviousHotelList(ArrayList<HotelVO> hotelvolist, int clientid)
			throws RemoteException {
		return hotelbl.searchpreviousHotelList(hotelvolist, clientid);
	}
	
	@Override
	public ResultMessage evalutehotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
		return hotelbl.evalutehotel(e, clientid, hotelid);
	}

	@Override
	public ArrayList<RoomVO> getallroom(int hotelid) throws RemoteException {
		return hotelbl.getallroom(hotelid);
	}
	
	@Override
	public ResultMessage client_login(String username, String password) throws RemoteException {
		return clientbl.client_login(username, password);
	}

	@Override
	public ResultMessage client_register(String username, String password) throws RemoteException {
		return clientbl.client_register(username, password);
	}
	
	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		return clientbl.client_change_password(username, oldpassword, newpassword);
	}


	@Override
	public ClientVO client_checkInfo(int clientid) throws RemoteException {
		return clientbl.client_checkInfo(clientid);
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) throws RemoteException {
		return clientbl.client_updateInfo(vo);
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) throws RemoteException {
		return clientbl.client_getpreviousHotelList(clientid);
	}

	@Override
	public int client_checkCredit(int clientid) throws RemoteException {
		return clientbl.client_checkCredit(clientid);
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) throws RemoteException {
		return clientbl.client_checkCreditList(clientid);
	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) throws RemoteException {
		return clientbl.client_checkHotelInfo(hotelid);
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException {
		return clientbl.client_evaluateHotel(e, clientid,hotelid);
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) throws RemoteException {
		return clientbl.client_enrollVIP(info, clientid);
	}

	@Override
	public ResultMessage updateClientCredit(int clientId, int value, int tag) throws RemoteException{
		return clientbl.updateClientCredit(clientId, value, tag);
	}

	@Override
	public Client checkClientInfo(int clientid) throws RemoteException{
		return clientbl.checkClientInfo(clientid);
	}

	@Override
	public ResultMessage updateClientInfo(Client client) throws RemoteException{
		return clientbl.updateClientInfo(client);
	}
	
	@Override
	public ResultMessage client_updateClientCreditList(int clientid, String CreditInfo) throws RemoteException {
		return clientbl.client_updateClientCreditList(clientid, CreditInfo);
	}
	
	@Override
	public WebMarketVO webmarket_getvo(String username) throws RemoteException {
		return managebl.webmarket_getvo(username);
	}

	@Override
	public WebManagerVO webmanager_getvo(String username) throws RemoteException {
		return managebl.webmanager_getvo(username);
	}
	
	@Override
	public HotelWorkerVO hotelworker_getvo(String username) throws RemoteException {
		return hotelbl.hotelworker_getvo(username);
	}

	@Override
	public ClientVO client_getclientvo(String username) throws RemoteException {
		return clientbl.client_getclientvo(username);
	}



	@Override
	public String update_client_viplevel(int credit) throws RemoteException{
		return clientbl.update_client_viplevel(credit);
	}










	



	

	





	


	


	



	

}
