//InfomationOfRoom.java
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfomationOfRoom extends JFrame{
	private JFrame frame;
	JButton button, button_1, button_2, button_3;
	public InfomationOfRoom() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("房间信息");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(600, 300, 362, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("  ----------------  \u623F \u95F4 \u4FE1 \u606F   ----------------");
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		label.setBounds(0, 10, 338, 27);
		frame.getContentPane().add(label);
		
	    button = new JButton("\u589E\u6DFB\u623F\u95F4");
		button.setForeground(Color.BLACK);
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(54, 70, 93, 54);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u5220\u9664\u623F\u95F4");
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("黑体", Font.PLAIN, 14));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(199, 70, 93, 54);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("\u67E5\u627E\u623F\u95F4\r\n");
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("黑体", Font.PLAIN, 14));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(54, 157, 93, 54);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("黑体", Font.PLAIN, 14));
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(199, 157, 93, 54);
		frame.getContentPane().add(button_3);
		frame.setVisible(true);
	
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
		button_2.addActionListener(listen);
		button_3.addActionListener(listen);
		
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button)
				new AddRoomInformation();
			if(source == button_1)
				new DeleteRoom();
			if(source == button_2)
				new SearchRoom();
			if(source == button_3)
				new ModifyRoomInformation();
		}
}
}
