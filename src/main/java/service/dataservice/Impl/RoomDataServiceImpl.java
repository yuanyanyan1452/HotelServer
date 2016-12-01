package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.RoomPO;
import service.dataservice.RoomDataService;

public class RoomDataServiceImpl implements RoomDataService{

	@Override
	public ArrayList<RoomPO> find(int hotelid) {
		// TODO Auto-generated method stub
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
	public ResultMessage insert(RoomPO po) {
		// TODO Auto-generated method stub
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
	public ResultMessage delete(RoomPO po) {
		// TODO Auto-generated method stub
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
	public ResultMessage update(RoomPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag=ResultMessage.Success;
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		String sql="update from room set hotelid=?,room_type=?,total_num=?,available_num=?,"
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

	public void setid(RoomPO po){
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from room where room_type='"+po.getroom_type()+"'";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt("id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
