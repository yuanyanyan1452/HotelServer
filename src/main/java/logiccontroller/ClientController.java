package logiccontroller;

import java.util.ArrayList;

import mock.MockClient1;
import objects.*;
import objects.VIPInfo.VIPType;
import vo.*;
import service.blservice.*;

public class ClientController implements ClientBLService {
	// �ͻ���Ϣ
	int clientid = 1;
	String client_name = "����";
	String contact = "11111111111";
	int credit = 0;
	ArrayList<String> credit_record = new ArrayList<String>();
	VIPInfo info = new VIPInfo(VIPType.NORMAL,"2000/01/01");

	// �Ƶ���Ϣ
	String address = "�Ͼ�";
	String business_address = "�½ֿ�";
	String hotel_name = "���";
	String introduction = "��";
	String service = "��";
	String room_state = "δԤ��";
	String room_type = "˫�˷�";
	int room_number = 1;
	int room_price = 200;
	String order = "��";
	String evaluation = "��";

	public ClientVO client_checkInfo(int clientid) {
		ClientVO client = new ClientVO(clientid,client_name,contact,credit,credit_record,info);
		return client;
	}

	public ResultMessage client_updateInfo(ClientVO vo) {
		if (vo != null) {
			return ResultMessage.Success;
		} else {
			return ResultMessage.Fail;
		}
	}

	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	public ArrayList<HotelVO> client_setLocation(String location) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	public ArrayList<HotelVO> client_searchHotel(String hotelname) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	public HotelVO client_checkHotelInfo(int hotelid) {
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		return hotel;
	}

	@Override
	public int client_checkCredit(int clientid) {
		return credit;
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) {
		return credit_record;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(RoomType type) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(int lowprice, int highprice) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(String inTime, String leaveTime) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(int star) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public ArrayList<HotelVO> client_searchHotel(double lowscore, double highscore) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,address, business_address, hotel_name, introduction, service, room_state, room_type,
				room_number, room_price, order, evaluation);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public ResultMessage client_evaluateHotel(Evaluation e, int clientid) {
		if(e!=null){
			return ResultMessage.Success;
		}
		else
			return ResultMessage.Fail;
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) {
		if(info!=null){
			return ResultMessage.Success;
		}
		else
			return ResultMessage.Fail;
	}

	@Override
	public ResultMessage updateClientCredit(int clientId, int value, int tag) {
		if(tag==1){
			credit+=value;
		}
		else{
			if(credit-value<0){
				return ResultMessage.Fail;
			}
			else
				credit-=value;
		}
		return ResultMessage.Success;
	}

	@Override
	public Client checkClientInfo(int clientid) {
		MockClient1 client  = new MockClient1(1);
		return client;
	}

	@Override
	public ResultMessage updateClientInfo(Client client) {
		if(client!=null){
			return ResultMessage.Success;
		}
		else {
			return ResultMessage.Fail;
		}
	}

}
