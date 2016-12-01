package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.OrderPO;
import service.dataservice.OrderDataService;

public class OrderDataServiceImpl implements OrderDataService {

	@Override
	public OrderPO findByid(int id) {
		// TODO Auto-generated method stub
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *from orderrecord where id="+id;
		OrderPO po = new OrderPO();
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				po.setroom_type(rs.getString("room_type"));
				po.setroom_number(rs.getInt("room_number"));
				po.setstrategy(rs.getString("strategy"));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
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
				OrderPO po = new OrderPO();
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				po.setroom_type(rs.getString("room_type"));
				po.setroom_number(rs.getInt("room_number"));
				po.setstrategy(rs.getString("strategy"));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
				orderlist.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderlist;
	}
	
	@Override
	public ArrayList<OrderPO> findByHotelid(int hotelid) {
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select *from orderrecord where hotelid="+hotelid;
		ArrayList<OrderPO> orderlist=new ArrayList<OrderPO>();
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderPO po = new OrderPO();
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				po.setroom_type(rs.getString("room_type"));
				po.setroom_number(rs.getInt("room_number"));
				po.setstrategy(rs.getString("strategy"));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
				orderlist.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return orderlist;
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
			ps.setString(5, po.getstart_time());
			ps.setString(6, po.getend_time());
			ps.setString(7, po.getlatest_execute_time());
			ps.setString(8, po.getroom_type());
			ps.setInt(9, po.getroom_number());
			ps.setString(10, po.getstrategy());
			ps.setInt(11,po.getprice());
			ps.setInt(12, po.getexpect_number_of_people());
			ps.setBoolean(13, po.gethave_child());
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
		String sql = "update orderrecord set clientid=?,hotelid=?,state=?,execute=?,start_time=?,"
				+ "end_time=?,latest_execute_time=?,room_type=?,room_number=?,strategy=?,"
				+ "price=?,expect_number_of_people=?,havechild=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			ps.setBoolean(4, po.getexecute());
			ps.setString(5, po.getstart_time());
			ps.setString(6, po.getend_time());
			ps.setString(7, po.getlatest_execute_time());
			ps.setString(8, po.getroom_type());
			ps.setInt(9, po.getroom_number());
			ps.setString(10, po.getstrategy());
			ps.setInt(11,po.getprice());
			ps.setInt(12, po.getexpect_number_of_people());
			ps.setInt(13, po.getid());
			ps.setBoolean(14, po.gethave_child());
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
	public OrderPO find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderPO> find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
