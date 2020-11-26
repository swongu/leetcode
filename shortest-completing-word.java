import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution
{
   public String shortestCompletingWord(String licensePlate, String[] words)
   {
      List<String> sorted = new ArrayList<>(Arrays.asList(words));
      sorted.sort(Comparator.comparingInt(String::length));
      
      Map<Integer, Long> licensePlateCounts = countLetters(licensePlate);

      return sorted
         .stream()
         .filter(word -> isShortestCompletingWord(licensePlateCounts, word))
         .findFirst()
         .orElseThrow(() -> new IllegalStateException("Expected one correct answer"));
   }

   private boolean isShortestCompletingWord(Map<Integer, Long> licensePlateCounts, String word)
   {
      Map<Integer, Long> counts = countLetters(word);
      return licensePlateCounts.entrySet()
         .stream()
         .allMatch(entry -> counts.getOrDefault(entry.getKey(), 0L) >= entry.getValue());
   }
   
   private Map<Integer, Long> countLetters(String word)
   {
      return word.toLowerCase().chars()
         .filter(i -> i >= 'a' && i <= 'z')
         .boxed()
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
   }
}
