package ir.rayacell.mahdaclient.manager;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

public class NetworkManager {
	
	public static String dstAddress /*= "192.168.1.112""172.20.46.132"*/;
	public static int dstPort = 8085;
	private static String serverPhoneNumber;
	
	public static void setServerNumber(String servernumber){
		serverPhoneNumber = servernumber;
	}
	
	public static String getServerNumber(){
		return serverPhoneNumber;
	}
	
	public static void setIpAddress(String ip){
		dstAddress = new String();
		dstAddress = ip;
	}
	@SuppressWarnings("deprecation")
	public static String getIpAddress() {
		String ip = "";
		WifiManager wm = (WifiManager) Container.activity.getSystemService(Context.WIFI_SERVICE);
		ip = Formatter.formatIpAddress(wm.getConnectionInfo()
				.getIpAddress());
//		try {
//			Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
//					.getNetworkInterfaces();
//			while (enumNetworkInterfaces.hasMoreElements()) {
//				NetworkInterface networkInterface = enumNetworkInterfaces
//						.nextElement();
//				Enumeration<InetAddress> enumInetAddress = networkInterface
//						.getInetAddresses();
//				while (enumInetAddress.hasMoreElements()) {
//					InetAddress inetAddress = enumInetAddress.nextElement();
//
//					if (inetAddress.isSiteLocalAddress()) {
//						ip /*+= "SiteLocalAddress: "
//								+*/= inetAddress.getHostAddress()/* + "\n"*/;
//					}
//
//				}
//
//			}
//
//		} catch (SocketException e) {
//			e.printStackTrace();
//			ip += "Something Wrong! " + e.toString() + "\n";
//		}

		return ip;
	}
}
