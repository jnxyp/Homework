package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 * <p>
 * <b>Password Program</b> according to 10_Layout_Exercises.pdf
 * </p>
 * 
 * @author Xin (jn_xyp)
 * @version 2017-02-06
 */
public class PasswordProgram extends JFrame {
	// Password
	private final String CORRECT_PASSWORD = "Helloworld";
	// Declare components
	private JPanel mainPanel, pwPanel, buttonPanel;
	private JLabel noticeLbl;
	private JPasswordField pwFld;
	private JButton okBtn, clearBtn, exitBtn;
	// Listeners
	private ActionListener btnListener = new ButtonListener();

	public PasswordProgram() {
		// Set frame properties
		setTitle("Password Program");
		setSize(300, 130);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Initialize containers
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		pwPanel = new JPanel();
		pwPanel.setLayout(new BoxLayout(pwPanel, BoxLayout.X_AXIS));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3, 20, 0));
		// Initialize components
		noticeLbl = new JLabel("Enter your password:");
		pwFld = new JPasswordField(10);
		okBtn = new JButton("OK");
		okBtn.setMnemonic(KeyEvent.VK_O);
		okBtn.addActionListener(btnListener);
		clearBtn = new JButton("CLEAR");
		clearBtn.setMnemonic(KeyEvent.VK_C);
		clearBtn.addActionListener(btnListener);
		exitBtn = new JButton("EXIT");
		exitBtn.setMnemonic(KeyEvent.VK_X);
		exitBtn.addActionListener(btnListener);
		// Add components into containers
		pwPanel.add(noticeLbl);
		pwPanel.add(Box.createHorizontalStrut(20));
		pwPanel.add(pwFld);
		buttonPanel.add(okBtn);
		buttonPanel.add(clearBtn);
		buttonPanel.add(exitBtn);
		mainPanel.add(pwPanel);
		mainPanel.add(buttonPanel);
		// Set visible
		setContentPane(mainPanel);
		setVisible(true);
	}

	/**
	 * The action listener for buttons
	 * 
	 * @author Xin
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// OK button actions
			if (e.getSource() == okBtn) {
				if (String.valueOf(pwFld.getPassword()).equals(CORRECT_PASSWORD)) {
					System.exit(0);
				}
				else {
					incorrectPwNotice();
				}
			}
			// CLEAR button actions
			else if (e.getSource() == clearBtn) {
				pwFld.setText(null);
			}
			// EXIT button actions
			else if (e.getSource() == exitBtn) {
				System.exit(0);
			}
		}
	}

	/**
	 * This method will show a confirm option pane when people input incorrect
	 * password.
	 */
	private void incorrectPwNotice() {
		JOptionPane.showConfirmDialog(this, "Incorrect Password!", "Error", JOptionPane.CLOSED_OPTION,
				JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		new PasswordProgram();
	}
}
