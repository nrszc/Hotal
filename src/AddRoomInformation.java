//AddRoomInformation.java

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddRoomInformation extends JFrame{
	JFrame frame;
	JButton button, button_1;
	JComboBox comboBox, comboBox_1;
	JFormattedTextField formattedTextField, formattedTextField_1;
	public AddRoomInformation() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("��ӷ�����Ϣ");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 429, 292);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("        -------------   \u6DFB \u52A0 \u623F \u95F4 \u4FE1 \u606F   -------------");
		label.setFont(new Font("΢���ź� Light", Font.BOLD, 15));
		label.setBounds(0, 10, 411, 27);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(72, 47, 56, 27);
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(127, 50, 218, 24);
		frame.getContentPane().add(formattedTextField);
		
		JLabel label_2 = new JLabel("\u623F\u95F4\u7C7B\u578B\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 14));
		label_2.setBounds(72, 84, 70, 27);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u623F\u95F4\u4F4D\u7F6E\uFF1A");
		label_3.setFont(new Font("����", Font.PLAIN, 14));
		label_3.setBounds(72, 121, 70, 27);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u4EF7\u683C\u8BBE\u7F6E\uFF1A");
		label_4.setFont(new Font("����", Font.PLAIN, 14));
		label_4.setBounds(72, 158, 70, 27);
		frame.getContentPane().add(label_4);
		
		comboBox = new JComboBox();
		comboBox.setBounds(137, 87, 208, 21);
		comboBox.addItem("��ͨ���˼�");
		comboBox.addItem("�������˼�");
		comboBox.addItem("��ͨ˫�˼�");
		comboBox.addItem("����˫�˼�");
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(137, 124, 208, 21);
		comboBox_1.addItem("��������ӾƵ�");
		comboBox_1.addItem("����Բ��԰�Ƶ�");
		comboBox_1.addItem("տ�����ݹ�Ԣ");
		comboBox_1.addItem("���ں����ǾƵ�");
		frame.getContentPane().add(comboBox_1);
		
		button = new JButton("\u5F55 \u5165");
		button.setFont(new Font("����", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(112, 207, 78, 30);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u91CD \u7F6E");
		button_1.setFont(new Font("����", Font.PLAIN, 12));
		button_1.setBackground(SystemColor.inactiveCaptionBorder);
		button_1.setBounds(219, 207, 78, 30);
		frame.getContentPane().add(button_1);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(137, 159, 208, 26);
		frame.getContentPane().add(formattedTextField_1);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
	}
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String s1 = null;
	String s2 = null;
	String s3 = null;
	String s4 = null;
	String s5 = "0";
	float  f;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button_1){
				formattedTextField.setText("");
				comboBox.setSelectedIndex(-1);
				comboBox_1.setSelectedIndex(-1);
				formattedTextField_1.setText("");
			}		
			if(source == button)
			{
				try{
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC����
					String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //���ӷ����������ݿ�sample
					String userName = "sa"; //Ĭ���û���
					String userPwd = "123456"; //����
					s1 = formattedTextField.getText().trim().toString();
					s2 = comboBox.getSelectedItem().toString();
					s3 = comboBox_1.getSelectedItem().toString();
					s4 = formattedTextField_1.getText().trim().toString();
					f  = Float.parseFloat(s4);
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt = conn.createStatement();
					String sql = "insert into Room values('"+s1+"',"+f+",'"+s3+"','"+s2+"','0')"; 
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "��ӳɹ���"); 
			       }
				catch (Exception E){
					JOptionPane.showMessageDialog(null, "���ִ���!", "����",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
