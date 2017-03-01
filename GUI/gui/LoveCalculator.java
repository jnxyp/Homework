package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Love Calculator from 7_Love_Calculator
 * 
 * @author Xin (jn_xyp)
 * @version 2017-01-31
 */
public class LoveCalculator extends JFrame implements ActionListener, DocumentListener {
  // GUI components
  JPanel     mainPanel, leftPanel, midPanel, rightPanel, contentPanel;
  JLabel     leftPicLbl, rightPicLbl, leftLbl, rightLbl, titleLbl, percentLbl;
  JButton    submitBtn, clearBtn;
  JTextField leftTField, rightTField;

  ImageIcon leftPic  = new ImageIcon(getClass().getResource("LoveCalculator-huaji1.png"));
  ImageIcon rightPic = new ImageIcon(getClass().getResource("LoveCalculator-huaji2.png"));

  Dimension frameSize  = new Dimension(670, 380);
  Dimension buttonSize = new Dimension((int) frameSize.getWidth() / 6, (int) frameSize.getHeight() / 12);

  Font titleF   = new Font("Arial", Font.BOLD, 36);
  Font labelF   = new Font("Arial", Font.PLAIN, 14);
  Font buttonF  = new Font("Arial", Font.PLAIN, 16);
  Font percentF = new Font("Arial", Font.PLAIN, 70);

  Color themeColorA = new Color(255, 0, 0);     // Color of texts and buttons
  Color themeColorB = new Color(255, 255, 255); // Color of texts on buttons

  public LoveCalculator() {
    // Set frame attributes
    setResizable(false);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setTitle("Love Calculator");
    setSize(frameSize);
    addWindowListener(new FrameListener());
    // Main component panel of frame
    mainPanel = new JPanel(new FlowLayout());
    // 4 panels on below
    contentPanel = new JPanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    midPanel = new JPanel();
    midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
    rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    // Initialize panels
    titleLbl = new JLabel("LOVE CALCULATOR", JLabel.CENTER);
    titleLbl.setFont(titleF);
    titleLbl.setForeground(themeColorA);
    leftLbl = new JLabel("Enter your name:", JLabel.CENTER);
    leftLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    leftLbl.setFont(labelF);
    leftLbl.setForeground(themeColorA);
    rightLbl = new JLabel("Enter his/her name:", JLabel.CENTER);
    rightLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    rightLbl.setFont(labelF);
    rightLbl.setForeground(themeColorA);
    percentLbl = new JLabel("??%", JLabel.CENTER);
    percentLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    percentLbl.setFont(percentF);
    percentLbl.setForeground(themeColorA);
    // Initialize buttons
    submitBtn = new JButton("SUBMIT");
    submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    submitBtn.setFont(buttonF);
    submitBtn.setForeground(themeColorB);
    submitBtn.setBackground(themeColorA);
    submitBtn.setOpaque(true);
    submitBtn.setMinimumSize(buttonSize);
    submitBtn.setMaximumSize(buttonSize);
    submitBtn.addActionListener(this);
    clearBtn = new JButton("CLEAR");
    clearBtn.setFont(buttonF);
    clearBtn.setForeground(themeColorB);
    clearBtn.setBackground(themeColorA);
    clearBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    clearBtn.setOpaque(true);
    clearBtn.setMinimumSize(buttonSize);
    clearBtn.setMaximumSize(buttonSize);
    clearBtn.addActionListener(this);
    // Initialize text fields
    leftTField = new JTextField();
    leftTField.setHorizontalAlignment(JTextField.CENTER);
    leftTField.getDocument().addDocumentListener(this);
    rightTField = new JTextField();
    rightTField.setHorizontalAlignment(JTextField.CENTER);
    rightTField.getDocument().addDocumentListener(this);
    // Initialize picture labels
    leftPicLbl = new JLabel(leftPic);
    leftPicLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    rightPicLbl = new JLabel(rightPic);
    rightPicLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    // Add components for panel on the left
    leftPanel.add(leftPicLbl);
    leftPanel.add(leftLbl);
    leftPanel.add(Box.createVerticalStrut(20));
    leftPanel.add(leftTField);
    // Add components for panel on the middle
    midPanel.add(Box.createVerticalGlue());
    midPanel.add(percentLbl);
    midPanel.add(Box.createVerticalGlue());
    midPanel.add(submitBtn);
    midPanel.add(Box.createVerticalStrut(14));
    midPanel.add(clearBtn);
    // Add components for panel on the right
    rightPanel.add(rightPicLbl);
    rightPanel.add(rightLbl);
    rightPanel.add(Box.createVerticalStrut(20));
    rightPanel.add(rightTField);

    contentPanel.add(leftPanel);
    contentPanel.add(midPanel);
    contentPanel.add(rightPanel);

    mainPanel.add(titleLbl);
    mainPanel.add(contentPanel);

    setContentPane(mainPanel);

    setVisible(true);
  }

  /**
   * Inner class for the window adapter
   * 
   * @author Xin (jn_xyp)
   *
   */
  class FrameListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      int option = JOptionPane.showConfirmDialog(LoveCalculator.this, "Are you sure you want to exit?",
          "Love Calculator", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (option == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
      else {
        return;
      }
    }
  }

  /**
   * Rewrite method of button click event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // SUBMIT button actions
    if (e.getActionCommand().equals(submitBtn.getActionCommand())) {
      boolean leftIsEmpty = leftTField.getText().isEmpty();
      boolean rightIsEmpty = rightTField.getText().isEmpty();
      if (leftIsEmpty || rightIsEmpty) {
        JOptionPane.showConfirmDialog(this, "You must enter two names!", "Error", JOptionPane.CLOSED_OPTION,
            JOptionPane.ERROR_MESSAGE);
        if (leftIsEmpty) {
          leftTField.setBackground(themeColorA);
        }
        if (rightIsEmpty) {
          rightTField.setBackground(themeColorA);
        }
      }
      else {
        percentLbl.setText("" + calculateLove(leftTField.getText(), rightTField.getText()) + "%");
      }
      // CLEAR button actions
    }
    else if (e.getActionCommand().equals(clearBtn.getActionCommand())) {
      leftTField.setBackground(themeColorB);
      rightTField.setBackground(themeColorB);
      leftTField.setText("");
      rightTField.setText("");
      percentLbl.setText("??%");
    }
  }

  /**
   * This method can calculate the "Love" between two names
   * 
   * @param name1
   *          First name
   * @param name2
   *          Second name
   * @return value of love rate
   */
  public static int calculateLove(String name1, String name2) {
    String concatenated;
    int sumOfChar = 0;
    name1 = name1.toUpperCase();
    name2 = name2.toUpperCase();
    name1 = removeDuplication(name1);
    name2 = removeDuplication(name2);
    concatenated = name1 + name2;
    for (int i = 0; i < concatenated.length(); i++) {
      sumOfChar += concatenated.charAt(i);
    }
    return sumOfChar % 101;
  }

  /**
   * This method can remove the same characters in a String
   * 
   * @param in
   *          input String
   * @return String without same character
   */
  public static String removeDuplication(String in) {
    String out = "";
    for (int i = 0; i < in.length(); i++) {
      if (!out.contains("" + in.charAt(i))) {
        out += in.charAt(i);
      }
    }
    return out;
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // DO NOTHING
    if (!leftTField.getText().isEmpty()) {
      leftTField.setBackground(themeColorB);
    }
    if (!rightTField.getText().isEmpty()) {
      rightTField.setBackground(themeColorB);
    }
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // DO NOTHING
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // DO NOTHING
  }

  public static void main(String[] args) {
    new LoveCalculator();
  }
}
