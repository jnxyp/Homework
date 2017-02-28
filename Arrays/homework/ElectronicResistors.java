package homework;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <p>
 * <b>Electronic Resistors<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-24
 */

public class ElectronicResistors extends JFrame implements ActionListener {

  private JButton     btnOK, btnClear;
  private JLabel      lblOutput;
  private JComboBox[] cboColours;

  public static void main(String[] args) {

    new ElectronicResistors();
  }

  public ElectronicResistors() {

    JLabel lblTitle = new JLabel();
    lblTitle.setText("ELECTRONIC RESISTORS");
    lblTitle.setFont(new Font("Britannic Bold", Font.BOLD, 24));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setPreferredSize(new Dimension(300, 20));

    JLabel lblInstructions = new JLabel();
    lblInstructions.setText("Enter the resistor's colour code:");
    lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
    lblInstructions.setPreferredSize(new Dimension(250, 20));

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.insets = new Insets(5, 5, 5, 5);
    gc.gridwidth = 3;
    panel.add(lblTitle, gc);

    gc.gridy = 1;
    gc.gridwidth = 2;
    panel.add(lblInstructions, gc);

    JLabel[] lblColours = new JLabel[3];
    cboColours = new JComboBox[3];

    String[] colours = { "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "Grey", "White" };
    // not using this...
    // int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    int y = 2;
    gc.gridwidth = 1;
    // Generate Combo Boxes and their Labels
    for (int i = 0; i < lblColours.length; i++) {
      lblColours[i] = new JLabel();
      lblColours[i].setText("Colour #" + (i + 1) + ":");
      lblColours[i].setPreferredSize(new Dimension(125, 20));
      lblColours[i].setHorizontalAlignment(SwingConstants.CENTER);
      gc.gridx = 0;
      gc.gridy = y;
      panel.add(lblColours[i], gc);
      // Add elements of combo box
      cboColours[i] = new JComboBox<String>(colours);
      cboColours[i].setPreferredSize(new Dimension(125, 20));
      gc.gridx = 1;
      panel.add(cboColours[i], gc);
      y++;
    }
    // Initialize Picture Label
    JLabel lblImage = new JLabel(new ImageIcon(getClass().getResource("resistor.png")));
    gc.gridx = 2;
    gc.gridy = 1;
    gc.gridheight = 6;
    panel.add(lblImage, gc);
    // Initialize Buttons
    // OK Button
    btnOK = new JButton("OK");
    btnOK.setPreferredSize(new Dimension(125, 30));
    btnOK.setFocusable(false);
    btnOK.addActionListener(this);

    gc.gridx = 0;
    gc.gridy = 5;
    gc.gridheight = 1;
    panel.add(btnOK, gc);
    // CLEAR Button
    btnClear = new JButton("CLEAR");
    btnClear.setPreferredSize(new Dimension(125, 30));
    btnClear.setFocusable(false);
    btnClear.addActionListener(this);

    gc.gridx = 1;
    panel.add(btnClear, gc);
    // Output Label
    lblOutput = new JLabel();
    lblOutput.setPreferredSize(new Dimension(250, 30));
    lblOutput.setHorizontalAlignment(SwingConstants.CENTER);

    gc.gridx = 0;
    gc.gridy = 6;
    gc.gridwidth = 2;
    panel.add(lblOutput, gc);
    // Set Visible
    setContentPane(panel);
    setTitle("Resistors");
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // OK Button
    if (e.getSource() == btnOK) {
      lblOutput.setText("Your resisters has " + calcValue() + "¦¸ (ohms)!");
    }
    // CLEAR Button
    else if (e.getSource() == btnClear) {
      lblOutput.setText(null);
      for (JComboBox<String> x : cboColours) {
        x.setSelectedIndex(0);
      }
    }
  }

  /**
   * This method can calculate resister's total ohms.
   * 
   * @return Total ohms.
   */
  private double calcValue() {
    int temp = Integer.parseInt(cboColours[0].getSelectedIndex() + "" + cboColours[1].getSelectedIndex());
    return (temp * Math.pow(10, cboColours[2].getSelectedIndex()));
  }
}
