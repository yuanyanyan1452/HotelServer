package driver;
import java.rmi.RemoteException;
import java.util.ArrayList;
import vo.*;
import service.blservice.*;
import objects.*;
import objects.VIPInfo.VIPType;



public class ClientBLService_driver {

	public void drive(ClientBLService clientBLService){
		//client_login
		ResultMessage resultMessage1 = ResultMessage.Fail;
		try {
			resultMessage1 = clientBLService.client_login("relate", "chinese");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage1==ResultMessage.Success){
			System.out.println("登录成功");
		}
		else {
			System.out.println("登录失败");
		}
		
		//client_register
		ResultMessage resultMessage2 = ResultMessage.Fail;
		try {
			resultMessage2 = clientBLService.client_register("xjw", "xjw");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage2==ResultMessage.Success){
			System.out.println("注册成功");
		}
		else {
			System.out.println("注册失败");
		}
		
		//client_change_password
		ResultMessage resultMessage3 = ResultMessage.Fail;
		try {
			resultMessage3 = clientBLService.client_change_password("relate", "chinese", "xjp");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage3==ResultMessage.Success){
			System.out.println("修改密码成功");
		}
		else {
			System.out.println("修改密码失败");
		}
	
		//client_checkInfo
		ClientVO vo = null;
		try {
			vo = clientBLService.client_checkInfo(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(vo.getclient_name());
		System.out.println(vo.getcontact());
		System.out.println(vo.getcredit());
		ArrayList<String> list1 = vo.getcredit_record();
		for(@SuppressWarnings("unused") String s:list1){
			System.out.println(list1);
		}
		VIPInfo info = vo.getvipinfo();
		System.out.println(info.getType());
		System.out.println(info.getInfo());


		//client_updateInfo
		
		ResultMessage resultMessage4 = ResultMessage.Fail;
		try {
			resultMessage4 = clientBLService.client_updateInfo(vo);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage4==ResultMessage.Success){
			System.out.println("更新成功");
		}
		else {
			System.out.println("更新失败");
		}

		//client_getpreviousHotelList
		ArrayList<HotelVO> list = null;
		try {
			list = clientBLService.client_getpreviousHotelList(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HotelVO vo2 = null;
		if(list.isEmpty()){
			System.out.println("未预定过酒店");
		}
		else{
			for(int i=0;i<list.size();i++){
				vo2 = list.get(i);
				System.out.println(vo2.getname());
				System.out.println(vo2.gethotel_evaluation());
			}
		}

		//client_checkCredit
		int credit = 0;
		try {
			credit = clientBLService.client_checkCredit(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(credit);

		//client_checkCreditList
		ArrayList<String> credit_record = null;
		try {
			credit_record = clientBLService.client_checkCreditList(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<credit_record.size();i++){
			System.out.println(credit_record.get(i));
		}
		
		//client_checkHotelInfo
		HotelVO hotelVO = null;
		try {
			hotelVO = clientBLService.client_checkHotelInfo(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(hotelVO.getname());
		System.out.println(hotelVO.getaddress());
		System.out.println(hotelVO.getbussiness_address());
		System.out.println(hotelVO.getintroduction());
		System.out.println(hotelVO.getservice());
		System.out.println(hotelVO.getstar());
		System.out.println(hotelVO.getscore());
		System.out.println(hotelVO.gethotel_evaluation());
		System.out.println(hotelVO.getbook_clientid());
		
		//client_evaluateHotel
		EvaluationVO e=new EvaluationVO(4.2,"nice");
		ResultMessage resultMessage5 = ResultMessage.Fail;
		try {
			resultMessage5 = clientBLService.client_evaluateHotel(e, 1, 1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage5==ResultMessage.Success){
			System.out.println("评价成功");
		}
		else {
			System.out.println("评价失败");
		}
		
		//client_enrollVIP
		VIPType type=VIPType.Enterprise;
		VIPInfo vipinfo=new VIPInfo(type,"一级会员,南京师范大学,学则路");
		ResultMessage resultMessage6 = ResultMessage.Fail;
		try {
			resultMessage6 = clientBLService.client_enrollVIP(vipinfo, 4);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage6==ResultMessage.Success){
			System.out.println("注册会员成功");
		}
		else {
			System.out.println("注册会员失败");
		}
		
		//client_updateClientCreditList
		ResultMessage resultMessage7 = null;
		try {
			resultMessage7 = clientBLService.client_updateClientCreditList(1, "'2016-12-18 02:11:46',10,订单执行,1080,5880");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage7==ResultMessage.Success){
			System.out.println("更新信用记录成功");
		}
		else {
			System.out.println("更新信用记录失败");
		}
		
		//checkClientInfo
		Client checkInfo = null;
		try {
			checkInfo = clientBLService.checkClientInfo(1);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(checkInfo.getclient_name());
		System.out.println(checkInfo.getcontact());
		System.out.println(checkInfo.getcredit());
		
		//updateClientInfo
		ResultMessage resultMessage8 = ResultMessage.Fail;
		ArrayList<String> record;
		try {
			record = clientBLService.checkClientInfo(1).getcredit_record();
			VIPInfo up_info = clientBLService.checkClientInfo(1).getinfo();
			resultMessage8 = clientBLService.updateClientInfo(new Client(1,"relate","chinese",5880,record,up_info,"xijinping","9898"));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(resultMessage8==ResultMessage.Success){
			System.out.println("更新信息成功");
		}
		else {
			System.out.println("更新信息失败");
		}
		
		//client_getclientvo
		try {
			ClientVO getclientvo=clientBLService.client_getclientvo("relate");
			System.out.println(getclientvo.getclient_name());
			System.out.println(getclientvo.getcontact());
			System.out.println(getclientvo.getcredit());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//update_client_viplevel
		try {
			String viplevel=clientBLService.update_client_viplevel(5000);
			System.out.println(viplevel);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
