package po;

import java.io.Serializable;
import java.util.Date;

import objects.HotelStrategy;
import vo.HotelStrategyVO;

public class HotelStrategyPO implements Serializable{
	private static final long serialVersionUID = 1L;
	int hsid;
	int hotelid;
	String name;
	String condition;
	Date start_time;
	Date end_time;
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
	
	public HotelStrategyPO(int id,int hid,String n,String con,Date st,Date et,String ew,boolean iss){
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
	
	public void setstart_time(Date start_time){
		this.start_time=start_time;
	}
	public Date getstart_time(){
		return start_time;
	}
	
	public void setend_time(Date end_time){
		this.end_time=end_time;
	}
	public Date getend_time(){
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
	
	public HotelStrategy changetohotelstrategy(){
		HotelStrategy hotelstrategy=new HotelStrategy();
		hotelstrategy.setid(this.hsid);
		hotelstrategy.sethotelid(this.hotelid);
		hotelstrategy.setname(this.name);
		hotelstrategy.setcondition(this.condition);
		hotelstrategy.setstart_time(this.start_time);
		hotelstrategy.setend_time(this.end_time);
		hotelstrategy.setexecuteway(this.executeway);
		hotelstrategy.setsuperposition(this.superposition);
		return hotelstrategy;
	}
	
	public HotelStrategyVO changetohotelstrategyvo(){
		HotelStrategyVO vo=new HotelStrategyVO();
		vo.setid(this.hsid);
		vo.sethotelid(this.hotelid);
		vo.setname(this.name);
		vo.setcondition(this.condition);
		vo.setstart_time(this.start_time);
		vo.setend_time(this.end_time);
		vo.setexecuteway(this.executeway);
		vo.setsuperposition(this.superposition);
		return vo;
	}
}
