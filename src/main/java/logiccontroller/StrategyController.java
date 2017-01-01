package logiccontroller;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.ResultMessage;
import service.blservice.StrategyBLService;
import vo.HotelStrategyVO;
import vo.WebStrategyVO;



 public class StrategyController implements StrategyBLService{
	 SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//酒店促销策略信息
	 int hsid=1;
	 int hotelid=1;
	 String hs_name="开业酬宾";
	 String hs_condition="开业酬宾";
	 String hs_start_time="2016-10-14 00:00:00";
	 String hs_end_time="2016-10-20 00:00:00";
	 String hs_executeway="九折";
	 boolean hs_superposition=false;
		
    //网站促销策略信息
	 int wsid=1;
	 String ws_name="开业酬宾";
	 String ws_condition="开业酬宾";
	 String ws_start_time="2016-10-14 00:00:00";
	 String ws_end_time="2016-10-20 00:00:00";
	 String ws_executeway="九折";
	 boolean ws_superposition=false;
	 
	 
	@Override
	public ResultMessage hotelstrategy_make(HotelStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(strategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	@Override
	public ResultMessage hotelstrategy_update(HotelStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(strategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	@Override
	public ResultMessage webstrategy_make(WebStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(strategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	@Override
	public ResultMessage webstrategy_update(WebStrategyVO strategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(strategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	@Override
	public ArrayList<HotelStrategyVO> getHotelStrategy(int hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		if(hotelid==this.hotelid){
			ArrayList<HotelStrategyVO> hotelStrategyList=new ArrayList<HotelStrategyVO>();
			try {
				hotelStrategyList.add(new HotelStrategyVO(hsid,hotelid,hs_name,hs_condition,
						fmt.parse(hs_start_time),fmt.parse(hs_end_time),hs_executeway,hs_superposition));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return hotelStrategyList;
		}
		return null;
	}
	@Override
	public ArrayList<WebStrategyVO> getWebStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyVO> webStrategyList=new ArrayList<WebStrategyVO>();
		try {
			webStrategyList.add(new WebStrategyVO(wsid,ws_name,ws_condition,
					fmt.parse(ws_start_time),fmt.parse(ws_end_time),ws_executeway,ws_superposition));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return webStrategyList;
	}
	@Override
	public HotelStrategyVO gethotelstrategybyname(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if(name.equals(this.hs_name)){
			try {
				return new HotelStrategyVO(hsid,hotelid,hs_name,hs_condition,
						fmt.parse(hs_start_time),fmt.parse(hs_end_time),hs_executeway,hs_superposition);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public WebStrategyVO getwebstrategybyname(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if(name.equals(this.ws_name)){
			try {
				return new WebStrategyVO(wsid,ws_name,ws_condition,
						fmt.parse(ws_start_time),fmt.parse(ws_end_time),ws_executeway,ws_superposition);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	@Override
	public ResultMessage hotelstrategy_delete(HotelStrategyVO hotelstrategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(hotelstrategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	@Override
	public ResultMessage webstrategy_delete(WebStrategyVO webstrategyvo) throws RemoteException {
		// TODO Auto-generated method stub
		if(webstrategyvo!=null){
			return ResultMessage.Success;
		}
		return ResultMessage.Fail;
	}
	

}
