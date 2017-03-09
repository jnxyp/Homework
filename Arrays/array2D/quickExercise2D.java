package array2D;

/**
 * <p>
 * <b>Quick Exercise 2D</b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-03-06
 */
public class quickExercise2D {
  // -----------------------------------------------------------------
  // Creates a 2D array of integers, fills it with increasing
  // integer values from 0 to 49, then prints them out.
  // -----------------------------------------------------------------
  public static void main(String[] args) {
    int[][] table = new int[5][10];
    int count = 0;
    // Load the table with values
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        table[i][j] = count;
        count++;
      }
    }
    // Print the table
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        System.out.printf("%-2s ", table[i][j]);
      }
      System.out.println();
    }
  }
}
