package service.dataservice;

import java.util.ArrayList;

import po.*;
import objects.*;

public interface UserDataService {
	
	public UserPO find (String name) ;
	
	public ResultMessage insert(UserPO po);
	
	public ResultMessage update(UserPO po);
	
	public ResultMessage delete(UserPO po);
	
	
	public ArrayList<UserPO> showUsers();

	ResultMessage check(UserPO po);
}
