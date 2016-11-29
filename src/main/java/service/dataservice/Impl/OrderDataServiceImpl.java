package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelPO;
import po.OrderPO;
import service.dataservice.OrderDataService;

public class OrderDataServiceImpl implements OrderDataService {

	@SuppressWarnings("null")
	@Override
	public OrderPO findByid(int id) {
		// TODO Auto-generated method stub
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *from orderrecord where id="+id;
		OrderPO po = null;
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setexecute(rs.getBoolean("execute"));
				po.sethotel(rs.getString("hotel"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				po.setroom_type(rs.getString("room_type"));
				po.setroom_number(rs.getInt("room_number"));
				po.setstrategy(rs.getString("strategy"));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return po;
	}
	
	@Override
	public ArrayList<OrderPO> findByClientid(int clientid) {
		// TODO Auto-generated method stub
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *from orderrecord where clientid="+clientid;
		ArrayList<OrderPO> orderlist=new ArrayList<OrderPO>();
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderPO po = null;
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setexecute(rs.getBoolean("execute"));
				po.sethotel(rs.getString("hotel"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				po.setroom_type(rs.getString("room_type"));
				po.setroom_number(rs.getInt("room_number"));
				po.setstrategy(rs.getString("strategy"));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				orderlist.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderlist;
	}
	
	public static void main(String[]args){
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> orderlist=order.findByClientid(1);
		int i=orderlist.size();
		System.out.println(i);
	}
	
	@Override
	public ArrayList<OrderPO> findByHotelid(int hotelid) {
		return null;
	}

	@Override
	public ResultMessage insert(OrderPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into orderrecord values(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			ps.setBoolean(4, po.getexecute());
			ps.setString(5, po.gethotel());
			ps.setString(6, po.getstart_time());
			ps.setString(7, po.getend_time());
			ps.setString(8, po.getlatest_execute_time());
			ps.setString(9, po.getroom_type());
			ps.setInt(10, po.getroom_number());
			ps.setString(11, po.getstrategy());
			ps.setInt(12,po.getprice());
			ps.setInt(13, po.getexpect_number_of_people());
			int i=ps.executeUpdate();
			setid(po);
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public void setid(OrderPO po){
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from orderrecord where hotelid = "+po.gethotelid() ;
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
	public ResultMessage delete(OrderPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "delete from orderrecord where id="+po.getid();
		try{
			ps=conn.prepareStatement(sql);
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
	public ResultMessage update(OrderPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "update orderrecord set clientid=?,hotelid=?,state=?,execute=?,hotel=?,start_time=?,"
				+ "end_time=?,latest_execute_time=?,room_type=?,room_number=?,strategy=?,"
				+ "price=?,expect_number_of_people=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			ps.setBoolean(4, po.getexecute());
			ps.setString(5, po.gethotel());
			ps.setString(6, po.getstart_time());
			ps.setString(7, po.getend_time());
			ps.setString(8, po.getlatest_execute_time());
			ps.setString(9, po.getroom_type());
			ps.setInt(10, po.getroom_number());
			ps.setString(11, po.getstrategy());
			ps.setInt(12,po.getprice());
			ps.setInt(13, po.getexpect_number_of_people());
			ps.setInt(14, po.getid());
			int i=ps.executeUpdate();
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

}
