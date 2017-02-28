package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is an exercise from GUI 5_Event_Handling_Exercises
 * 
 * @author Xin (Jn_xyp)
 * @version 2017-01-27
 */
public class BrainTeaser extends JFrame implements ActionListener {
	private JPanel jPanel;

	public BrainTeaser() {
		// Set frame properties
		setTitle("Brain Teaser");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Make components
		jPanel = new JPanel(new FlowLayout());
		JLabel jLabel1 = new JLabel("How many kinds of people are there in the world?");
		JButton jButton = new JButton("ANSWER");
		jLabel1.setFont(new Font("Arial", 0, 14));
		jButton.setMnemonic(KeyEvent.VK_A);
		// Add listener
		jButton.addActionListener(this);
		// Add components
		jPanel.add(jLabel1);
		jPanel.add(jButton);
		setContentPane(jPanel);
		setSize(380, 130);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BrainTeaser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ANSWER")) { // if ANSWER button was clicked
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				btn.setText("QUIT");
				btn.setMnemonic(KeyEvent.VK_Q);
				JLabel jLabel2 = new JLabel("10 - Those who know binary and those who don't!");
				jLabel2.setFont(new Font("Arial", Font.BOLD, 14));
				// btn.getRootPane().add(jLabel2);
				jPanel.add(jLabel2);
			}
		} else if (e.getActionCommand().equals("QUIT")) { // if QUIT button was
																											// clicked
			System.exit(0);
		}
	}
}
