package service.blservice.Impl;

import objects.ResultMessage;
import service.blservice.UserBLService;
import service.dataservice.UserDataService;
import service.dataservice.Impl.UserDataServiceImpl;

public class UserBLServiceImpl implements UserBLService {
	UserDataService userdataservice=new UserDataServiceImpl();
	
	@Override
	public ResultMessage login(String username, String password) {
		// TODO Auto-generated method stub
		ResultMessage result=userdataservice.check(username,password);
		return result;
	}

	@Override
	public ResultMessage register(String username, String password) {
		// TODO Auto-generated method stub
		ResultMessage result=userdataservice.insert(username,password);
		return result;
	}

}
