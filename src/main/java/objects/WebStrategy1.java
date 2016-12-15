package objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WebStrategy1 extends WebStrategy {


	public WebStrategy1(){
		wsid=1;
		name="开业酬宾";
		condition="无";
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
		start_time=fmt.parse("2016-10-14 00:00:00");
		end_time=fmt.parse("2016-10-20 23:59:59");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		executeway="九折";
		superposition=false;
	}

}
