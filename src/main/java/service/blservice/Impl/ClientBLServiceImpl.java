package service.blservice.Impl;

import java.util.ArrayList;

import objects.Client;
import objects.Evaluation;
import objects.Hotel;
import objects.ResultMessage;
import objects.RoomType;
import objects.VIPInfo;
import po.ClientPO;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.dataservice.ClientDataService;
import service.dataservice.Impl.ClientDataServiceImpl;
import vo.ClientVO;
import vo.HotelVO;

public class ClientBLServiceImpl implements ClientBLService {
	ClientDataService clientdataservice=new ClientDataServiceImpl();
	HotelBLService hotelblservice=new HotelBLServiceImpl();
	
	@Override
	public ClientVO client_checkInfo(int clientid) {
		// TODO Auto-generated method stub
		ClientPO clientpo=clientdataservice.find(clientid);
		
		return null;
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		// TODO Auto-generated method stub
		ResultMessage result=clientdataservice.update(po);
		
		return null;
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<Hotel> previoushotel_list=hotelblservice.previousHotel(clientid);
		
		return null;
	}

	@Override
	public int client_checkCredit(int clientid) {
		// TODO Auto-generated method stub
		int credit_number=clientdataservice.find(clientid).getCredit();
		return credit_number;
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) {
		// TODO Auto-generated method stub
		ArrayList<String> credit_list=clientdataservice.find(clientid).getCredit_record();
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
		
		return null;
	}

	@Override
	public ResultMessage client_evaluateHotel(Evaluation e, int clientid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateClientCredit(int clientId, int value, int tag) {
		// TODO Auto-generated method stub
		return null;
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
		ResultMessage result=clientdataservice.update(po);
		
		return null;
	}

}
