package runner;

import rmi.RemoteHelper;
import service.BL;

public class ServerRunner {
	
	public ServerRunner() {
		new RemoteHelper();
	}
	
	public static void main(String[] args) {
		new ServerRunner();
		final long timeintervals=10000;
		Runnable runnable=new Runnable(){
		BL bl=new BL();
		public void run(){
			while(true){
				bl.auto_change_ordersgtate();
				try{
					Thread.sleep(timeintervals);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		};
		Thread thread=new Thread(runnable);
		thread.start();
		}
	
}
