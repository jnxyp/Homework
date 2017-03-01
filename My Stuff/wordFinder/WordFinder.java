package wordFinder;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * <p>
 * <b>Word Finder<b> <br />
 * This program can find specific word from a character square
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-28
 */
public class WordFinder extends JFrame {
  JTextField[][] blocks;
  String[][]     characters;
  int            row, col;
  String         word;

  public WordFinder() {
    // Set frame properties
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Word Finder | Version 0.1 by Jn_xyp");

  }

  private class GridPane {
    public GridPane() {
      // Set frame properties
      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      setTitle("Input Pane");
    }
  }
}
