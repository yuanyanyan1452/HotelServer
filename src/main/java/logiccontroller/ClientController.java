package logiccontroller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import mock.MockClient1;
import objects.*;
import objects.VIPInfo.VIPType;
import vo.*;
import service.blservice.*;

public class ClientController implements ClientBLService {
	// 客户信息
	int clientid = 1;
	String client_name = "relate";
	String contact = "8989";
	int credit = 4800;
	ArrayList<String> credit_record = new ArrayList<String>();
	VIPInfo info = new VIPInfo(VIPType.NORMAL,"三级会员,南京大学, 仙林中心,学则路");
	String username="relate";
	String password="chinese";
	boolean logged=false;

	// 酒店信息
	int id=1;
	String name="天丰大酒店";
	String address = "南京市白下区洪武路26号";
	String business_address = "新街口";
	String introduction = "四星级商务酒店";
	String service = "wifi,餐饮,停车";
	String star="四星级";
	String score="4.2,20";
	ArrayList<String>hotel_evaluation=new ArrayList<String>();
	ArrayList<Integer>book_clientid=new ArrayList<Integer>();
	
	@Override
	public ClientVO client_checkInfo(int clientid) {
		ClientVO client = new ClientVO(clientid,client_name,contact,credit,credit_record,info,username,password);
		return client;
	}
	
	@Override
	public ResultMessage client_updateInfo(ClientVO vo) {
		if (vo != null) {
			return ResultMessage.Success;
		} else {
			return ResultMessage.Fail;
		}
	}
	
	@Override
	public ArrayList<HotelVO> client_getpreviousHotelList(int clientid) {
		ArrayList<HotelVO> HotelList_Client = new ArrayList<HotelVO>();
		HotelVO hotel = new HotelVO(1,name,address, business_address, introduction, service, star,score,hotel_evaluation,book_clientid);
		HotelList_Client.add(hotel);
		return HotelList_Client;
	}

	@Override
	public HotelVO client_checkHotelInfo(int hotelid) {
		HotelVO hotel = new HotelVO(1,name,address, business_address, introduction, service, star,score,hotel_evaluation,book_clientid);
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

	@Override
	public ResultMessage client_login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		if(username.equals(this.username)&&password.equals(this.password)){
			return ResultMessage.Success;
		}else{
			return ResultMessage.Fail;
		}
	}

	@Override
	public ClientVO client_getclientvo(String username) throws RemoteException {
		// TODO Auto-generated method stub
		if(username.equals(this.username)){
			ClientVO client=new ClientVO(clientid,client_name,contact,credit,credit_record,info,username,password);
			return client;
		}else{
			return null;
		}
	}

	@Override
	public ResultMessage client_register(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		if(!username.equals(this.username)&&username!=null&&password!=null){
			return ResultMessage.Success;
		}else{
			return ResultMessage.Fail;
		}
	}

	@Override
	public ResultMessage client_change_password(String username, String oldpassword, String newpassword)
			throws RemoteException {
		// TODO Auto-generated method stub
		if(username.equals("relate")&&oldpassword.equals(this.password)){
			this.password=newpassword;
			return ResultMessage.Success;
		}else{
			return ResultMessage.Fail;
		}
	}

	@Override
	public ResultMessage client_evaluateHotel(EvaluationVO e, int clientid, int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		if(clientid==this.clientid&&hotelid==this.id){
			String[]temp=this.score.split(",");
			double s=Double.parseDouble(temp[0]);
			s=(s*Integer.parseInt(temp[1])+e.getScore())/(Integer.parseInt(temp[1])+1);
			score=s+","+(Integer.parseInt(temp[1])+1);
			hotel_evaluation.add(e.getComments());
			return ResultMessage.Success;
		}else{
			return ResultMessage.Fail;
		}
	}

	@Override
	public ResultMessage client_updateClientCreditList(int clientid, String CreditInfo) throws RemoteException {
		// TODO Auto-generated method stub
		if(clientid==this.clientid){
			credit_record.add(CreditInfo);
			return ResultMessage.Success;
		}
		else{
			return ResultMessage.Fail;
		}
	}

	@Override
	public String update_client_viplevel(int credit) throws RemoteException {
		// TODO Auto-generated method stub
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
