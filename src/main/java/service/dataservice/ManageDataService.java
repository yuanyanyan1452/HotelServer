
package service.dataservice;

import po.*;
import objects.*;

public interface ManageDataService {
	public WebMarketPO findWebMarket(String WebMarketname);
	
	public ResultMessage insertWebMarket(WebMarketPO po);
	
	public ResultMessage updateWebMarket(WebMarketPO po);
	
	public ResultMessage deleteWebMarket(WebMarketPO po);
	
	public WebManagerPO findWebManager(String WebMangername);
	
	public ResultMessage insertWebManager(WebManagerPO po);
	
	public ResultMessage updateWebManager(WebManagerPO po);
	
	public ResultMessage deleteWebManager(WebManagerPO po);
	
}

