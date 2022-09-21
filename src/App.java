import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
  public static void main(String[] args) {
    List<List<Integer>> randomNumberLists = arrayListOfRandomArrayList(5, 30);
    System.out.println("\nGenerated arrayList of random valued arrayLists:");
    printList(randomNumberLists);

    System.out.println("------------------------------------------------------");

    // INSERT SORT
    System.out.println("\nINSERTION SORT ALGORITHM:");
    double insertionSortST = System.nanoTime();
    List<List<Integer>> insertionList = deepCopy(randomNumberLists);
    insertionSortMethod(insertionList, false);
    double insertionSortET = System.nanoTime();
    double insertionSortDurationMS = (insertionSortET - insertionSortST) / 1000000;
    System.out.println("  Time Elapased:");
    System.out.println("    " + insertionSortDurationMS + " milliseconds");
    System.out.println("\nInsertion Sorted:");
    printList(insertionList);

    System.out.println("------------------------------------------------------");

    // MERGE SORT
    System.out.println("\nMERGE SORT ALGORITHM:");
    double mergeSortST = System.nanoTime();
    List<List<Integer>> mergeSortedList = deepCopy(randomNumberLists);
    mergeSortMethod(mergeSortedList, false);
    double mergeSortET = System.nanoTime();
    double mergeSortDurationMS = (mergeSortET - mergeSortST) / 1000000;
    System.out.println("  Time Elapased:");
    System.out.println("    " + mergeSortDurationMS + " milliseconds");
    System.out.println("\nMerge Sorted:");
    printList(mergeSortedList);

    System.out.println("------------------------------------------------------");

    // QUICK SORT
    System.out.println("\nQUICK SORT ALGORITHM:");
    double quickSortST = System.nanoTime();
    List<List<Integer>> quickSortedList = deepCopy(randomNumberLists);
    quickSortMethod(quickSortedList, true);
    double quickSortET = System.nanoTime();
    double quickSortDurationMS = (quickSortET - quickSortST) / 1000000;
    System.out.println("  Time Elapased:");
    System.out.println("    " + quickSortDurationMS + " milliseconds");
    System.out.println("\nQuick Sorted:");
    printList(quickSortedList);
  }

  // ~ NUMBER GENERATOR METHODS
  private static List<Integer> randomArrayList(int range, int randomRange) {
    List<Integer> randomArrayList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < range; i++) {
      int randomNumber = random.nextInt(randomRange);
      while (randomArrayList.contains(randomNumber))
        randomNumber = random.nextInt(randomRange);
      randomArrayList.add(randomNumber);
    }
    return randomArrayList;
  }

  // creates a an arraylist of generated random arraylists of inputed range
  private static List<List<Integer>> arrayListOfRandomArrayList(int range, int randomRange) {
    List<List<Integer>> arrayListOfRandomArrayList = new ArrayList<>();
    for (int i = 0; i < range; i++)
      arrayListOfRandomArrayList.add(randomArrayList(range, randomRange));
    return arrayListOfRandomArrayList;
  }

  // ~ SORTING METHODS
  // INSERTION SORT
  private static List<List<Integer>> insertionSortMethod(List<List<Integer>> randomNumberLists, boolean ascending) {
    for (int i = 0; i < randomNumberLists.size(); i++) {
      for (int j = 1; j < randomNumberLists.get(i).size(); j++) {
        int key = randomNumberLists.get(i).get(j);
        int k = j - 1;
        while (k >= 0 && (ascending ? randomNumberLists.get(i).get(k) > key : randomNumberLists.get(i).get(k) < key)) {
          randomNumberLists.get(i).set(k + 1, randomNumberLists.get(i).get(k));
          k--;
        }
        randomNumberLists.get(i).set(k + 1, key);
      }
    }
    return randomNumberLists;
  }

  // MERGE SORT
  private static List<List<Integer>> mergeSortMethod(List<List<Integer>> arrayList, boolean ascending) {
    for (int i = 0; i < arrayList.size(); i++)
      arrayList.set(i, mergeSort(arrayList.get(i), ascending));
    return arrayList;
  }

  private static List<Integer> mergeSort(List<Integer> arrayList, boolean ascending) {
    if (arrayList.size() <= 1)
      return arrayList;
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    int middle = arrayList.size() / 2;
    for (int i = 0; i < middle; i++)
      left.add(arrayList.get(i));
    for (int i = middle; i < arrayList.size(); i++)
      right.add(arrayList.get(i));
    left = mergeSort(left, ascending);
    right = mergeSort(right, ascending);
    return merge(left, right, ascending);
  }

  private static List<Integer> merge(List<Integer> left, List<Integer> right, boolean ascending) {
    List<Integer> result = new ArrayList<>();
    while (!left.isEmpty() || !right.isEmpty()) {
      if (!left.isEmpty() && !right.isEmpty()) {
        boolean sortAscending = left.get(0) <= right.get(0);
        boolean sortDescending = left.get(0) >= right.get(0);
        if ((ascending) ? sortAscending : sortDescending) {
          result.add(left.get(0));
          left.remove(0);
        } else {
          result.add(right.get(0));
          right.remove(0);
        }
      } else if (!left.isEmpty()) {
        result.add(left.get(0));
        left.remove(0);
      } else if (!right.isEmpty()) {
        result.add(right.get(0));
        right.remove(0);
      }
    }
    return result;
  }

  // QUICK SORT
  private static List<List<Integer>> quickSortMethod(List<List<Integer>> arrayList, boolean ascending) {
    for (int i = 0; i < arrayList.size(); i++)
      arrayList.set(i, quickSort(arrayList.get(i), ascending));
    return arrayList;
  }

  private static List<Integer> quickSort(List<Integer> arrayList, boolean ascending) {
    if (arrayList.size() <= 1)
      return arrayList;
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    int pivot = arrayList.get(0);
    for (int i = 1; i < arrayList.size(); i++)
      if (ascending ? arrayList.get(i) <= pivot : arrayList.get(i) >= pivot)
        left.add(arrayList.get(i));
      else
        right.add(arrayList.get(i));
    left = quickSort(left, ascending);
    right = quickSort(right, ascending);
    left.add(pivot);
    left.addAll(right);
    return left;
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

  // METHOD TO DEEP COPY ALL THE ELEMENTS OF AN ARRAYLIST TO ANOTHER
  private static List<List<Integer>> deepCopy(List<List<Integer>> original) {
    List<List<Integer>> copy = new ArrayList<>();
    for (int i = 0; i < original.size(); i++) {
      copy.add(new ArrayList<>());
      for (int j = 0; j < original.get(i).size(); j++)
        copy.get(i).add(original.get(i).get(j));
    }
    return copy;
  }
}
