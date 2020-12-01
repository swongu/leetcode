class Solution
{
    int n;
    int[] nums;
    int[] s;
    
    public int[] productExceptSelf(int[] nums)
    {
        // [bcde acde abde abce abcd]
        // [_    a   ab abc abcd] <-- forwards
        // [bcde cde de e   _   ] <-- backwards
        this.n = nums.length;
        this.nums = nums;
        this.s = new int[n];
        Arrays.fill(s, 1);
        
        if (n == 1) return s;
        
        int p = nums[0];
        for (int i = 1; i < n; ++i)
        {
            s[i] *= p;
            p *= nums[i];
        }
        
        p = nums[n-1];
        for (int i = n - 2; i >= 0; --i)
        {
            s[i] *= p;
            p *= nums[i];
        }
        
        return s;
    }
}
