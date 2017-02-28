package homework;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This is an exercise from GUI 3_Labels_Exercises
 * 
 * @author Xin (Jn_xyp)
 * @version 2017-01-24
 */
public class LabelsE3 extends JFrame {
	public LabelsE3(String title) {
		setTitle(title);
		ImageIcon img = new ImageIcon(getClass().getResource("Labelse3-duke.gif"));
		JLabel label = new JLabel("Super Java", img, JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		add(label);
		setSize(300, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LabelsE3("Label E3");
	}
}
