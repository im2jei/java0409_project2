package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DB.GoodsDAO;
import DB.GoodsDTO;

public class Shopping extends JFrame {
	Scanner in = new Scanner(System.in);
	String header[] = { "상품코드", "상품이름", "수량", "가격", "체크" };
	JTabbedPane tabpane = new JTabbedPane();
	DefaultTableModel tablemodel = new DefaultTableModel(header, 0);
	JTable table = new JTable(tablemodel);
	JScrollPane tableScroll = new JScrollPane(table);

	// center panel
	JPanel tab_center = new JPanel();
	JPanel tab_south = new JPanel();
	JPanel south_north = new JPanel();

	JTextField tfield = null;

	int modIntRow = -1;

	GoodsDAO dao = GoodsDAO.getInstance();
	GoodsDTO dto = null;
	ArrayList<String[]> initList = new ArrayList<>();

	public Shopping() {
		super("쇼핑몰");// super의 생성자 호출
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();

		init();

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	private void init() {
		String in[] = new String[5];
		initList = dao.getList();
		for (int i = 0; i < initList.size(); i++) {
			tablemodel.addRow(initList.get(i));
//			JCheckBox chkbox = new JCheckBox("");
//			in[4] = chkbox.getActionCommand();
//			tab_center.add(chkbox);

		}
	}

	public void tablesetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {// 마우스 왼쪽 클릭
					modIntRow = table.getSelectedRow();
					if (e.getClickCount() == 2) {// 왼쪽 더블 클릭

					}
					if (e.getClickCount() == 3) {

						modIntRow = table.getSelectedRow();
					}
				}
			}
		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();// 테이블의 셀
																		// 값을 정렬
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}

	private void createtb() {

		JLabel all = new JLabel("총 금액");
		south_north.add(all);

		JTextField total = new JTextField(10);
		south_north.add(total);

		JButton addB = new JButton("장바구니에 담기");
		south_north.add(addB);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				tablemodel.addRow(in);
				// saveToDB(in);
			}

		});

		JButton modB = new JButton("바로 주문하기");
		south_north.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				// editToDB(in);
				modIntRow = -1;
			}
		});

	}

	private void createpanel() {
		this.add(tab_center, "Center");
		this.add(tab_south, "South");

		tab_center.setLayout(new BorderLayout());
		tab_center.add(tableScroll, "Center");
		tab_center.add(tab_south, "South");
		tabpane.add("shopping", tab_center);

		tab_south.setLayout(new BorderLayout());
		tab_south.add(south_north, "North");

	}

}
