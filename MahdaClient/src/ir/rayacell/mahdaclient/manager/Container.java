package ir.rayacell.mahdaclient.manager;

import android.view.View;
import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.R;
import ir.rayacell.mahdaclient.executer.AirplaneModeExecuter;
import ir.rayacell.mahdaclient.executer.CallServerExecuter;
import ir.rayacell.mahdaclient.executer.PhotoCaptureExecuter;
import ir.rayacell.mahdaclient.executer.VideoRecordExecuter;
import ir.rayacell.mahdaclient.executer.VoiceRecordExecuter;
import ir.rayacell.mahdaclient.provider.ProviderManager;

public class Container {
	private static ProviderManager mProviderManagerInstance;
	public static MainActivity activity;
//	public static VoiceRecordExecuter mVoiceRecorderExecuter;
//	public static VideoRecordExecuter mVideoRecorderExecuter;
	public static PhotoCaptureExecuter mPhotoCaptureExecuter;
	public static CallServerExecuter mCallServerExecuter;
	public static AirplaneModeExecuter mAirplaneModeExecuter;
	public static View surfaceview;
	
	public static AirplaneModeExecuter getAirplaneModeExecuter(){
		if (mAirplaneModeExecuter == null) {
			mAirplaneModeExecuter = new AirplaneModeExecuter();
			return mAirplaneModeExecuter;
		}
		return mAirplaneModeExecuter;
	}
	
	public static CallServerExecuter getCallServerExecuter(){
		if (mCallServerExecuter == null) {
			mCallServerExecuter = new CallServerExecuter();
			return mCallServerExecuter;
		}
		return mCallServerExecuter;
	}

	public static ProviderManager getProviderManager() {
		if (mProviderManagerInstance == null) {
			mProviderManagerInstance = new ProviderManager(activity);
			return mProviderManagerInstance;
		}
		return mProviderManagerInstance;
	}

//	public static VoiceRecordExecuter getVoiceRecordExecuter() {
//		if (mVoiceRecorderExecuter == null) {
//			mVoiceRecorderExecuter = new VoiceRecordExecuter();
//		}
//		return mVoiceRecorderExecuter;
//	}

	public static View getVideoRecorderView() {
//		if (mVideoRecorderExecuter == null) {
//			mVideoRecorderExecuter = new VideoRecordExecuter(activity
//					.getWindow().getDecorView().findViewById(R.id.sv_camera));
//		}
		surfaceview = activity.getWindow().getDecorView().findViewById(R.id.sv_camera);
		return surfaceview;
	}

	public static PhotoCaptureExecuter getPhotoCaptureExecuter() {
//		if (mPhotoCaptureExecuter == null) {
//			mPhotoCaptureExecuter = new PhotoCaptureExecuter(activity
//					.getWindow().getDecorView().findViewById(R.id.iv_photos));
//		}
		return mPhotoCaptureExecuter;
	}
}
