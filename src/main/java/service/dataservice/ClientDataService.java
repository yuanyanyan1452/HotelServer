
package service.dataservice;

import po.*;
import objects.*;
public interface ClientDataService {
	
	public ClientPO find(int clientid);
	
	public ResultMessage insert(ClientPO po);
	
	public ResultMessage update(ClientPO po);
	
	public ResultMessage delete(int clientid);
	
	public ResultMessage check(String username,String password);
	
	public ClientPO getclientpo(String username,String password);
	
	public int findClientIDbyUsername(String username);
}

