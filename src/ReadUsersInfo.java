import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ReadUsersInfo {

	private JFrame frame;
	private JTable table;

	public ReadUsersInfo() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u67E5\u770B\u7528\u6237\u4FE1\u606F");
		frame.setBounds(100, 100, 593, 391);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 575, 346);
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
				{null, null, null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u8EAB\u4EFD\u8BC1\u53F7\u7801", "\u540D\u5B57", "\u7535\u8BDD\u53F7\u7801"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
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
			rs = stmt.executeQuery("select* from UsersInfo where Type = '0'");
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
