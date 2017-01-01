package service.blservice.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import objects.Client;
import objects.Hotel;
import objects.ObjectChange;
import objects.ResultMessage;
import objects.VIPInfo;
import po.ClientPO;
import service.VOChange;
import service.blservice.ClientBLService;
import service.blservice.HotelBLService;
import service.datafactory.datafactory;
import service.datafactory.datafactoryImpl;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;

public class ClientBLServiceImpl implements ClientBLService {
	datafactory datafactory=new datafactoryImpl();
	HotelBLService hotelblservice =new HotelBLServiceImpl();
	VOChange vochange =new VOChange();
	ObjectChange objectchange=new ObjectChange();
	
	@Override
	public ResultMessage client_login(String username,String password){
		ResultMessage result=datafactory.getClientDataService().check(username, password);
		return result;
	}
	
	@Override
	public ResultMessage client_register(String username,String password){
		ClientPO clientpo=new ClientPO();
		int clientid=datafactory.getClientDataService().findClientIDbyUsername(username);
		if(clientid!=-1){
		clientpo.setusername(username);
		clientpo.setpassword(password);
		ResultMessage result=datafactory.getClientDataService().insert(clientpo);
		return result;
		}
		else{
			return ResultMessage.Fail;
		}
		
	}
	
	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		ResultMessage result=datafactory.getClientDataService().check(username, oldpassword);
		System.out.println("r1="+result);
		if(result==ResultMessage.Success){
		ClientPO clientpo=datafactory.getClientDataService().getclientpo(username, oldpassword);
		clientpo.setpassword(newpassword);
		ResultMessage result1=datafactory.getClientDataService().update(clientpo);
		System.out.println("r2="+result1);
		return result1;
		}
		else{
			return ResultMessage.Fail;
		}
	}
	
	
	@Override
	public ClientVO client_checkInfo(int clientid) {
		ClientPO clientpo=datafactory.getClientDataService().find(clientid);
		ClientVO clientvo=clientpo.changetoclientvo();
		return clientvo;
	}

	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		ClientPO po=vochange.clientvo_to_clientpo(vo);
		ResultMessage result=datafactory.getClientDataService().update(po);
		return result;
	}

	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) throws RemoteException{
		ArrayList<Hotel> previoushotel_list=hotelblservice.getpreviousHotel(clientid);
		ArrayList<HotelVO> previoushotelVO_list=new ArrayList<HotelVO>();
		ArrayList<Integer> hotelid=new ArrayList<Integer>();
		for(int i=0;i<previoushotel_list.size();i++){
			hotelid.add(previoushotel_list.get(i).getid());
		}
		LinkedHashSet<Integer> set=new LinkedHashSet<Integer>(hotelid);
		ArrayList<Integer> newidlist=new ArrayList<Integer>(set);
		for(int i=0;i<newidlist.size();i++){
			HotelVO hotelvo=hotelblservice.hotel_getInfo(newidlist.get(i));
			previoushotelVO_list.add(hotelvo);
		}
	
		return previoushotelVO_list;
		
	}

	@Override
	public int client_checkCredit(int clientid) {
		int credit_number=datafactory.getClientDataService().find(clientid).getcredit();
		return credit_number;
	}

	@Override
	public ArrayList<String> client_checkCreditList(int clientid) {
		ArrayList<String> credit_list=datafactory.getClientDataService().find(clientid).getcredit_record();
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
		ClientPO po=datafactory.getClientDataService().find(clientid);
		po.setvipinfo(info);
		ResultMessage result=datafactory.getClientDataService().update(po);
		return result;
	}
	

	@Override
	public ResultMessage client_updateClientCreditList(int clientid, String CreditInfo) throws RemoteException {
		ClientPO clientpo=datafactory.getClientDataService().find(clientid);
		ArrayList<String> creditrecord=clientpo.getcredit_record();
		creditrecord.add(CreditInfo);
		clientpo.setcredit_record(creditrecord);
		ResultMessage result=datafactory.getClientDataService().update(clientpo);
		return result;
	}


	//
	@Override
	public ResultMessage updateClientCredit(int clientid, int value, int tag) {
		ClientPO clientpo=datafactory.getClientDataService().find(clientid);
		int credit=clientpo.getcredit();
		if(tag==1){
			credit+=value;
		}
		else {
			credit-=value;
		}
		clientpo.setcredit(credit);
		
		//更新会员等级
		if(clientpo.getvipinfo() != null){
		VIPInfo info=new VIPInfo();
		info.setType(clientpo.getvipinfo().getType());
		String[] infor=clientpo.getvipinfo().getInfo().split(",");
		infor[0]=update_client_viplevel(credit);
		String newinfo="";
		for(int i=0;i<infor.length-1;i++){
			newinfo+=infor[i];
			newinfo+=",";
		}
		newinfo+=infor[infor.length-1];
		info.setInfo(newinfo);
		clientpo.setvipinfo(info);
		}
		
		ResultMessage result=datafactory.getClientDataService().update(clientpo);
		return result;
	}

	//这里为啥返回Client，，不返回ClientVO
	@Override
	public Client checkClientInfo(int clientid) {
		ClientPO clientpo=datafactory.getClientDataService().find(clientid);
		Client client=clientpo.changetoclient();
		return client;
	}

	@Override
	public ResultMessage updateClientInfo(Client client) {
		ClientPO po=objectchange.changetoclientpo(client);
		ResultMessage result=datafactory.getClientDataService().update(po);
		return result;
	}

	@Override
	public ClientVO client_getclientvo(String username) throws RemoteException {
		int clientid=datafactory.getClientDataService().findClientIDbyUsername(username);
		ClientPO clientpo=datafactory.getClientDataService().find(clientid);
		ClientVO vo=clientpo.changetoclientvo();
		return vo;
	}

	@Override
	public String update_client_viplevel(int credit) {
		int[] postpointarray={5000,50000,500000};
		int[] preintarray={0,5000,50000};
		String[] level={"一级会员","二级会员","三级会员"};
		String viplevel="";
		for(int i=0;i<=1;i++){
			if(preintarray[i]<credit&&postpointarray[i]>=credit){
				viplevel= level[i];
			}
		}
		if(credit>500000){
			viplevel= level[2];
		}
		return viplevel;
	}

	

}
