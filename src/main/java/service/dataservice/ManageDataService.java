
package service.dataservice;

import po.*;
import objects.*;

public interface ManageDataService {
		public WebMarketPO findWebMarket(int WebMarketid);
		
		public ResultMessage insertWebMarket(WebMarketPO po);
		
		public ResultMessage updateWebMarket(WebMarketPO po);
		
		public ResultMessage deleteWebMarket(int WebMarketid);
		
		public int findWebMarketIDbyName(String name);
		
		public WebManagerPO findWebManager(int WebManagerid);
		
		public ResultMessage insertWebManager(WebManagerPO po);
		
		public ResultMessage updateWebManager(WebManagerPO po);
		
		public ResultMessage deleteWebManager(int WebManagerid);
		
		public int findWebManagerIDbyName(String name);
	
}

