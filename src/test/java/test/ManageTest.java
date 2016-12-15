package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.Test;

import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import service.blservice.Impl.ManageBLServiceImpl;
import vo.ClientVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.WebManagerVO;
import vo.WebMarketVO;

public class ManageTest {

	ManageBLServiceImpl manage = new ManageBLServiceImpl();
	
	@Test
	public void test_webmanager_login() {
		try {
			assertEquals(ResultMessage.Success,manage.webmanager_login("sdad", "0000"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_webmarket_login(){
		try {
			assertEquals(ResultMessage.Success,manage.webmarket_login("beauty", "23333"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_webmarket_change_password(){
		try {
			assertEquals(ResultMessage.Success,manage.webmarket_change_password("xianggu", "bling", "bilibili"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_webmanager_change_password(){
		try {
			assertEquals(ResultMessage.Success,manage.webmanager_change_password("sdad", "0000", "6666"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_webmarket_getvo(){
		WebMarketVO vo=new WebMarketVO(1,"小方","5678","beauty","23333");
		try {
			assertEquals(vo,manage.webmarket_getvo("beauty"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_webmanager_getvo(){
		WebManagerVO vo=new WebManagerVO(2,"David","4648","sdad","6666");
		try {
			assertEquals(vo,manage.webmanager_getvo("sdad"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_searchClient(){
		ArrayList<String> record=new ArrayList<String>();
		record.add("'2016-11-08 04:18:36',2,订单异常，2300，100");
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"二级会员,05-13,鼓楼");
		ClientVO vo = new ClientVO(3,"glance","5656",300,record,info,"sing","appreciate");
		
		try {
			assertEquals(vo,manage.manage_searchClient(3));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_updateClient(){
		ArrayList<String> record=new ArrayList<String>();
		record.add("'2016-11-08 04:18:36',2,订单异常，2300，100");
		VIPInfo info=new VIPInfo(VIPType.NORMAL,"二级会员,05-13,鼓楼,新街口");
		ClientVO vo = new ClientVO(3,"glance","5656",300,record,info,"sing","appreciate");
		
		try {
			assertEquals(ResultMessage.Success,manage.manage_updateClient(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_addHotel(){
		ArrayList<String>evalu=new ArrayList<String>();
		ArrayList<Integer> book_id=new ArrayList<Integer>();
		HotelVO vo = new HotelVO(4,"格林豪泰酒店","北京市海淀区","海淀区","四星级商务酒店",
				"wifi,餐饮,停车","四星级","0,0",evalu,book_id);
		
		try {
			assertEquals(ResultMessage.Success,manage.manage_addHotel(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_addHotelWorker(){
		HotelWorkerVO worker=new HotelWorkerVO(4,"panminxue","4444","Nirvana","C++");
		try {
			assertEquals(ResultMessage.Success,manage.manage_addHotelWorker(worker));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_searchHotelWorker(){
		HotelWorkerVO vo=new HotelWorkerVO(1,"Susan","1111","Matin","yyj");
		try {
			assertEquals(vo,manage.manage_searchHotelWorker(1));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_updateHotelWorker(){
		HotelWorkerVO worker=new HotelWorkerVO(4,"panminxue","4444","hongniu","C++");
		try {
			assertEquals(ResultMessage.Success,manage.manage_updateHotelWorker(worker));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_manage_addMarketWorker(){
		WebMarketVO vo=new WebMarketVO(3,"万晓利","1469","fox","story");
		assertEquals(ResultMessage.Success,manage.manage_addMarketWorker(vo));
	}
	
	@Test
	public void test_manage_searchMarketWorker(){
		WebMarketVO vo=new WebMarketVO(1,"小方","5678","beauty","23333");
		assertEquals(vo,manage.manage_searchMarketWorker(1));
	}
	
	@Test
	public void test_manage_updateMarketWorker(){
		WebMarketVO vo=new WebMarketVO(1,"小方","7797","beauty","23333");
		assertEquals(ResultMessage.Success,manage.manage_updateMarketWorker(vo));
	}
}
