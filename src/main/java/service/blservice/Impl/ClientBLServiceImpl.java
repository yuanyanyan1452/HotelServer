package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Client;
import objects.Hotel;
import objects.ObjectChange;
import objects.ResultMessage;
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
	
	@Override
	public ResultMessage client_login(String username,String password){
		ResultMessage result=clientdataservice.check(username, password);
		return result;
	}
	
	@Override
	public ResultMessage client_register(String username,String password){
		ClientPO clientpo=new ClientPO();
		int clientid=clientdataservice.findClientIDbyUsername(username);
		if(clientid!=-1){
		clientpo.setusername(username);
		clientpo.setpassword(password);
		ResultMessage result=clientdataservice.insert(clientpo);
		return result;
		}
		else{
			return ResultMessage.Fail;
		}
		
	}
	
	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		ResultMessage result=clientdataservice.check(username, oldpassword);
		System.out.println("r1="+result);
		if(result==ResultMessage.Success){
		ClientPO clientpo=clientdataservice.getclientpo(username, oldpassword);
		clientpo.setpassword(newpassword);
		ResultMessage result1=clientdataservice.update(clientpo);
		System.out.println("r2="+result1);
		return result1;
		}
		else{
			return ResultMessage.Fail;
		}
	}
	
	
	@Override
	public ClientVO client_checkInfo(int clientid) {
		ClientPO clientpo=clientdataservice.find(clientid);
		ClientVO clientvo=clientpo.changetoclientvo();
		return clientvo;
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		ClientPO po=vochange.clientvo_to_clientpo(vo);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) throws RemoteException{
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
		int credit_number=clientdataservice.find(clientid).getcredit();
		return credit_number;
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) {
		ArrayList<String> credit_list=clientdataservice.find(clientid).getcredit_record();
		return credit_list;
	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) throws RemoteException{
		Hotel hotel=hotelblservice.searchHotel(hotelid);
		HotelVO hotelvo=objectchange.changetohotelvo(hotel);
		return hotelvo;
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid,int hotelid) throws RemoteException {
		ResultMessage result=hotelblservice.evalutehotel(e, clientid, hotelid);
		return result;
	}

	@Override
	public ResultMessage client_enrollVIP(VIPInfo info, int clientid) {
		ClientPO po=clientdataservice.find(clientid);
		po.setvipinfo(info);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}
	

	@Override
	public ResultMessage client_updateClientCreditList(int clientid, String CreditInfo) throws RemoteException {
		ClientPO clientpo=clientdataservice.find(clientid);
		clientpo.getcredit_record().add(CreditInfo);
		ResultMessage result=clientdataservice.update(clientpo);
		return result;
	}


	//
	@Override
	public ResultMessage updateClientCredit(int clientid, int value, int tag) {
		ClientPO clientpo=clientdataservice.find(clientid);
		int credit=clientpo.getcredit();
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
		ClientPO clientpo=clientdataservice.find(clientid);
		Client client=clientpo.changetoclient();
		return client;
	}

	@Override
	public ResultMessage updateClientInfo(Client client) {
		ClientPO po=objectchange.changetoclientpo(client);
		ResultMessage result=clientdataservice.update(po);
		return result;
	}

	@Override
	public ClientVO client_getclientvo(String username) throws RemoteException {
		int clientid=clientdataservice.findClientIDbyUsername(username);
		ClientPO clientpo=clientdataservice.find(clientid);
		ClientVO vo=clientpo.changetoclientvo();
		return vo;
	}

	

}
