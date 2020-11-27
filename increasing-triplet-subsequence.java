class Solution
{
   public boolean increasingTriplet(int[] nums)
   {
      if (nums.length == 0)
      {
         return false;
      }

      List<Integer> list = new ArrayList<>();
      list.add(nums[0]);

      for (int i = 1; i < nums.length; ++i)
      {
         int index = Collections.binarySearch(list, nums[i]);
         if (index < 0) // Not found
         {
            index = -index-1;
//            System.out.println("Updating array at position " + index);
            if (index == list.size())
            {
               list.add(nums[i]);
            }
            else
            {
               list.set(index, nums[i]);
            }
         }

//         System.out.println(list);
         if (list.size() >= 3)
         {
            return true;
         }
      }

      return false;
   }
}
