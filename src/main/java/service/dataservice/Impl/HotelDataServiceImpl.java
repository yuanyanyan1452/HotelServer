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
	public ArrayList<HotelPO> show_hotel_list(String address,String business_address){
		ArrayList<HotelPO> hotel_list=new ArrayList<HotelPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from hotel where address like '%"+address+"%' "
				+ "and business_address='"+business_address+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				HotelPO po=new HotelPO();
				po.setid(rs.getInt("id"));
				po.setname(rs.getString("name"));
				po.setaddress(rs.getString("address"));
				po.setbussiness_address(rs.getString("business_address"));
				po.setintroduction(rs.getString("introduction"));
				po.setservice(rs.getString("service"));
				po.setstar(rs.getString("star"));
				po.setscore(rs.getInt("score"));
				po.sethotel_evaluation(rs.getString("hotel_evaluation"));
				po.setmin_price(rs.getInt("min_price"));
				po.setbook_clientid(transformToArray(rs.getString("book_clientid")));
				hotel_list.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return hotel_list;
	}

	@Override
	public HotelPO findByid(int hotelid){
		// TODO Auto-generated method stub
		HotelPO hotelPO = new HotelPO();
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from hotel where id = "+hotelid;
	
		try{
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				hotelPO.setid(rs.getInt("id"));
				hotelPO.setname(rs.getString("name"));
				hotelPO.setaddress(rs.getString("address"));
				hotelPO.setbussiness_address(rs.getString("business_address"));
				hotelPO.setintroduction(rs.getString("introduction"));
				hotelPO.setservice(rs.getString("service"));
				hotelPO.setstar(rs.getString("star"));
				hotelPO.setscore(rs.getInt("score"));
				hotelPO.sethotel_evaluation(rs.getString("hotel_evaluation"));
				hotelPO.setmin_price(rs.getInt("min_price"));
				hotelPO.setbook_clientid(transformToArray(rs.getString("book_clientid")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hotelPO;
	}
	
	@Override
	public HotelPO findByName(String hotelname) {
		// TODO Auto-generated method stub
		HotelPO hotelPO = new HotelPO();
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from hotel where name = '"+hotelname+"'";

		try{
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				hotelPO.setid(rs.getInt("id"));
				hotelPO.setname(rs.getString("name"));
				hotelPO.setaddress(rs.getString("address"));
				hotelPO.setbussiness_address(rs.getString("business_address"));
				hotelPO.setintroduction(rs.getString("introduction"));
				hotelPO.setservice(rs.getString("service"));
				hotelPO.setstar(rs.getString("star"));
				hotelPO.setscore(rs.getInt("score"));
				hotelPO.sethotel_evaluation(rs.getString("hotel_evaluation"));
				hotelPO.setmin_price(rs.getInt("min_price"));
				hotelPO.setbook_clientid(transformToArray(rs.getString("book_clientid")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hotelPO;
	}
	
	@Override
	public HotelPO findByPrice(String price) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public synchronized ResultMessage insert(HotelPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into hotel values(NULL,?,?,?,?,?,?,?,?,?,?)";

		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getstar());
			ps.setInt(7, po.getscore());
			ps.setString(8, po.gethotel_evaluation());
			ps.setInt(9, po.getmin_price());
			ps.setString(10, transformToStr(po.getbook_clientid()));
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
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update hotel set name=?,address=?,business_address=?,introduction=?,"
				+ "service=?,star=?,score=?,hotel_evaluation=?,min_price=?,book_clientid=? where id=?";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getstar());
			ps.setInt(7, po.getscore());
			ps.setString(8, po.gethotel_evaluation());
			ps.setInt(9, po.getmin_price());
			ps.setString(10,transformToStr(po.getbook_clientid()));
			ps.setInt(11, po.getid());
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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

	public ArrayList<Integer> transformToArray(String s){
		ArrayList<Integer> book_clientid=new ArrayList<Integer>();
		String[]trans=s.split(",");
		for(int i=0;i<trans.length;i++){
			int temp=Integer.parseInt(trans[i]);
			book_clientid.add(temp);
		}
		return book_clientid;
	}
	
	public String transformToStr(ArrayList<Integer> book_clientid){
		String s="";
		for(int i=0;i<book_clientid.size()-1;i++){
			s+=String.valueOf(book_clientid.get(i))+",";
		}
		s+=String.valueOf(book_clientid.get(book_clientid.size()-1));
		return s;
	}
	
//	public static void main(String[]args){
//		HotelDataServiceImpl hotel=new HotelDataServiceImpl();
//		HotelPO po=hotel.findByid(1);
//		System.out.println(po.getbook_clientid().size());
//	}
}
