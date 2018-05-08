import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

public class UserInterface extends JFrame{

	private JFrame frame;
	String s;
	JButton button, button_1, button_2, button_3;
	
	public UserInterface(String ss) {
		s = ss;
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setFont(new Font("ºÚÌå", Font.PLAIN, 14));
		frame.setTitle("\u7528\u6237\u767B\u5F55");
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 386, 319);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" ------------   \u7528 \u6237 \u767B \u9646 \u754C \u9762   -------------");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ Light", Font.BOLD, 15));
		lblNewLabel.setBounds(36, 13, 318, 27);
		frame.getContentPane().add(lblNewLabel);
		
		button = new JButton("\u623F\u95F4\u67E5\u8BE2");
		button.setFont(new Font("ºÚÌå", Font.PLAIN, 14));
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(SystemColor.desktop);
		button.setBounds(64, 81, 93, 54);
		frame.getContentPane().add(button);
		
		button_1 = new JButton("\u9884\u8BA2\u623F\u95F4");
		button_1.setFont(new Font("ºÚÌå", Font.PLAIN, 14));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setForeground(SystemColor.desktop);
		button_1.setBounds(215, 81, 93, 54);
		frame.getContentPane().add(button_1);
		
		button_2 = new JButton("\u6211\u7684\u8BA2\u5355");
		button_2.setFont(new Font("ºÚÌå", Font.PLAIN, 14));
		button_2.setForeground(SystemColor.desktop);
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(64, 178, 93, 54);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("\u67E5\u770B\u623F\u95F4");
		button_3.setFont(new Font("ºÚÌå", Font.PLAIN, 14));
		button_3.setForeground(SystemColor.desktop);
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(215, 178, 93, 54);
		frame.getContentPane().add(button_3);
		ButtonListener listen = new ButtonListener();
		button.addActionListener(listen);
		button_1.addActionListener(listen);
		button_2.addActionListener(listen);
		button_3.addActionListener(listen);
		frame.setVisible(true);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			JButton source = (JButton)e.getSource();
			if(source == button)
				new USearchRoom();
			if(source == button_1)
			    new ReserveRoom(s);
			if(source == button_2)
				new SearchOrderNews(s);
			if(source == button_3)
				new ReadAllRoom();
		}
	}
}
