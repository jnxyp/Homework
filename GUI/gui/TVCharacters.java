package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>
 * <b>TV Characters</b> according to 11_TV_Characters_Program_v2.pdf
 * </p>
 * 
 * @author Xin (jn_xyp)
 * @version 2017-02-09
 */
public class TVCharacters extends JFrame {
  // GUI Variables
  private JComboBox<String>      characterCBox;
  private JPanel                 mainPanel;
  private JLabel                 topLbl, picLbl;
  private Map<String, ImageIcon> characters;

  private ImageIcon          topPic        = new ImageIcon(getClass().getResource("TVCharacters-LOGO.png"));
  private Dimension          frameSize     = new Dimension(300, 650);
  private GridBagConstraints gdConstraints = new GridBagConstraints();

  public TVCharacters() {
    // Initialize arrays
    characters = new HashMap<String, ImageIcon>();
    // Set properties
    setTitle("TV Characters");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(frameSize);
    // setResizable(false);
    // Initialize containers
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());
    // Initialize and add contents
    // topLbl
    gdConstraints.insets = new Insets(5, 20, 5, 20);
    gdBagSetting(0, 0, 1, 1, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(topLbl = new JLabel(topPic), gdConstraints);
    mainPanel.setBackground(Color.WHITE);
    // characterBox
    gdBagSetting(0, 1, 1, 1, 0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(characterCBox = new JComboBox<>(), gdConstraints);
    characterCBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        picLbl.setIcon(characters.get(characterCBox.getSelectedItem()));
        picLbl.setText((String) characterCBox.getSelectedItem());
      }
    });
    // picLbl
    gdBagSetting(0, 2, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
    mainPanel.add(picLbl = new JLabel(characters.get(characterCBox.getSelectedItem())), gdConstraints);
    picLbl.setText((String) characterCBox.getSelectedItem());
    picLbl.setHorizontalTextPosition(JLabel.CENTER);
    picLbl.setVerticalTextPosition(JLabel.BOTTOM);
    // [ADD CHARACTERS]
    addCharacter("Kazuma", "TVCharacters-KAZUMA.png");
    addCharacter("Aqua", "TVCharacters-AQUA.png");
    addCharacter("Dakunes", "TVCharacters-DAKUNES.png");
    addCharacter("Megumin", "TVCharacters-MEGUMIN.png");
    // Set visible
    setContentPane(mainPanel);
    setVisible(true);
  }

  /**
   * This method take the name and picture file name, and add this character to
   * the program selections
   * 
   * @param name
   *          The display name of character
   * @param fileName
   *          The file name of character picture
   */
  private void addCharacter(String name, String fileName) {
    characters.put(name, new ImageIcon(getClass().getResource(fileName)));
    characterCBox.addItem(name);
  }

  // This method can set the properties of Grid Bag Constraint
  private void gdBagSetting(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty,
      int fill, int anchor) {
    gdConstraints.gridx = gridx;
    gdConstraints.gridy = gridy;
    gdConstraints.gridwidth = gridwidth;
    gdConstraints.gridheight = gridheight;
    gdConstraints.weightx = weightx;
    gdConstraints.weighty = weighty;
    gdConstraints.fill = fill;
    gdConstraints.anchor = anchor;
  }

  public static void main(String[] args) {
    new TVCharacters();
  }
}
