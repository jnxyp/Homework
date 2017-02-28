package sixProblems;
/**
 * <p>
 * <b>Six Problems: Question 2<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-16
 */
public class Question2 {
  public static void main(String[] args) {
    char[] input1 = { '1', '2', '3', '4', '5' };
    char[] input2 = { 'a', 'b', 'c', 'd' };
    System.out.println(combine(input1, input2));
  }

  public static char[] combine(char[] in1, char[] in2) {
    char[] result = new char[in1.length + in2.length];
    boolean swich = true;
    int index1 = 0, index2 = 0;
    for (int i = 0; i < result.length; i++) {
      if (swich) {
        swich = false;
        if (index1 < in1.length) {
          result[i] = in1[index1];
          index1++;
        }
        else {
          result[i] = in2[index2];
          index2++;
        }
      }
      else {
        swich = true;
        if (index2 < in2.length) {
          result[i] = in2[index2];
          index2++;
        }
        else {
          result[i] = in1[index1];
          index1++;
        }
      }
    }
    return result;
  }
}
