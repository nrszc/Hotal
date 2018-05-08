//login.java
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JFrame{
	JFrame frame;
	JPanel pane1, pane2, pane3, pane4;
	JButton button1, button2;
	JLabel image, lblUserName, lblUserPasswd;
	JTextField txtUserName;
	JPasswordField txtUserPasswd;
	public Login() {
		frame = new JFrame("酒店管理系统");
		Container contentPane = frame.getContentPane();
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane3 = new JPanel();
		pane4 = new JPanel();
	    image = new JLabel();  
		image.setIcon(new ImageIcon("jiudian.jpg"));    
		lblUserName = new JLabel("用户名称:");
		txtUserName = new JTextField(10);
		lblUserPasswd = new JLabel("用户密码:");
		txtUserPasswd = new JPasswordField(10);
		button1 = new JButton("登录");
		button2 = new JButton("\u6CE8\u518C");
		contentPane.setLayout(new BorderLayout());
		pane4.setLayout(new GridLayout(3,1));
		pane1.add(lblUserName);
		pane1.add(txtUserName);
		pane2.add(lblUserPasswd);
		pane2.add(txtUserPasswd);
		pane3.add(button1);
		pane3.add(button2);
		pane4.add(pane1);
		pane4.add(pane2);
		pane4.add(pane3);
		pane4.setSize(50,25);
		contentPane.add(image,BorderLayout.CENTER);
		contentPane.add(pane4,BorderLayout.SOUTH);
		frame.setSize(500,350);
		frame.setLocation(600, 300);
		frame.setResizable(false);
		frame.show();
		ButtonListener listen = new ButtonListener();
		button1.addActionListener(listen);
		button2.addActionListener(listen);
	}         
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s1 = null;
	String s2 = null;
	int Type;
	boolean boo1=false;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button2){
				new Register();
			}
			else{
			try{
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
				String userName = "sa"; //默认用户名
				String userPwd = "123456"; //密码
				s1 = txtUserName.getText().trim();
				s2 = txtUserPasswd.getText().trim();
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select* from UsersInfo where Userid='"+s1+"' and Password='"+s2+"'");
				rs.next();
				Type = rs.getInt("Type");
				if(rs != null)
					boo1 = true;
				rs.close();
				conn.close();
				stmt.close();
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "用户名或密码不正确!", "错误",JOptionPane.ERROR_MESSAGE);
				}
			if(boo1 == true && Type == 0){
				frame.dispose();
				new UserInterface(s1);
			}
			if(boo1 == true && Type == 1){
				frame.dispose();
				new ManagerInterface();
			}
			}
		}
	}
}
