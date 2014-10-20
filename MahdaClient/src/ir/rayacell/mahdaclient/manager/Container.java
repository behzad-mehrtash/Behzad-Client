package ir.rayacell.mahdaclient.manager;

import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.provider.ProviderManager;


public class Container {
	private static ProviderManager mProviderManagerInstance;
	public static MainActivity activity;
	
	public static ProviderManager getProviderManager(){
		if(mProviderManagerInstance == null){
			mProviderManagerInstance = new ProviderManager(activity);
			return mProviderManagerInstance;
		}
		return mProviderManagerInstance;
	}
}
