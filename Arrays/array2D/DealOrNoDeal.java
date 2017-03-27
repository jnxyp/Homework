package array2D;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class DealOrNoDeal extends JFrame {
  // Constants
  // Location of pictures, null means using class path
  File RESOURCES_PATH             = null;
  int  INITIAL_SELECTION_QUANTITY = 6;
  // Picture size for brief cases and money label
  Dimension caseSize       = new Dimension(55, 50);
  Dimension moneyLabelSize = new Dimension(112, 20);
  // GUI Pictures
  ImageIcon iconTitle    = new ImageIcon(getClass().getResource("dealornodealtop.png"));
  ImageIcon iconPurse    = new ImageIcon(getClass().getResource("itsadealimage.png"));
  ImageIcon iconGameOver = new ImageIcon(getClass().getResource("gameoverimage.gif"));
  // GUI Components
  JPanel         panelMain, panelMoneyLeft, panelCaseMid, panelMoneyRight;
  JLabel         lblTitle, lblSelectedCase, lblBottomNotice;
  Component      CasePlaceholder, MoneyPlaceholder;
  BriefCase[]    allBriefCases;
  MoneyLabel[]   allMoneyLabels;
  BriefCase[][]  remainBriefCases;
  MoneyLabel[][] remainMoneyLabels;
  // Other Variables

  public DealOrNoDeal() throws URISyntaxException {
    // Set resources path if not set in the constants
    if (RESOURCES_PATH == null) {
      RESOURCES_PATH = new File(getClass().getResource("").toURI());
    }
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Deal or No Deal");
    setSize(700, 700);
    // Initialize Components

    // Set Visible
    setVisible(true);
  }

  public static void main(String[] args) throws URISyntaxException {
    new DealOrNoDeal();
  }

  /**
   * Inner class for Brief Case, contains its image and corresponding value
   * 
   * @author Jn_xyp
   *
   */
  private class BriefCase extends JLabel {
    ImageIcon  icon;
    double     value;
    boolean    isExist, isSelected;
    MoneyLabel moneyLabel;

    protected void select() {

    }

    protected void remove() {

    }

  }

  private class MoneyLabel extends JLabel {
    ImageIcon icon;
    double    value;

    protected void remove() {

    }
  }

}
