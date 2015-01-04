package ir.rayacell.mahdaclient.services;

import ir.rayacell.mahdaclient.manager.Container;
import ir.rayacell.mahdaclient.manager.NetworkManager;
import ir.rayacell.mahdaclient.manager.Utils;
import ir.rayacell.mahdaclient.param.BaseParam;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {

		String recMsgString = "";
		String fromAddress = "";
		SmsMessage currentMessage = null;
		byte[] data = null;

		final Bundle bundle = intent.getExtras();

		if (bundle != null) {
			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");

			for (int i = 0; i < pdus.length; i++) {

				currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
				try {
					data = currentMessage.getUserData();
				} catch (Exception e) {

				}
				if (data != null) {
					for (int index = 0; index < data.length; ++index) {
						recMsgString += Character.toString((char) data[index]);
					}
				}
				fromAddress = currentMessage.getOriginatingAddress();

				// String message = currentMessage.getDisplayMessageBody();

				// String sendingMessage =
				// Utils.getIMEI()+"&"+Utils.getSimSerial()+"&"+NetworkManager.getIpAddress();
				// Container.getProviderManager().sendSMS(sendingMessage);

			}

			Toast.makeText(context, "aaaaaaaaaa", 20000).show();
			BaseParam param = new BaseParam(0, fromAddress, null);
			param.mCommand = recMsgString;
			Container.getProviderManager().getProvider().recieve(param);
		}
		
	}
}