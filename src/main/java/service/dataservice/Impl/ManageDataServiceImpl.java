package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.ResultMessage;
import po.WebManagerPO;
import po.WebMarketPO;
import service.dataservice.ManageDataService;

public class ManageDataServiceImpl implements ManageDataService {

	public static void main(String args[]){
//		String webmarketname = "Rose";
		ManageDataServiceImpl a = new ManageDataServiceImpl();
//		a.findWebMarket(webmarketname);
//		
		WebMarketPO po1 = new WebMarketPO(1,"David","123456789");
//		a.insertWebMarket(po1);
		
		a.updateWebMarket(po1);
	}
	
	@Override
	public WebMarketPO findWebMarket(int WebMarketid) {
		Connection conn = Connect.getConn();
	    String sql = "select * from webmarket where id = " +	"\""+WebMarketid +"\"";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        WebMarketPO po = new WebMarketPO(rs.getInt(1),rs.getString(2),rs.getString(3));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public ResultMessage insertWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
	    String sql = "insert into webmarket(id,Name,Contact) values(NULL,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        setid(po);	//setid
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ResultMessage.Fail;
	    }
	}
	
	public void setid(WebMarketPO po){
		Connection conn = Connect.getConn();
	    String sql = "select * from webmarket where contact = " +"\""+po.getcontact()+"\"" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.setid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public ResultMessage updateWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
	    String sql = "update webmarket set name=?,contact=? where id=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setInt(3, po.getid());
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
	public ResultMessage deleteWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
	    String sql = "delete from webmarket where id='" + po.getid() + "'";
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
	public WebManagerPO findWebManager(String WebManagername) {
		Connection conn = Connect.getConn();
	    String sql = "select * from webmanager where name = '" + WebManagername +"'";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        WebManagerPO po = new WebManagerPO(rs.getString(1),rs.getString(2));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public ResultMessage insertWebManager(WebManagerPO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateWebManager(WebManagerPO po) {
		Connection conn = Connect.getConn();
	    String sql = "update webmanager set contact=? where name=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(2, po.getname());
	        pstmt.setString(1, po.getcontact());
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
	public ResultMessage deleteWebManager(WebManagerPO po) {
		// TODO Auto-generated method stub
		return null;
	}

}
