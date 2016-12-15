package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelPO;
import po.OrderPO;
import service.dataservice.HotelDataService;

public class HotelDataServiceImpl implements HotelDataService{
	
	@Override
	public ArrayList<HotelPO> getAllHotel(){
		String sql="select*from hotel";
		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
		ArrayList<HotelPO> hotel_list=hotel.find(sql);
		return hotel_list;
		
	}

	@Override
	public ArrayList<HotelPO> show_hotel_list(String address,String business_address){
		String sql="select *from hotel where address like '%"+address+"%' "
				+ "and business_address='"+business_address+"'";
		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
		ArrayList<HotelPO> hotel_list=hotel.find(sql);
		return hotel_list;
	}

	@Override
	public HotelPO findByid(int hotelid){
		String sql = "select * from hotel where id = "+hotelid;
		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
		ArrayList<HotelPO> hotel_list=hotel.find(sql);
		return hotel_list.get(0);
	}

	
	@Override
	public ArrayList<HotelPO> findByName(String hotelname) {
		String sql = "select * from hotel where name like '%"+hotelname+"%'";
		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
		ArrayList<HotelPO> hotel_list=hotel.find(sql);
		return hotel_list;
	}
	
	@Override
	public ArrayList<HotelPO> findByStar(String star){
		String sql="select*from hotel where star='"+star+"'";
		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
		ArrayList<HotelPO> hotel_list=hotel.find(sql);
		return hotel_list;
	}
	
	@Override
	public synchronized ResultMessage insert(HotelPO po) {
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into hotel values(NULL,?,?,?,?,?,?,?,?,?)";

		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, "");
			ps.setString(5, "");
			ps.setString(6, po.getstar());
			ps.setString(7, po.getscore());
			ps.setString(8,"");
			ps.setString(9, "");
			int i=ps.executeUpdate();
			setid(po);
			if(i==0){
				flag = ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public void setid(HotelPO po){
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from hotel where address = "+po.getaddress() ;
		try{
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized ResultMessage update(HotelPO po) {
		ResultMessage flag = ResultMessage.Success;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update hotel set name=?,address=?,business_address=?,introduction=?,"
				+ "service=?,star=?,score=?,hotel_evaluation=?,book_clientid=? where id=?";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getstar());
			ps.setString(7, po.getscore());
			ps.setString(8, evaluTransformToStr(po.gethotel_evaluation()));
			ps.setString(9, bookTransformToStr(po.getbook_clientid()));
			ps.setInt(10, po.getid());
			int i=ps.executeUpdate();
			if(i==0){
				flag = ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public synchronized ResultMessage delete(HotelPO po) {
		ResultMessage flag = ResultMessage.Success;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql="delete from hotel where id=?";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getid());
			int i=ps.executeUpdate();
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<HotelPO> showClientHotels(int clientid) {
		ArrayList<HotelPO> hotelList=new ArrayList<HotelPO>();
		OrderDataServiceImpl orderImpl=new OrderDataServiceImpl();
		HotelDataServiceImpl hotelImpl=new HotelDataServiceImpl();
		ArrayList<OrderPO> orderList=orderImpl.findByClientid(clientid);
		for(int i=0;i<orderList.size();i++){
			int hotelid=orderList.get(i).gethotelid();
			HotelPO po=new HotelPO();
			po=hotelImpl.findByid(hotelid);
			hotelList.add(po);
		}
		return hotelList;
	}

	public int find_min_price(int hotelid){
		RoomDataServiceImpl room=new RoomDataServiceImpl();
		return room.find_min_price(hotelid);
	}
	
	public int find_max_price(int hotelid){
		RoomDataServiceImpl room=new RoomDataServiceImpl();
		return room.find_max_price(hotelid);
	}
	
	public ArrayList<Integer> bookTransformToArray(String s){
		ArrayList<Integer> book_clientid=new ArrayList<Integer>();
		String[]trans=s.split(",");
		for(int i=0;i<trans.length;i++){
			int temp=Integer.parseInt(trans[i]);
			book_clientid.add(temp);
		}
		return book_clientid;
	}
	
	public String bookTransformToStr(ArrayList<Integer> book_clientid){
		String s="";
		for(int i=0;i<book_clientid.size()-1;i++){
			s+=String.valueOf(book_clientid.get(i))+",";
		}
		s+=String.valueOf(book_clientid.get(book_clientid.size()-1));
		return s;
	}
	
	public ArrayList<String> evaluTransformToArray(String s){
		ArrayList<String> evalu = new ArrayList<String>();
		String[]e=s.split(",");
		for(int i=0;i<e.length;i++){
			String temp=e[i];
			evalu.add(temp);
		}
		return evalu;
		
	}
	
	public String evaluTransformToStr(ArrayList<String> evalu){
		String s="";
		for(int i=0;i<evalu.size()-1;i++){
			s+=evalu.get(i)+",";
		}
		s+=evalu.get(evalu.size()-1);
		return s;
		
	}
	
	public ArrayList<HotelPO> find(String sql){
		ArrayList<HotelPO> hotel_list=new ArrayList<HotelPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				HotelPO hotelPO=new HotelPO();
				hotelPO.setid(rs.getInt("id"));
				hotelPO.setname(rs.getString("name"));
				hotelPO.setaddress(rs.getString("address"));
				hotelPO.setbussiness_address(rs.getString("business_address"));
				hotelPO.setintroduction(rs.getString("introduction"));
				hotelPO.setservice(rs.getString("service"));
				hotelPO.setstar(rs.getString("star"));
				hotelPO.setscore(rs.getString("score"));
				hotelPO.sethotel_evaluation(evaluTransformToArray(rs.getString("hotel_evaluation")));
				hotelPO.setbook_clientid(bookTransformToArray(rs.getString("book_clientid")));
				hotel_list.add(hotelPO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return hotel_list;
		
	}
//	public static void main(String[] args){
//		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
//		System.out.println(hotel.getAllHotel().size());
//	}
}
