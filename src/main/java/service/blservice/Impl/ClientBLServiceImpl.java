package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.ObjectChange;
import objects.ResultMessage;
import objects.RoomType;
import objects.VIPInfo;
import po.ClientPO;
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
	
	public ResultMessage client_login(String username,String password){
		return null;
	}
	
	public ResultMessage client_register(String username,String password){
		return null;
		
	}
	@Override
	public ClientVO client_checkInfo(int clientid) {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.find(clientid);
		return null;
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		// TODO Auto-generated method stub
		ClientPO po=vochange.clientvo_to_clientpo(vo);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<Hotel> previoushotel_list=hotelblservice.previousHotel(clientid);
		ArrayList<HotelVO> previoushotelVO_list=new ArrayList<HotelVO>();
		for(int i=0;i<previoushotel_list.size();i++){
//			HotelVO hotelvo=previoushotel_list.get(i)
//			previoushotelVO_list.add(hotelvo);
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

	@Override
	public ArrayList<HotelVO> client_setLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(String hotelname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(RoomType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(int lowprice, int highprice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(String inTime, String leaveTime) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(int star) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(double lowscore, double highscore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) {
		// TODO Auto-generated method stub
		Hotel hotel=hotelblservice.searchHotel(hotelid);
		HotelVO hotelvo=objectchange.changetohotelvo(hotel);
		return hotelvo;
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) {
		// TODO Auto-generated method stub
		ClientPO po=clientdataservice.find(clientid);
		po.setvipinfo(info);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

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
		ClientPO clientPO=clientdataservice.find(clientid);
		
		return null;
	}

	@Override
	public ResultMessage updateClientInfo(Client client) {
		// TODO Auto-generated method stub
		ClientPO po=objectchange.changetoclientpo(client);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

}
