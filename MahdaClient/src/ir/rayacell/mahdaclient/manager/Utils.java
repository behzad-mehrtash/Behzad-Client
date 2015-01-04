package ir.rayacell.mahdaclient.manager;

import ir.rayacell.mahdaclient.App;
import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.model.StatusModel;
import ir.rayacell.mahdaclient.model.StatusResponceModel;
import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

public class Utils {
	
	public static BaseModel statusMaker(StatusModel model) {
		long command_id = model.getCommand_id();
		String commandType = model.getCommand_type();
		String phone_number = model.getPhone_number();
		String iMEI = Utils.getIMEI();
		String serial_number = Utils.getSimSerial();
		String latitude = "";
		String longitude = "";
		String battery_level = Utils.batteryLevel();
		String memory_total = Utils.TotalMemory();
		String memory_left = Utils.FreeMemory();
		int wifi_state = Utils.wifiStatus();
		String iPaddress = NetworkManager.getIpAddress();

		BaseModel status_infomodel = new StatusResponceModel(command_id,
				phone_number, commandType, iMEI, serial_number, latitude,
				longitude, battery_level, memory_total, memory_left,
				wifi_state, iPaddress);

		return status_infomodel;
	}

	public static String getIMEI() {
		TelephonyManager mngr = (TelephonyManager) App.getContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = mngr.getDeviceId();
		return imei;
	}

	public static String getSimSerial() {
		return android.os.Build.SERIAL;
	}

	/*************************************************************************************************
	 * Returns size in bytes.
	 * 
	 * If you need calculate external memory, change this: StatFs statFs = new
	 * StatFs(Environment.getRootDirectory().getAbsolutePath()); to this: StatFs
	 * statFs = new
	 * StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
	 **************************************************************************************************/
	static long GB = 1073741824;

	@SuppressLint("NewApi")
	public static String TotalMemory() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		long Total = statFs.getTotalBytes();
		String total = "" + (float) Total / GB;
		return total.substring(0, 5);
	}

	@SuppressLint("NewApi")
	public static String FreeMemory() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		long Free = statFs.getFreeBytes();
		String free = "" + (float) Free / GB;
		return free.substring(0, 5);
	}

	/**
	 ********************************************************************************************** 
	 * battery level
	 *********************************************************************************************/
	public static String batteryLevel() {
		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = App.getContext().registerReceiver(null, ifilter);
		int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		float batteryPct = level / (float) scale;
		String battery = "" + batteryPct;

		return battery;
	}
	
	public static int wifiStatus() {
		ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return 1;
		} else {
			return 0;
		}
	}
}
