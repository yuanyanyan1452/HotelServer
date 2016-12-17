package test;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import objects.ResultMessage;
import service.blservice.Impl.StrategyBLServiceImpl;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;


public class StrategyTest {
	StrategyBLServiceImpl strategyblImpl = new StrategyBLServiceImpl();
	
	@Test
	public void testisHotelstrategy_make(){
		HotelStrategyVO hsvo = new HotelStrategyVO(6,4,"开业酬宾","无",String2Date("2016-11-14 00:00:00"),
				String2Date("2016-11-24 00:00:00"),"九折",true);
		assertEquals(ResultMessage.Success,strategyblImpl.hotelstrategy_make(hsvo));
	}
	
	@Test
	public void testisHotelstrategy_update(){
		HotelStrategyVO hsvo = new HotelStrategyVO(6,4,"开业酬宾","无",String2Date("2016-11-14 00:00:00"),
				String2Date("2016-11-24 00:00:00"),"九折",true);
		assertEquals(ResultMessage.Success,strategyblImpl.hotelstrategy_make(hsvo));
	}
	
	@Test
	public void testisWebstrategy_make(){
		WebStrategyVO wsvo = new WebStrategyVO(11,"专属商圈折扣","新街口",String2Date("2016-11-14 00:00:00"),
				String2Date("2016-11-24 00:00:00"),"九折",true);
		assertEquals(ResultMessage.Success,strategyblImpl.webstrategy_make(wsvo));
	}
	
	@Test
	public void testisWebstrategy_update(){
		WebStrategyVO wsvo = new WebStrategyVO(11,"专属商圈折扣","新街口",String2Date("2016-11-14 00:00:00"),
				String2Date("2016-11-24 00:00:00"),"九折",true);
		assertEquals(ResultMessage.Success,strategyblImpl.webstrategy_make(wsvo));
	}
	
	@Test
	public void testgetHotelStrategy(){
		
		ArrayList<HotelStrategyVO> expect_list = new ArrayList<HotelStrategyVO>();
		HotelStrategyVO hsvo = new HotelStrategyVO(3,2,"三间及以上预订优惠","无",String2Date("2000-01-01 00:00:00"),
				String2Date("2100-12-30 00:00:00"),"九折",false);
		expect_list.add(hsvo);
		
		assertEquals(expect_list,strategyblImpl.getHotelStrategy(3));
	}
	
	@Test
	public void testgetWebStrategy(){
		assertEquals(10,strategyblImpl.getWebStrategy().size());
	}
	
	@Test
	public void testgethotelstrategybyname() throws RemoteException{
		HotelStrategyVO expectvo = new HotelStrategyVO(3,2,"三间及以上预订优惠","无",String2Date("2000-01-01 00:00:00"),
				String2Date("2100-12-30 00:00:00"),"九折",false);
		assertEquals(expectvo,strategyblImpl.gethotelstrategybyname("三间及以上预订优惠"));
	}
	
	@Test
	public void testgetwebstrategybyname() throws RemoteException{
		WebStrategyVO expectvo = new WebStrategyVO(2,"双11活动折扣","无",String2Date("2016-11-11 00:00:00"),
				String2Date("2016-11-11 23:59:59"),"七折",true);
		assertEquals(expectvo,strategyblImpl.getwebstrategybyname("双11活动折扣"));
	}
	
	
	/**
	 * @throws RemoteException
	 * 执行delete测试后会删除数据库的数据。
	 * 	故暂时注释。
	 */
//	@Test
//	public void testhotelstrategy_delete() throws RemoteException {
//		HotelStrategyVO hotelstrategyvo = new HotelStrategyVO(3,2,"三间及以上预订优惠","无",String2Date("2000-01-01 00:00:00"),
//				String2Date("2100-12-30 00:00:00"),"九折",false);
//		assertEquals(ResultMessage.Success,strategyblImpl.hotelstrategy_delete(hotelstrategyvo));
//	}
//	
//	@Test
//	public void testwebstrategy_delete() throws RemoteException{
//		WebStrategyVO vo = new WebStrategyVO(2,"双11活动折扣","无",String2Date("2016-11-11 00:00:00"),
//				String2Date("2016-11-11 23:59:59"),"七折",true);
//		assertEquals(ResultMessage.Success,strategyblImpl.webstrategy_delete(vo));
//	}
	
	Date String2Date(String str){
		Date date;
		try{  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		    date = sdf.parse(str);  
		    return date;
		} catch (ParseException e)  {  
		    System.out.println(e.getMessage()); 
		    return null;
		}  

	}
}
