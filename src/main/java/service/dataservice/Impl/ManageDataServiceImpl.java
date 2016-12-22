package service.dataservice.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.WebManagerPO;
import po.WebMarketPO;
import service.dataservice.ManageDataService;

public class ManageDataServiceImpl implements ManageDataService {

	@Override
	public ArrayList<WebMarketPO> getallwebmarketPO() {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key'),logged from webmarket "; // 需要执行的sql语句
		PreparedStatement pstmt;
		ArrayList<WebMarketPO> webmarketlist = new ArrayList<WebMarketPO>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				WebMarketPO po = new WebMarketPO();
				po.setwebmarketid(rs.getInt("id"));
				po.setname(BlobtoString(rs.getBlob("decode(name,'key')")));
				po.setcontact(BlobtoString(rs.getBlob("decode(contact,'key')")));
				po.setusername(BlobtoString(rs.getBlob("decode(username,'key')")));
				po.setpassword(BlobtoString(rs.getBlob("decode(password,'key')")));
				po.setlogged(rs.getBoolean("logged"));
				webmarketlist.add(po);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return webmarketlist;
	}

	@Override
	public synchronized WebMarketPO findWebMarket(int WebMarketid) {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key'),logged from webmarket where id = '"
				+ WebMarketid + "'"; // 需要执行的sql语句
		PreparedStatement pstmt;
		WebMarketPO po = new WebMarketPO();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po.setwebmarketid(WebMarketid);
				po.setname(BlobtoString(rs.getBlob("decode(name,'key')")));
				po.setcontact(BlobtoString(rs.getBlob("decode(contact,'key')")));
				po.setusername(BlobtoString(rs.getBlob("decode(username,'key')")));
				po.setpassword(BlobtoString(rs.getBlob("decode(password,'key')")));
				po.setlogged(rs.getBoolean("logged"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return po;
	}

	@Override
	public synchronized ResultMessage insertWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
		String sql = "insert into webmarket(id,Name,Contact,Username,Password,logged) values(NULL,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key'),?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, "");
			pstmt.setString(2, "");
			pstmt.setString(3, po.getusername());
			pstmt.setString(4, po.getpassword());
			pstmt.setBoolean(5, po.getlogged());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			set_id(po); // setid
			pstmt.close();
			conn.close();
			return ResultMessage.Success;
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.Fail;
		}
	}

	public synchronized void set_id(WebMarketPO po) {
		Connection conn = Connect.getConn();
		String sql = "select max(id) from webmarket"; // 需要执行的sql语句
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po.setwebmarketid(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized ResultMessage updateWebMarket(WebMarketPO po) {
		Connection conn = Connect.getConn();
		String sql = "update webmarket set name=encode(?,'key'),contact=encode(?,'key'),username=encode(?,'key'),password=encode(?,'key'),logged=? where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, po.getname());
			pstmt.setString(2, po.getcontact());
			pstmt.setString(3, po.getusername());
			pstmt.setString(4, po.getpassword());
			pstmt.setBoolean(5, po.getlogged());
			pstmt.setInt(6, po.getwebmarketid());
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
	public ResultMessage checkWebMarket(String username, String password) {
		Connection conn = Connect.getConn();
		String sql = "select id from webmarket where username =encode(?,'key') and password = encode(?,'key')";
		PreparedStatement pstmt;
		ResultMessage flag = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id") != -1)
					flag = ResultMessage.Success;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			flag = ResultMessage.Fail;
		}
		return flag;
	}

	@Override
	public WebMarketPO getwebmarketpo(String username, String password) {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),logged from webmarket where username =encode(?,'key') and password=encode(?,'key')"; // 需要执行的sql语句
		PreparedStatement pstmt;

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			WebMarketPO po = null;
			while (rs.next()) {
				po = new WebMarketPO(rs.getInt("id"), BlobtoString(rs.getBlob("decode(name,'key')")),
						BlobtoString(rs.getBlob("decode(contact,'key')")), username, password);
				po.setlogged(rs.getBoolean("logged"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			return po;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized int findWebMarketIDbyUsername(String username) {
		Connection conn = Connect.getConn();
		int result = 0;
		String sql = "select id from webmarket where username = encode(?,'key')";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("id");
			}
			rs.close();
			pstmt.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public synchronized WebManagerPO findWebManager(int WebManagerid) {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key'),logged from webmanager where id = '"
				+ WebManagerid + "'"; // 需要执行的sql语句
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			WebManagerPO po = null;
			while (rs.next()) {
				po = new WebManagerPO(rs.getInt("id"), BlobtoString(rs.getBlob("decode(name,'key')")),
						BlobtoString(rs.getBlob("decode(contact,'key')")),
						BlobtoString(rs.getBlob("decode(username,'key')")),
						BlobtoString(rs.getBlob("decode(password,'key')")));
				po.setlogged(rs.getBoolean("logged"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			return po;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage insertWebManager(WebManagerPO po) {
		Connection conn = Connect.getConn();
		String sql = "insert into webnamager(id,Name,Contact,Username,Password,logged) values(NULL,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key'),?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, "");
			pstmt.setString(2, "");
			pstmt.setString(3, po.getusername());
			pstmt.setString(4, po.getpassword());
			pstmt.setBoolean(5, po.getlogged());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			set_id(po);
			pstmt.close();
			conn.close();
			return ResultMessage.Success;
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.Fail;
		}
	}

	public synchronized void set_id(WebManagerPO po) {
		Connection conn = Connect.getConn();
		String sql = "select max(id) from webmanager"; // 需要执行的sql语句
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po.setwebmanagerid(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized ResultMessage updateWebManager(WebManagerPO po) {
		Connection conn = Connect.getConn();
		String sql = "update webmanager set name=encode(?,'key'),contact=encode(?,'key'),username=encode(?,'key'),password=encode(?,'key'),logged=? where id="
				+ po.getwebmanagerid();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, po.getname());
			pstmt.setString(2, po.getcontact());
			pstmt.setString(3, po.getusername());
			pstmt.setString(4, po.getpassword());
			pstmt.setBoolean(5, po.getlogged());
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
	public ResultMessage checkWebManager(String username, String password) {
		Connection conn = Connect.getConn();
		String sql = "select id from webmanager where username =encode(?,'key') and password = encode(?,'key')";
		PreparedStatement pstmt;
		ResultMessage flag = ResultMessage.Fail;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("id") != -1)
					flag = ResultMessage.Success;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public WebManagerPO getwebmanagerpo(String username, String password) {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),logged from webmanager where username=encode(?,'key') and password=encode(?,'key')"; // 需要执行的sql语句
		PreparedStatement pstmt;
		WebManagerPO po =  new WebManagerPO();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po = new WebManagerPO(rs.getInt("id"), BlobtoString(rs.getBlob("decode(name,'key')")),
						BlobtoString(rs.getBlob("decode(contact,'key')")), username, password);
				po.setlogged(rs.getBoolean("logged"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			return po;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized int findWebManagerIDbyUsername(String username) {
		Connection conn = Connect.getConn();
		int result = 0;
		String sql = "select id from webmanager where username = encode(?,'key')";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("id");
			}
			rs.close();
			pstmt.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 将blob类型转化成string
	 * 
	 * @param blob
	 * @return
	 */
	public String BlobtoString(Blob blob) {
		try {
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[is.available()];
			is.read(b, 0, b.length);
			String str = new String(b);
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[]args){
//		ManageDataServiceImpl manage=new ManageDataServiceImpl();
//		WebMarketPO po=manage.getwebmarketpo("beauty", "23333");
//		System.out.println(po.getlogged());
//	}

}