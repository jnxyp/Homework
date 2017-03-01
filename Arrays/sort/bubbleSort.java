package sort;

import java.util.Arrays;

/**
 * <p>
 * <b>Array Sorting - Bubble Sort<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-28
 */
public class bubbleSort {
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

    bubble_Sort(randomNums);

    end = System.currentTimeMillis();

    System.out.println("Time Cost: " + (end - start) + "ms");

    if (SHOW_ARRAY) {
      System.out.println("Sorted Array:\n" + Arrays.toString(randomNums) + "\n");
    }

  }

  public static int[] bubble_Sort(int[] in) {
    int temp, count;
    do {
      count = 0;
      for (int j = 0; j < in.length - 1; j++) {
        if (in[j] > in[j + 1]) {
          temp = in[j];
          in[j] = in[j + 1];
          in[j + 1] = temp;
          count++;
        }
      }
    } while (count > 0);
    return in;
  }
}
