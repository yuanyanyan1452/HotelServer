package po;

import java.io.Serializable;
import java.util.Date;

import objects.WebStrategy;
import vo.WebStrategyVO;

public class WebStrategyPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int wsid;
	String name;
	String condition;
	Date start_time;
	Date end_time;
	String executeway;
	boolean superposition;
	
	public WebStrategyPO(){
		wsid=0;
		name=null;
		condition=null;
		start_time=null;
		end_time=null;
		executeway=null;
		superposition=false;
	}
	
	public WebStrategyPO(int id,String n,String con,Date st,Date et,String ew,boolean iss){
		wsid=id;
		name=n;
		condition=con;
		start_time=st;
		end_time=et;
		executeway=ew;
		superposition=iss;
	}

	public void setid(int id){
		wsid = id;
	}
	public int getid(){
		return wsid;
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
	
	public WebStrategy changetowebstrategy(){
		WebStrategy webstrategy=new WebStrategy();
		webstrategy.setid(this.wsid);
		webstrategy.setname(this.name);
		webstrategy.setcondition(this.condition);
		webstrategy.setstart_time(this.start_time);
		webstrategy.setend_time(this.end_time);
		webstrategy.setexecuteway(this.executeway);
		webstrategy.setsuperposition(this.superposition);
		return webstrategy;
	}
	
	public WebStrategyVO changetowebstrategyvo(){
		WebStrategyVO vo=new WebStrategyVO();
		vo.setid(this.wsid);
		vo.setname(this.name);
		vo.setcondition(this.condition);
		vo.setstart_time(this.start_time);
		vo.setend_time(this.end_time);
		vo.setexecuteway(this.executeway);
		vo.setsuperposition(this.superposition);
		return vo;
	}
}
