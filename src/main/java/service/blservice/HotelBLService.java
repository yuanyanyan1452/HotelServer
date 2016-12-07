package service.blservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import objects.Hotel;
import objects.HotelWorker;
import objects.ResultMessage;
import vo.AccommodationVO;
import vo.HotelVO;
import vo.RoomVO;

public interface HotelBLService extends Remote{
	// 提供给界面调用的接口
	
	public ResultMessage hotelworker_login(String username,String password) throws RemoteException;
	
	/**
	 * @param hotelid
	 * @return 酒店查看详细信息
	 */
	public HotelVO hotel_checkInfo(int hotelid)throws RemoteException;

	/**
	 * @param vo
	 * @return 酒店更新详细信息
	 */
	public ResultMessage hotel_updateInfo(HotelVO vo)throws RemoteException;

	/**
	 * @param room
	 * @return 酒店录入可用房间
	 */
	public ResultMessage hotel_importRoom(RoomVO room)throws RemoteException;

	/**
	 * @param info
	 * @return 酒店更新入住信息
	 */
	public ResultMessage hotel_updateAccomodation(AccommodationVO info)throws RemoteException;

	// 提供给同层调用的接口
	/**
	 * @param hotelid
	 * @return 返回对应酒店
	 */
	public Hotel searchHotel(int hotelid);

	/**
	 * @param clientid
	 * @return 返回对应客户预定过的酒店列表
	 */
	public ArrayList<Hotel> previousHotel(int clientid);

	/**
	 * @param hotel
	 * @return 添加酒店
	 */
	public ResultMessage addHotel(Hotel hotel);

	/**
	 * @param worker
	 * @return 添加酒店工作人员
	 */
	public ResultMessage addHotelWorker(HotelWorker worker);

	/**
	 * @param hotelid
	 * @return 返回对应酒店的工作人员
	 */
	public HotelWorker searchHotelWorker(int hotelid);

	/**
	 * @param hotelid
	 * @param worker
	 * @return 更新对应酒店的工作人员信息
	 */
	public ResultMessage updateHotelWokerInfo(HotelWorker worker);
}
