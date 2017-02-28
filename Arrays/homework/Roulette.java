package homework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * <p>
 * <b>Roulette<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-24
 */
public class Roulette extends JFrame implements ActionListener {

  private JTextArea   txtOutput;
  private JButton     btnSpin, btnClear, btnExit;
  private JLabel[]    lblResults;
  private JScrollPane sclPaneResults;

  private final int[]          BLACK_NUMS    = { 0, 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 29, 31, 33, 35,
      37 };
  private final int[]          RED_NUMS      = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 28, 30, 32, 34,
      36 };
  private final String[]       strResults    = new String[] { "ODD", "EVEN", "ZERO", "1st 12", "2nd 12", "3rd 12",
      "1-18", "19-36", "RED", "BLACK" };
  private int[]                statData      = new int[strResults.length];
  private int                  numCount      = 0;
  static private DecimalFormat decimalFormat = new DecimalFormat("##0.0%");

  public static void main(String[] args) {

    new Roulette();
  }

  public Roulette() {
    // Initialize Text Area
    txtOutput = new JTextArea(30, 33);
    txtOutput.setEditable(false);
    txtOutput.setBackground(new Color(0, 115, 0));
    txtOutput.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    txtOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
    txtOutput.setForeground(Color.LIGHT_GRAY);
    txtOutput.setText(String.format("%n%-2s%-10s%-10s%-10s%n", "", "ODD", "EVEN", "ZERO"));
    // Initialize Buttons
    // SPIN button
    btnSpin = new JButton("SPIN");
    btnSpin.setPreferredSize(new Dimension(80, 30));
    btnSpin.setFocusable(false);
    btnSpin.addActionListener(this);
    // CLEAR button
    btnClear = new JButton("CLEAR");
    btnClear.setPreferredSize(new Dimension(80, 30));
    btnClear.setFocusable(false);
    btnClear.addActionListener(this);
    // EXIT button
    btnExit = new JButton("EXIT");
    btnExit.setPreferredSize(new Dimension(80, 30));
    btnExit.setFocusable(false);
    btnExit.addActionListener(this);
    // Initialize Main Panel
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    // Initialize Grid Bag Constraints
    GridBagConstraints gc = new GridBagConstraints();
    gc.insets = new Insets(5, 5, 5, 5);
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridheight = 10;
    // Add Picture
    panel.add(new JLabel(new ImageIcon(getClass().getResource("roulette.png"))), gc);
    // Add Text Area
    gc.gridx = 1;
    gc.gridy = 0;
    gc.gridwidth = 3;
    panel.add(sclPaneResults = new JScrollPane(txtOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gc);
    // Add Buttons
    gc.gridx = 1;
    gc.gridy = 10;
    gc.gridwidth = 1;
    gc.gridheight = 1;
    panel.add(btnSpin, gc);

    gc.gridx = 2;
    panel.add(btnClear, gc);

    gc.gridx = 3;
    panel.add(btnExit, gc);
    // Initialize JLabel[]
    lblResults = new JLabel[10];

    gc.gridx = 4;
    gc.gridheight = 1;

    int yPos = 0;
    // Generate Labels
    for (int i = 0; i < lblResults.length; i++) {
      lblResults[i] = new JLabel();
      // Create Titled Border
      lblResults[i].setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
          strResults[i], TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
          new Font("Arial", Font.BOLD, 12), Color.LIGHT_GRAY));
      lblResults[i].setHorizontalAlignment(SwingConstants.CENTER);
      lblResults[i].setForeground(Color.LIGHT_GRAY);
      lblResults[i].setPreferredSize(new Dimension(90, 40));
      gc.gridy = yPos;
      panel.add(lblResults[i], gc);
      yPos++;
    }
    // Display Frame
    setContentPane(panel);
    pack();
    setTitle("Roulette");
    setLocationRelativeTo(null);
    getRootPane().setDefaultButton(btnSpin);
    getContentPane().setBackground(new Color(0, 115, 0));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // SPIN button action
    if (e.getSource() == btnSpin) {
      addNum(rnd());
      JScrollBar jsb = sclPaneResults.getVerticalScrollBar();
      jsb.setValue(jsb.getMaximum());
    }
    // CLEAR button action
    else if (e.getSource() == btnClear) {
      clearAll();
    }
    // EXIT button action
    else if (e.getSource() == btnExit) {
      System.exit(0);
    }
  }

  /**
   * This method will clear all statistic data and text in labels and text area
   */
  private void clearAll() {
    numCount = 0;
    Arrays.fill(statData, 0);
    txtOutput.setText(String.format("%n%-2s%-10s%-10s%-10s%n", "", "ODD", "EVEN", "ZERO"));
    for (JLabel label : lblResults) {
      label.setText(null);
    }
  }

  /**
   * This method will add one number to the statistic data and display them
   * 
   * @param i
   *          the number you want to add
   */
  private void addNum(int i) {
    numCount++;
    // Statistic Data
    // ZERO
    if (i == 0 || i == 37) {
      statData[2]++;
      if (i == 0) {
        txtOutput.append(String.format("%-2s%-10s%-10s%-10s%n", "", "", "", "0"));
      }
      else {
        txtOutput.append(String.format("%-2s%-10s%-10s%-10s%n", "", "", "", "00"));
      }
    }
    // ODD
    else if (i % 2 != 0) {
      statData[0]++;
      txtOutput.append(String.format("%-2s%-10s%-10s%-10s%n", "", i, "", ""));
    }
    // EVEN
    else {
      statData[1]++;
      txtOutput.append(String.format("%-2s%-10s%-10s%-10s%n", "", "", i, ""));
    }
    // 1st 12
    if (i >= 1 && i <= 12) {
      statData[3]++;
    }
    // 2nd 12
    else if (i >= 13 && i <= 24) {
      statData[4]++;
    }
    // 3rd 12
    else if (i >= 25 && i <= 36) {
      statData[5]++;
    }
    // 1-18
    if (i >= 1 && i <= 18) {
      statData[6]++;
    }
    // 19-36
    else if (i >= 19 && i <= 36) {
      statData[7]++;
    }
    // RED
    for (int red : RED_NUMS) {
      if (i == red) {
        statData[8]++;
        break;
      }
    }
    // BLACK
    for (int black : BLACK_NUMS) {
      if (i == black) {
        statData[9]++;
        break;
      }
    }
    // Display values in labels
    for (int i1 = 0; i1 < statData.length; i1++) {
      lblResults[i1].setText(toPercent(statData[i1] * 1.0 / numCount));
    }
  }

  /**
   * This method can generate a random number [0,37]
   * 
   * @return the random number
   */
  private static int rnd() {
    return (int) (Math.random() * 38);
  }

  /**
   * This method can convert a double value 0.6666 to String "66.6%"
   * 
   * @param value
   *          the double value to convert
   * @return the percentage with one decimal
   */
  private static String toPercent(double value) {
    return decimalFormat.format(value);
  }

  /**
   * This method can convert a String "66.6%" to double value 0.666
   * 
   * @param str
   *          the percentage convert
   * @return the double value
   */
  private static double toValue(String str) {
    return Double.parseDouble(str.substring(0, str.length() - 1));
  }

}
