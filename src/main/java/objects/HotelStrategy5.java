package objects;

public class HotelStrategy5 extends HotelStrategy implements Calculate {
	// 双十一折扣

	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		price *= 0.7;
		return price;
	}

}
