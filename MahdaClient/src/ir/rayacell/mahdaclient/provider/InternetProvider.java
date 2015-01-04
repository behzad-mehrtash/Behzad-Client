package ir.rayacell.mahdaclient.provider;

import ir.rayacell.mahdaclient.App;
import ir.rayacell.mahdaclient.Constants;
import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.manager.NetworkManager;
import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.model.Command;
import ir.rayacell.mahdaclient.param.*;
import ir.rayacell.mahdaclient.services.ServerService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class InternetProvider extends BaseProvider {

	public InternetProvider(ProviderManager providerManager,
			MainActivity activity) {
		super(providerManager, activity);

		IntentFilter mActionIntentFilter = new IntentFilter(
				Constants.BROADCAST_ACTION);
		ResponseReceiver mResponseReceiver = new ResponseReceiver();
		LocalBroadcastManager.getInstance(activity).registerReceiver(
				mResponseReceiver, mActionIntentFilter);
	}

	@Override
	public boolean connect(BaseParam param) {
		System.out.println("ip:" + NetworkManager.getIpAddress());
		Intent server_intent = new Intent(activity, ServerService.class);
		activity.startService(server_intent);
		return false;
	}

	@Override
	public boolean send(BaseParam param) {
		System.out.println(param.getCommand_type() + "  wifi  "
				+ "==============================");

		MyClientTask myClientTask = new MyClientTask(NetworkManager.dstAddress,
				NetworkManager.dstPort,
				/*
				 * param.getCommand_type() + "     " + param.getPhone_number() +
				 * "     " +
				 *//*((VoiceRecordParam) param).getDate_and_time()
						+ " $$$$$$$$$$$$$$$$$$$$$$$$$$"*/
				param.getCommand());
		myClientTask.execute();
		return false;
	}

	@Override
	public void recieve(BaseParam param) {
		// test//

		mProviderManager.recieve(param);
		// connect();
	}

	public class ResponseReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				String response = bundle.getString(Constants.RESULT_KEY);
				// Toast.makeText(App.getContext(), response + "!!!!!!!!!!!!!!",
				// Toast.LENGTH_LONG).show();
				Log.d("receiver", response + "!!!!!!!!!!!!!!");
				activity.updateView(response);
				BaseParam param = new BaseParam(0, null, null);
				param.mCommand = response;
				recieve(param);
			}
		}

	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {

		String dstAddress;
		int dstPort;
		String response = "";
		String msgToServer;

		MyClientTask(String addr, int port, String msgTo) {
			dstAddress = addr;
			dstPort = port;
			msgToServer = msgTo;
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			Socket socket = null;
			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;

			try {
				Log.d("ipport", dstAddress + "   HHHHHHHHHHHHHHHHHH "+ dstPort + "     JJJJJJJJJJJJJJ");
				socket = new Socket(dstAddress, dstPort);
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());

				if (msgToServer != null) {
					dataOutputStream.writeUTF(msgToServer);
				}

				response = dataInputStream.readUTF();

			} catch (UnknownHostException e) {
				e.printStackTrace();
				response = "UnknownHostException: " + e.toString();
			} catch (IOException e) {
				e.printStackTrace();
				response = "IOException: " + e.toString();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.d("tag", response + "((((((((((((((((((((((((((((((((((((((");
			activity.updateView(response);
			super.onPostExecute(result);
		}

	}

}
