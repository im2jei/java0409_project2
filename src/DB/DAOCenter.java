package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOCenter implements DAOInterface {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private MemberDAO memDAO = null;
	private GoodsDAO mgmtDAO = null;
	private CartDAO cartDAO = null;
	private static DAOCenter DAOcenter;

	public DAOCenter() {
		connect();
		if (conn != null) {
//			memDAO = MemberDAO.getInstance(conn);
//			mgmtDAO = ManagementDAO.getInstance(conn);
//			cartDAO = CartDAO.getInstance(conn);
		}
	}

	public static DAOCenter getInstance() {
		if (DAOcenter == null) {
			DAOcenter = new DAOCenter();
		}
		return DAOcenter;
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class load fail :" + e.getMessage());
		}
	}

	private void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class load fail : " + e.getMessage());
		}
	}

	public void whichone(Object objectMember, String m) {

		switch (m) {

		case "member":
			break;
		case "goods":
			break;
		case "cart":
			break;
		case "order":
			break;
		}

	}

	@Override
	public Boolean Insert(Object DTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Select(Object DTO) {
		System.out.println("왔구나");
		String[] check = (String[]) DTO;
		for(int i=0; i<check.length;i++) {
			System.out.println("이거 보낸다"+check[i]);
			String id=check[0];
			String pwd=check[1];
			memDAO= new MemberDAO();
			memDAO.loginchk(id, pwd);
			break;
		}
		
		return true;
	}

	@Override
	public Boolean Edit(Object DTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Delete(Object DTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
