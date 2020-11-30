class Solution
{
   public boolean canJump(int[] nums)
   {
      int i = 0;
      while (true)
      {
         // System.out.println("Advancing from index " + i);
         while (i < nums.length - 1 && nums[i] != 0)
         {
            i += nums[i];
         }
         
         if (i >= nums.length - 1)
         {
            return true;
         }

         // System.out.println("Reached 0 at index " + i);
         boolean skip = false;
         for (int j = i; !skip && j >= 0; --j)
         {
            if (nums[j] + j > i)
            {
               // System.out.println("Can skip past 0 with index " + j + " = " + nums[j]);
               i = nums[j] + j;
               skip = true;
            }
         }
         
         if (!skip)
         {
            // System.out.println("Can't skip past 0 at index " + i);
            return false;
         }
      }
   }
}
