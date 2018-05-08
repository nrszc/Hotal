//ModifyRoomInformation.java
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModifyRoomInformation {

	private JFrame frame;
	JFormattedTextField formattedTextField1,formattedTextField2,formattedTextField3;
	JComboBox comboBox1,comboBox2;
	JButton button, button_1;

	public ModifyRoomInformation() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 414, 360);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("   --------------   \u4FEE \u6539 \u623F \u95F4 \u4FE1 \u606F  --------------");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setBounds(52, 7, 339, 27);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(73, 46, 56, 27);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u623F\u95F4\u7C7B\u578B\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(59, 166, 70, 27);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u623F\u95F4\u4EF7\u683C\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(59, 206, 70, 27);
		frame.getContentPane().add(label_3);
		
		button = new JButton("\u4FEE \u6539");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(93, 258, 78, 30);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u91CD \u7F6E");
		button_1.setFont(new Font("宋体", Font.PLAIN, 12));
		button_1.setBackground(SystemColor.inactiveCaptionBorder);
		button_1.setBounds(218, 258, 78, 30);
		frame.getContentPane().add(button_1);
		
		comboBox2 = new JComboBox();
		comboBox2.setBounds(133, 167, 183, 24);
		comboBox2.addItem("普通单人间");
		comboBox2.addItem("豪华单人间");
		comboBox2.addItem("普通双人间");
		comboBox2.addItem("豪华双人间");
		frame.getContentPane().add(comboBox2);
		
		formattedTextField3 = new JFormattedTextField();
		formattedTextField3.setBounds(133, 206, 183, 24);
		frame.getContentPane().add(formattedTextField3);
		
		JLabel label_4 = new JLabel("\u6539\u623F\u95F4\u53F7\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(59, 86, 70, 27);
		frame.getContentPane().add(label_4);
		
		formattedTextField1 = new JFormattedTextField();
		formattedTextField1.setBounds(129, 47, 183, 24);
		frame.getContentPane().add(formattedTextField1);
		
		formattedTextField2 = new JFormattedTextField();
		formattedTextField2.setBounds(129, 87, 183, 24);
		frame.getContentPane().add(formattedTextField2);
		
		JLabel label_5 = new JLabel("\u5730\u5740\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(85, 126, 42, 27);
		frame.getContentPane().add(label_5);
		
		comboBox1 = new JComboBox();
		comboBox1.setBounds(133, 126, 183, 24);
		comboBox1.addItem("肇庆好日子酒店");
		comboBox1.addItem("北京圆明园酒店");
		comboBox1.addItem("湛江九州公寓");
		comboBox1.addItem("深圳好望角酒店");
		frame.getContentPane().add(comboBox1);
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
	String s5 = null;
	float  f;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button_1){
				formattedTextField1.setText("");
				formattedTextField3.setText("");
				formattedTextField2.setText("");
				comboBox1.setSelectedIndex(-1);
				comboBox2.setSelectedIndex(-1);
			}		
			if(source == button)
			{
				try{
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
					String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
					String userName = "sa"; //默认用户名
					String userPwd = "123456"; //密码
					s1 = formattedTextField1.getText().trim().toString();
					s2 = formattedTextField2.getText().trim().toString();
					s3 = (String)comboBox1.getSelectedItem().toString();
					s4 = (String)comboBox2.getSelectedItem().toString();
					s5 = formattedTextField3.getText().trim().toString();
					f  = Float.parseFloat(s5);
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt = conn.createStatement();
					String sql = "update Room "
							+ "set Rprice = "+f+",Rtype = '"+s4+"',Rid ='"+s2+"',Raddress = '"+s3+"' "
							+ "from Room where Rid = '"+s1+"'";
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
