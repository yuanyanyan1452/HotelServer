
package service.dataservice;

import java.util.ArrayList;

import objects.ResultMessage;
import po.WebManagerPO;
import po.WebMarketPO;

public interface ManageDataService {
		public ArrayList<WebMarketPO> getallwebmarketPO();
		
		public WebMarketPO findWebMarket(int WebMarketid);
		
		public ResultMessage insertWebMarket(WebMarketPO po);
		
		public ResultMessage updateWebMarket(WebMarketPO po);
		
		public ResultMessage deleteWebMarket(int WebMarketid);
		
		public ResultMessage checkWebMarket(String username,String password);
		
		public WebMarketPO getwebmarketpo(String username,String password);
		
		public int findWebMarketIDbyUsername(String username);
		
		
		
		public WebManagerPO findWebManager(int WebManagerid);
		
		public ResultMessage insertWebManager(WebManagerPO po);
		
		public ResultMessage updateWebManager(WebManagerPO po);
		
		public ResultMessage deleteWebManager(int WebManagerid);
		
		public ResultMessage checkWebManager(String username,String password);
		
		public WebManagerPO getwebmanagerpo(String username,String password);
		
		public int findWebManagerIDbyUsername(String username);
	
}

