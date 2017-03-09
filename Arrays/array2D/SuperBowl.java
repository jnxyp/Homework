package array2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * <p>
 * <b>Super Bowl</b> <br />
 * Part of 2D Array exercise.
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-03-06
 */

public class SuperBowl extends JFrame implements ActionListener {
  private JButton     btnSimulate, btnClear, btnExit;
  private JLabel      lblMessage;
  private JScrollPane jScrollPane;
  private JTextArea   t;

  private String  team1 = "Seahawks", team2 = "Patriots";
  private int[][] score = new int[2][6];

  public SuperBowl() {
    // Picture label on left
    JLabel lblSeahawks = new JLabel(new ImageIcon(getClass().getResource("seahawks.png")));
    lblSeahawks.setPreferredSize(new Dimension(100, 75));
    lblSeahawks.setHorizontalAlignment(SwingConstants.CENTER);
    // Text Area in the middle
    t = new JTextArea(4, 36);
    t.setFont(new Font("Consolas", Font.PLAIN, 12));
    t.setBackground(new Color(238, 238, 238));
    t.setEditable(false);
    t.setBorder(null);
    // Picture label or right
    JLabel lblPatriots = new JLabel(new ImageIcon(getClass().getResource("patriots.png")));
    lblPatriots.setPreferredSize(new Dimension(100, 75));
    lblPatriots.setHorizontalAlignment(SwingConstants.CENTER);
    // Simulate button
    btnSimulate = new JButton("SIMULATE");
    btnSimulate.setPreferredSize(new Dimension(100, 30));
    btnSimulate.addActionListener(this);
    // Clear button
    btnClear = new JButton("CLEAR");
    btnClear.setPreferredSize(new Dimension(100, 30));
    btnClear.addActionListener(this);
    // Exit button
    btnExit = new JButton("EXIT");
    btnExit.setPreferredSize(new Dimension(100, 30));
    btnExit.addActionListener(this);
    // Label on bottom
    lblMessage = new JLabel("Click SIMULATE to begin!");
    lblMessage.setPreferredSize(new Dimension(500, 30));
    lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
    // Set layout and add components
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panel.add(lblSeahawks);
    panel.add(Box.createHorizontalStrut(10));
    jScrollPane = new JScrollPane(t, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    // jScrollPane.setBorder(null);
    panel.add(jScrollPane);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(lblPatriots);
    panel.add(btnSimulate);
    panel.add(btnClear);
    panel.add(btnExit);
    panel.add(lblMessage);
    // Set Visible
    setContentPane(panel);
    setSize(650, 200);
    setTitle("Super Bowl XLVII");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
    printScore();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // SIMULATE Button
    if (e.getSource() == btnSimulate) {
      clearScore();
      calcScore();
      printScore();
    }
    // CLEAR Button
    else if (e.getSource() == btnClear) {
      clearScore();
      printScore();
      lblMessage.setText("Click SIMULATE to begin!");
    }
    // EXIT Button
    else if (e.getSource() == btnExit) {
      System.exit(0);
    }
  }

  /**
   * This method will clean all the data in 2D array.
   */
  private void clearScore() {
    Arrays.fill(score[0], 0);
    Arrays.fill(score[1], 0);
  }

  /**
   * This method will generate scores and calculate the result.
   */
  private void calcScore() {
    for (int i = 0; i < score[0].length - 1; i++) {
      score[0][i] = rnd();
      score[0][5] += score[0][i];
      score[1][i] = rnd();
      score[1][5] += score[1][i];
      if (score[0][5] == score[1][5]) {
        score[0][4] = rnd();
        score[0][5] += score[0][4];
        score[1][4] = rnd();
        score[1][5] += score[1][4];
      }
      // Display notice
      if (score[0][5] > score[1][5]) {
        lblMessage.setText("The " + team1 + " are Super Bowl champions!");
      }
      else if (score[0][5] < score[1][5]) {
        lblMessage.setText("The " + team2 + " are Super Bowl champions!");
      }
      else {
        lblMessage.setText("It's a tie!");
      }

    }
  }

  /**
   * This metohd will print the data from 2D array to the text area.
   */
  private void printScore() {
    t.setText(String.format("%-8s%4s%4s%4s%4s%4s%8s%n", "", "1", "2", "3", "4", "OT", "FINAL"));
    t.append(String.format("%-8s%4s%4s%4s%4s%4s%8s%n", team1, score[0][0], score[0][1], score[0][2], score[0][3],
        score[0][4], score[0][5]));
    t.append(String.format("%-8s%4s%4s%4s%4s%4s%8s%n", team2, score[1][0], score[1][1], score[1][2], score[1][3],
        score[1][4], score[1][5]));
  }

  // Generate random integer between [0,14]
  private int rnd() {
    return (int) (Math.random() * 15);
  }

  public static void main(String[] args) {
    new SuperBowl();
  }
}
