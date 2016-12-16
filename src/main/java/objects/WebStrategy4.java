package objects;

public class WebStrategy4 extends WebStrategy implements Calculate{
	//关门大吉
	
		@Override
		public double calculate(int clientid, int hotelid, double price, int roomnumber) {
			price*=0.3;
			return price;
		}
}
