package sixProblems;

/**
 * <p>
 * <b>Six Problems: Question 4<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class Question4 {
  public static void main(String[] args) {
    long startTime, endTime;
    int[] input = new int[10000];
    int[] result;
    for (int i = 0; i < input.length; i++) {
      // input[i] = i;
      input[i] = (int) (Math.random() * 10000);
    }

    // System.out.println("Input array:" + Arrays.toString(input));

    startTime = System.currentTimeMillis();
    result = selectionSort(input.clone());
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime + "ms");
    // System.out.println(Arrays.toString(result));

    startTime = System.currentTimeMillis();
    result = bubbleSort(input.clone());
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime + "ms");
    // System.out.println(Arrays.toString(result));

    startTime = System.currentTimeMillis();
    result = insertionSort(input.clone());
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime + "ms");
    // System.out.println(Arrays.toString(result));

  }

  // Solution 1 - Selection Sort
  public static int[] selectionSort(int[] in) {
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

  // Solution 2 - Bubble Sort
  public static int[] bubbleSort(int[] in) {
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

  // Solution 3 - Insertion Sort
  public static int[] insertionSort(int[] in) {
    int extractNum, temp;
    for (int i = 1; i < in.length; i++) {
      extractNum = in[i];
      for (int j = i - 1; j >= 0; j--) {
        if (in[j] > extractNum) {
          temp = in[j];
          in[j] = extractNum;
          in[j + 1] = temp;
        }
        else {
          break;
        }
      }
    }
    return in;
  }
}