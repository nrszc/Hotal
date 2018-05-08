//UserInterface.java
import java.awt.EventQueue;


import javax.swing.JFrame;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsersInterface {

	private JFrame frame;
	JButton btnNewButton,button,button_1,button_2,button_3,button_4;

	public UsersInterface() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u4FE1\u606F");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("\u6DFB\u52A0\u7528\u6237");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(55, 28, 145, 47);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("\u5220\u9664\u7528\u6237");
		button.setBounds(249, 28, 145, 47);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u67E5\u627E\u7528\u6237");
		button_1.setBounds(55, 106, 145, 47);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("\u4FEE\u6539\u7528\u6237");
		button_2.setBounds(249, 106, 145, 47);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("\u67E5\u770B\u7528\u6237\u4FE1\u606F");
		button_3.setBounds(55, 177, 145, 47);
		frame.getContentPane().add(button_3);
		
		button_4 = new JButton("\u67E5\u770B\u8BA2\u5355\u60C5\u51B5");
		button_4.setBounds(249, 177, 145, 47);
		frame.getContentPane().add(button_4);
		frame.setVisible(true);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
		button_2.addActionListener(listen);
		button_3.addActionListener(listen);
		button_4.addActionListener(listen);
		btnNewButton.addActionListener(listen);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == btnNewButton){
				new AddUsers();
			}
			if(source == button){
				new DeleteUsers();
			}
			if(source == button_1){
				new SearchUsers();
			}
			if(source == button_2){
				new ModifyUsers();
			}
			if(source == button_3){
				new ReadUsersInfo();
			}
			if(source == button_4){
				new ReadOrder();
			}
		}
	}
}
