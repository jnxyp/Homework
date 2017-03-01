package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 * This is an exercise from GUI 3_Labels_Exercises
 * 
 * @author Xin (Jn_xyp)
 * @version 2017-01-24
 */
public class LabelsE1 extends JFrame {
  public LabelsE1() {
    setTitle("LabelsE1");
    setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension dimension = new Dimension(100, 20);
    JLabel label1 = new JLabel("red label", JLabel.CENTER);
    JLabel label2 = new JLabel("blue label", JLabel.CENTER);
    JLabel label3 = new JLabel("orange label", JLabel.CENTER);
    JLabel label4 = new JLabel("magenta label", JLabel.CENTER);
    label1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    label2.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.YELLOW));
    label3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    label4.setBorder(BorderFactory.createLoweredBevelBorder());
    label1.setOpaque(true);
    label2.setOpaque(true);
    label3.setOpaque(true);
    label4.setOpaque(true);
    label1.setBackground(Color.RED);
    label2.setBackground(Color.BLUE);
    label3.setBackground(Color.ORANGE);
    label4.setBackground(Color.MAGENTA);
    label1.setPreferredSize(dimension);
    label2.setPreferredSize(dimension);
    label3.setPreferredSize(dimension);
    label4.setPreferredSize(dimension);
    add(label1);
    add(label2);
    add(label3);
    add(label4);
    setSize(300, 100);
    setVisible(true);
  }

  public static void main(String[] args) {
    new LabelsE1();
  }
}
