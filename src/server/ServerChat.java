package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import client.Login;

public class ServerChat extends Thread {
	private Socket withClient = null;// 어떤 클라이언트와 소통할지 모르니까 null
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
			reMsg = withClient.getInputStream();// 소켓으로 주고받기한 것을 jvm으로 in하는것
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			id = new String(reBuffer);
			id = id.trim();// 공백제거
			
			MemberDTO member = new MemberDTO();

			MemberDAO dao = MemberDAO.getInstance();
			MemberDTO result = dao.loginchk(id, pwd);

			if (result.getId().equals(member.getId())&&result.getPwd().equals(member.getPwd())) {
				
			}

			InetAddress c_ip = withClient.getInetAddress();// 소켓을 통해 아이피를 불러오기
			String ip = c_ip.getHostAddress();

			System.out.println(id + " 님이 로그인하셨습니다. (" + ip + ")");

			String reMsg = "정상 접속되었습니다.";
			sendMsg = withClient.getOutputStream();
			sendMsg.write(reMsg.getBytes());

		} catch (IOException e) {
		}

	}

}
