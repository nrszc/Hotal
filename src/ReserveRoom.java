import java.awt.EventQueue;


import java.util.UUID;  
import java.util.Date;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class ReserveRoom extends JFrame{

	private JFrame frame;
	String s;
	JButton button;
	JFormattedTextField formattedTextField, formattedTextField_1,formattedTextField_2;
	
	public ReserveRoom(String ss){
		s = ss;
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u8BA2\u623F");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 409, 296);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("        -------------   \u7528 \u6237 \u8BA2 \u623F \u754C \u9762   -------------");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setBounds(0, 10, 411, 27);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u623F\u95F4\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(67, 54, 103, 27);
		frame.getContentPane().add(label_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(127, 57, 177, 21);
		frame.getContentPane().add(formattedTextField);
		
		button = new JButton("\u8BA2 \u623F");
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBackground(SystemColor.inactiveCaptionBorder);
		button.setBounds(147, 206, 78, 30);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("\u5165\u4F4F\u65F6\u95F4\uFF1A");
		lblNewLabel.setBounds(49, 89, 78, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u79BB\u5F00\u65F6\u95F4\uFF1A");
		lblNewLabel_1.setBounds(49, 129, 78, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(127, 94, 177, 21);
		frame.getContentPane().add(formattedTextField_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(127, 129, 177, 21);
		frame.getContentPane().add(formattedTextField_2);
		
		JLabel lblNewLabel_2 = new JLabel("\u5982\uFF1A2017-5-1");
		lblNewLabel_2.setBounds(137, 163, 139, 30);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
	}
	Connection conn = null,conn1;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement pstmt;
	String s1 = null;
	 Date d = new Date();  
	 String dateStr,dateStr2;
	 Date date2,date;
	 float day;  
	 int isUpdate;
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			Date date=new Date();     //获取一个Date对象
	    	 DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
	    	 String punchTime = simpleDateFormat.format(date); 
			if(source == button){
				try{
					try
					 {
						 dateStr = formattedTextField_1.getText().trim().toString();
						 dateStr2 = formattedTextField_2.getText().trim().toString();
						 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					  date2 = format.parse(dateStr2);
					  date = format.parse(dateStr);
					  day =  differentDaysByMillisecond(date,date2);
					 } catch (ParseException e1) {
					  e1.printStackTrace();
					 }
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动
					String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=Hotel1"; //连接服务器和数据库sample
					String userName = "sa"; //默认用户名
					String userPwd = "123456"; //密码
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName, userPwd);
					stmt = conn.createStatement();
					s1 = formattedTextField.getText().trim().toString();
					String sql = "select* from Room where Rid='"+s1+"' and Rstate ='"+0+"'";
					rs = stmt.executeQuery(sql);
					UUID uuid = UUID.randomUUID();
					String s2 = "gaozhiji";
			    	int i = 1;
			    	float f = 200;
					if(rs.next())
						{
						day = day * rs.getFloat("Rprice");
						stmt.executeUpdate("update Room set Rstate ='"+1+"' from Room where Rid ='"+s1+"'");
						String sql1 ="insert into la "
								+ "values('"+uuid+"','"+s+"','"+s1+"','"+punchTime+"','"+dateStr+"','"+dateStr2+"',"+day+","+i+")";
						stmt.executeUpdate(sql1);
						JOptionPane.showMessageDialog(null, "订单成功！"); 
						}
					else{
						JOptionPane.showMessageDialog(null, "找不到该房间信息或该房间已被预订！");
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
	public static int differentDaysByMillisecond(Date date1,Date date2)
	{
	int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
	return days;
	}
}
