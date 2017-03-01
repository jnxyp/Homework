package sixProblems;

/**
 * <p>
 * <b>Six Problems: Question 1<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class Question1 {
  public static void main(String[] args) {
    int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // Sum = 55
    System.out.println(calcSumFor(input));
    System.out.println(calcSumWhile(input));
    System.out.println(calcSumRecursion(input, 0));
  }

  public static int calcSumFor(int[] input) {
    int sum = 0;
    for (int i = 0; i < input.length; i++) {
      sum += input[i];
    }
    return sum;
  }

  public static int calcSumWhile(int[] input) {
    int sum = 0;
    int index = 0;
    while (index < input.length) {
      sum += input[index];
      index++;
    }
    return sum;
  }

  public static int calcSumRecursion(int[] input, int index) {
    if (index < input.length) {
      return input[index] + calcSumRecursion(input, index + 1);
    }
    return 0;
  }
}
