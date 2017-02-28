package homework;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RBCLogin {
	public static void main(String[] args) {
		new RBCLFrame();
	}
}

class RBCLFrame extends JFrame implements ActionListener {
	JPanel mainPanel;
	JButton signInBtn, exitBtn;
	JLabel usrLbl, pwLbl, picLbl;
	JTextField usrFld;
	JPasswordField pwFld;
	Dimension frameSize;

	public RBCLFrame() {
		// Set properties
		setTitle("RBC Royal Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		frameSize = new Dimension(300, 200);
		setSize(frameSize);
		// Load images
		ImageIcon rbc = new ImageIcon(getClass().getResource("RBCLogin-RBC.gif"));
		// Generate components
		mainPanel = new JPanel(null); // Close layout manager
		mainPanel.setSize(this.getSize());
		signInBtn = new JButton("SIGN IN");
		signInBtn.addActionListener(this);
		exitBtn = new JButton("EXIT");
		exitBtn.addActionListener(this);
		usrLbl = new JLabel("Username (Card Number):");
		pwLbl = new JLabel("Password:");
		picLbl = new JLabel(rbc);
		usrFld = new JTextField(15);
		pwFld = new JPasswordField();
		// Add components
		mainPanel.add(signInBtn);
		mainPanel.add(exitBtn);
		mainPanel.add(usrLbl);
		mainPanel.add(pwLbl);
		mainPanel.add(picLbl);
		mainPanel.add(usrFld);
		mainPanel.add(pwFld);
		// Set location and size
		// Okay, it's also not a good idea to turn off layout
		signInBtn.setBounds(toAbs(0.42, 0.6, 0.25, 0.15));
		exitBtn.setBounds(toAbs(0.69, 0.6, 0.25, 0.15));
		usrLbl.setBounds(toAbs(0.42, 0.05, 0.5, 0.1));
		usrFld.setBounds(toAbs(0.42, 0.15, 0.5, 0.1));
		pwLbl.setBounds(toAbs(0.42, 0.3, 0.5, 0.1));
		pwFld.setBounds(toAbs(0.42, 0.4, 0.5, 0.1));
		picLbl.setBounds(toAbs(0, 0, 0.4, 0.85));
		// Display
		setContentPane(mainPanel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ImageIcon lock = new ImageIcon(getClass().getResource("RBCLogin-lock.gif"));
		ImageIcon unlock = new ImageIcon(getClass().getResource("RBCLogin-unlock.gif"));
		// if SIGN IN button is clicked
		if (e.getActionCommand().equals("SIGN IN")) {
			if (new String(pwFld.getPassword()).length() != 0 && usrFld.getText().length() == 15) {
				JOptionPane.showConfirmDialog(this, "You have successfully logged in!", "RBC Royal Bank",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, unlock);
			} else {
				JOptionPane.showConfirmDialog(this, "The username and/or password is incorrect!\nPlease try again!",
						"RBC Royal Bank", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, lock);
			}
		} else if (e.getActionCommand().equals("EXIT")) {
			JOptionPane.showConfirmDialog(this, "Thank you for choosing Royal Bank!", "RBC Royal Bank",
					JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	/**
	 * This method can convert an relative coordinate (in percentage) area to an
	 * abstract area of its parent container.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return An rectangle that include the abstract coordinates
	 */
	public Rectangle toAbs(double x, double y, double width, double height) {
		int parentWidth, parentHeight;
		Rectangle size;
		parentWidth = this.getWidth();
		parentHeight = this.getHeight();
		size = new Rectangle((int) (parentWidth * x), (int) (parentHeight * y), (int) (parentWidth * width),
				(int) (parentHeight * height));
		return size;
	}
}