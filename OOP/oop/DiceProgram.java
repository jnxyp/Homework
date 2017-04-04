package oop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiceProgram extends JFrame implements ActionListener {

  private JButton btnRoll;
  private JLabel  lblMessage;

  public static void main(String[] args) {

    new DiceProgram();
  }

  public DiceProgram() {

    btnRoll = new JButton("ROLL");
    btnRoll.setPreferredSize(new Dimension(100, 30));
    btnRoll.setFocusable(false);
    btnRoll.addActionListener(this);

    lblMessage = new JLabel();
    lblMessage.setPreferredSize(new Dimension(250, 25));
    lblMessage.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
    lblMessage.setHorizontalAlignment(JLabel.CENTER);
    lblMessage.setText("Click ROLL to begin");

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panel.add(btnRoll);
    panel.add(lblMessage);

    setContentPane(panel);
    getRootPane().setDefaultButton(btnRoll);
    setSize(300, 250);
    setTitle("Dice Program");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  private class Dice {
    private ImageIcon[] imgDice;
    private Random      rnd;
    private JLabel      lblDice;
    private int         value;

    protected Dice() {
      // Load Images
      imgDice = new ImageIcon[7];
      for (int i = 0; i < imgDice.length; i++) {
        imgDice[i] = loadImg("dice" + i + ".png");
      }
      // Initialize Variables
      rnd = new Random();
      value = 0;
      lblDice = new JLabel(arg0)
    }
  }

  private ImageIcon loadImg(String fileName) {
    return new ImageIcon(getClass().getResource(fileName));
  }
}
