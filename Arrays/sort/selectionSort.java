package sort;

import java.util.Arrays;

/**
 * <p>
 * <b>Array Sorting - Selection Sort<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-28
 */
public class selectionSort {
  public static void main(String[] args) {
    // Test Cases
    final int ARRAY_LENGTH = 100;
    final int RANDOM_RANGE = 10000;
    final boolean SHOW_ARRAY = true;
    long start, end;
    int[] randomNums = new int[ARRAY_LENGTH];
    for (int i = 0; i < randomNums.length; i++) {
      randomNums[i] = (int) (Math.random() * RANDOM_RANGE);
    }
    System.out.println("Array Length: " + ARRAY_LENGTH);
    if (SHOW_ARRAY) {
      System.out.println("Initial Array:\n" + Arrays.toString(randomNums) + "\n");
    }

    start = System.currentTimeMillis();

    selection_Sort(randomNums);

    end = System.currentTimeMillis();

    System.out.println("Time Cost: " + (end - start) + "ms");

    if (SHOW_ARRAY) {
      System.out.println("Sorted Array:\n" + Arrays.toString(randomNums) + "\n");
    }
  }

  public static int[] selection_Sort(int[] in) {
    int temp, min;
    for (int i = 0; i < in.length - 1; i++) {
      min = i;
      for (int j = i + 1; j < in.length; j++) {
        if (in[min] > in[j]) {
          min = j;
        }
      }
      temp = in[i];
      in[i] = in[min];
      in[min] = temp;
    }
    return in;
  }
}
