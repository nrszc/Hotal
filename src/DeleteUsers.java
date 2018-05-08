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

public class DeleteUsers extends JFrame{

	private JFrame frame;
	JButton button;
	JFormattedTextField formattedTextField;
	
	public DeleteUsers() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5220\u9664\u7528\u6237");
		frame.setBounds(100, 100, 404, 260);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(74, 59, 71, 30);
		frame.getContentPane().add(lblNewLabel);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setText("");
		formattedTextField.setBounds(140, 62, 153, 24);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u5220\u9664");
		button.setBounds(124, 117, 113, 37);
		frame.getContentPane().add(button);
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
			    stmt.executeUpdate("delete from UsersInfo where Userid = '"+s+"'");
				JOptionPane.showMessageDialog(null, "删除成功");
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "出现错误!", "错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
