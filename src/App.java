import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
  public static void main(String[] args) {
    List<List<Integer>> randomNumberLists = arrayOfRandomNumbersArray(10);
    System.out.println("\nUnsorted Array of Random Numbers Array");
    printList(randomNumberLists);
  }

  // method to generate a random list of integers of inputed range
  public static List<Integer> randomNumbersArray(int range) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < range; i++)
      list.add(new Random().nextInt(range));
    return list;
  }

  // method to generate a list of randomNumberLists
  public static List<List<Integer>> arrayOfRandomNumbersArray(int range) {
    List<List<Integer>> randomNumberLists = new ArrayList<>();
    for (int i = 0; i < range; i++)
      randomNumberLists.add(randomNumbersArray(range));
    return randomNumberLists;
  }

  // print list in human readable format
  private static void printList(List<List<Integer>> randomNumberLists) {
    System.out.println("{");
    for (int i = 0; i < randomNumberLists.size(); i++) {
      System.out.print("  { ");
      for (int j = 0; j < randomNumberLists.get(i).size(); j++)
        System.out.print(randomNumberLists.get(i).get(j) + (j == randomNumberLists.get(i).size() - 1 ? " " : ", "));
      if (i == randomNumberLists.size() - 1)
        System.out.println("}");
      else
        System.out.println("},");
    }
    System.out.println("}");
  }
}
