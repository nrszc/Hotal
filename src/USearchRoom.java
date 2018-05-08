
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
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class USearchRoom extends JFrame{

	private JFrame frame;
	private JTable table;
	JButton button;
	JFormattedTextField formattedTextField;

	public USearchRoom() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u67E5\u8BE2\u623F\u95F4");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 583, 378);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u623F\u95F4\u53F7\uFF1A");
		label_1.setBounds(85, 13, 140, 27);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(226, 15, 114, 21);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u67E5 \u8BE2");
		button.setBounds(363, 16, 71, 21);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("   \u67E5\u8BE2\u7ED3\u679C\uFF1A");
		label_2.setBounds(197, 53, 103, 26);
		label_2.setFont(new Font("宋体", Font.BOLD, 12));
		frame.getContentPane().add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 92, 565, 241);
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
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		frame.setVisible(true);
	}
	String s;
	boolean boo;
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	Object[] array = new Object[5];
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button)
			{
				s = formattedTextField.getText().trim().toString();
				try{
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
					String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
					String userName = "sa"; //默认用户名
					String userPwd = "123456"; //密码
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt = conn.createStatement();
					String sql = "select* from Room where Rid='"+s+"'";
					rs = stmt.executeQuery(sql);
					String str = "0";
					if(rs.next())
						{
						array[0] = rs.getString("Rid");
						array[1] = rs.getFloat("Rprice");
						array[2] = rs.getString("Raddress");
						array[3] = rs.getString("Rtype");
						if( Integer.parseInt(rs.getString("Rstate")) == 0)
							array[4] = "有剩房";
						else
							array[4] = "此房已出售";
						TableModel model1 = table.getModel();
						for(int i = 0; i < 5; i++)
						model1.setValueAt(array[i],0, i);
						}
					else{
						JOptionPane.showMessageDialog(null, "找不到该房间信息！");
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
