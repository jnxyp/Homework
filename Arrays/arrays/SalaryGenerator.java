package arrays;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * <p>
 * <b>Salary Generater<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-28
 */
public class SalaryGenerator extends JFrame implements ActionListener {

  private final double MIN_SALARY = 8.00;
  private final double MAX_SALARY = 15.00;

  private JButton     btnAscend, btnDescend, btnGenerate, btnSearch;
  private JLabel      lblTitle;
  private JPanel      buttonPanel, panel;
  private JScrollPane txtSclPane;
  private JTextArea   txtArea;

  private Double[] salaries;

  private DecimalFormat decFmt = new DecimalFormat("#,##0.00$");

  public static void main(String[] args) {

    new SalaryGenerator();
  }

  public SalaryGenerator() {
    // Set frame properties
    lblTitle = new JLabel("SALARY GENERATOR");
    lblTitle.setPreferredSize(new Dimension(300, 30));
    lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    // Create button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 1, 5, 5));
    // Determine button size
    Dimension d = new Dimension(100, 30);
    // Create buttons
    btnGenerate = new JButton("GENERATE");
    btnGenerate.setPreferredSize(d);
    btnGenerate.addActionListener(this);
    btnAscend = new JButton("ASCEND");
    btnAscend.setPreferredSize(d);
    btnAscend.setEnabled(false);
    btnAscend.addActionListener(this);
    btnDescend = new JButton("DESCEND");
    btnDescend.setPreferredSize(d);
    btnDescend.setEnabled(false);
    btnDescend.addActionListener(this);
    btnSearch = new JButton("SEARCH");
    btnSearch.setPreferredSize(d);
    btnSearch.setEnabled(false);
    btnSearch.addActionListener(this);
    // Add buttons
    buttonPanel.add(btnGenerate);
    buttonPanel.add(btnAscend);
    buttonPanel.add(btnDescend);
    buttonPanel.add(btnSearch);
    // Create text area and scroll pane
    txtSclPane = new JScrollPane(txtArea = new JTextArea(10, 16), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    txtArea.setFont(new Font("Consolas", Font.PLAIN, 12));
    txtArea.setEditable(false);
    // Add panels
    panel = new JPanel();
    panel.add(lblTitle);
    panel.add(txtSclPane);
    panel.add(buttonPanel);
    // Set visible
    setContentPane(panel);
    setTitle("Salary Generator");
    setSize(300, 270);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // GENERATE button
    if (e.getSource() == btnGenerate) {
      btnGenerate.setEnabled(false);
      boolean isInvalid;
      String input;
      int num = 0;
      // Ask user for number of employees
      do {
        isInvalid = false;
        input = JOptionPane.showInputDialog(null, "Enter number of employees:", "Salary Generator",
            JOptionPane.QUESTION_MESSAGE);
        try {
          num = Integer.parseInt(input);
        }
        catch (NumberFormatException err) {
          isInvalid = true;
        }
        if (num <= 0) {
          isInvalid = true;
        }
        if (isInvalid) {
          JOptionPane.showMessageDialog(null, "Please enter a positive integer!");
        }
      } while (isInvalid);
      generateSalaries(num);
      printSalaries();
      btnAscend.setEnabled(true);
      btnDescend.setEnabled(true);
    }
    else if (e.getSource() == btnAscend) {
      Arrays.sort(salaries);
      printSalaries();
    }
    else if (e.getSource() == btnDescend) {
      Arrays.sort(salaries, Collections.reverseOrder());
      printSalaries();
    }
  }

  /**
   * This method can generate random salaries for each employees.
   * 
   * @param num
   *          number of employees.
   */
  private void generateSalaries(int num) {
    salaries = new Double[num];
    int max = (int) (MAX_SALARY * 100);
    int min = (int) (MIN_SALARY * 100);
    for (int i = 0; i < salaries.length; i++) {
      salaries[i] = ((int) (Math.random() * (max - min + 1)) + min) / 100.0;
    }
  }

  /**
   * This method can print all salaries on text area.
   */
  private void printSalaries() {
    txtArea.setText(null);
    int count = 1;
    txtArea.append(String.format("%-8s%8s%n", "RANK", "WAGE"));
    for (double salary : salaries) {
      txtArea.append(String.format("%-8s%8s%n", count, decFmt.format(salary)));
      count++;
    }
  }
}
