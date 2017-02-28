package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is an exercise from GUI 5_Event_Handling_Exercises
 * 
 * @author Xin (Jn_xyp)
 * @version 2017-01-27
 */
public class Clicker extends JFrame implements ActionListener {
	JPanel jPanel;
	JButton jButton;
	int clickCount;

	public Clicker() {
		// Set frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Clickety Click");
		setSize(300, 200);
		// Initialize components and variables
		jPanel = new JPanel(null);
		jButton = new JButton(String.format("You have clicked %d times!", clickCount));
		clickCount = 0;
		jButton.setSize(200, 35);
		// How can I set the location to center without using layout??????????
		jButton.setLocation(getWidth() / 2 - jButton.getWidth() / 2, getHeight() / 2 - jButton.getHeight());
		jPanel.add(jButton);
		jButton.addActionListener(this);
		setContentPane(jPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (clickCount < 10) {
			clickCount++;
			jButton.setText(String.format("You have clicked %d times!", clickCount));
		} else {
			JOptionPane.showConfirmDialog(null, "You are all out of clicks!", "Game Over!", JOptionPane.CLOSED_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new Clicker();
	}
}
