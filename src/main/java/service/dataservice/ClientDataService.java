
package service.dataservice;

import po.*;
import objects.*;
public interface ClientDataService {
	
	public ClientPO find(int clientid);
	
	public ResultMessage insert(ClientPO po);
	
	public ResultMessage update(ClientPO po);
	
	public ResultMessage delete(ClientPO po);
	
	public ResultMessage updateCredit(ClientPO po);
}

