package po;

import java.io.Serializable;

public class UserPO implements Serializable {
	String username;
	String password;
	public UserPO(String u,String p){
		username=u;
		password=p;
	}
	public String getusername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
}
