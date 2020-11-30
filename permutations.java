import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution
{
   int n;
   int[] nums;
   List<List<Integer>> s = new ArrayList<>();

   public List<List<Integer>> permute(int[] nums)
   {
      this.n = nums.length;
      this.nums = nums;
      return s;
   }

   public void bt(List<Integer> list)
   {
      if (reject(list))
      {
         return;
      }

      if (accept(list))
      {
         s.add(list);
      }

      for (int i = 0; i < n; ++i)
      {
         list.add(nums[i]);
         bt(list);
         list.remove(list.size() - 1);
      }
   }

   public boolean reject(List<Integer> list)
   {
      return new HashSet<>(list).size() != list.size();
   }

   public boolean accept(List<Integer> list)
   {
      return n == list.size();
   }
}
