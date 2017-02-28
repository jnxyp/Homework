package homework;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * <p>
 * <b>Cafeteria Survey<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-23
 */
public class CafeteriaSurvey extends JFrame {
  // GUI Components
  JLabel            topNotice, midNotice;
  JComboBox<String> rateCbBox;
  JButton           submitBtn, clearBtn;
  JTextArea         resultTArea;
  JScrollPane       resultSrlPane;
  JPanel            mainPanel;
  // GUI Variables
  GridBagConstraints gBC = new GridBagConstraints();

  // Other Variables
  int[] frequency = new int[10];

  public CafeteriaSurvey() {
    // Frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Cafeteria Survey");
    setSize(300, 500);
    setResizable(false);
    setLocationRelativeTo(null);
    // Initialize Components
    gBC.insets = new Insets(5, 5, 5, 5);
    mainPanel = new JPanel(new GridBagLayout());

    topNotice = new JLabel("Rate cafeteria food:");
    topNotice.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 0, 1, 1, 0.5, 0.1, GridBagConstraints.HORIZONTAL);
    mainPanel.add(topNotice, gBC);
    // Rate Combo Box
    rateCbBox = new JComboBox<String>();
    rateCbBox.addItem("-Enter rating-");
    rateCbBox.addItem("" + 1 + " - Awful");
    rateCbBox.addItem("" + 2);
    rateCbBox.addItem("" + 3);
    rateCbBox.addItem("" + 4);
    rateCbBox.addItem("" + 5 + " - Average");
    rateCbBox.addItem("" + 6);
    rateCbBox.addItem("" + 7);
    rateCbBox.addItem("" + 8);
    rateCbBox.addItem("" + 9);
    rateCbBox.addItem("" + 10 + " - Excellent");
    gBCSetting(1, 0, 1, 1, 0.5, 0.1, GridBagConstraints.HORIZONTAL);
    mainPanel.add(rateCbBox, gBC);

    midNotice = new JLabel("SURVEY RESULTS");
    midNotice.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 2, 2, 1, 1, 0.1, GridBagConstraints.HORIZONTAL);
    mainPanel.add(midNotice, gBC);

    resultTArea = new JTextArea(14, 25);
    resultTArea.setEditable(false);
    resultTArea.setFont(new Font("Consolas", Font.PLAIN, 16));

    resultSrlPane = new JScrollPane(resultTArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    gBCSetting(0, 3, 2, 1, 1, 0.7, GridBagConstraints.BOTH);
    mainPanel.add(resultSrlPane, gBC);

    // Buttons and Action listeners
    // SUBMIT button
    submitBtn = new JButton("SUBMIT");
    gBCSetting(0, 1, 1, 1, 0.5, 0.1, GridBagConstraints.HORIZONTAL);
    mainPanel.add(submitBtn, gBC);
    submitBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (rateCbBox.getSelectedIndex() > 0) {
          frequency[rateCbBox.getSelectedIndex() - 1]++;
          updateResult();
        }
      }
    });
    // CLEAR button
    clearBtn = new JButton("CLEAR");
    gBCSetting(1, 1, 1, 1, 0.5, 0.1, GridBagConstraints.HORIZONTAL);
    mainPanel.add(clearBtn, gBC);
    clearBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clearAll();
      }
    });

    // Set visible
    setContentPane(mainPanel);
    setVisible(true);
  }

  // Generate the display text in text area
  private void updateResult() {
    String temp = "";
    String frequencyString;
    int sum = 0;
    // Print title
    temp += "\n";
    temp += String.format("%-15s%-10s\n", "RATING", "FREQUENCY");
    temp += "\n";
    // Generate lines for each level
    for (int i = 0; i < frequency.length; i++) {
      sum += frequency[i];
      frequencyString = "";
      // Generate votes
      for (int j = 0; j < frequency[i]; j++) {
        frequencyString += "*";
      }
      temp += String.format("%-15s%-10s\n", i + 1, frequencyString);
    }
    temp += "\n";
    // Print total votes
    temp += String.format("%-15s%-10s\n", "TOTAL VOTES", sum);

    resultTArea.setText(temp);
  }

  // Clear all the data and text area
  private void clearAll() {
    resultTArea.setText(null);
    rateCbBox.setSelectedIndex(0);
    Arrays.fill(frequency, 0);
  }

  // This method can set the properties of Grid Bag Constraint
  private void gBCSetting(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
      int fill) {
    gBC.gridx = gridx;
    gBC.gridy = gridy;
    gBC.gridwidth = gridwidth;
    gBC.gridheight = gridheight;
    gBC.weightx = weightx;
    gBC.weighty = weighty;
    gBC.fill = fill;
  }

  public static void main(String[] args) {
    new CafeteriaSurvey();
  }
}
