import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution
{
   Map<Integer, Long> f;

   public int[] topKFrequent(int[] nums, int k)
   {
      f = IntStream.of(nums)
         .boxed()
         .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

      return f.entrySet()
         .stream().sorted((l, r) -> Long.compare(r.getValue(), l.getValue()))
         .limit(k)
         .mapToInt(Map.Entry::getKey)
         .toArray();
   }
}
