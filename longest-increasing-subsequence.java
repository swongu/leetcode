class Solution 
{
   public int lengthOfLIS(int[] nums)
   {
      List<Integer> list = new ArrayList<>();

      for (int num: nums)
      {
         // Use binary search to find insertion point
         int i = Collections.binarySearch(list, num);
         if (i == - list.size() - 1)
         {
            // Greater than all existing values; add to end
            list.add(num);
         }
         else
         {
            // Found; replace next highest with this value
            list.set(i < 0 ? - i - 1 : i, num);
         }
      }

      return list.size();
   }
}
