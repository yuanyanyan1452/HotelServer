package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelStrategyPO;
import po.WebStrategyPO;
import service.dataservice.StrategyDataService;

public class StrategyDataServiceImpl implements StrategyDataService {

	@Override
	public HotelStrategyPO find_hotel(String name) {
		// TODO Auto-generated method stub
		HotelStrategyPO po=new HotelStrategyPO();
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql = "select *from hotelstrategy where name='"+name+"'";
		
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt("id"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setname(rs.getString("name"));
				po.setcondition(rs.getString("hs_condition"));
				po.setstart_time(rs.getString("hs_start_time"));
				po.setend_time(rs.getString("hs_end_time"));
				po.setexecuteway(rs.getString("executeway"));
				po.setsuperposition(rs.getBoolean("superposition"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public ResultMessage insert_hotel(HotelStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "insert into hotelstrategy values(NULL,?,?,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.gethotelid());
			ps.setString(2, po.getname());
			ps.setString(3, po.getcondition());
			ps.setString(4, po.getstart_time());
			ps.setString(5, po.getend_time());
			ps.setString(6, po.getexecuteway());
			ps.setBoolean(7, po.getsuperposition());
			int i=ps.executeUpdate();
			sethsid(po);
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public void sethsid(HotelStrategyPO po){
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from hotelstrategy where name = "+po.getname() ;
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
	
	public void setwsid(WebStrategyPO po){
		Connection conn = Connect.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from webstrategy where name = "+po.getname() ;
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
	public ResultMessage delete_hotel(HotelStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "delete from hotelstrategy where id=?";
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
	public ResultMessage update_hotel(HotelStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "update hotelstrategy set hotelid=?,name=?,hs_condition=?,hs_start_time=?,"
				+ "hs_end_time=?,executeway=?,superposition=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, po.gethotelid());
			ps.setString(2, po.getname());
			ps.setString(3, po.getcondition());
			ps.setString(4, po.getstart_time());
			ps.setString(5, po.getend_time());
			ps.setString(6, po.getexecuteway());
			ps.setBoolean(7, po.getsuperposition());
			ps.setInt(8, po.getid());
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
	public ArrayList<HotelStrategyPO> showlist_hotel() {
		// TODO Auto-generated method stub
		ArrayList<HotelStrategyPO>hslist=new ArrayList<HotelStrategyPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from hotelstrategy";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				HotelStrategyPO po=new HotelStrategyPO();
				po.setid(rs.getInt("id"));
				po.sethotelid(rs.getInt("hotelid"));
				po.setname(rs.getString("name"));
				po.setcondition(rs.getString("hs_condition"));
				po.setstart_time(rs.getString("hs_start_time"));
				po.setend_time(rs.getString("hs_end_time"));
				po.setexecuteway(rs.getString("executeway"));
				po.setsuperposition(rs.getBoolean("superposition"));
				hslist.add(po);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return hslist;
	}

	@Override
	public WebStrategyPO find_web(String name) {
		// TODO Auto-generated method stub
		WebStrategyPO po=new WebStrategyPO();
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql = "select *from webstrategy where name="+name;
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				po.setid(rs.getInt("id"));
				po.setname(rs.getString("name"));
				po.setcondition(rs.getString("web_condition"));
				po.setstart_time(rs.getString("ws_start_time"));
				po.setend_time(rs.getString("ws_end_time"));
				po.setexecuteway(rs.getString("executeway"));
				po.setsuperposition(rs.getBoolean("superposition"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public ResultMessage insert_web(WebStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "insert into webstrategy values(NULL,?,?,?,?,?,?)";
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getcondition());
			ps.setString(3, po.getstart_time());
			ps.setString(4, po.getend_time());
			ps.setString(5, po.getexecuteway());
			ps.setBoolean(6, po.getsuperposition());
			int i=ps.executeUpdate();
			setwsid(po);
			if(i==0){
				flag=ResultMessage.Fail;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ResultMessage delete_web(WebStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "delete from webstrategy where id=?";
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
	public ResultMessage update_web(WebStrategyPO po) {
		// TODO Auto-generated method stub
		ResultMessage flag = ResultMessage.Success;
		Connection conn = Connect.getConn();
		PreparedStatement ps=null;
		String sql = "update webstrategy set name=?,web_condition=?,ws_start_time=?,"
				+ "ws_end_time=?,executeway=?,superposition=? where id=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, po.getname());
			ps.setString(2, po.getcondition());
			ps.setString(3, po.getstart_time());
			ps.setString(4, po.getend_time());
			ps.setString(5, po.getexecuteway());
			ps.setBoolean(6, po.getsuperposition());
			ps.setInt(7, po.getid());
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
	public ArrayList<WebStrategyPO> showlist_web() {
		// TODO Auto-generated method stub
		ArrayList<WebStrategyPO>wslist=new ArrayList<WebStrategyPO>();
		Connection conn=Connect.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from webstrategy";
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				WebStrategyPO po=new WebStrategyPO();
				po.setid(rs.getInt("id"));
				po.setname(rs.getString("name"));
				po.setcondition(rs.getString("web_condition"));
				po.setstart_time(rs.getString("ws_start_time"));
				po.setend_time(rs.getString("ws_end_time"));
				po.setexecuteway(rs.getString("executeway"));
				po.setsuperposition(rs.getBoolean("superposition"));
				wslist.add(po);
			}
		}catch(SQLException e){
				e.printStackTrace();
		}
		return wslist;
	}

}
