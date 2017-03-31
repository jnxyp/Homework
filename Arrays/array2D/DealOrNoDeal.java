package array2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * <p>
 * <b>Deal or No Deal</b> <br/>
 * Part of 2D Array exercise. <br/>
 * Rename image file for money labels as <code>[money value]d.png</code> to add
 * more money values<br/>
 * Rename image file for brief cases as <code>case[#].png</code> to add more
 * brief cases<br/>
 * The number of money labels and brief cases must be same<br/>
 * 
 * <b>[Source File Changes]</b><br/>
 * "1cent.png" --> "0.01d.png"<br/>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-03-27
 */
public class DealOrNoDeal extends JFrame {
  // Constants
  int INITIAL_SELECTION_QUANTITY = 6;
  int BRIEF_CASES_PER_LINE       = 5;
  // Regular expression to match source files
  String REGEX_CASE_IMAGE_NAMES  = "^case\\d{1,3}\\.png$";
  String REGEX_MONEY_IMAGE_NAMES = "^\\d{1,}\\.{0,1}\\d{0,2}d\\.png$";
  // Location of pictures, null means using class path
  File RESOURCES_PATH = null;
  // Picture size for brief cases and money label
  Dimension CASE_SIZE        = new Dimension(55, 50);
  Dimension MONEY_LABEL_SIZE = new Dimension(115, 20);
  // GUI Pictures
  ImageIcon iconTitle    = new ImageIcon(getClass().getResource("dealornodealtop.png"));
  ImageIcon iconPurse    = new ImageIcon(getClass().getResource("itsadealimage.png"));
  ImageIcon iconGameOver = new ImageIcon(getClass().getResource("gameoverimage.gif"));
  // GUI Components
  JPanel      panelMain, panelMoneyLeft, panelCaseMid, panelMoneyRight;
  JScrollPane sclPaneCase;
  JLabel      lblTitle, lblSelectedCase, lblBottomNotice;
  // Component CasePlaceholder, MoneyPlaceholder;
  JLabel[][] allElements;
  // Other Variables
  Pattern            patternCaseImgNames, patternMoneyImgNames;
  GridBagConstraints gBC;
  Color              cFont       = new Color(251, 223, 136);
  Color              cBackground = Color.BLACK;
  DecimalFormat      moneyFormat = new DecimalFormat("$#,##0.00");
  int                selectionQuantity, remainSelection, roundCount;
  BriefCase          selectedCase;

  public DealOrNoDeal() throws URISyntaxException, IOException, PatternSyntaxException {
    // Set resources path if not set in the constants
    if (RESOURCES_PATH == null) {
      RESOURCES_PATH = new File(getClass().getResource("").toURI());
    }
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Deal or No Deal");
    setSize(700, 700);
    gBC = new GridBagConstraints();
    gBC.insets = new Insets(15, 5, 15, 5);
    // Initialize GUI Components
    // Main Panel
    panelMain = new JPanel(new GridBagLayout());
    panelMain.setBackground(cBackground);
    // Picture labels
    lblTitle = new JLabel(iconTitle);
    gBCSetting(0, 0, 3, 1, 1.0, 0.2, GridBagConstraints.BOTH);
    panelMain.add(lblTitle, gBC);

    lblSelectedCase = new JLabel();
    lblSelectedCase.setPreferredSize(CASE_SIZE);
    lblSelectedCase.setHorizontalAlignment(JLabel.CENTER);
    gBCSetting(0, 2, 1, 1, 0.1, 0.1, GridBagConstraints.BOTH);
    panelMain.add(lblSelectedCase, gBC);

    lblBottomNotice = new JLabel();
    lblBottomNotice.setBackground(cFont);
    lblBottomNotice.setHorizontalAlignment(JLabel.CENTER);
    lblBottomNotice.setFont(new Font("Arial", Font.BOLD, 16));
    lblBottomNotice.setForeground(cFont);
    gBCSetting(1, 2, 1, 1, 0.8, 0.1, GridBagConstraints.BOTH);
    panelMain.add(lblBottomNotice, gBC);

    gBCSetting(2, 2, 1, 1, 0.1, 0.1, GridBagConstraints.BOTH);
    panelMain.add(Box.createRigidArea(CASE_SIZE), gBC);

    // Load Pictures & Generate Components
    load();
    init();
    // Money and Brief Case Panels
    panelMoneyLeft = new JPanel();
    panelMoneyLeft.setLayout(new BoxLayout(panelMoneyLeft, BoxLayout.Y_AXIS));
    panelMoneyLeft.setBackground(cBackground);

    panelMoneyRight = new JPanel();
    panelMoneyRight.setLayout(new BoxLayout(panelMoneyRight, BoxLayout.Y_AXIS));
    panelMoneyRight.setBackground(cBackground);

    sclPaneCase = new JScrollPane(panelCaseMid = new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    sclPaneCase.getVerticalScrollBar().setBackground(cBackground);
    sclPaneCase.setViewportBorder(null);
    sclPaneCase.setBorder(null);
    panelCaseMid.setLayout(new GridLayout(0, BRIEF_CASES_PER_LINE));
    panelCaseMid.setBackground(cBackground);
    // Add panels
    gBCSetting(1, 1, 1, 1, 0.6, 0.7, GridBagConstraints.BOTH);
    panelMain.add(sclPaneCase, gBC);
    gBCSetting(0, 1, 1, 1, 0.2, 0.7, GridBagConstraints.VERTICAL);
    panelMain.add(panelMoneyLeft, gBC);
    gBCSetting(2, 1, 1, 1, 0.2, 0.7, GridBagConstraints.VERTICAL);
    panelMain.add(panelMoneyRight, gBC);
    // Add Cases and Money Labels
    addCasesAndMoneyLabels();
    // Set Visible
    setContentPane(panelMain);
    setVisible(true);
  }

  /**
   * This method will load all pictures and values from source files.
   * 
   * @throws IOException
   */
  private void load() throws IOException {
    patternCaseImgNames = Pattern.compile(REGEX_CASE_IMAGE_NAMES);
    patternMoneyImgNames = Pattern.compile(REGEX_MONEY_IMAGE_NAMES);
    allElements = new JLabel[2][];
    allElements[0] = generateBriefCases();
    allElements[1] = generateMoneyLabels();
    // Check if the number of brief cases and money values are same
    if (allElements[0].length != allElements[1].length) {
      throw new IOException("The number does not match. Brief Cases: " + allElements[0].length + " Money Labels: "
          + allElements[1].length + "\nCheck the images in " + RESOURCES_PATH.getAbsolutePath());
    }
  }

  /**
   * This method will clear and initialize everything in the program.
   * 
   * @throws PatternSyntaxException
   */
  private void init() throws PatternSyntaxException {
    selectedCase = null;
    lblSelectedCase.setIcon(null);
    selectionQuantity = INITIAL_SELECTION_QUANTITY;
    remainSelection = selectionQuantity;
    roundCount = 1;
    lblBottomNotice.setText("Choose one of the brief cases!");
    for (int i = 0; i < allElements[0].length; i++) {
      BriefCase b = (BriefCase) allElements[0][i];
      MoneyLabel m = (MoneyLabel) allElements[1][i];
      b.isExist = true;
      b.moneyLabel = null;
      b.setIcon(b.icon);
      m.isExist = true;
      m.isPlaced = false;
      m.setIcon(m.icon);
    }
    placeMoneyInsideCases();
  }

  /**
   * This method will generate and update notice on the bottom according to the
   * remain selection quantity.
   */
  private void updateNotice() {
    String num;
    switch (remainSelection) {
      case 10:
        num = "ten (10)";
        break;
      case 9:
        num = "nine (9)";
        break;
      case 8:
        num = "eight (8)";
        break;
      case 7:
        num = "seven (7)";
        break;
      case 6:
        num = "six (6)";
        break;
      case 5:
        num = "five (5)";
        break;
      case 4:
        num = "four (4)";
        break;
      case 3:
        num = "three (3)";
        break;
      case 2:
        num = "two (2)";
        break;
      case 1:
        num = "one (1)";
        break;
      default:
        num = String.valueOf(remainSelection);
        break;
    }
    lblBottomNotice.setText("Open " + num + " briefcases!");
  }

  /**
   * This method will add all Brief Cases and Money Labels to the GUI Panels.
   */
  private void addCasesAndMoneyLabels() {
    for (int i = 0; i < allElements[0].length; i++) {
      panelCaseMid.add(allElements[0][i]);
      // ((BriefCase) allElements[0][i]).isExist = true;
    }
    for (int i = 0; i < allElements[1].length / 2; i++) {
      panelMoneyLeft.add(allElements[1][i]);
      // ((MoneyLabel) allElements[1][i]).isExist = true;
      panelMoneyLeft.add(Box.createGlue());
    }
    for (int i = allElements[1].length / 2; i < allElements[1].length; i++) {
      panelMoneyRight.add(allElements[1][i]);
      // ((MoneyLabel) allElements[1][i]).isExist = true;
      panelMoneyRight.add(Box.createGlue());
    }
  }

  /**
   * This method will loop through each brief case and put random money value
   * inside it.
   */
  private void placeMoneyInsideCases() {
    for (int i = 0; i < allElements[0].length; i++) {
      MoneyLabel mLabel;
      do {
        mLabel = (MoneyLabel) allElements[1][(int) (Math.random() * allElements[1].length)];
      } while (mLabel.isPlaced);
      ((BriefCase) allElements[0][i]).setMoneyLabel(mLabel);
    }
  }

  /**
   * This method will calculate the banker's offer at now.
   * 
   * @return double value of banker's offer with two decimals.
   */
  private double getDealOffer() {
    double sum = 0;
    int caseCount = 0;
    for (int i = 0; i < allElements[0].length; i++) {
      BriefCase bCase = (BriefCase) allElements[0][i];
      if (bCase.isExist) {
        sum += bCase.value;
        caseCount++;
      }
    }
    // Banker’s offer = Average value of the money remaining * round number / 10
    // Keep two decimals
    return ((int) (sum / caseCount * roundCount / 10 * 100)) / 100;
  }

  /**
   * This method can check if the game entered the last round.
   * 
   * @return if this is the last round.
   */
  private boolean isLastRound() {
    int caseCount = 0;
    for (int i = 0; i < allElements[0].length; i++) {
      BriefCase bCase = (BriefCase) allElements[0][i];
      if (bCase.isExist) {
        caseCount++;
      }
    }
    if (caseCount == 1) {
      return true;
    }
    return false;
  }

  /**
   * Things to do in the last round. Include let player pick which brief case
   * they want to take.
   */
  private void lastRoundProcess() {
    int returnValue = JOptionPane.showConfirmDialog(this,
        "There is only one case left!\nWoud you like to keep your case?", this.getTitle(), JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    if (returnValue == JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(this,
          "Congratulations... you're going home with " + moneyFormat.format(selectedCase.value), this.getTitle(),
          JOptionPane.INFORMATION_MESSAGE, selectedCase.icon);
      gameOverProcess();
      return;
    }
    else {
      for (int i = 0; i < allElements[0].length; i++) {
        BriefCase bCase = (BriefCase) allElements[0][i];
        if (bCase.isExist) {
          JOptionPane.showMessageDialog(this,
              "Congratulations... you're going home with " + moneyFormat.format(bCase.value), this.getTitle(),
              JOptionPane.INFORMATION_MESSAGE, bCase.icon);
          gameOverProcess();
          return;
        }
      }
    }
  }

  /**
   * This method will show the banker's offer dialog at the end of each round.
   * 
   * @return the user's selection. 0 = DEAL, 1 = NOT DEAL.
   */
  private int showBankerOfferDialog() {
    Object[] op = { "DEAL", "NO DEAL" };
    return JOptionPane.showOptionDialog(this,
        "The banker's offer is " + moneyFormat.format(getDealOffer()) + "!\nDeal or no deal?", "Banker'soffer",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
  }

  /**
   * This method will show the final dialog after player make the deal. It will
   * also laugh at players if the player can get more money by keeping his own
   * brief case.
   */
  private void showDealResultDialog() {
    double value = getDealOffer();
    JOptionPane.showConfirmDialog(this, "Congratulations... you're going home with " + moneyFormat.format(value),
        "It's a deal!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, iconPurse);
    if (selectedCase.value > value) {
      JOptionPane.showConfirmDialog(this, "You can go home with " + moneyFormat.format(selectedCase.value),
          this.getTitle(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, selectedCase.icon);
    }
    gameOverProcess();
  }

  /**
   * This method will show the dialog that contains the icon and the value of
   * the brief case.
   * 
   * @param briefCase
   *          The brief case you want to show.
   */
  private void showBriefCaseValueDialog(BriefCase briefCase) {
    JOptionPane.showConfirmDialog(this,
        "Case #" + briefCase.serialNum + " contains " + moneyFormat.format(briefCase.value),
        "Case #" + briefCase.serialNum, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, briefCase.icon);
  }

  /**
   * The final process after game ended. Include ask player if they want to play
   * again.
   */
  private void gameOverProcess() {
    int returnValue = JOptionPane.showConfirmDialog(this, "Would you like to play again?", this.getTitle(),
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (returnValue == JOptionPane.NO_OPTION) {
      JOptionPane.showMessageDialog(this, "Thank you for playing " + getTitle(), "Game over",
          JOptionPane.INFORMATION_MESSAGE, iconGameOver);
      System.exit(0);
    }
    else {
      init();
    }
  }

  /**
   * This method will generate all Brief Case object for game. This method will
   * load the images from specific directory and generate brief case object for
   * each of them.
   * 
   * @return The Brief Case array that contains all brief cases, sorted in
   *         serial number order.
   * @throws IOException
   */
  private BriefCase[] generateBriefCases() throws IOException {
    BriefCase[] allBriefCases;
    File[] files = RESOURCES_PATH.listFiles();
    if (files == null) {
      throw new IOException("Cannot read the pictures from " + RESOURCES_PATH.getAbsolutePath());
    }
    ArrayList<File> imageFiles = new ArrayList<>();
    for (int i = 0; i < files.length; i++) {
      if (patternCaseImgNames.matcher(files[i].getName()).matches()) {
        imageFiles.add(files[i]);
      }
    }
    allBriefCases = new BriefCase[imageFiles.size()];
    for (int i = 0; i < allBriefCases.length; i++) {
      File temp = imageFiles.get(i);
      allBriefCases[i] = new BriefCase(new ImageIcon(temp.getAbsolutePath()),
          Integer.parseInt(temp.getName().substring(4, temp.getName().length() - 4)));
      // "case123.png" --> 123
    }
    // Sort the Brief Cases in serial number order
    Arrays.sort(allBriefCases, new Comparator<BriefCase>() {
      @Override
      public int compare(BriefCase o1, BriefCase o2) {
        if (o1.serialNum > o2.serialNum) {
          return 1;
        }
        else if (o1.serialNum < o2.serialNum) {
          return -1;
        }
        else {
          return 0;
        }
      }
    });
    return allBriefCases;
  }

  /**
   * This method will generate all Money Label object for game. This method will
   * load the images from specific directory and generate money label object for
   * each of them. The value will be read from file names.
   * 
   * @return The Money Label array that contains all brief cases, sorted in
   *         value order.
   * @throws IOException
   */
  private MoneyLabel[] generateMoneyLabels() throws IOException {
    MoneyLabel[] allMoneyLabels;
    File[] files = RESOURCES_PATH.listFiles();
    if (files == null) {
      throw new IOException("Cannot read the pictures from " + RESOURCES_PATH.getAbsolutePath());
    }
    ArrayList<File> imageFiles = new ArrayList<>();
    for (int i = 0; i < files.length; i++) {
      if (patternMoneyImgNames.matcher(files[i].getName()).matches()) {
        imageFiles.add(files[i]);
      }
    }
    allMoneyLabels = new MoneyLabel[imageFiles.size()];
    for (int i = 0; i < allMoneyLabels.length; i++) {
      File temp = imageFiles.get(i);
      allMoneyLabels[i] = new MoneyLabel(new ImageIcon(temp.getAbsolutePath()),
          Double.parseDouble(temp.getName().substring(0, temp.getName().length() - 5)));
      // "10000d.png" --> 10000.0
    }
    // Sort the Money Labels in money value order
    Arrays.sort(allMoneyLabels, new Comparator<MoneyLabel>() {
      @Override
      public int compare(MoneyLabel o1, MoneyLabel o2) {
        if (o1.value > o2.value) {
          return 1;
        }
        else if (o1.value < o2.value) {
          return -1;
        }
        else {
          return 0;
        }
      }
    });
    return allMoneyLabels;
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

  public static void main(String[] args) throws URISyntaxException, IOException, PatternSyntaxException {
    new DealOrNoDeal();
  }

  /**
   * Inner class for Brief Case, contains its image and corresponding money
   * label.
   * 
   * @author Jn_xyp
   *
   */
  private class BriefCase extends JLabel {
    ImageIcon  icon;
    int        serialNum;
    double     value;
    boolean    isExist;
    MoneyLabel moneyLabel;

    protected BriefCase(ImageIcon icon, int serialNumber) {
      this.serialNum = serialNumber;
      this.icon = icon;
      // Set default size
      // setSize(CASE_SIZE);
      setPreferredSize(CASE_SIZE);
      // Set image icon
      setIcon(icon);
      // Mouse click actions
      addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          BriefCase bCase = (BriefCase) e.getComponent();
          if (bCase.isExist) {
            bCase.isExist = false;
            bCase.setIcon(null);
            if (selectedCase != null) {
              showBriefCaseValueDialog(bCase);
              bCase.moneyLabel.isExist = false;
              bCase.moneyLabel.setIcon(null);
              if (isLastRound()) {
                lastRoundProcess();
                return;
              }
              remainSelection--;
              if (remainSelection == 0) {
                int value = showBankerOfferDialog();
                if (value == 0) {
                  showDealResultDialog();
                  return;
                }
                roundCount++;
                if (selectionQuantity > 1) {
                  selectionQuantity--;
                }
                remainSelection = selectionQuantity;
              }
              updateNotice();
            }
            else {
              selectedCase = bCase;
              lblSelectedCase.setIcon(bCase.icon);
              updateNotice();
            }
          }
        }
      });
    }

    protected void setMoneyLabel(MoneyLabel mLabel) {
      this.moneyLabel = mLabel;
      this.value = mLabel.value;
      mLabel.isPlaced = true;
    }
  }

  /**
   * Inner class for Money Labels, contains its image and corresponding value.
   * 
   * @author Jn_xyp
   *
   */
  private class MoneyLabel extends JLabel {
    ImageIcon icon;
    double    value;
    boolean   isExist, isPlaced;

    protected MoneyLabel(ImageIcon icon, double value) {
      this.icon = icon;
      this.value = value;
      // Set default size
      // setSize(MONEY_LABEL_SIZE);
      setPreferredSize(MONEY_LABEL_SIZE);
      // Set image icon
      setIcon(icon);
    }
  }
}