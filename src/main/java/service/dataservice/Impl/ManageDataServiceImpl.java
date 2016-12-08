package service.dataservice.Impl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.ResultMessage;
import po.ClientPO;
import po.WebManagerPO;
import po.WebMarketPO;
import service.dataservice.ManageDataService;

public class ManageDataServiceImpl implements ManageDataService {

	public static void main(String args[]){
//		String webmarketname = "Rose";
		ManageDataServiceImpl a = new ManageDataServiceImpl();
//		a.findWebMarket(5);
//		
//		WebMarketPO po1 = new WebMarketPO(1,"David","123456789");
//		a.insertWebMarket(po1);
		a.findWebMarket(6);
		
//		a.updateWebMarket(po1);
	}
	
	@Override
	public synchronized WebMarketPO findWebMarket(int WebMarketid) {
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key') from webmarket where id = '"+WebMarketid +"'";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	WebMarketPO po = new WebMarketPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),BlobtoString(rs.getBlob("decode(username,'key')")),BlobtoString(rs.getBlob("decode(password,'key')")));
	        return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public synchronized ResultMessage insertWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
	    String sql = "insert into webmarket(id,Name,Contact,Username,Password) values(NULL,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key'))";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setString(3, po.getusername());
	        pstmt.setString(4, po.getpassword());
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
	
	public synchronized void set_id(WebMarketPO po){
		Connection conn = Connect.getConn();
	    String sql = "select max(id) from webmarket" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.setwebmarketid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	@Override
	public synchronized ResultMessage updateWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
	    String sql = "update webmarket set name=encode(?,'key'),contact=encode(?,'key'),username=encode(?,'key'),password=encode(?,'key') where id=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setString(3, po.getusername());
	        pstmt.setString(4, po.getpassword());
	        pstmt.setInt(5, po.getwebmarketid());
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
	public synchronized ResultMessage deleteWebMarket(int webMarketid) {
		Connection conn = Connect.getConn();
	    String sql = "delete from webmarket where id='" + webMarketid + "'";
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
	public ResultMessage checkWebMarket(String username,String password){
		Connection conn = Connect.getConn();
		String sql = "select id from webmarket where username =encode(?,'key') and password = encode(?,'key')" ;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getInt("id")!=-1)
					return ResultMessage.Success;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ResultMessage.Fail;
	}
	
	@Override
	public WebMarketPO getwebmarketpo(String username,String password){
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key') from webmarket where username =encode(?,'key') and password=encode(?,'key')";	//需要执行的sql语句
	    PreparedStatement pstmt;

	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
		    pstmt.setString(1, username);
		    pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	WebMarketPO po = new WebMarketPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),username,password);
	        return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public synchronized int findWebMarketIDbyUsername(String username){
		Connection conn = Connect.getConn();
		int result =0;
		String sql = "select id from webmarket where name = encode(?,'key')";
		PreparedStatement pstmt;
		try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	result = rs.getInt("id");
	        }
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }

	}
  
	@Override
	public synchronized WebManagerPO findWebManager(int WebManagerid) {
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key') from webmanager where id = '" + WebManagerid +"'";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	WebManagerPO po = new WebManagerPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),BlobtoString(rs.getBlob("decode(username,'key')")),BlobtoString(rs.getBlob("decode(password,'key')")));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public synchronized ResultMessage insertWebManager(WebManagerPO po) {
		Connection conn = Connect.getConn();
	    String sql = "insert into webnamager(id,Name,Contact,Username,Password) values(NULL,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key'))";
	    PreparedStatement pstmt;
	    ByteArrayInputStream stream = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setString(3, po.getusername());
	        pstmt.setString(4, po.getpassword());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ResultMessage.Fail;
	    }
	}

	
	public synchronized void set_id(WebManagerPO po){
		Connection conn = Connect.getConn();
	    String sql = "select max(id) from webmanager" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.setwebmanagerid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public synchronized ResultMessage updateWebManager(WebManagerPO po) {
		Connection conn = Connect.getConn();
	    String sql = "update webmanager set name=encode(?,'key'),contact=encode(?,'key'),username=encode(?,'key'),password=encode(?,'key') where id=" +po.getwebmanagerid();
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setString(3, po.getusername());
	        pstmt.setString(4, po.getpassword());
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
	public synchronized ResultMessage deleteWebManager(int webManagerid) {
		Connection conn = Connect.getConn();
	    String sql = "delete from webmanager where id =" + webManagerid;
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
	public ResultMessage checkWebManager(String username,String password){
		Connection conn = Connect.getConn();
		String sql = "select id from webmanager where username =encode(?,'key') and password = encode(?,'key')" ;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getInt("id")!=-1)
					return ResultMessage.Success;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ResultMessage.Fail;
	}
	
	@Override
	public WebManagerPO getwebmanagerpo(String username,String password){
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key') from webmanager where username=encode(?,'key') and password=encode(?,'key')";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	WebManagerPO po = new WebManagerPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),username,password);
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public synchronized int findWebManagerIDbyUsername(String username){
		Connection conn = Connect.getConn();
		int result =0;
		String sql = "select id from webmanager where name = encode(?,'key')";
		PreparedStatement pstmt;
		try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	result = rs.getInt("id");
	        }
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }

	}
	
	/**
	 * 将blob类型转化成string
	 * @param blob
	 * @return
	 */
	public String BlobtoString(Blob blob){
		try{
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[is.available()];
			is.read(b, 0, b.length);
			String str = new String(b);
			return str;
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}