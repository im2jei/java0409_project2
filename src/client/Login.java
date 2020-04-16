package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.MemberDTO;

public class Login extends JFrame implements ActionListener {
	JPanel nP, cP, sP, eP;
	JLabel idLabel, pwLabel, joinlabel;
	JTextField idField, pwdField, loginField;
	JButton loginBtn, joinBtn;
	private static final String USERINFO_SER = "user.ser";
	ClientChat ch = null;
	String[] check = null;

	public Login(ClientChat ch) {
		super("Login");
		this.ch = ch;
		createpanel();
		loginchk(check);
		setClose();
		gosignup();
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	private void createpanel() {
		this.setLayout(new BorderLayout());
		nP = new JPanel();
		nP.setBorder(new EmptyBorder(10, 10, 0, 10));
		idLabel = new JLabel("ID");
		nP.add(idLabel);

		// �ؽ�Ʈ �ʵ� �����
		idField = new JTextField(15);
		nP.add(idField);

		// center �г� �����
		cP = new JPanel();
		pwLabel = new JLabel("�� ȣ");
		pwdField = new JPasswordField(15);

		cP.add(pwLabel);
		cP.add(pwdField);
		// south �г� �����
		sP = new JPanel();
		loginBtn = new JButton("�α���");
		sP.add(loginBtn);

		eP = new JPanel();
		joinBtn = new JButton("ȸ������");
		sP.add(joinBtn);
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);

		// �г� �����ӿ� �ֱ� ��ġ�� ����"".
		this.add(nP, "North");
		this.add(cP, "Center");
		this.add(sP, "South");
		this.add(eP, "East");

		// �� ����
		// this.setBackground(Color,blue);

		// ������ �����ֱ�
		this.setBounds(400, 500, 300, 200);

	}

	public void setClose() {
		// this.setVisible(false);
		// �ڵ����� �����Ը���
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		// true�� ȭ�鿡�� ��Ÿ���� false�� ȭ�鿡�� �������
		this.setVisible(true);
	}

	public void loginchk(String[] check) {

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String[] check = { idField.getText(), pwdField.getText(), "login" };
					ch.Send(check);
					System.out.println("�α���-->clientchat");

					for (int i = 0; i < check.length; i++) {
						if (idField.getText().equals(check[0]) && pwdField.getText().equals(check[1])) {
							JOptionPane.showMessageDialog(null, "�α��� �Ϸ�");
							dispose();
							if (check[check.length-1].equals("1")) {
								System.out.println("���θ�â �߰��ϱ�");
								dispose();
							} else if (check[check.length-1].equals("5")) {
								System.out.println("������â �߰� �ϱ� ������ ��ü�� �������� â���� ������");
								dispose();
							}
						}

					}

//					else {
//						JOptionPane.showMessageDialog(null, "�������� �ʴ� ���̵�ų� ��й�ȣ�� ���� �ʽ��ϴ�.");
//						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//						idField.setText("");
//						pwdField.setText("");
//					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private void gosignup() {
		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUp();
			}

		});
	}
}
