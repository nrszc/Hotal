import java.awt.EventQueue;

import java.awt.Window.Type;
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

public class ReadAllRoom {

	private JFrame frame;
	private JTable table;

	public ReadAllRoom() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6240\u6709\u623F\u95F4\u4FE1\u606F");
		frame.setBounds(100, 100, 588, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 570, 343);
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
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u623F\u95F4\u53F7", "\u623F\u95F4\u4EF7\u683C", "\u5730\u5740", "\u7C7B\u578B", "\u72B6\u6001"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Float.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
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
			rs = stmt.executeQuery("select* from Room");
			TableModel model1 = table.getModel();
			int i = 0, j = 0;
			while(rs.next())
			{
				
				model1.setValueAt(rs.getString("Rid"),i, j); j++;
				model1.setValueAt(rs.getFloat("Rprice"),i, j); j++;
				model1.setValueAt(rs.getString("Raddress"),i, j); j++;
				model1.setValueAt(rs.getString("Rtype"),i, j); j++;
				if(Integer.parseInt(rs.getString("Rstate")) == 0)
				model1.setValueAt("此房空余",i, j);
				else
				model1.setValueAt("此房已出售", i, j);
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
