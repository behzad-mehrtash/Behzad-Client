package ir.rayacell.mahdaclient.executer;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class VoiceRecordExecuter {

	private String LOG_TAG = "AudioRecordTest";
	private MediaRecorder mRecorder;
	private String mFileName = "";
	private boolean mStartRecording = true;

	public VoiceRecordExecuter() {
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/audiorecordtest.3gp";
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

	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}

		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	public void pause() {
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}
	}

}
