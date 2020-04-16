package client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import server.ServerChat;

public class ClientChat implements Serializable {
	private static final long serialVersionUID = 1L;

	private Socket withServer = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	String[] check = null;
	ServerChat sv = null;
	Login log= null;

	public ClientChat() {
	}

	public ClientChat(String[] check) {
		this.check = check;
	}

	private void start() {
		new Login(this);

	}

	ClientChat(Socket c) throws IOException {
		this.withServer = c;
		start();
		Send(check);
		receive();
	}

	private void receive() throws IOException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("received");
					while (true) {
						reMsg = withServer.getInputStream();// 소켓으로 주고받기한 것을 jvm으로 in하는것
						byte[] bytes = new byte[1024];
						reMsg.read(bytes);

						ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
						ObjectInputStream ois = new ObjectInputStream(bais);
						Object objectMember = ois.readObject();
						if (objectMember != null) {
							check = (String[]) objectMember;
							log= new Login();
							log.loginchk(check);
						}
					}

				} catch (IOException e) {
				} catch (ClassNotFoundException e) {
				}
			}
		}).start();
	}

	public void Send(String[] check) {

		try {
			System.out.println("client-->login");
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(check);

			bytes = baos.toByteArray();
			System.out.println("여기c: " + bytes.toString());

			sendMsg = withServer.getOutputStream();
			sendMsg.write(bytes);
			System.out.println("끝");
		} catch (IOException e) {
			System.out.println("Client chat Error");
		}

	}

}
