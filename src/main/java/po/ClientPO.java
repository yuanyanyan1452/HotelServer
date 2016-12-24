package po;

import java.io.Serializable;
import java.util.ArrayList;

import objects.Client;
import objects.VIPInfo;
import vo.ClientVO;

public class ClientPO implements Serializable {
	int clientid;
	String client_name;
	String contact;
	int credit;
	ArrayList<String> credit_record;
	VIPInfo info;
	String username;
	String password;
	boolean logged;
	
	public ClientPO(){
		clientid=0;
		client_name=null;
		contact=null;
		credit=0;
		credit_record=null;
		info=null;
		username=null;
		password=null;
		logged=false;
	}
	
	public ClientPO(int clientid, String client_name, String contact, int credit, ArrayList<String> credit_record,
			VIPInfo info,String username,String password) {
		super();
		this.clientid = clientid;
		this.client_name = client_name;
		this.contact = contact;
		this.credit = credit;
		this.credit_record = credit_record;
		this.info = info;
		this.username = username;
		this.password = password;
		this.logged = false;
	}

	public VIPInfo getinfo() {
		return info;
	}

	public void setinfo(VIPInfo info) {
		this.info = info;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public int getclientid() {
		return clientid;
	}

	public void setclientid(int clientid) {
		this.clientid = clientid;
	}

	public String getclient_name() {
		return client_name;
	}

	public void setclient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact = contact;
	}

	public int getcredit() {
		return credit;
	}

	public void setcredit(int credit) {
		this.credit = credit;
	}

	public ArrayList<String> getcredit_record() {
		return credit_record;
	}

	public void setcredit_record(ArrayList<String> credit_record) {
		this.credit_record = credit_record;
	}

	public VIPInfo getvipinfo() {
		return info;
	}

	public void setvipinfo(VIPInfo info) {
		this.info = info;
	}
	
	public boolean getlogged() {
		return logged;
	}

	public void setlogged(boolean logged) {
		this.logged = logged;
	}
	
	public ClientVO changetoclientvo(){
		ClientVO vo=new ClientVO(0,null,null,0,null,null,null,null);
		vo.setclientid(this.clientid);
		vo.setclient_name(this.client_name);
		vo.setcontact(this.contact);
		vo.setcredit(this.credit);
		vo.setcredit_record(this.credit_record);
		vo.setvipinfo(this.info);
		vo.setusername(this.username);
		vo.setpassword(this.password);
		vo.setlogged(this.logged);
		
		return vo;
	}
	
	public Client changetoclient(){
		Client client = new Client();
		client.setclientid(this.clientid);
		client.setclient_name(this.client_name);
		client.setcontact(this.contact);
		client.setcredit(this.credit);
		client.setcredit_record(this.credit_record);
		client.setvipinfo(this.info);
		client.setusername(this.username);
		client.setpassword(this.password);
		client.setlogged(this.logged);
		return client;
	}

}
