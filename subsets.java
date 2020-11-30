import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Solution
{
   int n;
   int[] nums;
   List<List<Integer>> s = new ArrayList<>();

   public List<List<Integer>> subsets(int[] nums)
   {
      this.n = nums.length;
      this.nums = nums;
      bt(0, new ArrayList<>());
      return s;
   }

   public void bt(int start, List<Integer> list)
   {
      if (reject(list))
      {
         return;
      }

      if (accept(list))
      {
         s.add(new ArrayList<>(list));
      }

      // System.out.println("start = " + start + ", list = " + list);
      for (int i = start; i < n; ++i)
      {
         list.add(nums[i]);
         bt(i + 1, list);
         list.remove(list.size() - 1);
      }
   }

   public boolean reject(List<Integer> list)
   {
      return new HashSet<>(list).size() != list.size();
   }

   public boolean accept(List<Integer> list)
   {
      return n >= list.size();
   }
}
