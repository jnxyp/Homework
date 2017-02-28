package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>
 * <b>Binary Converter<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class BinaryConverter extends JFrame {
  // GUI Components
  private JPanel     mainPanel;
  private JLabel     titleLbl, leftPicLbl, binaryLbl, decimalLbl;
  private JTextField binaryTFld, decimalTFld;
  private JButton    convertBtn, clearBtn;
  // GUI Variables
  private Font               titleF  = new Font("Arial", Font.BOLD, 36);
  private GridBagConstraints gBC     = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
      GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 5, 5);
  private ImageIcon          leftPic = new ImageIcon(getClass().getResource("BinaryConverter-Binary.png"));

  public BinaryConverter() {
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Binary Converter");
    setSize(400, 250);
    // Initialize and add components
    mainPanel = new JPanel(new GridBagLayout());

    titleLbl = new JLabel("Binary Convert");
    titleLbl.setFont(titleF);
    titleLbl.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 0, 3, 1, 1, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(titleLbl, gBC);

    leftPicLbl = new JLabel(leftPic);
    gBCSetting(0, 1, 1, 3, 0.33, 0.75, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(leftPicLbl, gBC);

    binaryLbl = new JLabel("Binary number");
    gBCSetting(1, 1, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(binaryLbl, gBC);

    binaryTFld = new JTextField(10);
    binaryTFld.setHorizontalAlignment(JTextField.CENTER);
    gBCSetting(2, 1, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(binaryTFld, gBC);

    convertBtn = new JButton("CONVERT");
    convertBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isValid = true;
        if (!binaryTFld.getText().isEmpty()) {
          binaryTFld.setBackground(Color.WHITE);
          decimalTFld.setBackground(Color.WHITE);
          String text = binaryTFld.getText();
          for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != '0' && text.charAt(i) != '1') {
              isValid = false;
            }
          }
          if (isValid) {
            binaryTFld.setBackground(Color.WHITE);
            decimalTFld.setText(String.valueOf(toDecimal(text)));
          }
          else {
            binaryTFld.setBackground(Color.RED);
            binaryTFld.requestFocus();
            binaryTFld.selectAll();
            JOptionPane.showConfirmDialog(null, "Please enter a valid binary number!", "Error",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
          }
        }
        else if (!decimalTFld.getText().isEmpty()) {
          binaryTFld.setBackground(Color.WHITE);
          decimalTFld.setBackground(Color.WHITE);
          int decimal;
          try {
            decimal = Integer.parseInt(decimalTFld.getText());
            binaryTFld.setText(toBinary(decimal));
          }
          catch (NumberFormatException err) {
            decimalTFld.setBackground(Color.RED);
            decimalTFld.requestFocus();
            decimalTFld.selectAll();
            JOptionPane.showConfirmDialog(null, "Please enter a valid decimal number!", "Error",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
          }
        }
        else {
          binaryTFld.setBackground(Color.RED);
          decimalTFld.setBackground(Color.RED);
          JOptionPane.showConfirmDialog(null, "Please enter a number in one of the text fields!", "Error",
              JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

      }
    });
    gBCSetting(1, 2, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(convertBtn, gBC);

    clearBtn = new JButton("CLEAR");
    clearBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        binaryTFld.setText(null);
        binaryTFld.setBackground(Color.WHITE);
        decimalTFld.setText(null);
        decimalTFld.setBackground(Color.WHITE);
        // Let binary text field get focus
        binaryTFld.requestFocus();
      }
    });
    gBCSetting(2, 2, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(clearBtn, gBC);

    decimalLbl = new JLabel("Decimal number:");
    gBCSetting(1, 3, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(decimalLbl, gBC);

    decimalTFld = new JTextField(10);
    decimalTFld.setHorizontalAlignment(JTextField.CENTER);
    gBCSetting(2, 3, 1, 1, 0.33, 0.25, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(decimalTFld, gBC);

    // Set visible
    setContentPane(mainPanel);
    setVisible(true);

  }

  private static int toDecimal(String binary) {
    int decimal = 0;
    for (int i = binary.length() - 1; i >= 0; i--) {
      decimal += Integer.parseInt("" + binary.charAt(i)) * Math.pow(2, binary.length() - i - 1);
    }
    return decimal;
  }

  private static String toBinary(int decimal) {
    String binary = "";
    while (decimal > 0) {
      binary = decimal % 2 + binary;
      decimal /= 2;
    }
    return binary;
  }

  // This method can set the properties of Grid Bag Constraint
  private void gBCSetting(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill,
      int anchor) {
    gBC.gridx = gridx;
    gBC.gridy = gridy;
    gBC.gridwidth = gridwidth;
    gBC.gridheight = gridheight;
    gBC.weightx = weightx;
    gBC.weighty = weighty;
    gBC.fill = fill;
    gBC.anchor = anchor;
  }

  public static void main(String[] args) {
    new BinaryConverter();
  }

}
