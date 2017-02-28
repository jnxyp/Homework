package sixProblems;
import java.util.Arrays;

/**
 * <p>
 * <b>Six Problems: Question 5<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-17
 */
public class Question5 {
  public static void main(String[] args) {
    int[] input = { 50, 2, 1, 9, 500, 5440 };
    System.out.println(makeNumber(input));
  }

  public static String makeNumber(int[] input) {
    String temp;
    int count;
    String[] nums = new String[input.length];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = "" + input[i];
    }
    Arrays.sort(nums);
    String[] reverse = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      reverse[i] = nums[nums.length - i - 1];
    }
    nums = reverse;
    do {
      count = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i].charAt(0) == nums[i + 1].charAt(0)) {
          if (nums[i].length() > nums[i + 1].length()) {
            temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
            count++;
          }
        }
      }
    } while (count > 0);
    String result = "";
    for (String x : nums) {
      result += x;
    }
    return result;
  }
}
