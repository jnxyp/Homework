package sixProblems;

import java.util.Arrays;

/**
 * <p>
 * <b>Six Problems: Question 6<b> <br />
 * </p>
 * 
 * @author Xin <a href= "http://blog.jnxyp.tk/">(Jn_xyp)</a>
 * @version 2017-02-17
 */
public class Question6 {
  public static void main(String[] args) {
    int[] elements = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int target = 100;
    ChanceFinder cf = new ChanceFinder(elements, target);
    cf.printAll();
  }
}

class ChanceFinder {
  int[]    elements;
  String[] formula;
  int      target;

  public ChanceFinder(int[] elements, int target) {
    this.elements = elements;
    this.target = target;
    init();
  }

  public void init() {
    formula = new String[elements.length * 2 - 1];
    Arrays.fill(formula, " ");
    int count = 0;
    for (int i = 0; i < formula.length; i += 2) {
      formula[i] = "" + elements[count];
      count++;
    }
  }

  public void printAll() {
    changeSymbol(1);
  }

  public void changeSymbol(int index) {
    if (index == formula.length - 2) {
      formula[index] = " ";
      if (calculate(formula) == target) {
        print(formula);
      }
      formula[index] = "+";
      if (calculate(formula) == target) {
        print(formula);
      }
      formula[index] = "-";
      if (calculate(formula) == target) {
        print(formula);
      }
    }
    else {
      formula[index] = " ";
      changeSymbol(index + 2);
      formula[index] = "+";
      changeSymbol(index + 2);
      formula[index] = "-";
      changeSymbol(index + 2);
    }
  }

  public int calculate(String[] formula) {
    String expression = "";
    for (String x : formula) {
      if (!x.equals(" ")) {
        if (x.equals("+") || x.equals("-")) {
          expression += " " + x + " ";
        }
        else {
          expression += x;
        }
      }
    }
    String[] temp = expression.split(" ");
    int result = Integer.parseInt(temp[0]);
    for (int i = 1; i < temp.length; i += 2) {
      if (temp[i].equals("+")) {
        result += Integer.parseInt(temp[i + 1]);
      }
      else if (temp[i].equals("-")) {
        result -= Integer.parseInt(temp[i + 1]);
      }
    }
    return result;
  }

  public static void print(String[] formula) {
    for (String x : formula) {
      if (!x.equals(" ")) {
        System.out.print(x);
      }
    }
    System.out.println();
  }
}
