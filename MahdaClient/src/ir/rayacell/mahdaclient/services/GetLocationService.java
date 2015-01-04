package ir.rayacell.mahdaclient.services;

import ir.rayacell.mahdaclient.App;
import ir.rayacell.mahdaclient.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GetLocationService extends Service {
	private static final String TAG = "RecorderService";
	private boolean mGpsStatus;
	private LocationManager mLocationManager;
	private LocationListener locationListener;
	private File mLocationFile;
	private int duration;
	private int delay;

	public static final String DEFAULT_STORAGE_LOCATION = App.getContext()
			.getResources().getString(R.string.default_location);

	@Override
	public void onCreate() {

		super.onCreate();
		Log.d("in voice record service",
				"voice record service /////////////////////////////");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		duration = intent.getExtras().getInt(
				App.getContext().getResources().getString(R.string.duration));
		delay = intent.getExtras().getInt(
				App.getContext().getResources().getString(R.string.delay));
		Log.d("in service", "?????????????????????????? in service\n"
				+ "delay = " + delay + "\nduration = " + duration);

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			startRecording();
		}
		return START_STICKY;
	}

	private void startRecording() {
		try {
			mLocationFile = createLocationFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		new CountDownTimer(duration * 60 * 1000, duration * 60 * 1000) {
			
			@Override
			public void onFinish() {
				Log.d("stop self", "stop self +++++++++++++++++++++++++++++++++++++");
				stopSelf();
			}
			
			@Override
			public void onTick(long arg0) {
			}
			
		}.start();
		locationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				0/*delay * 60 * 1000*/, 0, locationListener);

	}

	/*---------- Listener class to get coordinates ------------- */
	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location loc) {
			Toast.makeText(App.getContext(), "on location changed", Toast.LENGTH_SHORT).show();
			String longitude = "Longitude: " + loc.getLongitude();
			Log.v(TAG, longitude);
			String latitude = "Latitude: " + loc.getLatitude();
			Log.v(TAG, latitude);

			String s = longitude + "#" + latitude + "\n";

			FileOutputStream outputStream;
//			try {
//				outputStream = new FileOutputStream(mLocationFile, true);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			try {
				outputStream = openFileOutput(mLocationFile.getName(),
						Context.MODE_APPEND);
				outputStream.write(s.getBytes());
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	@Override
	public void onDestroy() {
		mLocationManager.removeUpdates(locationListener);
		mLocationManager = null;
		super.onDestroy();
		
	}

	private File createLocationFile() throws IOException {

		// Create an image file name
		File dir = new File(DEFAULT_STORAGE_LOCATION);

		// test dir for existence and writeability
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				return null;
			}
		} else {
			if (!dir.canWrite()) {
				return null;
			}
		}

		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
				.format(new Date());
		String locationFileName = "MAHDA_" + "Location_" + timeStamp + "_";
		try {
			return File.createTempFile(locationFileName, /* prefix */
					".txt", /* suffix */
					dir /* directory */
			);
		} catch (IOException e) {
			return null;
		}
	}

	public GetLocationService() {
	}

}
