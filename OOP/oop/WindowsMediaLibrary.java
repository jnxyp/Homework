package oop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>
 * <b>Windows Media Manager</b> <br/>
 * Part of Object Oriented Programming exercise. <br/>
 * The program provided a GUI to manage the Digital Media objects in the
 * library. <br/>
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-4-4
 */
public class WindowsMediaLibrary extends JFrame implements ActionListener {

  private String[]                headings;
  private JLabel[]                labels;
  private JTextField[]            fields;
  private JButton                 btnForward, btnBack, btnAdd;
  private ArrayList<DigitalMedia> digitalMedias;
  private int                     currentPos;
  private Color                   INVALID_BG = Color.RED;
  private Color                   DEFAULT_BG = Color.WHITE;

  public static void main(String[] args) {

    new WindowsMediaLibrary();
  }

  public WindowsMediaLibrary() {
    // Initialize variables
    currentPos = 0;
    digitalMedias = new ArrayList<>();
    headings = new String[] { "TITLE", "ARTIST", "LENGTH (hh:mm:ss)", "FILE SIZE (KB)", "GENRE", "RATING (0-5)" };
    // Load images
    ImageIcon imgLogo = new ImageIcon(getClass().getResource("windows_media_player_logo.png"));
    JLabel lblLogo = new JLabel(imgLogo);
    lblLogo.setPreferredSize(new Dimension(imgLogo.getIconWidth(), imgLogo.getIconHeight() + 20));
    // Create GUI
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
    panel.add(lblLogo);

    labels = new JLabel[headings.length];
    fields = new JTextField[headings.length];
    // Generate JLabels and JTextfields
    for (int i = 0; i < labels.length; i++) {
      labels[i] = new JLabel();
      labels[i].setText(headings[i] + ":");
      labels[i].setPreferredSize(new Dimension(120, 30));
      labels[i].setFont(new Font("Arial", Font.PLAIN, 12));
      panel.add(labels[i]);

      fields[i] = new JTextField(15);
      fields[i].setEditable(false);
      fields[i].setBackground(Color.WHITE);
      panel.add(fields[i]);
    }
    // Create buttons
    btnBack = new JButton("BACK");
    btnBack.setPreferredSize(new Dimension(90, 30));
    btnBack.setFont(new Font("Arial", Font.PLAIN, 11));
    btnBack.setFocusable(false);
    btnBack.addActionListener(this);

    btnAdd = new JButton("ADD");
    btnAdd.setPreferredSize(new Dimension(90, 30));
    btnAdd.setFont(new Font("Arial", Font.PLAIN, 11));
    btnAdd.setFocusable(false);
    btnAdd.addActionListener(this);

    btnForward = new JButton("FORWARD");
    btnForward.setPreferredSize(new Dimension(90, 30));
    btnForward.setFont(new Font("Arial", Font.PLAIN, 11));
    btnForward.setFocusable(false);
    btnForward.addActionListener(this);

    panel.add(btnBack);
    panel.add(btnAdd);
    panel.add(btnForward);
    // Set frame properties
    setContentPane(panel);
    setSize(350, 450);
    setTitle("Windows Media Player");
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * This method can load all informations from specific Digital Media object to
   * the GUI.
   * 
   * @param media
   *          - the Digital Media object you want to load info from.
   */
  private void loadInfo(DigitalMedia media) {
    fields[0].setText(media.getTitle());
    fields[1].setText(media.getArtist());
    fields[2].setText(media.getLength());
    fields[3].setText(String.valueOf(media.getFileSize()));
    fields[4].setText(media.genre);
    fields[5].setText(String.valueOf(media.rating));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // ADD or SAVE button
    if (e.getSource() == btnAdd) {
      // ADD button
      if (btnAdd.getText().equals("ADD")) {
        for (int i = 0; i < fields.length; i++) {
          fields[i].setEditable(true);
          fields[i].setText("");
        }

        btnBack.setEnabled(false);
        btnForward.setEnabled(false);
        btnAdd.setText("SAVE");
        fields[0].requestFocus();
      }
      // SAVE button
      else {

        // Create new digital media object
        DigitalMedia media = new DigitalMedia();
        media.setTitle(fields[0].getText());
        media.setArtist(fields[1].getText());
        media.setLength(fields[2].getText());
        media.setFileSize(fields[3].getText());
        media.setGenre(fields[4].getText());
        media.setRating(fields[5].getText());

        // Check if any value is invalid
        boolean isInvalid = false;
        for (int i = 0; i < fields.length; i++) {
          if (fields[i].getBackground() == INVALID_BG) {
            isInvalid = true;
            fields[i].requestFocus();
            fields[i].selectAll();
            break;
          }
        }

        // Saving process if inputs are valid
        if (!isInvalid) {
          digitalMedias.add(currentPos, media);
          for (int i = 0; i < fields.length; i++) {
            fields[i].setEditable(false);
          }
          btnBack.setEnabled(true);
          btnForward.setEnabled(true);
          btnAdd.setText("ADD");
          // fields[0].requestFocus();
          // Refresh the values in labels
          loadInfo(media);
        }
      }
    }
    // FORWARD Button
    else if (e.getSource() == btnForward) {
      if (currentPos < digitalMedias.size()) {
        currentPos++;
        if (currentPos == digitalMedias.size()) {
          fields[0].setText("(Empty)");
          fields[1].setText("(Empty)");
          fields[2].setText("(Empty)");
          fields[3].setText("(Empty)");
          fields[4].setText("(Empty)");
          fields[5].setText("(Empty)");
        }
        else {
          loadInfo(digitalMedias.get(currentPos));
        }
      }
    }
    // BACK Button
    else {
      if (currentPos > 0) {
        currentPos--;
        loadInfo(digitalMedias.get(currentPos));
      }
    }
  }

  /**
   * This class stores all properties of a digital media, use setXXX and getXXX
   * to access the fields.<br/>
   * The setXXX methods will check if the input is valid. If invalid, the input
   * will not be applied to the digital media object.
   * 
   * @author Xin (Jn_Xyp)
   *
   */
  private class DigitalMedia {
    private String title, length, genre, artist;
    private int    fileSize;
    private double rating;

    private DigitalMedia() {
      // Default values for new media instant
      title = "(No Title)";
      length = "(Unknown)";
      genre = "(Unknown)";
      artist = "(Unknown)";
      fileSize = 0;
      rating = 2.5;
    }

    // Get and Set properties

    private void setTitle(String songTitle) {
      songTitle = songTitle.trim();
      if (songTitle.equals("")) {
        return;
      }
      this.title = songTitle;
    }

    private String getTitle() {
      return title;
    }

    private void setArtist(String artistName) {
      artistName = artistName.trim();
      if (artistName.equals("")) {
        return;
      }
      this.artist = artistName;
    }

    private String getArtist() {
      return artist;
    }

    public void setLength(String time) {
      // Check if the input string matches the format (h)h:(m)m:(s)s
      time = time.trim();
      if (time.equals("")) {
        return;
      }
      Pattern pattern = Pattern.compile("([0-9]{0,1}[0-9]):([0-5]{0,1}[0-9]):([0-5]{0,1}[0-9])");
      Matcher matcher = pattern.matcher(time);
      if (matcher.matches()) {
        // Format the string to hh:mm:ss
        time = "";
        time += String.format("%02d", Integer.parseInt(matcher.group(1)));
        time += ":";
        time += String.format("%02d", Integer.parseInt(matcher.group(2)));
        time += ":";
        time += String.format("%02d", Integer.parseInt(matcher.group(3)));
        this.length = time;
        fields[2].setBackground(DEFAULT_BG);
      }
      else {
        fields[2].setBackground(INVALID_BG);
      }
    }

    public String getLength() {
      return length;
    }

    public void setFileSize(String size) {
      size = size.trim();
      if (size.equals("")) {
        return;
      }
      int temp = 0;
      try {
        temp = Integer.parseInt(size);
        fields[3].setBackground(DEFAULT_BG);
      }
      catch (NumberFormatException e) {
        fields[3].setBackground(INVALID_BG);
      }
      if (temp < 0) {
        fields[3].setBackground(INVALID_BG);
        return;
      }
      this.fileSize = temp;
    }

    public int getFileSize() {
      return fileSize;
    }

    public void setGenre(String gen) {
      gen = gen.trim();
      if (gen.equals("")) {
        return;
      }
      this.genre = gen;
    }

    public String getGenre() {
      return genre;
    }

    public void setRating(String rate) {
      rate = rate.trim();
      if (rate.equals("")) {
        return;
      }
      double temp = 2.5;
      try {
        temp = Double.parseDouble(rate);
      }
      catch (NumberFormatException e) {
        fields[5].setBackground(INVALID_BG);
        return;
      }
      if (temp < 0 || temp > 5) {
        fields[5].setBackground(INVALID_BG);
        return;
      }
      this.rating = temp;
      fields[5].setBackground(DEFAULT_BG);
    }

    public double getRating() {
      return rating;
    }

  }
}