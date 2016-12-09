package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.ObjectChange;
import objects.ResultMessage;
import objects.VIPInfo;
import po.ClientPO;
import po.HotelPO;
import service.VOChange;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.dataservice.ClientDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;

public class ClientBLServiceImpl implements ClientBLService {
	ClientDataService clientdataservice=new ClientDataServiceImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	VOChange vochange =new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage client_login(String username,String password){
		ResultMessage result=clientdataservice.check(username, password);
		return result;
	}
	
	@Override
	public ResultMessage client_register(String username,String password){
		ClientPO clientpo=new ClientPO();
		clientpo.setusername(username);
		clientpo.setpassword(password);
		ResultMessage result=clientdataservice.insert(clientpo);
		return result;
		
	}
	
	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.getclientpo(username, oldpassword);
		clientpo.setpassword(newpassword);
		ResultMessage result=clientdataservice.update(clientpo);
		return result;
	}
	
	
	@Override
	public ClientVO client_checkInfo(int clientid) {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.find(clientid);
		ClientVO clientvo=clientpo.changetoclientvo();
		return clientvo;
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		// TODO Auto-generated method stub
		ClientPO po=vochange.clientvo_to_clientpo(vo);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) throws RemoteException{
		// TODO Auto-generated method stub
		ArrayList<Hotel> previoushotel_list=hotelblservice.previousHotel(clientid);
		ArrayList<HotelVO> previoushotelVO_list=new ArrayList<HotelVO>();
		for(int i=0;i<previoushotel_list.size();i++){
			HotelVO hotelvo=objectchange.changetohotelvo(previoushotel_list.get(i));
			previoushotelVO_list.add(hotelvo);
		}
		return previoushotelVO_list;
	}

	@Override
	public int client_checkCredit(int clientid) {
		// TODO Auto-generated method stub
		int credit_number=clientdataservice.find(clientid).getcredit();
		return credit_number;
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<String> credit_list=clientdataservice.find(clientid).getcredit_record();
		return credit_list;
	}

//	@Override
//	public ArrayList<HotelVO> client_searchHotelByaddress(String address,String business_address) throws RemoteException{
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelBylocation(address, business_address);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByname(ArrayList<HotelVO> list,String hotelname) throws RemoteException{
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelByname(list,hotelname);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBytype(ArrayList<HotelVO> list,String type) throws RemoteException {
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelByroom(list,type);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//		
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByprice(ArrayList<HotelVO> list,int lowprice, int highprice) throws RemoteException {
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelByprice(list,lowprice, highprice);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBytime(ArrayList<HotelVO> list,String inTime, String leaveTime)throws RemoteException {
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelBytime(list,inTime, leaveTime);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelBystar(ArrayList<HotelVO> list,String star) throws RemoteException{
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelBystar(list,star);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	}
//
//	@Override
//	public ArrayList<HotelVO> client_searchHotelByscore(ArrayList<HotelVO> list,double lowscore, double highscore)throws RemoteException {
//		// TODO Auto-generated method stub
//		ArrayList<Hotel> hotel_list=hotelblservice.searchHotelByscore(list,lowscore, highscore);
//		ArrayList<HotelVO> hotelvo_list=new ArrayList<HotelVO>();
//		for(int i=0;i<hotel_list.size();i++){
//			HotelVO vo=objectchange.changetohotelvo(hotel_list.get(i));
//			hotelvo_list.add(vo);
//		}
//		return hotelvo_list;
//	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) throws RemoteException{
		// TODO Auto-generated method stub
		Hotel hotel=hotelblservice.searchHotel(hotelid);
		HotelVO hotelvo=objectchange.changetohotelvo(hotel);
		return hotelvo;
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage result=hotelblservice.evalutehotel(e, clientid, hotelid);
		return result;
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) {
		// TODO Auto-generated method stub
		ClientPO po=clientdataservice.find(clientid);
		po.setvipinfo(info);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	//
	@Override
	public ResultMessage updateClientCredit(int clientid, int value, int tag) {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.find(clientid);
		int credit=clientpo.getclientid();
		if(tag==1){
			credit+=value;
		}
		else {
			credit-=value;
		}
		clientpo.setcredit(credit);
		ResultMessage result=clientdataservice.update(clientpo);
		return result;
	}

	
	@Override
	public Client checkClientInfo(int clientid) {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.find(clientid);
		Client client=clientpo.changetoclient();
		return client;
	}

	@Override
	public ResultMessage updateClientInfo(Client client) {
		// TODO Auto-generated method stub
		ClientPO po=objectchange.changetoclientpo(client);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	

}
