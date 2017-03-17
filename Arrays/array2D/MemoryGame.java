package array2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 * <p>
 * <b>Memory Game</b> <br/>
 * Part of 2D Array exercise. <br/>
 * Rename image file as "MemoryGame * .png" and add to resource folder to add
 * images for the game.
 * </p>
 * <p>
 * <b>How to cheat:</b><br/>
 * <b>←</b> -1 s<br/>
 * <b>→</b> +1 s<br/>
 * <b>↑</b> delay -100 ms<br/>
 * <b>↓</b> delay +100 ms<br/>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-03-10
 */
public class MemoryGame extends JFrame implements ActionListener {
  // Program Setting
  private final String imgPrefix = "MemoryGame";
  private final int    row       = 3, col = 6;
  // GUI Components
  private JPanel  panel, cardPanel;
  private JLabel  lblTimer, lblTime, lblTitle;
  private JButton btnStart;
  // Listeners and Timer
  private ClickListener      clickListener;
  private FrameListener      frameListener;
  private Timer              timer;
  private GridBagConstraints gBC = new GridBagConstraints();
  // GUI Variables
  private Dimension    frameSize  = new Dimension(740, 670);
  private Dimension    timerSize  = new Dimension(150, 25);
  private final Border cardBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
  // Storage Arrays
  private ImageIcon[] imgs;
  private Card[][]    cards;
  // Other Variables
  private Card    card1, card2; // The temporary storage for the selected cards.
  private int     second;
  private boolean gameRunning;

  public static void main(String[] args) throws URISyntaxException, IllegalArgumentException, IOException {
    new MemoryGame();
  }

  /**
   * Constructor of a new game instance.
   * 
   * @param row
   *          The number of rows of card board.
   * @param col
   *          The number of columns of card board.
   * @throws URISyntaxException
   * @throws IllegalArgumentException
   *           When the input rows and columns were not positive number or they
   *           did not have an even product.
   * @throws IOException
   *           When the error occurred during loading images from source folder.
   */
  public MemoryGame() throws URISyntaxException, IllegalArgumentException, IOException {
    // Throw IllegalArgumentException when the parameter is not correct
    if (col * row % 2 != 0 || col <= 0 || row <= 0) {
      throw new IllegalArgumentException("Illegal row and column number " + row + "x" + col);
    }

    gameRunning = false;

    clickListener = new ClickListener();
    frameListener = new FrameListener();
    timer = new Timer(1000, this);
    // Initialize Arrays
    cards = new Card[row][col];
    loadCardPic(imgPrefix, new File(getClass().getResource("").toURI()));

    gBC.insets = new Insets(10, 10, 10, 10);
    // Initialize main panel
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    // Title label on top
    lblTitle = new JLabel("MEMORY GAME");
    lblTitle.setPreferredSize(new Dimension(700, 30));
    lblTitle.setFont(new Font("Britannic Bold", Font.BOLD, 28));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    gBCSetting(0, 0, 4, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
    panel.add(lblTitle, gBC);
    // Card panel
    cardPanel = new JPanel();
    cardPanel.setLayout(new GridLayout(row, col, 10, 10));
    generateCards();
    setPicsForCards();
    gBCSetting(0, 1, 4, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
    panel.add(cardPanel, gBC);
    // Start button
    btnStart = new JButton("START");
    btnStart.setPreferredSize(new Dimension(660, 30));
    btnStart.setFocusable(false);
    btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
    btnStart.addActionListener(this);
    gBCSetting(0, 2, 4, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
    panel.add(btnStart, gBC);
    // Timer label
    lblTime = new JLabel("ELAPSED TIME:");
    lblTime.setPreferredSize(timerSize);
    lblTime.setFont(new Font("Britannic Bold", Font.BOLD, 18));
    panel.add(lblTime);
    gBCSetting(1, 3, 1, 1, 0.5, 0, GridBagConstraints.NONE, GridBagConstraints.EAST);
    panel.add(lblTime, gBC);

    lblTimer = new JLabel();
    lblTimer.setPreferredSize(timerSize);
    lblTimer.setText(timeFormat(0));
    lblTimer.setPreferredSize(new Dimension(100, 20));
    lblTimer.setFont(new Font("Britannic Bold", Font.BOLD, 18));
    lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
    gBCSetting(2, 3, 1, 1, 0.5, 0, GridBagConstraints.NONE, GridBagConstraints.WEST);
    panel.add(lblTimer, gBC);
    // Set frame properties
    addWindowListener(frameListener);
    addKeyListener(new CheatEngine());
    setContentPane(panel);
    setSize(frameSize);
    // setResizable(false);
    setTitle("Memory Game");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  // Button and Timer action listener
  @Override
  public void actionPerformed(ActionEvent e) {
    // Timing
    if (e.getSource() == timer) {
      second++;
      lblTimer.setText(timeFormat(second));
    }
    // Start button in START mode
    else if (e.getActionCommand().equals("START")) {
      JButton btn = (JButton) e.getSource();
      btn.setText("EXIT");
      for (Card[] x : cards) {
        for (Card y : x) {
          y.isActive = true;
        }
      }
      second = 0;
      gameRunning = true;
      timer.start();
    }
    // Start button in EXIT mode
    else if (e.getActionCommand().equals("EXIT")) {
      exitConfirm();
    }
  }

  /**
   * This method can load PNG images with specific prefix from a directory to
   * imgs[].
   * 
   * @param prefix
   *          The specific prefix of file name.
   * @param path
   *          The directory of pictures.
   * @throws IOException
   *           When the error occurred during loading images from source folder.
   */
  private void loadCardPic(String prefix, File path) throws IOException {
    File[] files = path.listFiles();
    if (files == null) {
      throw new IOException("Cannot read the pictures from " + path.getAbsolutePath());
    }
    ArrayList<File> pics = new ArrayList<>();
    for (File file : files) {
      if (file.getName().startsWith(prefix) && file.getName().endsWith(".png")) {
        pics.add(file);
      }
    }
    imgs = new ImageIcon[pics.size()];
    for (int i = 0; i < imgs.length; i++) {
      imgs[i] = new ImageIcon(pics.get(i).getAbsolutePath());
    }
  }

  /**
   * This method will check if two cards are same. It will pause the timer
   * during processing.
   */
  private void checkIfMatch() {
    timer.stop();
    if (card1.image != card2.image) {
      JOptionPane.showMessageDialog(this, "Not Match!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
      card1.setShow(false);
      card2.setShow(false);
    }
    else {
      JOptionPane.showMessageDialog(this, "You found a match!", this.getTitle(), JOptionPane.WARNING_MESSAGE);
      card1.isActive = false;
      card2.isActive = false;
      if (isWin()) {
        winProcess();
      }
    }
    card1 = null;
    card2 = null;
    if (gameRunning) {
      timer.start();
    }
  }

  /**
   * This method will initialize each of the cards in cards[][] and add them to
   * the card panel.
   */
  private void generateCards() {
    for (int i = 0; i < cards.length; i++) {
      for (int j = 0; j < cards[i].length; j++) {
        cards[i][j] = new Card();
        cards[i][j].addMouseListener(clickListener);
        cardPanel.add(cards[i][j]);
      }
    }
  }

  /**
   * This method will pick two cards randomly from cards[][] and give them the
   * same image. If there are more pictures in imgs[] than the card pairs on
   * board, it will pick random image and ensure there's no duplicate image;
   * Else, the method will pick the image in proper order from imgs[] and start
   * from beginning until there's no empty card pairs.
   */
  private void setPicsForCards() {
    int imgIndex = 0;
    Card card1, card2;
    if (cards.length * cards[0].length / 2 < imgs.length) {
      boolean[] isUsed = new boolean[imgs.length];
      while (hasSpace()) {
        while (true) {
          card1 = rndCard();
          if (card1.image == null) {
            break;
          }
        }
        while (true) {
          card2 = rndCard();
          if (card2.image == null && card2 != card1) {
            break;
          }
        }
        card1.image = imgs[imgIndex];
        card2.image = imgs[imgIndex];
        isUsed[imgIndex] = true;
        while (isUsed[imgIndex]) {
          imgIndex = (int) (Math.random() * imgs.length);
        }
      }
    }
    else {
      while (hasSpace()) {
        while (true) {
          card1 = rndCard();
          if (card1.image == null) {
            break;
          }
        }
        while (true) {
          card2 = rndCard();
          if (card2.image == null && card2 != card1) {
            break;
          }
        }
        card1.image = imgs[imgIndex];
        card2.image = imgs[imgIndex];
        imgIndex++;
        if (imgIndex >= imgs.length) {
          imgIndex = 0;
        }
      }
    }
  }

  /**
   * This method can check if there's any card that does not have a picture.
   * 
   * @return If there's card without image.
   */
  private boolean hasSpace() {
    boolean space = false;
    for (Card[] x : cards) {
      for (Card y : x) {
        if (y.image == null) {
          space = true;
          break;
        }
      }
    }
    return space;
  }

  /**
   * Check if all the card pairs were already discovered.
   * 
   * @return If all the card pairs were already discovered.
   */
  private boolean isWin() {
    boolean win = true;
    for (Card[] x : cards) {
      for (Card y : x) {
        if (y.isActive) {
          win = false;
          break;
        }
      }
    }
    return win;
  }

  /**
   * What should the program do after player win the game, include ask if player
   * want to play again.
   */
  private void winProcess() {
    gameRunning = false;
    timer.stop();
    JOptionPane.showMessageDialog(this,
        "Congratulations... you cleared the board in " + second / 60 + " minute(s) and " + second % 60 + " seconds!",
        this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
    int value = JOptionPane.showConfirmDialog(this, "Do you want to try again?", this.getTitle(),
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (value == JOptionPane.YES_OPTION) {
      resetAll();
    }
  }

  /**
   * This method will clear the images in cards and reset the timer and button.
   */
  private void resetAll() {
    for (Card[] x : cards) {
      for (Card y : x) {
        y.setShow(false);
        y.isActive = false;
        y.image = null;
      }
    }
    setPicsForCards();
    second = 0;
    lblTimer.setText(timeFormat(second));
    btnStart.setText("START");
  }

  /**
   * This method will pick a random card form cards[][].
   * 
   * @return A random card from cards[][].
   */
  private Card rndCard() {
    return cards[(int) (Math.random() * cards.length)][(int) (Math.random() * cards[0].length)];
  }

  /**
   * This method will format the time in second to "mm:ss" format.
   * 
   * @param in
   *          Integer time in second.
   * @return String contains the time in "mm:ss" format.
   */
  private static String timeFormat(int in) {
    int min = in / 60;
    int sec = in % 60;
    return String.format("%02d:%02d", min, sec);
  }

  /**
   * This method will show a confirm dialog ask user if they want to exit the
   * program.s
   */
  private void exitConfirm() {
    timer.stop();
    int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", this.getTitle(),
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (value == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
    if (gameRunning) {
      timer.start();
    }
  }

  // Help to set parameters for Grid Bag Constraints.
  private void gBCSetting(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill,
      int anchor) {
    gBC.gridx = gridx;
    gBC.gridy = gridy;
    gBC.gridwidth = gridwidth;
    gBC.gridheight = gridheight;
    gBC.weightx = weightx;
    gBC.weighty = weighty;
    gBC.fill = fill;
    gBC.anchor = anchor;
  }

  /**
   * The action listener for window closing.
   * 
   * @author Xin (Jn_xyp)
   *
   */
  private class FrameListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      exitConfirm();
    }
  }

  /**
   * The listener for mouse click on cards (Jlabel).
   * 
   * @author Xin (Jn_xyp)
   *
   */
  private class ClickListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      Card card = (Card) e.getComponent();
      if (!card.isActive) {
        return;
      }
      card.setShow(true);
      if (card1 == null) {
        card1 = card;
      }
      else if (card2 == null && card != card1) {
        card2 = card;
        checkIfMatch();
      }
    }
  }

  private class CheatEngine extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT && gameRunning) {
        second--;
        lblTimer.setText(timeFormat(second));
        System.out.println("Time -1s");
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT && gameRunning) {
        second++;
        lblTimer.setText(timeFormat(second));
        System.out.println("Time +1s");
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        if (timer.getDelay() < 5000) {
          timer.setDelay(timer.getDelay() + 100);
        }
        System.out.println("Current Delay: " + timer.getDelay() + " ms");
      }
      else if (e.getKeyCode() == KeyEvent.VK_UP) {
        if (timer.getDelay() >= 100) {
          timer.setDelay(timer.getDelay() - 100);
        }
        System.out.println("Current Delay: " + timer.getDelay() + " ms");
      }
    }
  }

  /**
   * The custom class for Cards in the game. This class extends JLabel.
   * 
   * @author Xin (Jn_xyp)
   * @see javax.swing.JLabel
   */
  private class Card extends JLabel {
    private boolean isShowed;
    boolean         isActive;
    ImageIcon       image;

    protected Card() {
      isShowed = false;
      isActive = false;
      image = null;
      this.setBorder(cardBorder);
      this.setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * This method can set if the image of the card be showed. The image will
     * fit the size of the card when this method be called.
     * 
     * @param mode
     *          If the image of card be showed.
     */
    protected void setShow(boolean mode) {
      if (mode) {
        if (this.getWidth() < this.getHeight()) {
          this.setIcon(new ImageIcon(image.getImage().getScaledInstance(this.getWidth(),
              (int) (image.getIconHeight() * (1.0 * this.getWidth() / image.getIconWidth())), Image.SCALE_SMOOTH)));
        }
        else {
          this.setIcon(new ImageIcon(image.getImage().getScaledInstance(
              (int) (image.getIconWidth() * (1.0 * this.getHeight() / image.getIconHeight())), this.getHeight(),
              Image.SCALE_SMOOTH)));
        }
        isShowed = true;
      }
      else {
        this.setIcon(null);
        isShowed = false;
      }
    }
  }
}
