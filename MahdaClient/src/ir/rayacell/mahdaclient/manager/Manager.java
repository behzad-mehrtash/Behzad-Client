package ir.rayacell.mahdaclient.manager;

import android.util.Log;
import android.widget.Toast;
import ir.rayacell.mahdaclient.App;
import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.model.BaseModel;

public class Manager {
	public static void controll(final BaseModel model){
//		((MainActivity)App.getContext()).runOnUiThread(new Runnable() {
//			
//			public void run() {
//				// TODO Auto-generated method stub
//				
//				Toast.makeText(App.getContext(), model.getCommand_type() + " ^^^^^^^^^^^" , Toast.LENGTH_LONG).show();
//			}
//		});
		Log.d("control", model.getCommand_type()+ " ^^^^^^^^^^^");
//		switch (model.getCommand_type()) {
//		case "hello":
//			
//			break;
//
//		default:
//			break;
//		}
//		
	}

}
