package homework;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * <p>
 * <b>Tic Tac Toe</b> according to 10_Layout_Exercises.pdf
 * </p>
 * 
 * @author Xin (jn_xyp)
 * @version 2017-02-06
 */
public class TicTacToe extends JFrame {
  // Game Setting
  private final String BLOCK_EMPTY = " ", BLOCK_1 = "O", BLOCK_2 = "X";
  // GUI Variables
  private JPanel    mainPanel, blockPanel;
  private JLabel    titleLbl;
  private JButton[] blocks;
  // Other Variables
  private int            player;
  private ActionListener blockListner = new BlockListener();

  // Constructor
  public TicTacToe() {
    // Set frame properties
    setSize(300, 430);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setResizable(false);
    // Initialize containers
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    blockPanel = new JPanel();
    blockPanel.setLayout(new GridLayout(3, 3, 10, 10));
    // Initialize components
    titleLbl = new JLabel("Tic Tac Toe");
    titleLbl.setFont(new Font("Arial", Font.BOLD, 40));
    titleLbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    // Generate blocks
    blocks = new JButton[9];
    for (int i = 0; i < blocks.length; i++) {
      blocks[i] = new JButton(BLOCK_EMPTY);
      blocks[i].setBackground(Color.lightGray);
      blocks[i].setBorder(BorderFactory.createLineBorder(Color.gray, 1));
      blocks[i].setFont(new Font("Arial", Font.BOLD, 36));
      // blocks[i].setActionCommand(String.valueOf(i));
      blocks[i].addActionListener(blockListner);
    }
    // Add components
    for (JButton btn : blocks) {
      blockPanel.add(btn);
    }
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(titleLbl);
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(blockPanel);
    mainPanel.add(Box.createVerticalStrut(20));
    // Set player
    player = 1;
    // Set visible
    setContentPane(mainPanel);
    setVisible(true);
  }

  // Listener for buttons
  private class BlockListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      // if block is empty
      JButton x = (JButton) e.getSource();
      if (x.getText().equals(BLOCK_EMPTY)) {
        if (player == 1) {
          x.setText(BLOCK_1);
          player = 2;
        }
        else if (player == 2) {
          x.setText(BLOCK_2);
          player = 1;
        }
        checkResult();
      }
    }

  }

  /**
   * This method will check if anyone win the game
   */
  private void checkResult() {
    int[] v = new int[blocks.length];
    int empty = 0;
    int win = 0;
    for (int i = 0; i < blocks.length; i++) {
      if (blocks[i].getText().equals(BLOCK_1)) {
        v[i] = 1;
      }
      else if (blocks[i].getText().equals(BLOCK_2)) {
        v[i] = 2;
      }
      else {
        v[i] = 0;
        empty++;
      }
    }
    // Check horizontal, vertical and diagonal direction
    if (v[0] == v[1] && v[1] == v[2]) {
      win = v[0];
    }
    else if (v[3] == v[4] && v[4] == v[5]) {
      win = v[3];
    }
    else if (v[6] == v[7] && v[7] == v[8]) {
      win = v[6];
    }
    else if (v[0] == v[3] && v[3] == v[6]) {
      win = v[0];
    }
    else if (v[1] == v[4] && v[4] == v[7]) {
      win = v[1];
    }
    else if (v[2] == v[5] && v[5] == v[8]) {
      win = v[2];
    }
    else if (v[0] == v[4] && v[4] == v[8]) {
      win = v[0];
    }
    else if (v[2] == v[4] && v[4] == v[6]) {
      win = v[2];
    }
    // ↑ It's stupid but I don't want to change it......
    // Three possible cases
    if (win == 0 && empty == 0) {
      JOptionPane.showConfirmDialog(this, "It's a tie!", "Tie", JOptionPane.CLOSED_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }
    else if (win == 1) {
      JOptionPane.showConfirmDialog(this, BLOCK_1 + " win the game!", "Win", JOptionPane.CLOSED_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }
    else if (win == 2) {
      JOptionPane.showConfirmDialog(this, BLOCK_2 + " win the game!", "Win", JOptionPane.CLOSED_OPTION,
          JOptionPane.INFORMATION_MESSAGE);
    }
    else {
      return;
    }
    System.exit(0);
  }

  public static void main(String[] args) {
    new TicTacToe();
  }
}