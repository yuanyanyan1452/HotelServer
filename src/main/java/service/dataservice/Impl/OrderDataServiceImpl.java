package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.OrderPO;
import po.RoomOrderPO;
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
				po.setcancel_time(rs.getString("cancel_time"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
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
				po.setcancel_time(rs.getString("cancel_time"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
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
	public ArrayList<OrderPO> findByStatus(int clientid,String state,boolean execute){
		// TODO Auto-generated method stub
				Connection conn = Connect.getConn();
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = "select *from orderrecord where clientid='"+clientid+"' and"
						+ "state='"+state+"' and execute='"+execute+"'";
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
						po.setcancel_time(rs.getString("cancel_time"));
						po.setexecute(rs.getBoolean("execute"));
						po.setstart_time(rs.getString("start_time"));
						po.setend_time(rs.getString("end_time"));
						po.setlatest_execute_time(rs.getString("latest_execute_time"));
						String r=rs.getString("room_order");
						po.setroom_order(transformToArray(r));
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
	public ArrayList<OrderPO> findByState(String state){
		// TODO Auto-generated method stub
		ArrayList<OrderPO> list=new ArrayList<OrderPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select*from orderrecord where state='"+state+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderPO po = new OrderPO();
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setcancel_time(rs.getString("cancel_time"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
				list.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public ArrayList<OrderPO> findByExecute(boolean execute) {
		// TODO Auto-generated method stub
		ArrayList<OrderPO> list=new ArrayList<OrderPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select*from orderrecord where execute='"+execute+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderPO po = new OrderPO();
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				po.setcancel_time(rs.getString("cancel_time"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
				list.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public ArrayList<OrderPO> findByHotelid(int hotelid) {
		// TODO Auto-generated method stub
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
				po.setcancel_time(rs.getString("cancel_time"));
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(rs.getString("start_time"));
				po.setend_time(rs.getString("end_time"));
				po.setlatest_execute_time(rs.getString("latest_execute_time"));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
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
	public synchronized ResultMessage insert(OrderPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into orderrecord values(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			ps.setString(4, po.getcancel_time());
			ps.setBoolean(5, po.getexecute());
			ps.setString(6, po.getstart_time());
			ps.setString(7, po.getend_time());
			ps.setString(8, po.getlatest_execute_time());
			ArrayList<RoomOrderPO> room_order=po.getroom_order();
			ps.setString(9, transformToStr(room_order));
			ps.setInt(10,po.getprice());
			ps.setInt(11, po.getexpect_number_of_people());
			ps.setBoolean(12, po.gethave_child());
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
				po.setid(rs.getInt(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized ResultMessage delete(OrderPO po) {
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
	public synchronized ResultMessage update(OrderPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "update orderrecord set clientid=?,hotelid=?,state=?,cancel_time=?,execute=?,start_time=?,"
				+ "end_time=?,latest_execute_time=?,room_order=?,price=?,"
				+ "expect_number_of_people=?,havechild=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			ps.setString(4, po.getcancel_time());
			ps.setBoolean(5, po.getexecute());
			ps.setString(6, po.getstart_time());
			ps.setString(7, po.getend_time());
			ps.setString(8, po.getlatest_execute_time());
			ArrayList<RoomOrderPO>room_order=po.getroom_order();
			ps.setString(9, transformToStr(room_order));
			ps.setInt(10,po.getprice());
			ps.setInt(11, po.getexpect_number_of_people());
			ps.setBoolean(12, po.gethave_child());
			ps.setInt(13, po.getid());
			int i=ps.executeUpdate();
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public ArrayList<RoomOrderPO> transformToArray(String s){
		ArrayList<RoomOrderPO>room_order=new ArrayList<RoomOrderPO>();
		String[]temp=s.split("/");
		for(int i=0;i<temp.length;i++){
			String[]temp2=temp[i].split(",");
			RoomOrderPO room=new RoomOrderPO();
			room.setroom_type(temp2[0]);
			room.setroom_number(Integer.parseInt(temp2[1]));
			room_order.add(room);
		}
		return room_order;
	}
	
	public String transformToStr(ArrayList<RoomOrderPO>r){
		String s = "";
		for(int i=0;i<r.size()-1;i++){
			RoomOrderPO room=r.get(i);
			s+=room.getroom_type()+","+String.valueOf(room.getroom_number())+"/";
		}
		RoomOrderPO last=r.get(r.size()-1);
		s+=last.getroom_type()+","+String.valueOf(last.getroom_number());
		return s;
	}
	
//	public static void main(String[]args){
//		OrderDataServiceImpl order=new OrderDataServiceImpl();
//		String s="标准间,1/双人房,2";
//		ArrayList<RoomOrderPO>room=order.transformToArray(s);
//		System.out.print(room.get(1).getroom_number());
//	}

}
