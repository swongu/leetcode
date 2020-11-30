import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution
{
   public int[] searchRange(int[] nums, int target)
   {
      if (nums.length == 0)
      {
         return new int[]{ -1, -1 };
      }

      int[] lowerBound = search(0, nums.length - 1, i -> nums[i] >= target);
      int[] upperBound = search(0, nums.length - 1, i -> nums[i] > target);
      // System.out.println("lowerBound: (" + lowerBound[0] + ", " + lowerBound[1] + ")");
      // System.out.println("upperBound: (" + upperBound[0] + ", " + upperBound[1] + ")");

      int[] bound = IntStream.concat(IntStream.of(lowerBound), IntStream.of(upperBound))
         .filter(i -> nums[i] == target)
         .toArray();

      if (bound.length == 0)
      {
         return new int[]{ -1, -1 };
      }
      else
      {
         return new int[]{ bound[0], bound[bound.length - 1] };
      }
   }

   public int[] search(int s, int e, Function<Integer, Boolean> condition)
   {
      int i = (e - s) / 2;
      while (e - s > 1)
      {
         // System.out.println("s = " + s + ", i = " + i + ", e = " + e);
         if (condition.apply(i))
         {
            // Lower half
            e = i;
            i = s + (e - s) / 2;
         }
         else
         {
            // Upper half
            s = i;
            i = s + (e - s) / 2;
         }
      }

      return new int[]{ s, e };
   }
}
