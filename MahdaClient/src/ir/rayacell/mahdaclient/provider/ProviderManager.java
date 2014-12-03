package ir.rayacell.mahdaclient.provider;

import java.util.Queue;

import android.os.AsyncTask;

import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.manager.Manager;
import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.param.BaseParam;

public class ProviderManager {
	/*private*/public BaseProvider mProvider;
	private Queue<BaseParam> mQueue;
	private MainActivity activity;

	public ProviderManager(MainActivity activity){
		this.activity=activity;
	}
	
	public void setConnection() {
		// TODO Auto-generated method stub
		mProvider = new InternetProvider(this, activity);
		mProvider.connect();
	}

	public boolean send(BaseParam param) {
		new ConnectionAsyncTask().execute(param);
		return false;

	}

	/** implement a call to database and manager.
	 * 	every command must first be written in database 
	 *  then forwarded to manager in order to start a service.
	 * @param model
	 */
	public boolean recieve(BaseModel model) {
		//call to database
		
		//call to manager
		Manager.controll(model);
		return false;

	}

	private class ConnectionAsyncTask extends AsyncTask<BaseParam, Void, Void> {

		@Override
		protected Void doInBackground(BaseParam... param) {
			mProvider.send(param[0]);
			return null;
		}

	}

}
