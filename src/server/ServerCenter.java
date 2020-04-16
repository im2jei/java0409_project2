package server;

import java.util.ArrayList;

import DB.DAOCenter;
import DB.MemberDTO;

public class ServerCenter {
	private ArrayList<ServerChat> sList = new ArrayList<>();
	private DAOCenter dc = null;
	private String check[]=null;
	ServerChat sc= null;

	public void addServerChat(ServerChat s) {
		this.sList.add(s);
	}

	public void select(Object objectMember) {
			check = (String[]) objectMember;
			for(int i=0; i<check.length;i++) {
				System.out.println("¹ÞÀ½"+check[i]);
				if(check[check.length-1].equals("login")) {
					dc= new DAOCenter();
					dc.Select(check);
				}
			}

	}

	public void reselect(MemberDTO member) {
		System.out.println("memberDTO="+member);
		sc=new ServerChat(check);
		sc.Send(member);
		
		
		
	}

}
