package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WebStrategy2 extends WebStrategy {


	public WebStrategy2(){
		wsid=2;
		name="双十一活动折扣";
		condition="无";
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
		start_time=fmt.parse("2016-11-11 00:00:00");
		end_time=fmt.parse("2016-11-11 23:59:59");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		executeway="七折";
		superposition=true;
	}

}
