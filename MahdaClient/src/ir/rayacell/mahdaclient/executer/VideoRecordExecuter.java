package ir.rayacell.mahdaclient.executer;




import ir.rayacell.mahdaclient.manager.Container;
import android.content.Intent;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class VideoRecordExecuter implements SurfaceHolder.Callback{
	
//	private static final String TAG = "Recorder";
	public static SurfaceView mSurfaceView;
	public static SurfaceHolder mSurfaceHolder;
	public static Camera mCamera;
//	public static boolean mPreviewRunning;
	public boolean mStartRecording = true;

	
	public VideoRecordExecuter(View v) {
		mSurfaceView = (SurfaceView) v;
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public String onRecord() {
		if (mStartRecording) {
			startRecording();
			mStartRecording = !mStartRecording;
			return "Stop recording";
		}
		stopRecording();
		mStartRecording = !mStartRecording;
		return "Start recording";
	}
	
	public void startRecording(){
		Intent intent = new Intent(Container.activity, RecorderService.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Container.activity.startService(intent);
//		Container.activity.finish();
	}
	
	public void stopRecording(){
		Container.activity.stopService(new Intent(Container.activity, RecorderService.class));
	}
	
	
	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

}
