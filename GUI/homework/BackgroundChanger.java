package homework;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is an exercise from GUI 5_Event_Handling_Exercises
 * 
 * @author Xin (Jn_xyp)
 * @version 2017-01-28
 */
public class BackgroundChanger {
	public static void main(String[] args) {
		new BCFrame();
	}
}

class BCFrame extends JFrame {
	JPanel jPanel;
	JButton jButton;

	public BCFrame() {
		// Set properties
		setTitle("Background Changer");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		// Initialize components
		jPanel = new JPanel(null);
		jPanel.setBackground(Color.RED);
		jButton = new JButton("CHANGE");
		jButton.setMnemonic(KeyEvent.VK_C);
		jPanel.add(jButton);
		jButton.setSize(100, 35);
		// Set location at center
		jButton.setLocation(getWidth() / 2 - jButton.getWidth() / 2, getHeight() / 2 - jButton.getHeight());
		jButton.addActionListener(new BCListener());
		setContentPane(jPanel);
		setVisible(true);
	}
}

class BCListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel panel = (JPanel) ((JButton) e.getSource()).getParent();
		if (panel.getBackground().equals(Color.RED)) {
			panel.setBackground(Color.BLUE);
		} else if (panel.getBackground().equals(Color.BLUE)) {
			panel.setBackground(Color.RED);
		}
	}
}
