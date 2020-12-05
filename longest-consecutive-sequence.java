class Solution
{
    public int longestConsecutive(int[] nums) 
    {
        if (nums.length <= 1)
        {
            return nums.length;
        }
        
        Arrays.sort(nums);
        int longest = 1;
        int start = 0;
        for (int i = 1; i < nums.length; ++i)
        {
            if (nums[i] - nums[i-1] > 1)
            {
                // Start a new count
                longest = Math.max(longest, i - start);
                start = i;
            }
            else if (nums[i] == nums[i-1])
            {
                // Duplicate
                start++;
            }
        }
        
        return Math.max(longest, nums.length - start);
    }
}
