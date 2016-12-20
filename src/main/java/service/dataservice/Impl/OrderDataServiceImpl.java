package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objects.ResultMessage;
import po.OrderPO;
import po.RoomOrderPO;
import service.dataservice.OrderDataService;

public class OrderDataServiceImpl implements OrderDataService {

	@Override
	public int getAllOrder(){
		String sql = "select*from orderrecord";
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list.size();
	}
	
	@Override
	public OrderPO findByid(int id) {
		String sql = "select *from orderrecord where id="+id;
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list.get(0);
	}
	
	@Override
	public ArrayList<OrderPO> findByClientid(int clientid) {
		String sql = "select *from orderrecord where clientid="+clientid;
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list;
	}
	
	@Override
	public ArrayList<OrderPO> findByStatus(int clientid,String state,boolean execute){
		String sql = "select *from orderrecord where clientid='"+clientid+"' and"
				+ "state='"+state+"' and execute='"+execute+"'";
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list;		
	}

	@Override
	public ArrayList<OrderPO> findByState(String state){
		String sql="select*from orderrecord where state='"+state+"'";
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list;
	}
	
	@Override
	public ArrayList<OrderPO> findByExecute(boolean execute) {
		String sql="select*from orderrecord where execute='"+execute+"'";
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list;
	}
	
	@Override
	public ArrayList<OrderPO> findByHotelid(int hotelid) {
		String sql = "select *from orderrecord where hotelid="+hotelid;
		OrderDataServiceImpl order=new OrderDataServiceImpl();
		ArrayList<OrderPO> order_list=order.find(sql);
		return order_list;
	}

	@Override
	public synchronized ResultMessage insert(OrderPO po) {
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		String sql = "insert into orderrecord values(NULL,?,?,?,?,?,?,?,?,?,?,?,?)";
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			if(po.getcancel_time()==null){
				ps.setString(4, null);
			}else{
				ps.setString(4, fmt.format(po.getcancel_time()));
			}
			ps.setBoolean(5, po.getexecute());
			ps.setString(6, fmt.format(po.getstart_time()));
			ps.setString(7,fmt.format(po.getend_time()));
			ps.setString(8, fmt.format(po.getlatest_execute_time()));
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
			ps.close();
			conn.close();
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
			ps.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized ResultMessage delete(OrderPO po) {
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
			ps.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public synchronized ResultMessage update(OrderPO po) {
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "update orderrecord set clientid=?,hotelid=?,state=?,cancel_time=?,execute=?,start_time=?,"
				+ "end_time=?,latest_execute_time=?,room_order=?,price=?,"
				+ "expect_number_of_people=?,havechild=? where id=?";
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.getclientid());
			ps.setInt(2, po.gethotelid());
			ps.setString(3,po.getstate());
			if(po.getcancel_time()==null){
				ps.setString(4, null);
			}else{
				ps.setString(4, fmt.format(po.getcancel_time()));
			}
			ps.setBoolean(5, po.getexecute());
			ps.setString(6, fmt.format(po.getstart_time()));
			ps.setString(7,fmt.format(po.getend_time()));
			ps.setString(8, fmt.format(po.getlatest_execute_time()));
			ArrayList<RoomOrderPO> room_order=po.getroom_order();
			ps.setString(9,transformToStr(room_order));
			ps.setInt(10,po.getprice());
			ps.setInt(11, po.getexpect_number_of_people());
			ps.setBoolean(12, po.gethave_child());
			ps.setInt(13, po.getid());
			int i=ps.executeUpdate();
			if(i==0){
				flag=ResultMessage.Fail;
			}
			ps.close();
			conn.close();
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
			room.setnum_of_days(Integer.parseInt(temp2[2]));
			room_order.add(room);
		}
		return room_order;
	}
	
	public String transformToStr(ArrayList<RoomOrderPO>r){
		String s = "";
		for(int i=0;i<r.size()-1;i++){
			RoomOrderPO room=r.get(i);
			s+=room.getroom_type()+","+String.valueOf(room.getroom_number())+","+String.valueOf(room.getnum_of_days())+"/";
		}
		RoomOrderPO last=r.get(r.size()-1);
		s+=last.getroom_type()+","+String.valueOf(last.getroom_number())+","+String.valueOf(last.getnum_of_days());
		return s;
	}
	
	public ArrayList<OrderPO> find(String sql){
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OrderPO> orderlist=new ArrayList<OrderPO>();
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderPO po = new OrderPO();
				po.setid(rs.getInt("id"));
				po.setclientid(rs.getInt("clientid"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setstate(rs.getString("state"));
				if(rs.getString("cancel_time")==null){
					po.setcancel_time(null);
				}else{
					po.setcancel_time(fmt.parse(rs.getString("cancel_time")));
				}
				po.setexecute(rs.getBoolean("execute"));
				po.setstart_time(fmt.parse(rs.getString("start_time")));
				po.setend_time(fmt.parse(rs.getString("end_time")));
				po.setlatest_execute_time(fmt.parse(rs.getString("latest_execute_time")));
				String r=rs.getString("room_order");
				po.setroom_order(transformToArray(r));
				po.setprice(rs.getInt("price"));
				po.setexpect_number_of_people(rs.getInt("expect_number_of_people"));
				po.sethave_child(rs.getBoolean("havechild"));
				orderlist.add(po);
				ps.close();
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return orderlist;
	}
	
//	public static void main(String[]args){
//		OrderDataServiceImpl order=new OrderDataServiceImpl();
//		System.out.print(order.findByClientid(1).get(0).getprice());
//		
//	}


}