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

public class ReadOrder {

	private JFrame frame;
	private JTable table;

	public ReadOrder() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u67E5\u770B\u8BA2\u5355\u60C5\u51B5");
		frame.setBounds(100, 100, 626, 425);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 608, 380);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"\u8BA2\u5355\u53F7", "\u7528\u6237\u540D", "\u623F\u95F4\u53F7", "\u4E0B\u5355\u65F6\u95F4", "\u5165\u4F4F\u65F6\u95F4", "\u79BB\u5F00\u65F6\u95F4", "\u4EF7\u683C"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
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
			rs = stmt.executeQuery("select* from la");
			TableModel model1 = table.getModel();
			int i = 0, j = 0;
			while(rs.next())
			{
				model1.setValueAt(rs.getString("Soleld"),i, j); j++;
				model1.setValueAt(rs.getString("Userid"),i, j); j++;
				model1.setValueAt(rs.getString("Rid"),i, j); j++;
				model1.setValueAt(rs.getString("Ordertime"),i, j); j++;
				model1.setValueAt(rs.getString("Indate"),i, j); j++;
				model1.setValueAt(rs.getString("Outdate"),i, j); j++;
				model1.setValueAt(rs.getFloat("Price"),i, j);
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
