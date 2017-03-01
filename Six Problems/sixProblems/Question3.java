package sixProblems;

/**
 * <p>
 * <b>Six Problems: Question 3<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class Question3 {
  public static void main(String[] args) {
    System.out.println(sumOfFibonacci(100));
  }

  public static long sumOfFibonacci(int limit) {
    long[] num = new long[limit];
    num[0] = 0;
    num[1] = 1;
    for (int i = 2; i < limit; i++) {
      num[i] = num[i - 1] + num[i - 2];
    }
    // long sum = 0;
    // for (long i : num) {
    // sum += i;
    // }
    // return sum;
    return 2 * num[limit - 1] + num[limit - 2] - 1;
  }
}
