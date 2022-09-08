import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
  public static void main(String[] args) {
    List<List<Integer>> randomNumberLists = arrayListOfRandomArrayList(5);
    System.out.println("\n Generated arrayList of random valued arrayLists:");
    printList(randomNumberLists);

    // Insert Sort
    System.out.println("\nINSERTION SORT ALGORITHM:");
    double insertionSortST = System.nanoTime();
    List<List<Integer>> insertSortedList = insertionSortMethod(randomNumberLists, true);
    double insertionSortET = System.nanoTime();
    double insertionSortDurationNS = insertionSortET - insertionSortST;
    double insertionSortDurationMS = insertionSortDurationNS / 1000000;
    System.out.println("  Time Elapased:");
    System.out.println("    " + insertionSortDurationNS + " nanoseconds");
    System.out.println("    " + insertionSortDurationMS + " milliseconds");

    // Sorted
    System.out.println("\nInsertion Sorted:");
    printList(insertSortedList);
  }

  // ~ NUMBER GENERATOR METHODS
  // creates a random arrayList of integers of inputed range
  private static List<Integer> randomArrayList(int range) {
    List<Integer> randomArrayList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < range; i++)
      randomArrayList.add(random.nextInt(range));
    return randomArrayList;
  }

  // creates a an arraylist of generated random arraylists of inputed range
  private static List<List<Integer>> arrayListOfRandomArrayList(int range) {
    List<List<Integer>> arrayListOfRandomArrayList = new ArrayList<>();
    for (int i = 0; i < range; i++)
      arrayListOfRandomArrayList.add(randomArrayList(range));
    return arrayListOfRandomArrayList;
  }

  // ~ SORTING METHODS
  // INSERTION SORT
  private static List<List<Integer>> insertionSortMethod(List<List<Integer>> randomNumberLists, boolean ascending) {
    for (int i = 0; i < randomNumberLists.size(); i++) {
      for (int j = 1; j < randomNumberLists.get(i).size(); j++) {
        int key = randomNumberLists.get(i).get(j);
        int k = j - 1;
        if (ascending)
          while (k >= 0 && randomNumberLists.get(i).get(k) > key) {
            randomNumberLists.get(i).set(k + 1, randomNumberLists.get(i).get(k));
            k--;
          }
        else
          while (k >= 0 && randomNumberLists.get(i).get(k) < key) {
            randomNumberLists.get(i).set(k + 1, randomNumberLists.get(i).get(k));
            k--;
          }
        randomNumberLists.get(i).set(k + 1, key);
      }
    }
    return randomNumberLists;
  }

  // PRINT ELEMENTS IN HUMAN READABLE FORMAT
  private static void printList(List<List<Integer>> randomNumberLists) {
    int max = 0;
    for (int i = 0; i < randomNumberLists.size(); i++)
      for (int j = 0; j < randomNumberLists.get(i).size(); j++)
        if (randomNumberLists.get(i).get(j).toString().length() > max)
          max = randomNumberLists.get(i).get(j).toString().length();
    System.out.println("{");
    for (int i = 0; i < randomNumberLists.size(); i++) {
      System.out.print("  { ");
      for (int j = 0; j < randomNumberLists.get(i).size(); j++) {
        String str = randomNumberLists.get(i).get(j).toString();
        for (int k = 0; k < max - str.length(); k++)
          System.out.print(" ");
        System.out.print(str + (j == randomNumberLists.get(i).size() - 1 ? " " : ", "));
      }
      System.out.println(i == randomNumberLists.size() - 1 ? "}" : "},");
    }
    System.out.println("}");
  }
}
