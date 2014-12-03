package ir.rayacell.mahdaclient;

import ir.rayacell.mahdaclient.manager.Container;
import ir.rayacell.mahdaclient.manager.Manager;
import ir.rayacell.mahdaclient.manager.NetworkManager;
import ir.rayacell.mahdaclient.model.Command;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Container.activity = this;
		
		
		final EditText et_phonenumber = (EditText)findViewById(R.id.et_call_number);
		Button btn_call = (Button)findViewById(R.id.btn_call);
		btn_call.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Container.getCallServerExecuter().startCall(et_phonenumber.getText().toString());
			}
		});
		
		final Button btn_airplanemode = (Button)findViewById(R.id.btn_airplane_mode);
		btn_airplanemode.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				String onbutton = Container.getAirplaneModeExecuter().onAirPlanePressed();
				btn_airplanemode.setText(onbutton);
			}
		});

//		final ImageView iv_photo = (ImageView) findViewById(R.id.iv_photos);
		Button btn_photo = (Button) findViewById(R.id.btn_take_photo);
		btn_photo.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Container.getPhotoCaptureExecuter().takePhoto();
			}
		});

		final Button btn_audiorecord = (Button) findViewById(R.id.btn_record_audio);
		btn_audiorecord.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				String buttonname = Container.getVoiceRecordExecuter()
						.onRecord();
				btn_audiorecord.setText(buttonname);
			}
		});

		final Button btn_videorecord = (Button) findViewById(R.id.btn_record_video);
		btn_videorecord.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				String buttonname = Container.getVideoRecorderExecuter()
						.onRecord();
				btn_videorecord.setText(buttonname);
			}
		});

		System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		TextView tv_ip = (TextView) findViewById(R.id.textView1);
		tv_ip.setText(NetworkManager.getIpAddress());

		final EditText et_dstip = (EditText) findViewById(R.id.et_ip);
		Button btn_setip = (Button) findViewById(R.id.btn_setip);
		btn_setip.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				NetworkManager.setIpAddress(et_dstip.getText().toString());
				Container.getProviderManager().setConnection();
			}
		});

		final EditText et_message = (EditText) findViewById(R.id.et_send_message);
		Button btn_send = (Button) findViewById(R.id.btn_send_message);
		btn_send.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Command command = new Command(1, (long) 1234567890, App
						.getContext().getResources()
						.getString(R.string.command_voice_record), et_message
						.getText().toString(), 0, 0, 0);
				Manager.soundRecord(command);
				updateView(et_message.getText().toString());
			}
		});
		// BaseModel model = new Command(1, 111111111,
		// App.getContext().getResources().getString(R.string.command_voice_record),
		// "", 0, 0, 0);
		// Container.getProviderManager().mProvider.recieve(model);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Container.getVoiceRecordExecuter().pause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void updateView(String s) {
		TextView tv = (TextView) findViewById(R.id.tv_show_recieved_message);
		tv.setMovementMethod(new ScrollingMovementMethod());
		text += s + "\n";
		tv.setText(text);
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		// TODO Auto-generated method stub
//		if (requestCode == 1
//				&& resultCode == RESULT_OK) {
////			super.onActivityResult(requestCode, resultCode, data);
//			ImageView imv = (ImageView) findViewById(R.id.iv_photos);
//			Bundle extras = data.getExtras();
//			Bitmap imageBitmap = (Bitmap) extras.get("data");
//			imv.setImageBitmap(imageBitmap);
//		}
//	}
}
