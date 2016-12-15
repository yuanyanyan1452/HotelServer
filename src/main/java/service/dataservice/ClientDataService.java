
package service.dataservice;

import java.util.ArrayList;

import objects.ResultMessage;
import po.ClientPO;
public interface ClientDataService {
	
	public ClientPO find(int clientid);
	
	public ArrayList<ClientPO> getallclientPO(); 
	
	public ResultMessage insert(ClientPO po);
	
	public ResultMessage update(ClientPO po);
	
	public ResultMessage delete(int clientid);
	
	public ResultMessage check(String username,String password);
	
	public ClientPO getclientpo(String username,String password);
	
	public int findClientIDbyUsername(String username);
}

