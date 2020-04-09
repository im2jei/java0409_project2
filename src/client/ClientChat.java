package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private String pwd = null;
	private Scanner input = new Scanner(System.in);

	ClientChat(Socket c) {
		this.withServer = c;
		streamSet();
		receive();
		// send();
	}

	public ClientChat(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	private void receive() {

	}

	private void streamSet() {

		try {
			new Login();

			sendMsg = withServer.getOutputStream();// 소켓을 통해 외부로 나가는 자원
			sendMsg.write(id.getBytes());
			sendMsg.write(pwd.getBytes());

			reMsg = withServer.getInputStream();// 소켓으로 주고받기한 것을 jvm으로 in하는것
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();// 공백제거
			System.out.println(msg);// 받아온 메세지 출력

		} catch (IOException e) {
			System.out.println("End");
		}

	}

}
