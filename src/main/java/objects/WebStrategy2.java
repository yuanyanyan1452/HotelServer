package objects;

public class WebStrategy2 extends WebStrategy implements Calculate{
	//双十一折扣
	
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		price*=0.7;
		return price;
	}

}
