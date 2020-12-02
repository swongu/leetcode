class Solution 
{
    public int maxArea(int[] height) 
    {
        int n = height.length;
        int i = 0;
        int j = n - 1;
        int max = area(height, i, j);
        while (i < j)
        {
            int a = area(height, i, j);
            // System.out.println("(" + i + ", " + j + ") = " + a);
            max = Math.max(max, area(height, i, j));
                
            if (height[i] > height[j])
            {
                --j;
            }
            else
            {
                ++i;
            }
        }
        
        return max;
    }
    
    public int area(int[] h, int i, int j)
    {
        return Math.min(h[i], h[j]) * Math.abs(i - j);
    }
}
