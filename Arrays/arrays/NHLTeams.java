package arrays;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * <p>
 * <b>NHL Teams<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-28
 */
public class NHLTeams extends JFrame implements ActionListener {

  private JButton     btnEastA, btnEastD, btnWestA, btnWestD, btnSearchEast, btnSearchWest;
  private JScrollPane eastSclPane, westSclPane;
  private JTextArea   eastTxtArea, westTxtArea;
  private JLabel      lblEast, lblWest;

  private String[] eastTeams = { "Carolina Hurricanes", "Columbus Blue Jackets", "New Jersey Devils",
      "New York Islanders", "New York Rangers", "Philadelphia Flyers", "Pittsburgh Penguins", "Washington Capitals",
      "Boston Bruins", "Buffalo Sabres", "Detroit Red Wings", "Florida Panthers", "Montr��al Canadiens",
      "Ottawa Senators", "Tampa Bay Lightning", "Toronto Maple Leafs" };
  private String[] westTeams = { "Chicago Blackhawks", "Colorado Avalanche", "Dallas Stars", "Minnesota Wild",
      "Nashville Predators", "St. Louis Blues", "Winnipeg Jets", "Anaheim Ducks", "Arizona Coyotes", "Calgary Flames",
      "Edmonton Oilers", "Los Angeles Kings", "San Jose Sharks", "Vancouver Canucks" };

  public static void main(String[] args) {
    new NHLTeams();
  }

  public NHLTeams() {
    // Create picture labels
    lblEast = new JLabel(new ImageIcon(getClass().getResource("east.gif")));
    lblWest = new JLabel(new ImageIcon(getClass().getResource("west.gif")));

    Font f = new Font("Arial", Font.BOLD, 10);
    // Create buttons
    btnEastA = new JButton("ASCEND");
    btnEastA.setPreferredSize(new Dimension(85, 30));
    btnEastA.setFont(f);
    btnEastA.addActionListener(this);
    btnEastD = new JButton("DESCEND");
    btnEastD.setPreferredSize(new Dimension(85, 30));
    btnEastD.setFont(f);
    btnEastD.addActionListener(this);
    btnWestA = new JButton("ASCEND");
    btnWestA.setPreferredSize(new Dimension(85, 30));
    btnWestA.setFont(f);
    btnWestA.addActionListener(this);
    btnWestD = new JButton("DESCEND");
    btnWestD.setPreferredSize(new Dimension(85, 30));
    btnWestD.setFont(f);
    btnWestD.addActionListener(this);
    btnSearchEast = new JButton("SEARCH");
    btnSearchEast.setFont(f);
    btnSearchEast.setEnabled(false);
    btnSearchEast.setPreferredSize(new Dimension(180, 30));
    btnSearchEast.addActionListener(this);
    btnSearchWest = new JButton("SEARCH");
    btnSearchWest.setFont(f);
    btnSearchWest.setEnabled(false);
    btnSearchWest.setPreferredSize(new Dimension(180, 30));
    btnSearchWest.addActionListener(this);
    // Create panels
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    // Add components
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = 0;
    gc.gridy = 0;
    gc.gridwidth = 2;
    gc.weightx = 100;
    gc.weighty = 100;
    gc.insets = new Insets(5, 5, 5, 5);

    panel.add(lblEast, gc);
    gc.gridx = 2;
    panel.add(lblWest, gc);
    gc.gridx = 0;
    gc.gridy = 1;

    // add text area
    eastSclPane = new JScrollPane(eastTxtArea = new JTextArea(15, 20), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    eastTxtArea.setEditable(false);
    westSclPane = new JScrollPane(westTxtArea = new JTextArea(15, 20), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    westTxtArea.setEditable(false);
    gc.anchor = GridBagConstraints.CENTER;
    panel.add(eastSclPane, gc);
    gc.gridx = 2;
    gc.anchor = GridBagConstraints.CENTER;
    panel.add(westSclPane, gc);

    gc.gridx = 0;
    gc.gridy = 2;
    gc.gridwidth = 1;
    gc.anchor = GridBagConstraints.EAST;
    panel.add(btnEastA, gc);
    gc.gridx = 1;
    gc.anchor = GridBagConstraints.WEST;
    panel.add(btnEastD, gc);
    gc.gridx = 2;
    gc.anchor = GridBagConstraints.EAST;
    panel.add(btnWestA, gc);
    gc.gridx = 3;
    gc.anchor = GridBagConstraints.WEST;
    panel.add(btnWestD, gc);
    gc.gridx = 0;
    gc.gridwidth = 2;
    gc.gridy = 3;
    gc.anchor = GridBagConstraints.CENTER;
    panel.add(btnSearchEast, gc);
    gc.gridx = 2;
    panel.add(btnSearchWest, gc);
    // Print teams
    printEast();
    printWest();
    // Set visible
    setContentPane(panel);
    pack();
    setTitle("National Hockey League");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object s = e.getSource();
    String keyWord = "";
    int line = 0;
    int pos = 0;
    // ASCEND button for east teams
    if (s == btnEastA) {
      Arrays.sort(eastTeams);
      printEast();
      btnSearchEast.setEnabled(true);
    }
    // DECEND button for east teams
    else if (s == btnEastD) {
      Arrays.sort(eastTeams, Collections.reverseOrder());
      printEast();
      btnSearchEast.setEnabled(false);
    }
    // ASCEND button for west teams
    else if (s == btnWestA) {
      Arrays.sort(westTeams);
      printWest();
      btnSearchWest.setEnabled(true);
    }
    // DECEND button for west teams
    else if (s == btnWestD) {
      Arrays.sort(westTeams, Collections.reverseOrder());
      printWest();
      btnSearchWest.setEnabled(false);
    }
    // SEARCH actions
    // East teams
    else if (s == btnSearchEast) {
      keyWord = JOptionPane.showInputDialog(null, "Enter team from Eastern COnference:", "NHL Teams",
          JOptionPane.INFORMATION_MESSAGE);
      line = Arrays.binarySearch(eastTeams, keyWord);
      if (line > 0) {
        JOptionPane.showMessageDialog(null, keyWord + " was found!", "Team found!", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i < line; i++) {
          // Add all the lengths of the names on front, +1 for "\n"
          pos += eastTeams[i].length() + 1;
        }
        eastTxtArea.requestFocus();
        eastTxtArea.select(pos, pos + eastTeams[line].length());
      }
      else {
        JOptionPane.showMessageDialog(null, keyWord + " was not found!", "Team not found!", JOptionPane.ERROR_MESSAGE);
      }
    }
    // West teams
    else if (s == btnSearchWest) {
      keyWord = JOptionPane.showInputDialog(null, "Enter team from Western COnference:", "NHL Teams",
          JOptionPane.INFORMATION_MESSAGE);
      line = Arrays.binarySearch(westTeams, keyWord);
      if (line > 0) {
        JOptionPane.showMessageDialog(null, keyWord + " was found!", "Team found!", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i < line; i++) {
          // Add all the lengths of the names on front, +1 for "\n"
          pos += westTeams[i].length() + 1;
        }
        westTxtArea.requestFocus();
        westTxtArea.select(pos, pos + westTeams[line].length());
      }
      else {
        JOptionPane.showMessageDialog(null, keyWord + " was not found!", "Team not found!", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  // This two method will update the team names in text area.
  private void printEast() {
    eastTxtArea.setText(null);
    for (String x : eastTeams) {
      eastTxtArea.append(x + "\n");
    }
  }

  private void printWest() {
    westTxtArea.setText(null);
    for (String x : westTeams) {
      westTxtArea.append(x + "\n");
    }
  }
}
