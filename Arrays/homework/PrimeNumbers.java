package homework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * <p>
 * <b>Prime Numbers<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-24
 */
public class PrimeNumbers extends JFrame implements ActionListener {
  // GUI Components
  private JTextField  txtRange;
  private JButton     btnGenerate, btnClear, btnExit;
  private JScrollPane jSPOutput;
  private JTextArea   jTAOutput;

  public static void main(String[] args) {
    new PrimeNumbers();
  }

  public PrimeNumbers() {
    // Initialize Components
    // Title Label
    JLabel lblTitle = new JLabel();
    lblTitle.setText("PRIME NUMBERS");
    lblTitle.setPreferredSize(new Dimension(400, 40));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitle.setFont(new Font("Britannic Bold", Font.PLAIN, 36));
    // Range Label
    JLabel lblRange = new JLabel();
    lblRange.setText("Enter the range of numbers:");
    lblRange.setPreferredSize(new Dimension(200, 30));
    lblRange.setHorizontalAlignment(SwingConstants.CENTER);
    // Range TFld
    txtRange = new JTextField(5);
    txtRange.setHorizontalAlignment(SwingConstants.CENTER);
    // GENERATE Button
    btnGenerate = new JButton("GENERATE");
    btnGenerate.setPreferredSize(new Dimension(100, 30));
    btnGenerate.addActionListener(this);
    // CLEAR Button
    btnClear = new JButton("CLEAR");
    btnClear.setPreferredSize(new Dimension(100, 30));
    btnClear.addActionListener(this);
    // EXIT Button
    btnExit = new JButton("EXIT");
    btnExit.setPreferredSize(new Dimension(100, 30));
    btnExit.addActionListener(this);
    // Output Scroll Pane and Text Area
    jTAOutput = new JTextArea(22, 30);
    jTAOutput.setEditable(false);
    jTAOutput.setLineWrap(true);
    jTAOutput.setWrapStyleWord(true);
    jTAOutput.setFont(new Font("Consolas", Font.PLAIN, 14));
    jSPOutput = new JScrollPane(jTAOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    // Add components to main panel
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
    panel.add(lblTitle);
    panel.add(Box.createHorizontalStrut(50));
    panel.add(lblRange);
    panel.add(txtRange);
    panel.add(Box.createHorizontalStrut(50));
    panel.add(btnGenerate);
    panel.add(btnClear);
    panel.add(btnExit);
    panel.add(Box.createRigidArea(new Dimension(400, 5)));
    panel.add(jSPOutput);
    // Set visible
    setContentPane(panel);
    setSize(400, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Sieve of Eratosthenes");
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // GENERATE button action
    if (e.getSource() == btnGenerate) {
      int[] primeNums = null;
      long t1 = 0, t2 = 0;
      try {
        t1 = System.currentTimeMillis();
        primeNums = generatePrimeNums(Integer.parseInt(txtRange.getText()));
        t2 = System.currentTimeMillis();
      }
      catch (NumberFormatException exception) {
        jTAOutput.setText("Invalid Input \"" + txtRange.getText() + "\"");
      }
      if (primeNums != null) {
        if (primeNums[0] == 0) {
          jTAOutput.setText("No prime number in this range");
        }
        else {
          String output = "";
          for (int prime : primeNums) {
            output += prime + " ";
          }
          jTAOutput.setText(output);
        }
        jTAOutput.append("\n\nTime Cost: " + (t2 - t1) + "ms");
      }
    }
    // CLEAR button action
    else if (e.getSource() == btnClear) {
      jTAOutput.setText(null);
      txtRange.setText(null);
      txtRange.requestFocus();
    }
    // EXIT button action
    else if (e.getSource() == btnExit) {
      System.exit(0);
    }
  }

  /**
   * This method can find out all prime numbers between (1,limit].
   * 
   * @param limit
   *          int value which is the upper limit of prime numbers.
   * @return an int[] includes all prime numbers, include the limit. If there's
   *         no prime number in this range, return 0.
   */
  private static int[] generatePrimeNums(int limit) {
    // If limit smaller or equals 1, return an empty int[]
    if (limit <= 1) {
      return new int[] { 0 };
    }
    int[] primeNums = new int[limit + 1];
    primeNums[0] = 2; // the first prime number
    int primeCount = 1;
    boolean isPrime;
    // Check each of numbers bigger than 2
    for (int num = 3; num <= limit; num++) {
      isPrime = true;
      // Divide it with each of prime numbers smaller than it
      for (int i = 0; i < primeCount; i++) {
        if (primeNums[i] == 0) {
          break;
        }
        if (num % primeNums[i] == 0) {
          isPrime = false;
          break;
        }
      }
      // If it's a prime number, add it to the prime number list
      if (isPrime) {
        primeNums[primeCount] = num;
        primeCount++;
      }
    }
    int[] result = new int[primeCount];
    for (int i = 0; i < primeCount; i++) {
      result[i] = primeNums[i];
    }
    return result;
  }
}
