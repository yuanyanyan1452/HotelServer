package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import objects.VIPInfo;
import objects.VIPInfo.VIPType;
import po.ClientPO;
import service.dataservice.ClientDataService;

public class ClientDataServiceImpl implements ClientDataService {

	@Override
	public ClientPO find(int clientid) {
		Connection conn = Connect.getConn();
		String sql = "select * from client where id = " + clientid; // 需要执行的sql语句
		PreparedStatement pstmt;
		ClientPO clientpo = new ClientPO(0, null, null, 0, null, null);
		String tempCreditRecord; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				clientpo.setClientid(rs.getInt("id"));
				clientpo.setClient_name(rs.getString("name"));
				clientpo.setContact(rs.getString("contact"));
				clientpo.setCredit(rs.getInt("credit"));

				tempCreditRecord = rs.getString("creditrecord");
				CreditRecords = tempCreditRecord.split("/");
				for (int i = 0; i < CreditRecords.length; i++) {
					CreditRecordList.add(CreditRecords[i]);
				}
				clientpo.setCredit_record(CreditRecordList);
				if (rs.getString("info").contains("normal")) {
					clientpo.setVIPInfo(new VIPInfo(VIPType.NORMAL, rs.getString("info")));
				} else
					clientpo.setVIPInfo(new VIPInfo(VIPType.Enterprise, rs.getString("info")));

				return clientpo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage insert(ClientPO po) {
		Connection conn = Connect.getConn();
		String tempCreditRecord =""; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
		String sql = "insert into client (id,Name,Contact,Credit,CreditRecord,viptype,info) values(NULL,?,?,?,?,?,?)";
		PreparedStatement pstmt;

		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, po.getClient_name());
			pstmt.setString(2, po.getContact());
			pstmt.setInt(3, po.getCredit());
			CreditRecordList = po.getCredit_record();
			for(int i=0;i<CreditRecordList.size();i++){
				if(i!=CreditRecordList.size()-1){
					tempCreditRecord += CreditRecordList.get(i) + "/";
				}else
					tempCreditRecord += CreditRecordList.get(i);
			}
			pstmt.setString(4,tempCreditRecord);
			pstmt.setString(5,po.getVIPInfo().getType().toString());
			pstmt.setString(6, po.getVIPInfo().getInfo());
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
	public synchronized ResultMessage update(ClientPO po) {
		Connection conn = Connect.getConn();
		String tempCreditRecord =""; // 存取数据库中读取的string
		String[] CreditRecords; // 根据符号 /将各条信用记录分开
		ArrayList<String> CreditRecordList = new ArrayList<String>();
	    String sql = "update client set name=?, contact=?, credit=?, creditrecord=?, viptype=?, info=? where id=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getClient_name());
	        pstmt.setString(2, po.getContact());
	        pstmt.setInt(3, po.getCredit());
	        //将arraylist转成string
	        CreditRecordList = po.getCredit_record();
			for(int i=0;i<CreditRecordList.size();i++){
				if(i!=CreditRecordList.size()-1){
					tempCreditRecord += CreditRecordList.get(i) + "/";
				}else
					tempCreditRecord += CreditRecordList.get(i);
			}
			pstmt.setString(4,tempCreditRecord);
			pstmt.setString(5,po.getVIPInfo().getType().toString());
			pstmt.setString(6, po.getVIPInfo().getInfo());
			pstmt.setInt(7, po.getClientid());
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
	public synchronized ResultMessage delete(ClientPO po) {
		Connection conn = Connect.getConn();
	    String sql = "delete from client where id = " + po.getClientid();
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

//	public static void main(String[] args) {
//		ClientDataServiceImpl a = new ClientDataServiceImpl();
//		a.find(1);
//		ArrayList<String> aaa = new ArrayList<String>();
//		VIPInfo info = new VIPInfo(VIPType.NORMAL, "1");
//		ClientPO po = new ClientPO(1, "yyy", "1", 320, aaa,info);
//		a.insert(po);
//	}

}
