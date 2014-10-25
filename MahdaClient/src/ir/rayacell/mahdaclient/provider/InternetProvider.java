package ir.rayacell.mahdaclient.provider;

import ir.rayacell.mahdaclient.MainActivity;
import ir.rayacell.mahdaclient.model.BaseModel;
import ir.rayacell.mahdaclient.model.Command;
import ir.rayacell.mahdaclient.param.BaseParam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class InternetProvider extends BaseProvider {

//	String message = "";

	public InternetProvider(ProviderManager providerManager,
			MainActivity activity) {
		super(providerManager, activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean connect() {
		System.out.println("ip:" + getIpAddress());
//		IntentService RunServerService = new RunServerService();
		Intent server_intent = new Intent(activity, ServerService.class);
		activity.startService(server_intent);
		return false;
	}

	@Override
	public boolean send(BaseParam param) {
		System.out.println(param.getCommand_type() + "  wifi  "
				+ "==============================");
		// System.out.println("ip:" + getIpAddress());
		// runserver();

		return false;
	}

	@Override
	public void recieve(BaseModel model) {
		// test//

		mProviderManager.recieve(model);
		// connect();
	}

	private String getIpAddress() {
		String ip = "";
		try {
			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (enumNetworkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = enumNetworkInterfaces
						.nextElement();
				Enumeration<InetAddress> enumInetAddress = networkInterface
						.getInetAddresses();
				while (enumInetAddress.hasMoreElements()) {
					InetAddress inetAddress = enumInetAddress.nextElement();

					if (inetAddress.isSiteLocalAddress()) {
						ip += "SiteLocalAddress: "
								+ inetAddress.getHostAddress() + "\n";
					}

				}

			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ip += "Something Wrong! " + e.toString() + "\n";
		}

		return ip;
	}

//	public class RunServerService extends IntentService {
//
//		final int SocketServerPORT = 8081;
//		int count = 0;
//		Socket socket = null;
//		DataInputStream dataInputStream = null;
//		DataOutputStream dataOutputStream = null;
//		private ServerSocket serverSocket;
//		private BaseModel model = null;
//
//		public RunServerService() {
//			super("serverIntentService");
//
//		}
//
//		@Override
//		protected void onHandleIntent(Intent arg0) {
//			try {
//				serverSocket = new ServerSocket(SocketServerPORT);
//				socket = serverSocket.accept();
//				dataInputStream = new DataInputStream(socket.getInputStream());
//				dataOutputStream = new DataOutputStream(
//						socket.getOutputStream());
//
//				while (true) {
//					String messageFromClient = "";
//
//					messageFromClient = dataInputStream.readUTF();
//					if (messageFromClient.equals("busy")) {
//						new AsyncTask<String, Void, Void>() {
//
//							@Override
//							protected Void doInBackground(String... arg0) {
//								System.out
//										.println(arg0
//												+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//								try {
//									serverSocket.close();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//								return null;
//							}
//						}.execute(message);
//					}
//
//					count++;
//					message += "#" + count + " from " + socket.getInetAddress()
//							+ ":" + socket.getPort() + "\n"
//							+ "Msg from client: " + messageFromClient + "\n";
//
//					new AsyncTask<String, Void, Void>() {
//
//						@Override
//						protected Void doInBackground(String... arg0) {
//							System.out.println(arg0);
//							Log.d("message from client", arg0
//									+ "##################################3");
//							model = new Command(1, 1, arg0[0].toString(), "",
//									1, 1, 1);
//							recieve(model);
//							return null;
//						}
//
//					}.execute(message);
//					String msgReply = "Hello from Android, you are #" + count;
//					dataOutputStream.writeUTF(msgReply);
//				}
//
//			} catch (IOException e1) {
//				e1.printStackTrace();
//				final String errMsg = e1.toString();
//				System.out.println(errMsg);
//			}
//		}
//
//		@Override
//		public void onDestroy() {
//			super.onDestroy();
//			Log.d("finally", "finally &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//			if (socket != null) {
//				try {
//					socket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (dataInputStream != null) {
//				try {
//					dataInputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (dataOutputStream != null) {
//				try {
//					dataOutputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
	//
	// public class RunServerTask extends AsyncTask<Void, Void, Void> {
	//
	// final int SocketServerPORT = 8081;
	// int count = 0;
	// Socket socket = null;
	// DataInputStream dataInputStream = null;
	// DataOutputStream dataOutputStream = null;
	// private ServerSocket serverSocket;
	// private BaseModel model = null;
	//
	// @Override
	// protected Void doInBackground(Void... arg0) {
	// try {
	// serverSocket = new ServerSocket(SocketServerPORT);
	//
	// socket = serverSocket.accept();
	// dataInputStream = new DataInputStream(socket.getInputStream());
	// dataOutputStream = new DataOutputStream(
	// socket.getOutputStream());
	//
	// String messageFromClient = "";
	//
	// messageFromClient = dataInputStream.readUTF();
	// if (messageFromClient.equals("busy")) {
	// new AsyncTask<String, Void, Void>() {
	//
	// @Override
	// protected Void doInBackground(String... arg0) {
	// System.out.println(arg0);
	// try {
	// serverSocket.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// }.execute(message);
	// // (activity).runOnUiThread(new Runnable() {
	// //
	// // public void run() {
	// // System.out.println(message);
	// // try {
	// // serverSocket.close();
	// // } catch (IOException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// //
	// // }
	// // });
	// }
	//
	// count++;
	// message += "#" + count + " from " + socket.getInetAddress()
	// + ":" + socket.getPort() + "\n" + "Msg from client: "
	// + messageFromClient + "\n";
	//
	// new AsyncTask<String, Void, Void>() {
	//
	// @Override
	// protected Void doInBackground(String... arg0) {
	// System.out.println(arg0);
	// Log.d("message from client", arg0
	// + "##################################3");
	// // Toast.makeText(activity, message, Toast.LENGTH_SHORT)
	// // .show();
	//
	// model = new Command(1, 1, arg0[0].toString(), "", 1, 1,
	// 1);
	// recieve(model);
	// return null;
	// }
	//
	// }.execute(message);
	// // (activity).runOnUiThread(new Runnable() {
	// //
	// // public void run() {
	// // System.out.println(message);
	// // Toast.makeText(activity, message, Toast.LENGTH_SHORT)
	// // .show();
	// //
	// // }
	// // });
	//
	// String msgReply = "Hello from Android, you are #" + count;
	// dataOutputStream.writeUTF(msgReply);
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// final String errMsg = e.toString();
	//
	// System.out.println(errMsg);
	// // Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show();
	//
	// } finally {
	// Log.d("finally", "finally &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	// if (socket != null) {
	// try {
	// socket.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// if (dataInputStream != null) {
	// try {
	// dataInputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// if (dataOutputStream != null) {
	// try {
	// dataOutputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// return null;
	// }
	//
	// // @Override
	// // protected void onPostExecute(Void result) {
	// // recieve(model);
	// // // textResponse.setText(response);
	// // // Log.d("tag", response + "((((((((((((((((((((((((((((((((((((((");
	// // super.onPostExecute(result);
	// // }
	// //
	//
	// }
	//

	// private void runserver() {
	// final int SocketServerPORT = 8081;
	// int count = 0;
	// Socket socket = null;
	// DataInputStream dataInputStream = null;
	// DataOutputStream dataOutputStream = null;
	//
	// try {
	// serverSocket = new ServerSocket(SocketServerPORT);
	// (activity).runOnUiThread(new Runnable() {
	//
	// public void run() {
	// System.out.println("im waiting");
	//
	// }
	// });
	//
	// while (true) {
	// socket = serverSocket.accept();
	// dataInputStream = new DataInputStream(socket.getInputStream());
	// dataOutputStream = new DataOutputStream(
	// socket.getOutputStream());
	//
	// String messageFromClient = "";
	//
	// // If no message sent from client, this code will block the
	// // program
	// messageFromClient = dataInputStream.readUTF();
	// if (messageFromClient.equals("buy")) {
	// (activity).runOnUiThread(new Runnable() {
	//
	// public void run() {
	// System.out.println(message);
	// try {
	// serverSocket.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// });
	// }
	// count++;
	// message += "#" + count + " from " + socket.getInetAddress()
	// + ":" + socket.getPort() + "\n" + "Msg from client: "
	// + messageFromClient + "\n";
	//
	// (activity).runOnUiThread(new Runnable() {
	//
	// public void run() {
	// System.out.println(message);
	// Toast.makeText(activity, message, Toast.LENGTH_SHORT)
	// .show();
	//
	// }
	// });
	//
	// String msgReply = "Hello from Android, you are #" + count;
	// dataOutputStream.writeUTF(msgReply);
	//
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// final String errMsg = e.toString();
	// (activity).runOnUiThread(new Runnable() {
	//
	// public void run() {
	// System.out.println(errMsg);
	// Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show();
	// }
	// });
	//
	// } finally {
	// if (socket != null) {
	// try {
	// socket.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// if (dataInputStream != null) {
	// try {
	// dataInputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// if (dataOutputStream != null) {
	// try {
	// dataOutputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// }

}
