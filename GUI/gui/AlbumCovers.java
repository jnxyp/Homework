package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 * <p>
 * <b>Album Covers</b> according to 9_Album_Covers_v2_1.pdf
 * </p>
 * <p>
 * Using
 * <code> addAlbum(String bandName, String albumTitle, String picFileName) </code>
 * to add new album
 * </p>
 * 
 * @author Xin (jn_xyp)
 * @version 2017-02-05
 */

public class AlbumCovers extends JFrame {
  // GUI components
  private JPanel      mainPanel, midPanel, leftPanel, buttonPanel;
  private JScrollPane leftSclPane;
  private JButton     clearBtn, exitBtn;
  private JLabel      topLbl, rightPicLbl;
  // Album informations
  private ButtonGroup             albumBtnGroup;
  private ArrayList<String[]>     albumInfo;
  private ArrayList<ImageIcon>    albumPic;
  private ArrayList<JRadioButton> albumBtn;
  // Listeners
  private WindowListener frameListener    = new FrameListener();
  private ActionListener buttonListener   = new ButtonListener();
  private ItemListener   checkBoxListener = new CheckBoxListener();
  // Other variables
  private Dimension frameSize    = new Dimension(800, 500);
  private Dimension btnPanelSize = new Dimension(300, 40);
  private Font      titleFont    = new Font("Arial", Font.BOLD, 46);
  // Default text on the label
  private final String DEFAULT_HINT = "Album cover goes here";

  // Create new instance
  public static void main(String[] args) {
    new AlbumCovers();
  }

  // Constructor
  public AlbumCovers() {
    // Set frame properties
    setTitle("Album Covers");
    // setResizable(false);
    setSize(frameSize);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(frameListener);
    // Initialize variables
    albumInfo = new ArrayList<String[]>();
    albumPic = new ArrayList<ImageIcon>();
    albumBtn = new ArrayList<JRadioButton>();
    albumBtnGroup = new ButtonGroup();
    // Initialize containers
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    midPanel = new JPanel();
    midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));
    midPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftSclPane = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    // leftSclPane.setBorder(null);

    buttonPanel = new JPanel(new GridLayout(1, 2, 20, 10));
    buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    buttonPanel.setMaximumSize(btnPanelSize);

    // Initialize components
    topLbl = new JLabel("ALBUM COVERS", JLabel.CENTER);
    topLbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    topLbl.setFont(titleFont);

    rightPicLbl = new JLabel(DEFAULT_HINT);
    rightPicLbl.setHorizontalAlignment(JLabel.CENTER);

    clearBtn = new JButton("CLEAR");
    clearBtn.addActionListener(buttonListener);

    exitBtn = new JButton("EXIT");
    exitBtn.addActionListener(buttonListener);

    // Add components
    midPanel.add(Box.createHorizontalGlue());
    midPanel.add(leftSclPane);
    midPanel.add(Box.createHorizontalGlue());
    midPanel.add(rightPicLbl);
    midPanel.add(Box.createHorizontalGlue());

    buttonPanel.add(clearBtn);
    buttonPanel.add(exitBtn);

    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(topLbl);
    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(midPanel);
    mainPanel.add(Box.createVerticalGlue());
    mainPanel.add(buttonPanel);
    mainPanel.add(Box.createVerticalGlue());

    // [ADD ALBUMS]
    addAlbum("EastNewSound", "Solitude Blossom", "AlbumCovers-SolitudeBlossom.jpg");
    addAlbum("EastNewSound", "Sprout Intention", "AlbumCovers-SproutIntention.jpg");
    addAlbum("EastNewSound", "Mindless Act the Instrumental", "AlbumCovers-Mindless Act the Instrumental.jpg");
    addAlbum("Inon Zur", "Fallout 4 (Original Game Soundtrack)",
        "AlbumCovers-Fallout 4 (Original Game Soundtrack).jpg");

    // Display frame
    generateComponents();
    updateAlbumList();
    setContentPane(mainPanel);
    setVisible(true);
  }

  /**
   * <p>
   * Using this method to add album for this program, input the band name, album
   * title and the file name of picture.
   * </p>
   * <p>
   * Picture should be put in the class path and <b>MUST be a square</b>
   * </p>
   * 
   * @param bandName
   *          The name of the band or the music author
   * @param albumTitle
   *          The title of this album
   * @param picFileName
   *          The file name of album cover picture
   */
  protected void addAlbum(String bandName, String albumTitle, String picFileName) {
    String[] temp = new String[3];
    temp[0] = bandName;
    temp[1] = albumTitle;
    temp[2] = picFileName;
    albumInfo.add(temp);
  }

  // Inner class for listeners
  /**
   * Listener for window closing
   * 
   * @author Xin
   */
  private class FrameListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      exitConfirm();
    }
  }

  /**
   * Listener for button clicking
   * 
   * @author Xin
   */
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // CLEAR button
      if (e.getActionCommand().equals(clearBtn.getActionCommand())) {
        albumBtnGroup.clearSelection();
        changePic(null);
        // EXIT button
      }
      else if (e.getActionCommand().equals(exitBtn.getActionCommand())) {
        exitConfirm();
      }
    }
  }

  /**
   * The listener for radio buttons
   * 
   * @author Xin
   */
  private class CheckBoxListener implements ItemListener {
    // Check which button was selected
    @Override
    public void itemStateChanged(ItemEvent e) {
      for (int i = 0; i < albumBtn.size(); i++) {
        if (e.getSource() == albumBtn.get(i)) {
          changePic(albumPic.get(i));
          break;
        }
      }
    }
  }

  /**
   * This method can change the image in the right side label, input null to
   * reset the label with default text
   * 
   * @param pic
   *          The picture you want to show on it
   */
  private void changePic(ImageIcon pic) {
    if (pic == null) {
      rightPicLbl.setIcon(null);
      rightPicLbl.setText(DEFAULT_HINT);
    }
    else {
      rightPicLbl.setText(null);
      rightPicLbl.setIcon(pic);
    }
  }

  /**
   * This method show a confirm option pane for user to confirm if they want to
   * exit the program
   */
  private void exitConfirm() {
    int returnValue = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (returnValue == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }

  /**
   * This method can create the ImageIcons and RadioButtons according to the
   * album information in albumInfo
   */
  private void generateComponents() {
    albumBtn.clear();
    albumPic.clear();
    for (String[] x : albumInfo) {
      albumBtn.add(new JRadioButton(x[0].toUpperCase() + ": " + x[1]));
      albumPic.add(new ImageIcon(getClass().getResource(x[2])));
    }
  }

  /** This method will add all radio buttons to the panel on the left */
  private void updateAlbumList() {
    leftPanel.removeAll();
    for (JRadioButton x : albumBtn) {
      x.addItemListener(checkBoxListener);
      albumBtnGroup.add(x);
      leftPanel.add(x);
    }
  }
}
