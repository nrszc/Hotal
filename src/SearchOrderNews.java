import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class SearchOrderNews extends JFrame{

	private JFrame frame;
	private JTable table;

	public SearchOrderNews(String ss){
		frame = new JFrame();
		frame.setTitle("\u67E5\u8BE2\u8BA2\u5355");
		frame.getContentPane().setFont(new Font("华文楷体", Font.PLAIN, 17));
		frame.setBounds(100, 100, 666, 352);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u60A8\u7684\u8BA2\u5355\u60C5\u51B5\u5982\u4E0B\uFF1A");
		lblNewLabel.setBounds(215, 13, 146, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 53, 648, 254);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u8BA2\u5355\u53F7", "\u623F\u95F4\u53F7", "\u623F\u95F4\u4EF7\u683C", "\u5165\u4F4F\u65F6\u95F4", "\u79BB\u5F00\u65F6\u95F4"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
			scrollPane.setViewportView(table);
			frame.setVisible(true);
			Connection conn = null;
			ResultSet rs = null;
			Statement stmt = null;
			try{
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
				String userName = "sa"; //默认用户名
				String userPwd = "123456"; //密码
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select* from la where Userid='"+ss+"'");
				TableModel model1 = table.getModel();
				int i = 0, j = 0;
				while(rs.next())
				{
					model1.setValueAt(rs.getString("Userid"),i, j); j++;
					model1.setValueAt(rs.getString("Soleld"),i, j); j++;
					model1.setValueAt(rs.getString("Rid"),i, j); j++;
					model1.setValueAt(rs.getFloat("Price"),i, j); j++;
					model1.setValueAt(rs.getString("Indate"),i, j); j++;
					model1.setValueAt(rs.getString("Outdate"),i, j); j=0;
					i++;
				}
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "您没有订单，快去下订单吧！");
				}
	}
}
