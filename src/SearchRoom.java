//SearchRoom.java
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class SearchRoom {

	JFrame frame;
	JFormattedTextField formattedTextField ;
	JButton button;
	
	public SearchRoom() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("查找房间");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 525, 378);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("   -------------------   \u67E5 \u627E \u623F \u95F4   -------------------");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setBounds(83, 13, 410, 27);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(66, 47, 140, 27);
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(213, 49, 114, 21);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u67E5 \u8BE2");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(356, 50, 71, 21);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("   \u67E5\u8BE2\u7ED3\u679C\uFF1A");
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		label_2.setBounds(197, 77, 103, 26);
		frame.getContentPane().add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 109, 507, 224);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u623F\u95F4\u53F7", "\u623F\u95F4\u4EF7\u683C", "\u5730\u5740", "\u7C7B\u578B", "\u72B6\u6001"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(95);
		table.setBounds(0, 116, 507, 217);
		scrollPane.setViewportView(table);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s = null;
	String s1,s2,s3,s4;
	float f;
	private JTable table;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			try{
				String str = "";
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
				String userName = "sa"; //默认用户名
				String userPwd = "123456"; //密码
				s = formattedTextField.getText().trim().toString();
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				String sql = "select * from Room where Rid = '"+s+"'";
				rs = stmt.executeQuery(sql);
				TableModel model1 = table.getModel();
				int i = 0, j = 0;
				while(rs.next())
				{
					model1.setValueAt(rs.getString("Rid"),i, j); j++;
					model1.setValueAt(rs.getFloat("Rprice"),i, j); j++;
					model1.setValueAt(rs.getString("Raddress"),i, j); j++;
					model1.setValueAt(rs.getString("Rtype"),i, j); j++;
					if(rs.getString("Rstate") == "0")
					model1.setValueAt("此房已出售",i, j);
					else
					model1.setValueAt("此房空余", i, j);
					i++;
						j=0;
				}
				rs.close();
				conn.close();
				stmt.close();
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "出现错误!", "错误",JOptionPane.ERROR_MESSAGE);
				}
		}
	}
}
