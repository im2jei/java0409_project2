package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import client.Login;

public class ServerChat extends Thread {
	private Socket withClient = null;// � Ŭ���̾�Ʈ�� �������� �𸣴ϱ� null
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private String pwd = null;

	public ServerChat(Socket c) {
		this.withClient = c;
	}

	public ServerChat(String id, String pwd) {
		this.id=id;
		this.pwd=pwd;
	}

	@Override
	public void run() {
		StreamSet();
	}

	private void StreamSet() {
		try {
			reMsg = withClient.getInputStream();// �������� �ְ�ޱ��� ���� jvm���� in�ϴ°�
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim();// ��������
			
			MemberDTO member = new MemberDTO();

			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO result = dao.loginchk(id, pwd);

			if (result.getId().equals(member.getId())&&result.getPwd().equals(member.getPwd())) {
				
			}

			InetAddress c_ip = withClient.getInetAddress();// ������ ���� �����Ǹ� �ҷ�����
			String ip = c_ip.getHostAddress();

			System.out.println(id + " ���� �α����ϼ̽��ϴ�. (" + ip + ")");

			String reMsg = "���� ���ӵǾ����ϴ�.";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (IOException e) {
		}

	}

}
