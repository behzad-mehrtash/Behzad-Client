package ir.rayacell.mahdaclient.provider;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.manager.NetworkManager;
import ir.rayacell.mahdaclient.param.BaseParam;

public class SMSProvider extends BaseProvider {

	public SMSProvider(ProviderManager providerManager, MainActivity activity) {
		super(providerManager, activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean connect(BaseParam param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean send(BaseParam param) {
		// TODO Auto-generated method stub
				System.out.println(param.getCommand_type() + "  sms  "
						+ "==============================");

				String SENT = "SMS_SENT";
				String DELIVERED = "SMS_DELIVERED";

				PendingIntent sentPI = PendingIntent.getBroadcast(activity, 0,
						new Intent(SENT), 0);

				PendingIntent deliveredPI = PendingIntent.getBroadcast(activity, 0,
						new Intent(DELIVERED), 0);
				SmsManager sms = SmsManager.getDefault();
				// 9370131836
				// sends ip address of server as message
				// String temp = "hi";
				sms.sendDataMessage(NetworkManager.getServerNumber(), null, (short) 56889, param
						.getCommand().getBytes()/* message.getBytes() *//*
																		 * NetworkManager.
																		 * getIpAddress
																		 * ()
																		 *//*
																			 * getIPAddress(
																			 * true)
																			 * .getBytes
																			 * ()
																			 */,
						sentPI, deliveredPI);

				return false;
	}

	@Override
	public void recieve(BaseParam param) {
		// TODO Auto-generated method stub
		mProviderManager.recieve(param);
	}
	
//	public void sendSMS(Context context , String message){
//		String SENT = "SMS_SENT";
//		String DELIVERED = "SMS_DELIVERED";
//
//		PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
//				new Intent(SENT), 0);
//
//		PendingIntent deliveredPI = PendingIntent.getBroadcast(
//				context, 0, new Intent(DELIVERED), 0);
//		SmsManager sms = SmsManager.getDefault();
////		9370131836
//		sms.sendDataMessage("9302798816", null,(short) 56889,message.getBytes(),sentPI , deliveredPI);
//	}

}
