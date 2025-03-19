package BOJ_1759;

import java.io.*;
import java.util.*;

public class Main {
  static int L, C;
  static char[] elements;
  static List<String> results = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // Read L and C
    StringTokenizer st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    // Read the characters
    elements = new char[C];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      elements[i] = st.nextToken().charAt(0);
    }

    // Sort the elements to ensure the combinations are generated in lexicographical
    // order
    Arrays.sort(elements);

    // Separate vowels and consonants
    List<Character> vowels = new ArrayList<>();
    List<Character> consonants = new ArrayList<>();
    for (char c : elements) {
      if ("aeiou".indexOf(c) >= 0) {
        vowels.add(c);
      } else {
        consonants.add(c);
      }
    }

    // Generate valid combinations of vowels and consonants
    generateCombinations(vowels, consonants);

    // Sort the results
    Collections.sort(results);

    // Print the results
    for (String result : results) {
      bw.write(result + "\n");
    }

    bw.flush();
    bw.close();
  }

  // Generate all valid combinations of vowels and consonants
  private static void generateCombinations(List<Character> vowels, List<Character> consonants) {
    // Iterate over all possible numbers of vowels from 1 to min(L-2, vowels.size())
    for (int i = 1; i <= Math.min(L - 2, vowels.size()); i++) {
      // Generate combinations of i vowels
      List<List<Character>> vowelCombinations = getCombinations(vowels, i);

      // Generate combinations of L-i consonants
      List<List<Character>> consonantCombinations = getCombinations(consonants, L - i);

      // Combine vowels and consonants
      for (List<Character> vowelCombination : vowelCombinations) {
        for (List<Character> consonantCombination : consonantCombinations) {
          // Combine and sort the characters, then convert to string
          List<Character> combined = new ArrayList<>(vowelCombination);
          combined.addAll(consonantCombination);
          Collections.sort(combined);
          results.add(new String(toCharArray(combined)));
        }
      }
    }
  }

  // Helper function to generate combinations
  private static List<List<Character>> getCombinations(List<Character> list, int count) {
    List<List<Character>> combinations = new ArrayList<>();
    backtrack(combinations, list, new ArrayList<>(), 0, count);
    return combinations;
  }

  // Helper function to backtrack and generate combinations
  private static void backtrack(List<List<Character>> combinations, List<Character> list,
      List<Character> current, int start, int count) {
    if (current.size() == count) {
      combinations.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < list.size(); i++) {
      current.add(list.get(i));
      backtrack(combinations, list, current, i + 1, count);
      current.remove(current.size() - 1);
    }
  }

  // Convert a list of characters to a char array
  private static char[] toCharArray(List<Character> list) {
    char[] array = new char[list.size()];
    for (int i = 0; i < list.size(); i++) {
      array[i] = list.get(i);
    }
    return array;
  }
}
