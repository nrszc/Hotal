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
import javax.swing.JPasswordField;

public class ModifyUsers {

	private JFrame frame;
	JFormattedTextField formattedTextField;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JFormattedTextField formattedTextField_1;
	private JFormattedTextField formattedTextField_2;
	private JFormattedTextField formattedTextField_3;
	private JFormattedTextField formattedTextField_4;
	private JPasswordField passwordField;
	private JButton button;
	private JButton button1;

	public ModifyUsers() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4FEE\u6539\u7528\u6237");
		frame.setBounds(100, 100, 440, 354);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(89, 40, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(156, 37, 167, 24);
		frame.getContentPane().add(formattedTextField);
		
		lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(103, 102, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		label.setBounds(58, 133, 97, 18);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("\u540D\u5B57\uFF1A");
		label_1.setBounds(103, 164, 72, 18);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("\u6539\u7528\u6237\u540D\uFF1A");
		label_2.setBounds(73, 71, 88, 18);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		label_3.setBounds(73, 196, 75, 18);
		frame.getContentPane().add(label_3);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(156, 68, 167, 24);
		frame.getContentPane().add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(156, 130, 167, 24);
		frame.getContentPane().add(formattedTextField_2);
		
		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(156, 161, 167, 24);
		frame.getContentPane().add(formattedTextField_3);
		
		formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBounds(156, 193, 167, 24);
		frame.getContentPane().add(formattedTextField_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 99, 167, 21);
		frame.getContentPane().add(passwordField);
		
		button = new JButton("\u4FEE\u6539");
		button.setBounds(89, 247, 106, 33);
		frame.getContentPane().add(button);
		
		button1 = new JButton("\u91CD\u7F6E");
		button1.setBounds(239, 247, 106, 33);
		frame.getContentPane().add(button1);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button1.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s = null;
	String s1 = null;
	String s2 = null;
	String s3 = null;
	String s4 = null;
	String PW = null;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button1){
				formattedTextField.setText("");
				formattedTextField_1.setText("");
				formattedTextField_2.setText("");
				formattedTextField_3.setText("");
				formattedTextField_4.setText("");
				passwordField.setText("");
			}		
			if(source == button)
			{
				try{
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
					String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
					String userName = "sa"; //默认用户名
					String userPwd = "123456"; //密码
					s = formattedTextField.getText().trim().toString();
					s1 = formattedTextField_1.getText().trim().toString();
					s2 = formattedTextField_2.getText().trim().toString();
					s3 = formattedTextField_3.getText().trim().toString();
					s4 = formattedTextField_3.getText().trim().toString();
					PW = passwordField.getText().trim().toString();
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt = conn.createStatement();
					String sql = "update UsersInfo set Userid = '"+s1+"',Password = '"+PW+"',IDCard = '"+s2+"',RealName = '"+s3+"',Phone = '"+s4+"' from UsersInfo where Userid = '"+s+"'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "修改成功！");  
			       }
				catch (Exception E){
					JOptionPane.showMessageDialog(null, "出现错误!", "错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
