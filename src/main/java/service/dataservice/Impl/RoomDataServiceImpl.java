package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.OrderPO;
import po.RoomOrderPO;
import po.RoomPO;
import service.dataservice.RoomDataService;

public class RoomDataServiceImpl implements RoomDataService{

	@Override
	public ArrayList<RoomPO> find(int hotelid) {
		ArrayList<RoomPO> roomlist=new ArrayList<RoomPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from room where hotelid="+hotelid;
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				RoomPO po=new RoomPO();
				po.setid(rs.getInt("id"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setroom_type(rs.getString("room_type"));
				po.settotal_num(rs.getInt("total_num"));
				po.setavailable_num(rs.getInt("available_num"));
				po.setprice(rs.getInt("price"));
				roomlist.add(po);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return roomlist;
	}

	@Override
	public synchronized ResultMessage insert(RoomPO po) {
		ResultMessage flag=ResultMessage.Success;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		String sql="insert into room values(NULL,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.gethotelid());
			ps.setString(2, po.getroom_type());
			ps.setInt(3, po.gettotal_num());
			ps.setInt(4, po.getavailable_num());
			ps.setInt(5, po.getprice());
			setid(po);
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
	public synchronized ResultMessage delete(RoomPO po) {
		ResultMessage flag=ResultMessage.Success;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		String sql="delete from room where id=?";
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
	public synchronized ResultMessage update(RoomPO po) {
		ResultMessage flag=ResultMessage.Success;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		String sql="update room set hotelid=?,room_type=?,total_num=?,available_num=?,"
				+ "price=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.gethotelid());
			ps.setString(2, po.getroom_type());
			ps.setInt(3, po.gettotal_num());
			ps.setInt(4, po.getavailable_num());
			ps.setInt(5, po.getprice());
			ps.setInt(6, po.getid());
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
	public synchronized ResultMessage reduce(OrderPO po){
		ResultMessage flag=ResultMessage.Success;
		int hotelid=po.gethotelid();
		ArrayList<RoomOrderPO> room_order=po.getroom_order();
		
		for(int i=0;i<room_order.size();i++){
			String type=room_order.get(i).getroom_type();
			int order_num=room_order.get(i).getroom_number();
			int num=available_num(hotelid,type);
			num=num-order_num;
			
			Connection conn=Connect.getConn();
			PreparedStatement ps=null;
			String sql="update room set available_num=? where hotelid=? and room_type=?";
			try{
				ps=conn.prepareStatement(sql);
				ps.setInt(1, num);
				ps.setInt(2,hotelid);
				ps.setString(3, type);
				
				int j=ps.executeUpdate();
				if(j==0){
					flag=ResultMessage.Fail;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		return flag;
	}

	public synchronized int available_num(int hotelid,String room_type){
		int available_num=0;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select*from room where hotelid='"+hotelid+"' and room_type='"+room_type+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				available_num=rs.getInt("available_num");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return available_num;
	}
	
	public void setid(RoomPO po){
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from room where room_type='"+po.getroom_type()+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int find_min_price(int hotelid){
		int min_price=0;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select price from room where hotelid="+hotelid;
		
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				if(min_price==0||min_price>rs.getInt("price")){
					min_price=rs.getInt("price");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return min_price;
	}
	
	public int find_max_price(int hotelid){
		int max_price=0;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select price from room where hotelid="+hotelid;
		
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				if(max_price<rs.getInt("price")){
					max_price=rs.getInt("price");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return max_price;
	}
	
//	public static void main(String[]args){
//		RoomDataServiceImpl room=new RoomDataServiceImpl();
//		int min_price=room.find_min_price(2);
//		System.out.println(min_price);
//	}
	
}
