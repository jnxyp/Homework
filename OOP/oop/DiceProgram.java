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

/**
 * <p>
 * <b>Dice Program</b> <br/>
 * Part of Object Oriented Programming exercise. <br/>
 * The program provided a GUI to roll two dice and get the result. <br/>
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-4-4
 */
public class DiceProgram extends JFrame implements ActionListener {

  private JButton btnRoll;
  private JLabel  lblMessage;
  private Dice    dice1, dice2;

  public static void main(String[] args) {

    new DiceProgram();
  }

  private DiceProgram() {

    btnRoll = new JButton("ROLL");
    btnRoll.setPreferredSize(new Dimension(100, 30));
    btnRoll.setFocusable(false);
    btnRoll.addActionListener(this);

    lblMessage = new JLabel();
    lblMessage.setPreferredSize(new Dimension(250, 25));
    lblMessage.setFont(new Font("Britannic Bold", Font.PLAIN, 18));
    lblMessage.setHorizontalAlignment(JLabel.CENTER);
    lblMessage.setText("Click ROLL to begin");

    dice1 = new Dice();
    dice2 = new Dice();

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panel.add(dice1.getDice());
    panel.add(dice2.getDice());
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

  /**
   * Dice class stores the properties of a dice and provided method to roll it.
   * 
   * @author Xin (Jn_xyp)
   *
   */
  private class Dice {
    private ImageIcon[] imgDice;
    private Random      rnd;
    /**
     * The JLabel that contains the picture of the dice for current value.
     */
    private JLabel      lblDice;
    /**
     * The value of the dice.
     */
    private int         value;

    private Dice() {
      imgDice = new ImageIcon[7];
      for (int i = 0; i < imgDice.length; i++) {
        imgDice[i] = getimage("dice" + i + ".png");
      }
      rnd = new Random();
      lblDice = new JLabel(imgDice[0]);
      value = 0;
    }

    /**
     * Roll the dice. Generate a random integer [0,6] to the value of dice and
     * change the image in the lblDice.
     */
    private void roll() {
      value = rnd.nextInt(6) + 1;
      lblDice.setIcon(imgDice[value]);
    }

    /**
     * This method can get the JLabel that contains image of dice for current
     * value.
     * 
     * @return JLabel with a image inside it.
     */
    private JLabel getDice() {
      return lblDice;
    }

    /**
     * Get current value of the dice, return 0 before dice is rolled.
     * 
     * @return Current value of the dice.
     */
    private int getvalue() {
      return value;
    }

    /**
     * This method can load the specific image from the class path.
     * 
     * @param name
     *          - the name of the image file.
     * @return the ImageIcon created by that file.
     */
    private ImageIcon getimage(String name) {
      return new ImageIcon(getClass().getResource(name));
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    dice1.roll();
    dice2.roll();
    lblMessage.setText("You rolled " + (dice1.getvalue() + dice2.getvalue()));
  }
}
