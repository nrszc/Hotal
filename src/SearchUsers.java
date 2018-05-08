import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchUsers {

	private JFrame frame;
	private JTable table;
	JButton button;
	JFormattedTextField formattedTextField;

	public SearchUsers() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u67E5\u627E\u7528\u6237");
		frame.setBounds(100, 100, 610, 387);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(124, 48, 72, 23);
		frame.getContentPane().add(lblNewLabel);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(196, 47, 128, 24);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u67E5\u627E");
		button.setBounds(361, 46, 113, 27);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 110, 592, 232);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u8EAB\u4EFD\u8BC1\u53F7\u7801", "\u540D\u5B57", "\u7535\u8BDD\u53F7\u7801"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("\u7ED3\u679C\u5982\u4E0B\uFF1A");
		label.setBounds(238, 84, 86, 18);
		frame.getContentPane().add(label);
        frame.setVisible(true);
        ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s = null;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button){
			try{
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
				String userName = "sa"; //默认用户名
				String userPwd = "123456"; //密码
				s = formattedTextField.getText().trim().toString();
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select* from UsersInfo where Userid = '"+s+"'");
				TableModel model1 = table.getModel();
				int i = 0, j = 0;
				while(rs.next())
				{
					model1.setValueAt(rs.getString("Userid"),i, j); j++;
					model1.setValueAt(rs.getString("IDCard"),i, j); j++;
					model1.setValueAt(rs.getString("RealName"),i, j); j++;
					model1.setValueAt(rs.getString("Phone"),i, j); 
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
}
