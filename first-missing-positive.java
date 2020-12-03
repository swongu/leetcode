class Solution 
{
    public int firstMissingPositive(int[] nums) 
    {
        Arrays.sort(nums);
        int i = Arrays.binarySearch(nums, 1);
        if (i < 0)
        {
            return 1;
        }
        
        for (; i + 1 < nums.length; ++i)
        {
            if (nums[i + 1] - nums[i] > 1)
            {
                return nums[i] + 1;
            }
        }
        
        return nums[nums.length - 1] + 1;
    }
}
