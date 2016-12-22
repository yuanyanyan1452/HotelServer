package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import objects.Client;
import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import service.blservice.Impl.ClientBLServiceImpl;
import vo.ClientVO;
import vo.EvaluationVO;
import vo.HotelVO;

public class ClientTest {

	ClientBLServiceImpl client = new ClientBLServiceImpl();
	
	@Test
	public void test_client_login() {
		ResultMessage flag = client.client_login("relate", "chinese");
		assertEquals(ResultMessage.Success,flag);
	}
	
	@Test
	public void test_client_getclientvo(){
		ArrayList<String>credit_record=new ArrayList<String>();
		credit_record.add("'2016-11-12 12:05:39',1,订单执行，1050,2500");
		credit_record.add("'2016-11-21 07:00:00',3,订单执行，2800,4800");
		VIPInfo info=new VIPInfo(VIPType.Enterprise,"三级会员,南京大学, 仙林中心,学则路");
		ClientVO vo=new ClientVO(1,"xijinping","8989",4800,credit_record,info,"relate","chinese");
		try {
			assertEquals(vo,client.client_getclientvo("relate"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_client_register(){
		ResultMessage flag = client.client_register("liuqin", "test");
		assertEquals(ResultMessage.Success,flag);
	}
	
	@Test
	public void test_client_change_password(){
		try {
			ResultMessage flag = client.client_change_password("sing", "fans", "appreciate");
			assertEquals(ResultMessage.Success,flag);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_client_checkInfo(){
		ArrayList<String>credit_record=new ArrayList<String>();
		credit_record.add("'2016-11-12 12:05:39',1,订单执行，1050,2500");
		credit_record.add("'2016-11-21 07:00:00',3,订单执行，2800,4800");
		VIPInfo info=new VIPInfo(VIPType.Enterprise,"三级会员,南京大学, 仙林中心,学则路");
		ClientVO vo=new ClientVO(1,"xijinping","8989",4800,credit_record,info,"relate","chinese");
		
		assertEquals(vo,client.client_checkInfo(1));
	}
	
	@Test
	public void test_client_updateInfo(){
		
		ArrayList<String>credit_record=new ArrayList<String>();
		credit_record.add("'2016-11-12 12:05:39',1,订单执行，1050,2500");
		credit_record.add("'2016-11-21 07:00:00',3,订单执行，2800,4800");
		credit_record.add("'2016-12-01 10:00:00',9,订单执行，500,5300");
		VIPInfo info=new VIPInfo(VIPType.Enterprise,"三级会员,南京大学, 仙林中心,学则路");
		ClientVO vo=new ClientVO(1,"xijinping","8989",4800,credit_record,info,"relate","chinese");
		
		assertEquals(ResultMessage.Success,client.client_updateInfo(vo));
	}
	
//	@Test
//	public void test_client_getpreviousHotelList(){
//		ArrayList<HotelVO> hotel_list=new ArrayList<HotelVO>();
//		ArrayList<String>evalu=new ArrayList<String>();
//		evalu.add("just so so");
//		evalu.add("bad");
//		ArrayList<Integer> book_id=new ArrayList<Integer>();
//		book_id.add(2);
//		book_id.add(3);
//		hotel_list.add(new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
//				"wifi,餐饮,停车","四星级","4.1,15",evalu,book_id));
//		
//		try {
//			assertEquals(hotel_list,client.client_getpreviousHotelList(2));
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void test_client_checkCredit(){
		assertEquals(1000,client.client_checkCredit(2));
	}
	
	@Test
	public void test_client_checkCreditList(){
		ArrayList<String>credit_record=new ArrayList<String>();
		credit_record.add("'2016-11-08 04:18:36',2,订单异常,2300,100");
		assertEquals(credit_record,client.client_checkCreditList(3));
	}
	
	@Test
	public void test_client_checkHotelInfo(){
		ArrayList<String>evalu=new ArrayList<String>();
		evalu.add("just so so");
		evalu.add("bad");
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		book_id.add(2);
		book_id.add(3);
		HotelVO vo = new HotelVO(1,"天丰大酒店","南京市白下区洪武路26号","新街口","四星级商务酒店",
				"wifi,餐饮,停车","四星级","4.1,15",evalu,book_id);
		
		try {
			assertEquals(vo,client.client_checkHotelInfo(1));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_client_evaluateHotel(){
		EvaluationVO vo=new EvaluationVO(4.2,"nice");
		try {
			assertEquals(ResultMessage.Success,client.client_evaluateHotel(vo, 2, 2));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_client_enrollVIP(){
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"一级会员,05-13,鼓楼");
		assertEquals(ResultMessage.Success,client.client_enrollVIP(info, 3));
	}
	
	@Test
	public void test_client_updateClientCreditList(){
		try {
			assertEquals(ResultMessage.Success,client.client_updateClientCreditList(2, "'2016-12-08 01:11:46',10,订单执行,1200,2200"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_updateClientCredit(){
		assertEquals(ResultMessage.Success,client.updateClientCredit(3, 200, 1));
	}
	
	@Test
	public void test_checkClientInfo(){
		ArrayList<String> record=new ArrayList<String>();
		record.add("'2016-11-08 04:18:36',2,订单异常，2300，100");
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"一级会员,05-13,鼓楼");
		Client c = new Client(3,"glance","5656",300,record,info,"sing","appreciate");
		
		assertEquals(c,client.checkClientInfo(3));
	}

	@Test
	public void test_updateClientInfo(){
		ArrayList<String> record=new ArrayList<String>();
		record.add("'2016-11-08 04:18:36',2,订单异常，2300，100");
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"二级会员,05-13,鼓楼");
		ClientVO vo = new ClientVO(3,"glance","5656",300,record,info,"sing","appreciate");
		
		assertEquals(ResultMessage.Success,client.client_updateInfo(vo));
	}
}
