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

			sendMsg = withServer.getOutputStream();// ������ ���� �ܺη� ������ �ڿ�
			sendMsg.write(id.getBytes());
			sendMsg.write(pwd.getBytes());

			reMsg = withServer.getInputStream();// �������� �ְ�ޱ��� ���� jvm���� in�ϴ°�
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();// ��������
			System.out.println(msg);// �޾ƿ� �޼��� ���

		} catch (IOException e) {
			System.out.println("End");
		}

	}

}
