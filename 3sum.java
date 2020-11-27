class Solution
{
   public List<List<Integer>> threeSum(int[] nums)
   {
      Arrays.sort(nums);

      int n = nums.length;

      Set<List<Integer>> triplets = new HashSet<>();
      for (int i = 0; i < n; ++i)
      {
         for (int j = i + 1; j < n; ++j)
         {
            int k = Arrays.binarySearch(nums, - nums[i] - nums[j]);
            if (k >= 0 && k != i && k != j)
            {
               int[] triplet = new int[]{ nums[i], nums[j], nums[k] };
               Arrays.sort(triplet);
               triplets.add(Arrays.asList(triplet[0], triplet[1], triplet[2]));
            }
         }
      }

      return new ArrayList<>(triplets);
   }
}
