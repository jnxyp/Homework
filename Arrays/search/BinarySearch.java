package search;

import java.util.Arrays;

public class BinarySearch {
  public static void main(String[] args) {
    int[] input = { 1, 2, 3, 4, 5, 6 };
    System.out.println(binarySearch(input, 6)); // 5

    String[] input2 = { "Calgary", "Montreal", "Ottawa", "Toronto", "Vancouver", "Winnipeg" };
    System.out.println(binarySearch(input2, "Toronto")); // 3
  }

  // Binary Search for int[]¡£
  private static int binarySearch(int[] array, int key) {
    Arrays.sort(array);
    return binarySearchRecursive(array, key, 0, array.length);
  }

  private static int binarySearchRecursive(int[] array, int key, int start, int end) {
    if (end - start == 2) {
      if (array[start] == key) {
        return start;
      }
      else if (array[start + 1] == key) {
        return start + 1;
      }
      else {
        return -1;
      }
    }
    int mid = (end - start) / 2;
    if (array[mid] > key) {
      return binarySearchRecursive(array, key, start, mid);
    }
    else if (array[mid] < key) {
      return binarySearchRecursive(array, key, mid + 1, end);
    }
    else {
      return mid;
    }
  }

  // Implement String.compareTo in another way
  private static int compareStr(String str1, String str2) {
    for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) {
      if (str1.charAt(i) > str2.charAt(i)) {
        return 1;
      }
      else if (str1.charAt(i) < str2.charAt(i)) {
        return -1;
      }
      else if (str1.charAt(i) == str2.charAt(i) && str1.length() == str2.length()) {
        return 0;
      }
    }
    if (str1.length() > str2.length()) {
      return 1;
    }
    else {
      return -1;
    }
  }

  // Binary Search for String[]
  private static int binarySearch(String[] array, String key) {
    Arrays.sort(array);
    return binarySearchRecursive(array, key, 0, array.length);
  }

  private static int binarySearchRecursive(String[] array, String key, int start, int end) {
    if (end - start == 2) {
      if (array[start].equals(key)) {
        return start;
      }
      else if (array[start + 1].equals(key)) {
        return start + 1;
      }
      else {
        return -1;
      }
    }
    int mid = (end - start) / 2;
    if (compareStr(array[mid], key) == 1) {
      return binarySearchRecursive(array, key, start, mid);
    }
    else if (compareStr(array[mid], key) == -1) {
      return binarySearchRecursive(array, key, mid + 1, end);
    }
    else {
      return mid;
    }
  }
}
