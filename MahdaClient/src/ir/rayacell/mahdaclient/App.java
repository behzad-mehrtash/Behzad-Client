package ir.rayacell.mahdaclient;

import android.app.Application;
import android.content.Context;

public class App extends Application {

	/**
	 * Keeps a reference of the application context
	 */
	private static Context mAppContext;

	@Override
	public void onCreate() {
		super.onCreate();
//		TypefaceUtil.overrideFont(getApplicationContext(), "SERIF",
//				"fonts/DroidKufi-Bold.ttf"); // font from assets:
												// "assets/fonts/Roboto-Regular.ttf
		mAppContext = getApplicationContext();
//		if (!Container.getCreditManager().isUserExist()) {
//			Container.getCreditManager().createUser();

		
	}

	/**
	 * Returns the application context
	 * 
	 * @return application context
	 */
	public static Context getContext() {
		return mAppContext;
	}
}
