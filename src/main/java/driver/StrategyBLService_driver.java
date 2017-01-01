package driver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.ResultMessage;
import service.blservice.StrategyBLService;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;

public class StrategyBLService_driver {
	public void drive(StrategyBLService strategyBLService){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		ResultMessage resultMessage=ResultMessage.Success;
		
		try {
			HotelStrategyVO hs=new HotelStrategyVO(1,1,"开业酬宾","开业酬宾",fmt.parse("2016-10-14 00:00:00"),
					fmt.parse("2016-10-20 00:00:00"),"九折",false);
			
			WebStrategyVO ws=new WebStrategyVO(1,"开业酬宾","开业酬宾",fmt.parse("2016-10-14 00:00:00"),
					fmt.parse("2016-10-20 00:00:00"),"九折",false);
			
			//hotelstrategy_make
			resultMessage=strategyBLService.hotelstrategy_make(hs);
			if(resultMessage==ResultMessage.Success){
				System.out.print("制定酒店促销策略成功");
			}else{
				System.out.print("制定酒店促销策略失败");
			}
			
			//hotelstrategy_update
			hs.setexecuteway("八折");
			resultMessage=strategyBLService.hotelstrategy_update(hs);
			if(resultMessage==ResultMessage.Success){
				System.out.print("更新酒店促销策略成功");
			}else{
				System.out.print("更新酒店促销策略失败");
			}
			
			//webstrategy_make
			resultMessage=strategyBLService.webstrategy_make(ws);
			if(resultMessage==ResultMessage.Success){
				System.out.print("制定网站促销策略成功");
			}else{
				System.out.print("制定网站促销策略失败");
			}
			
			//webstrategy_update
			ws.setexecuteway("八折");
			resultMessage=strategyBLService.webstrategy_update(ws);
			if(resultMessage==ResultMessage.Success){
				System.out.print("更新网站促销策略成功");
			}else{
				System.out.print("更新网站促销策略失败");
			}
			
			//getHotelStrategy
			ArrayList<HotelStrategyVO>  allHotelStrategy=strategyBLService.getHotelStrategy(1);
			System.out.print(allHotelStrategy);
			
			//getWebStrategy
			ArrayList<WebStrategyVO>  allWebStrategy=strategyBLService.getWebStrategy();
			System.out.print(allWebStrategy);
			
			//gethotelstrategybyname
			HotelStrategyVO findHotelStrategyByName=strategyBLService.gethotelstrategybyname("开业酬宾");
			System.out.print(findHotelStrategyByName);
			
			//getwebstrategybyname
			WebStrategyVO findWebStrategyByName=strategyBLService.getwebstrategybyname("开业酬宾");
			System.out.print(findWebStrategyByName);
			
			//hotelstrategy_delete
			resultMessage=strategyBLService.hotelstrategy_delete(hs);
			if(resultMessage==ResultMessage.Success){
				System.out.print("删除酒店促销策略成功");
			}else{
				System.out.print("删除酒店促销策略失败");
			}
			
			//webstrategy_delete
			resultMessage=strategyBLService.webstrategy_delete(ws);
			if(resultMessage==ResultMessage.Success){
				System.out.print("删除网站促销策略成功");
			}else{
				System.out.print("删除网站促销策略失败");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
