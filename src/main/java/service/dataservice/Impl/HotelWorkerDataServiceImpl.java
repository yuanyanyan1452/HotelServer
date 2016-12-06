package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.ResultMessage;
import po.HotelWorkerPO;
import po.WebMarketPO;
import service.dataservice.HotelWorkerDataService;

public class HotelWorkerDataServiceImpl implements HotelWorkerDataService {
	@Override
	public HotelWorkerPO find(int hotelid){
		Connection conn = Connect.getConn();
	    String sql = "select * from hotelworker where id = " +hotelid;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	HotelWorkerPO po = new HotelWorkerPO(rs.getInt(1),rs.getString(2),rs.getString(3));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	@Override
	public ResultMessage insert(HotelWorkerPO po){
		Connection conn = Connect.getConn();
	    String sql = "insert into hotelworker(hotelid,Name,Contact) values(NULL,encode(?,'key'),encode(?,'key'))";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        set_id(po);	//setid
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ResultMessage.Fail;
	    }
	}
	

	public void set_id(HotelWorkerPO po){
		Connection conn = Connect.getConn();
		 String sql = "select max(id) from hotelworker" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.sethotelid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public ResultMessage update(HotelWorkerPO po){
		Connection conn = Connect.getConn();
	    String sql = "update hotelworker set name=encode(?,'key'),contact=(?,'key') where id=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setInt(3, po.gethotelid());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return ResultMessage.Fail;
	}
	
	@Override
	public ResultMessage delete(HotelWorkerPO po){
		Connection conn = Connect.getConn();
	    String sql = "delete from hotelworker where id='" + po.gethotelid() + "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ResultMessage.Fail;
	}
}
