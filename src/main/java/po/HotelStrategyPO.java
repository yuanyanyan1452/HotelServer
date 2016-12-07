package po;

import java.io.Serializable;

import objects.HotelStrategy;
import vo.HotelStrategyVO;

public class HotelStrategyPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int hsid;
	int hotelid;
	String name;
	String condition;
	String start_time;
	String end_time;
	String executeway;
	boolean superposition;
	
	public HotelStrategyPO(){
		hsid=0;
		hotelid=0;
		name=null;
		condition=null;
		start_time=null;
		end_time=null;
		executeway=null;
		superposition=false;
	}
	
	public HotelStrategyPO(int id,int hid,String n,String con,String st,String et,String ew,boolean iss){
		hsid=id;
		hotelid=hid;
		name=n;
		condition=con;
		start_time=st;
		end_time=et;
		executeway=ew;
		superposition=iss;
	}
	
	public void setid(int id){
		hsid = id;
	}
	public int getid(){
		return hsid;
	}
	
	public void sethotelid(int id){
		hotelid = id;
	}
	public int gethotelid(){
		return hotelid;
	}
	
	public void setname(String name){
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	public void setcondition(String condition){
		this.condition = condition;
	}
	public String getcondition(){
		return condition;
	}
	
	public void setstart_time(String start_time){
		this.start_time=start_time;
	}
	public String getstart_time(){
		return start_time;
	}
	
	public void setend_time(String end_time){
		this.end_time=end_time;
	}
	public String getend_time(){
		return end_time;
	}
	
	public void setexecuteway(String executeway){
		this.executeway=executeway;
	}
	public String getexecuteway(){
		return executeway;
	}
	
	public void setsuperposition(boolean superposition){
		this.superposition=superposition;
	}
	public boolean getsuperposition(){
		return superposition;
	}
	
	public HotelStrategy changetohotelstrategy(HotelStrategyPO po){
		HotelStrategy hotelstrategy=new HotelStrategy();
		hotelstrategy.setid(po.hsid);
		hotelstrategy.sethotelid(po.hotelid);
		hotelstrategy.setname(po.name);
		hotelstrategy.setcondition(po.condition);
		hotelstrategy.setstart_time(po.start_time);
		hotelstrategy.setend_time(po.end_time);
		hotelstrategy.setexecuteway(po.executeway);
		hotelstrategy.setsuperposition(po.superposition);
		return hotelstrategy;
	}
	
	public HotelStrategyVO changetohotelstrategyvo(HotelStrategyPO po){
		HotelStrategyVO vo=new HotelStrategyVO();
		vo.setid(po.hsid);
		vo.sethotelid(po.hotelid);
		vo.setname(po.name);
		vo.setcondition(po.condition);
		vo.setstart_time(po.start_time);
		vo.setend_time(po.end_time);
		vo.setexecuteway(po.executeway);
		vo.setsuperposition(po.superposition);
		return vo;
	}
}
