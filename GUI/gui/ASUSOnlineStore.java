package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ASUSOnlineStore extends JFrame {
	// Static instance
	static ASUSOnlineStore newInstance;
	// Prices
	final double ORIGINAL_PRICE = 799.99;
	final double BATTERY_PRICE = 179.99;
	final double OFFICE_PRICE = 119;
	final double MCAFEE_PRICE = 79;
	final double WARRANTY_PRICE = 85;
	// GUI components
	JPanel mainPanel, midPanel, btnPanel, chkBoxPanel;
	JCheckBox batteryCBox, officeCBox, mcAfeeCBox, warrantyCBox;
	JLabel topPicLbl, leftPicLbl, rightNoticeLbl, totalLbl;
	JTextField totalTFld;
	JButton checkoutBtn, clearBtn;
	ImageIcon topPic, leftPic, checkoutPic;
	// Other variables
	DecimalFormat decfmt = new DecimalFormat("$#,###.00");
	Font themeFont1 = new Font("Arial", Font.BOLD, 12);
	Font themefont2 = new Font("Arial", Font.PLAIN, 14);
	Dimension frameSize = new Dimension(500, 400);
	Dimension btnPanelSize = new Dimension(300, 80);
	// Public listeners
	ButtonListener btnListener = new ButtonListener();
	CheckBoxListener chkBoxListener = new CheckBoxListener();

	public ASUSOnlineStore() {
		// Set frame properties
		setTitle("ASUS Online Store");
		setResizable(false);
		setSize(frameSize);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Load resources
		topPic = new ImageIcon(getClass().getResource("ASUSOnlineStore-ASUS.png"));
		leftPic = new ImageIcon(getClass().getResource("ASUSOnlineStore-Laptop.png"));
		checkoutPic = new ImageIcon(getClass().getResource("ASUSOnlineStore-Earth.png"));
		// Initialize container
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		midPanel = new JPanel(new FlowLayout());
		midPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		btnPanel = new JPanel(new GridLayout(2, 2, 20, 15));
		btnPanel.setMaximumSize(btnPanelSize);
		btnPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		chkBoxPanel = new JPanel();
		chkBoxPanel.setLayout(new BoxLayout(chkBoxPanel, BoxLayout.Y_AXIS));
		// Initialize components and set properties
		// Check boxes
		batteryCBox = new JCheckBox(String.format("9-Cell Lithium-Ion Battery (%s)", decfmt.format(BATTERY_PRICE)));
		batteryCBox.addItemListener(chkBoxListener);
		officeCBox = new JCheckBox(String.format("Microsoft Office 365 (%s)", decfmt.format(OFFICE_PRICE)));
		officeCBox.addItemListener(chkBoxListener);
		mcAfeeCBox = new JCheckBox(String.format("McAfee Security Center (%s)", decfmt.format(MCAFEE_PRICE)));
		mcAfeeCBox.addItemListener(chkBoxListener);
		warrantyCBox = new JCheckBox(String.format("2-Year Extended Warranty (%s)", decfmt.format(WARRANTY_PRICE)));
		warrantyCBox.addItemListener(chkBoxListener);
		// Labels
		topPicLbl = new JLabel(topPic);
		topPicLbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		leftPicLbl = new JLabel(leftPic);
		rightNoticeLbl = new JLabel("Select from the following accessories:");
		rightNoticeLbl.setFont(themeFont1);
		totalLbl = new JLabel("GRAND TOTAL:", JLabel.CENTER);
		totalLbl.setFont(themefont2);
		// Buttons
		checkoutBtn = new JButton("CHECKOUT");
		checkoutBtn.addActionListener(btnListener);
		clearBtn = new JButton("CLEAR");
		clearBtn.addActionListener(btnListener);
		// Text field
		totalTFld = new JTextField(decfmt.format(ORIGINAL_PRICE));
		totalTFld.setHorizontalAlignment(JTextField.CENTER);
		totalTFld.setEditable(false);
		totalTFld.setBackground(Color.WHITE);
		totalTFld.setFont(themeFont1);
		// Add components
		chkBoxPanel.add(rightNoticeLbl);
		chkBoxPanel.add(batteryCBox);
		chkBoxPanel.add(officeCBox);
		chkBoxPanel.add(mcAfeeCBox);
		chkBoxPanel.add(warrantyCBox);

		btnPanel.add(totalLbl);
		btnPanel.add(totalTFld);
		btnPanel.add(checkoutBtn);
		btnPanel.add(clearBtn);

		midPanel.add(leftPicLbl);
		midPanel.add(chkBoxPanel);

		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(topPicLbl);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(midPanel);
		mainPanel.add(Box.createVerticalGlue());
		mainPanel.add(btnPanel);
		mainPanel.add(Box.createVerticalGlue());

		setContentPane(mainPanel);
		addWindowListener(new FrameListener());
		setVisible(true);
	}

	/**
	 * This method will check if the check boxes was clicked and update the price
	 * in the text field
	 */
	public void updatePrice() {
		double currentPrice = ORIGINAL_PRICE;
		if (batteryCBox.isSelected()) {
			currentPrice += BATTERY_PRICE;
		}
		if (officeCBox.isSelected()) {
			currentPrice += OFFICE_PRICE;
		}
		if (mcAfeeCBox.isSelected()) {
			currentPrice += MCAFEE_PRICE;
		}
		if (warrantyCBox.isSelected()) {
			currentPrice += WARRANTY_PRICE;
		}
		totalTFld.setText(decfmt.format(currentPrice));
		// setVisible(true);
	}

	public class FrameListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int returnValue = JOptionPane.showConfirmDialog(newInstance, "Are you sure you want to exit?",
					"ASUS Online Store", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (returnValue == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	public class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Checkout button actions
			if (e.getActionCommand().equals(checkoutBtn.getActionCommand())) {
				int returnValue = JOptionPane.showConfirmDialog(newInstance,
						String.format("Your grand total is %s!%nIs this order correct?", totalTFld.getText()), "Checkout",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, checkoutPic);
				if (returnValue == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			// Clear button actions
			else if (e.getActionCommand().equals(clearBtn.getActionCommand())) {
				batteryCBox.setSelected(false);
				officeCBox.setSelected(false);
				mcAfeeCBox.setSelected(false);
				warrantyCBox.setSelected(false);
				updatePrice();
			}
		}
	}

	/**
	 * This class is hte listener for the check box select event
	 * 
	 * @author Xin (Jn_xyp)
	 *
	 */
	public class CheckBoxListener implements ItemListener {
		@Override
		/**
		 * This method will be called when people select or !select check box
		 */
		public void itemStateChanged(ItemEvent e) {
			updatePrice();
		}
	}

	public static void main(String[] args) {
		newInstance = new ASUSOnlineStore();
	}
}
