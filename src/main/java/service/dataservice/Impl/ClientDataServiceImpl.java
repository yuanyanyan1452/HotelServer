package service.dataservice.Impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import po.ClientPO;
import po.WebMarketPO;
import service.dataservice.ClientDataService;

public class ClientDataServiceImpl implements ClientDataService {

	@Override
	public ClientPO find(int clientid) {
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(name,'contact'),credit,creditrecord,viptype,info,decode(username,'key'),decode(password,'key') from client where id = " + clientid; // 需要执行的sql语句
		PreparedStatement pstmt;
		ClientPO clientpo = new ClientPO(0, null, null, 0, null, null,null,null);
		String tempCreditRecord; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				clientpo.setclientid(rs.getInt("id"));
				clientpo.setclient_name(BlobtoString(rs.getBlob("decode(name,'key')")));
				clientpo.setcontact(BlobtoString(rs.getBlob("decode(name,'contact')")));
				clientpo.setcredit(rs.getInt("credit"));
				tempCreditRecord = rs.getString("creditrecord");
				CreditRecords = tempCreditRecord.split("/");
				for (int i = 0; i < CreditRecords.length; i++) {
					CreditRecordList.add(CreditRecords[i]);
				}
				clientpo.setcredit_record(CreditRecordList);
				if (rs.getString("info").contains("normal")) {
					clientpo.setvipinfo(new VIPInfo(VIPType.NORMAL, rs.getString("info")));
				} else
					clientpo.setvipinfo(new VIPInfo(VIPType.Enterprise, rs.getString("info")));
				
				clientpo.setusername(BlobtoString(rs.getBlob("decode(username,'key')")));
				clientpo.setpassword(BlobtoString(rs.getBlob("decode(password,'key')")));

				return clientpo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 将存储在数据库中的blob类型转化成string
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
	


	public synchronized void set_id(ClientPO po){
		Connection conn = Connect.getConn();
	    String sql = "select max(id) from client" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.setclientid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public ResultMessage insert(ClientPO po) {
		Connection conn = Connect.getConn();
		String tempCreditRecord =""; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
		String sql = "insert into client (id,Name,Contact,Credit,CreditRecord,viptype,info,username,password) values(NULL,encode(?,'key'),encode(?,'key'),?,?,?,?,encode(?,'key'),encode(?,'key'))";
		PreparedStatement pstmt;
		ByteArrayInputStream stream = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, po.getclient_name());
			pstmt.setString(2, po.getcontact());
			pstmt.setInt(3, po.getcredit()); 
			CreditRecordList = po.getcredit_record();
			for(int i=0;i<CreditRecordList.size();i++){
				if(i!=CreditRecordList.size()-1){
					tempCreditRecord += CreditRecordList.get(i) + "/";
				}else
					tempCreditRecord += CreditRecordList.get(i);
			}
			pstmt.setString(4,tempCreditRecord);
			pstmt.setString(5,po.getvipinfo().getType().toString());
			pstmt.setString(6, po.getvipinfo().getInfo());
			pstmt.setString(7, po.getusername());
			pstmt.setString(8, po.getpassword());
            
            set_id(po);
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
	public ResultMessage update(ClientPO po) {
		Connection conn = Connect.getConn();
		String tempCreditRecord =""; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();

	    String sql = "update client set name=encode(?,'key'), contact=encode(?,'key'), credit=?, creditrecord=?, viptype=?, info=? ,username=encode(?,'key') , password=encode(?,'key') where id=?";
	    PreparedStatement pstmt;
	    ByteArrayInputStream stream = null;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getclient_name());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setInt(3, po.getcredit());
	        //将arraylist转成string
	        CreditRecordList = po.getcredit_record();
			for(int i=0;i<CreditRecordList.size();i++){
				if(i!=CreditRecordList.size()-1){
					tempCreditRecord += CreditRecordList.get(i) + "/";
				}else
					tempCreditRecord += CreditRecordList.get(i);
			}
			pstmt.setString(4,tempCreditRecord);
			pstmt.setString(5,po.getvipinfo().getType().toString());
			pstmt.setString(6,po.getvipinfo().getInfo());
			
			stream = new ByteArrayInputStream(po.getusername().getBytes());
            pstmt.setBinaryStream(7,stream,stream.available());
            
            stream = new ByteArrayInputStream(po.getpassword().getBytes());
            pstmt.setBinaryStream(8,stream,stream.available());
			pstmt.setInt(9, po.getclientid());
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
	public ResultMessage delete(ClientPO po) {
		Connection conn = Connect.getConn();
	    String sql = "delete from client where id = " + po.getclientid();
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
	public ResultMessage updateCredit(ClientPO po) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		ClientDataServiceImpl a = new ClientDataServiceImpl();
//		ArrayList<String> aaa = new ArrayList<String>();
//		VIPInfo info = new VIPInfo(VIPType.NORMAL, "1");
//		ClientPO po = new ClientPO(1, "yyy", "1", 320, aaa,info,"1","1");
//		a.insert(po);
		a.find(1);
		ClientPO po = a.check("1","1");
		System.out.print(po.getclientid());
		System.out.print(po.getclient_name());
		System.out.print(po.getcontact());
		System.out.print(po.getcredit());
		System.out.print(po.getcredit_record());
		System.out.print(po.getusername());
//		a.update(po);
		
	}
	public ClientPO check(String username,String password){
		Connection conn = Connect.getConn();
		String sql = "select id,decode(name,'key'),decode(contact,'key'),credit,creditrecord,viptype,info from client where username =encode(?,'key') and password = encode(?,'key')" ;
		PreparedStatement pstmt;
		ClientPO clientpo = new ClientPO();
		String tempCreditRecord; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				clientpo.setclientid(rs.getInt("id"));
				clientpo.setclient_name(BlobtoString(rs.getBlob("decode(name,'key')")));
				clientpo.setcontact(BlobtoString(rs.getBlob("decode(contact,'key')")));
				clientpo.setcredit(rs.getInt("credit"));

				tempCreditRecord = rs.getString("creditrecord");
				CreditRecords = tempCreditRecord.split("/");
				for (int i = 0; i < CreditRecords.length; i++) {
					CreditRecordList.add(CreditRecords[i]);
				}
				clientpo.setcredit_record(CreditRecordList);
				if (rs.getString("info").contains("normal")) {
					clientpo.setvipinfo(new VIPInfo(VIPType.NORMAL, rs.getString("info")));
				} else
					clientpo.setvipinfo(new VIPInfo(VIPType.Enterprise, rs.getString("info")));
				
				clientpo.setusername(username);
				clientpo.setpassword(password);

				return clientpo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return null;
	}
	
//	public static void main(String[] args) {
//		ClientDataServiceImpl a = new ClientDataServiceImpl();
//		a.find(1);
//		ArrayList<String> aaa = new ArrayList<String>();
//		VIPInfo info = new VIPInfo(VIPType.NORMAL, "1");
//		ClientPO po = new ClientPO(1, "yyy", "1", 320, aaa,info);
//		a.insert(po);
//	}

}
