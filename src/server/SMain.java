package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SMain {

	public static void main(String[] args) throws Exception {
		// ������ Ŭ���̾�Ʈ�� ����� ��� program ver 1.0
		// TCP ����� �ϱ� ���� �ڿ�
		ServerSocket serverS = null;
		Socket withClient = null;
		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.35", 9999));

		ArrayList<Socket> cList = new ArrayList<>();
		while (true) {
			System.out.println("�� �������");
			withClient = serverS.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "Ŭ���̾�Ʈ ���� ��");
			ServerChat s = new ServerChat(withClient);
			// sc.sList.add(s);
			s.start();

		}
	}
}
