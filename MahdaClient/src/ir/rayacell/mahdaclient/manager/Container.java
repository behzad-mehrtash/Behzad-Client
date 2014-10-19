package ir.rayacell.mahdaclient.manager;

import ir.rayacell.mahdaclient.provider.ProviderManager;


public class Container {
	private static ProviderManager mProviderManagerInstance;
	
	public static ProviderManager getProviderManager(){
		if(mProviderManagerInstance == null){
			mProviderManagerInstance = new ProviderManager();
			return mProviderManagerInstance;
		}
		return mProviderManagerInstance;
	}
}
