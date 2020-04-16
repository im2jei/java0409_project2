package client;

import java.net.Socket;

import manager.Setting;
import manager.Shopping;

public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("10.0.0.67", 9999);
//		new Setting();
		new ClientChat(withServer);
	}

}
