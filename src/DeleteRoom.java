//DeleteRoom.java
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteRoom extends JFrame{

	private JFrame frame;
	JFormattedTextField formattedTextField ;
	JButton button,button_1;
	public DeleteRoom() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ɾ������");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 387, 176);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("        -------------   \u5220 \u9664 \u623F \u95F4   -------------");
		label.setFont(new Font("΢���ź� Light", Font.BOLD, 15));
		label.setBounds(0, 10, 372, 27);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(68, 47, 56, 27);
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(123, 50, 178, 21);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u786E \u5B9A");
		button.setFont(new Font("����", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(91, 91, 78, 30);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u53D6 \u6D88");
		button_1.setFont(new Font("����", Font.PLAIN, 12));
		button_1.setBackground(SystemColor.inactiveCaptionBorder);
		button_1.setBounds(199, 91, 78, 30);
		frame.getContentPane().add(button_1);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s1 = null;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button_1){
				frame.dispose();
			}
			else{
			try{
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC����
				String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //���ӷ����������ݿ�sample
				String userName = "sa"; //Ĭ���û���
				String userPwd = "123456"; //����
				s1 = formattedTextField.getText().trim().toString();
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				stmt = conn.createStatement();
				String sql = "delete from Room where Rid = '"+s1+"'";
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");  
				} catch (Exception E){
					JOptionPane.showMessageDialog(null, "���ִ���", "����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
