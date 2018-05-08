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
import javax.swing.JPasswordField;

import javax.swing.JButton;

public class AddUsers {

	private JFrame frame;
	private JPasswordField passwordField;
	JButton button,button_1;
	JFormattedTextField formattedTextField,formattedTextField_1,formattedTextField_2,formattedTextField_3;
	

	public AddUsers() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6DFB\u52A0\u7528\u6237");
		frame.setBounds(100, 100, 404, 291);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(70, 24, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setBounds(80, 55, 72, 18);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		label_2.setBounds(37, 86, 100, 18);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u540D\u5B57\uFF1A");
		label_3.setBounds(80, 117, 72, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_1 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		label_1.setBounds(50, 148, 87, 18);
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(135, 21, 157, 21);
		frame.getContentPane().add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(135, 83, 157, 21);
		frame.getContentPane().add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(135, 117, 157, 21);
		frame.getContentPane().add(formattedTextField_2);
		
		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(135, 148, 157, 21);
		frame.getContentPane().add(formattedTextField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 55, 157, 18);
		frame.getContentPane().add(passwordField);
		
		button = new JButton("\u6DFB\u52A0");
		button.setBounds(83, 195, 87, 27);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u91CD\u7F6E");
		button_1.setBounds(205, 195, 87, 27);
		frame.getContentPane().add(button_1);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s1 ,s2,s3,s4,PW;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button_1){
				formattedTextField.setText("");
				formattedTextField_1.setText("");
				formattedTextField_3.setText("");
				formattedTextField_2.setText("");
				passwordField.setText("");
			}
			else{
			try{
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
				String userName = "sa"; //默认用户名
				String userPwd = "123456"; //密码
				s1 = formattedTextField.getText().trim().toString();
				s2 = formattedTextField_1.getText().trim().toString();
				s3 = formattedTextField_2.getText().trim().toString();
				s4 = formattedTextField_3.getText().trim().toString();
				PW = passwordField.getText().trim().toString();
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				String sql = "insert into UsersInfo values('"+s1+"','"+PW+"','0','"+s2+"','"+s3+"','"+s4+"')";
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "添加成功！");
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "出现错误!", "错误",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		}
}
