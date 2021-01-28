class Solution
{
    public int lengthOfLIS(int[] nums)
    {
        int[] lis = new int[nums.length];
        
        for (int i = 0; i < nums.length; ++i)
        {
            // System.out.println(Arrays.toString(lis));
            int max = 0;
            for (int j = 0; j < i; ++j)
            {
                if (nums[i] > nums[j])
                {
                    max = Math.max(max, lis[j]);
                }
            }
            
            lis[i] = max + 1;
            // System.out.println(Arrays.toString(lis));
        }
        
        int max = 0;
        for (int i: lis)
        {
            max = Math.max(max, i);
        }
        
        return max;
    }
}
