package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelPO;
import po.HotelWorkerPO;
import po.OrderPO;
import service.dataservice.HotelDataService;

public class HotelDataServiceImpl implements HotelDataService{

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
				hotelPO.setevaluation(rs.getString("evaluation"));
				hotelPO.setmark(rs.getInt("mark"));
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
				hotelPO.setevaluation(rs.getString("hotel_evaluation"));
				hotelPO.setmark(rs.getInt("mark"));
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
	public ResultMessage insert(HotelPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into hotel values(NULL,?,?,?,?,?,?,?)";

		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getevaluation());
			ps.setInt(7, po.getmark());
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
				+ "service=?,hotel_evaluation=?,mark=? where id=?";
		conn = Connect.getConn();
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getaddress());
			ps.setString(3, po.getbussiness_address());
			ps.setString(4, po.getintroduction());
			ps.setString(5, po.getservice());
			ps.setString(6, po.getevaluation());
			ps.setInt(7, po.getmark());
			ps.setInt(8, po.getid());
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

	@Override
	public HotelWorkerPO findHotelWorker(int hotelid) {
		// TODO Auto-generated method stub
		HotelWorkerDataServiceImpl worker=new HotelWorkerDataServiceImpl();
		
		return worker.find(hotelid);
	}

	@Override
	public ResultMessage insertHotelWorker(int hotelid, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		HotelWorkerDataServiceImpl worker=new HotelWorkerDataServiceImpl();
		
		return worker.insert(po);
	}

	@Override
	public ResultMessage updateHotelWorker(int hotelid, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		HotelWorkerDataServiceImpl worker=new HotelWorkerDataServiceImpl();
		
		return worker.update(po);
	}

	@Override
	public ResultMessage deleteHotelWorker(int hotelid, HotelWorkerPO po) {
		// TODO Auto-generated method stub
		HotelWorkerDataServiceImpl worker=new HotelWorkerDataServiceImpl();
		
		return worker.delete(po);
	}

}
