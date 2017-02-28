package homework;

import java.awt.Color;

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
public class LabelsE2 extends JFrame {
	public LabelsE2() {
		setTitle("LabelsE2");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label1 = new JLabel("red label", JLabel.CENTER);
		JLabel label2 = new JLabel("blue label", JLabel.CENTER);
		JLabel label3 = new JLabel("orange label", JLabel.CENTER);
		JLabel label4 = new JLabel("magenta label", JLabel.CENTER);
		label1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		label2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		label3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		label4.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		label1.setOpaque(true);
		label2.setOpaque(true);
		label3.setOpaque(true);
		label4.setOpaque(true);
		label1.setBackground(Color.RED);
		label2.setBackground(Color.BLUE);
		label3.setBackground(Color.ORANGE);
		label4.setBackground(Color.MAGENTA);
		label1.setBounds(10, 10, 100, 45);
		label2.setBounds(10, 10 + 45 + 10, 100, 45);
		label3.setBounds(10, (10 + 45) * 2 + 10, 100, 45);
		label4.setBounds(10, (10 + 45) * 3 + 10, 100, 45);
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LabelsE2();
	}
}
