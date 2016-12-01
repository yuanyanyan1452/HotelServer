package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.UserPO;
import service.dataservice.UserDataService;

public class UserDataServiceImpl implements UserDataService {

	@Override
	public UserPO find(String name) {
		Connection conn = Connect.getConn();
	    String sql = "select * from user where username = '" +name +"'";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        UserPO po = new UserPO(rs.getString(2),rs.getString(3));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public ResultMessage check(UserPO po){
		Connection conn = Connect.getConn();
	    String sql = "select * from user where username = '" +po.getusername() +"' and password = '" + po.getPassword() + "'";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	return ResultMessage.Success;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ResultMessage.Fail;
	}
	
	@Override
	public ResultMessage insert(UserPO po) {
		Connection conn = Connect.getConn();
	    String sql = "insert into user(id,username,password) values(NULL,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getusername());
	        pstmt.setString(2, po.getPassword());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ResultMessage.Fail;
	    }
	}

	@Override
	public ResultMessage update(UserPO po) {
		Connection conn = Connect.getConn();
	    String sql = "update user set password =? where username=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getPassword());
	        pstmt.setString(2, po.getusername());
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
	public ResultMessage delete(UserPO po) {
		Connection conn = Connect.getConn();
	    String sql = "delete from user where username='" + po.getusername() + "'";
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

	@Override
	public ArrayList<UserPO> showUsers() {
		Connection conn = Connect.getConn();
		String sql = "select * from user";
		PreparedStatement pstmt;
		ArrayList<UserPO> allusers = new ArrayList<UserPO>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserPO currentUserPO = new UserPO(rs.getString(2),rs.getString(3));
				allusers.add(currentUserPO);
			}
			return allusers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
