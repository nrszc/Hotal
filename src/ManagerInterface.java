//ManagerInterface.java
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ManagerInterface extends JFrame{
	private JFrame frame;
	JButton btnNewButton,button;
	public ManagerInterface() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("管理登录界面");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 365, 191);
	
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("-----------   \u7BA1 \u7406 \u5458 \u767B \u9646 \u754C \u9762   -----------");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setBounds(10, 10, 338, 27);
		frame.getContentPane().add(label);
		
		btnNewButton = new JButton("\u623F\u95F4\u4FE1\u606F");
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 14));
		btnNewButton.setBounds(47, 69, 100, 53);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("\u7528\u6237\u4FE1\u606F");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(196, 69, 100, 53);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		btnNewButton.addActionListener(listen);
		button.addActionListener(listen);
	}
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == btnNewButton)
				new InfomationOfRoom();
			if(source == button)
				new UsersInterface();
		}
}
}
