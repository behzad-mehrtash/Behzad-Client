package ir.rayacell.mahdaclient.executer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ir.rayacell.mahdaclient.App;
import ir.rayacell.mahdaclient.R;
import ir.rayacell.mahdaclient.manager.Container;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoCaptureExecuter /*implements OnActivityResultListener*/{
	static final int REQUEST_IMAGE_CAPTURE = 1;
	private ImageView mImageView;
	String mCurrentPhotoPath;
	
	public PhotoCaptureExecuter(View v) {
		mImageView = (ImageView)v;
	}
	
	public void takePhoto(){
		dispatchTakePictureIntent();
		Toast.makeText(App.getContext(), "photo taken", Toast.LENGTH_SHORT).show();
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(Container.activity.getPackageManager()) != null) {
	    	
	    	File photofile = null;
	    	try{
	    		photofile = createImageFile();
	    	}catch(IOException e){
	    		
	    	}
	    	if (photofile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photofile));
				Container.activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
	    }
	}

//	public boolean onActivityResult(int requestCode, int resultCode	, Intent data) {
//		mImageView.setImageResource(R.drawable.ic_launcher);
//		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Container.activity.RESULT_OK) {
//	        Bundle extras = data.getExtras();
//	        Bitmap imageBitmap = (Bitmap) extras.get("data");
//	        mImageView.setImageBitmap(imageBitmap);
//	        return true;
//		}
//		
//		return false;
//	}
//	
	

	private File createImageFile() throws IOException {
	    // Create an image file name

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_"+ "behzad_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	
}
