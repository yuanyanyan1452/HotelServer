package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelPO;
import po.HotelWorkerPO;
import service.dataservice.HotelDataService;

public class HotelDataServiceImpl implements HotelDataService{

	@SuppressWarnings("null")
	@Override
	public HotelPO find(int hotelid){
		// TODO Auto-generated method stub
		HotelPO hotelPO = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from hotel where id = "+hotelid;
		conn = Connect.getConn();
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
				hotelPO.setroom_state(rs.getString("room_state"));
				hotelPO.setroom_type(rs.getString("room_type"));
				hotelPO.setroom_number(rs.getInt("room_number"));
				hotelPO.setroom_price(rs.getInt("room_price"));
				hotelPO.setorder(rs.getString("hotel_order"));
				hotelPO.setevaluation(rs.getString("evaluation"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hotelPO;
	}

	@Override
	public ResultMessage insert(HotelPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into hotel values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getroom_state());
			ps.setString(7, po.getroom_type());
			ps.setInt(8, po.getroom_number());
			ps.setInt(9, po.getroom_price());
			ps.setString(10, po.getorder());
			ps.setString(11, po.getevaluation());
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
				po.setid(rs.getInt("id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultMessage update(HotelPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update hotel set name=?,address=?,business_address=?,introduction=?,"
				+ "service=?,room_state=?,room_type=?,room_number=?,room_price=?,hotel_order=?,"
				+ "hotel_evaluation=? where id=?";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getroom_state());
			ps.setString(7, po.getroom_type());
			ps.setInt(8, po.getroom_number());
			ps.setInt(9, po.getroom_price());
			ps.setString(10, po.getorder());
			ps.setString(11, po.getevaluation());
			ps.setInt(12, po.getid());
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
	public ResultMessage delete(HotelPO po) {
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
		HotelPO po;
		OrderDataServiceImpl orderImpl=new OrderDataServiceImpl();
		
		return null;
	}

	@Override
	public HotelWorkerPO findHotelWorker(String hotelname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage insertHotelWorker(String hotelname, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateHotelWorker(String hotelname, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteHotelWorker(String hotelname, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[]args){
		HotelPO hotelpo = new HotelPO(10243,"1","1","1","1","1","1","1",1,1,"1","1");
		HotelDataServiceImpl a = new HotelDataServiceImpl();
		System.out.println(a.insert(hotelpo));
		System.out.println(hotelpo.getid());
		
	}

	@Override
	public HotelPO find(String hotelname) {
		// TODO Auto-generated method stub
		return null;
	}
}
