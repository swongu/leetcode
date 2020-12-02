class Solution
{
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D)
    {
        int[] e = join(A, B);
        int[] f = join(C, D);
        
        int count = 0;
        for (int i = 0; i < e.length; ++i)
        {
            // System.out.println("Looking for " + (-e[i]) + " in " + Arrays.toString(f));
            int j = Arrays.binarySearch(f, -e[i]);
            if (j >= 0)
            {
                count += equalRange(f, -e[i], j); // Found
            }
        }
        
        return count;
    }
    
    // Returns C++ STL's equal_range() length; number of elements that have same value in sorted array
    private int equalRange(int[] a, int val, int start)
    {
        int count = 1;
        for (int i = start - 1; i >= 0 && a[i] == val; --i)
        {
            ++count;
        }
        
        for (int i = start + 1; i < a.length && a[i] == val; ++i)
        {
            ++count;
        }
        
        // System.out.println("equalRange for " + val + " starting at " + start + " found " + count);
        return count;
    }
    
    public int[] join(int[] a, int[] b)
    {
        int[] c = new int[a.length * b.length];
        for (int i = 0; i < a.length; ++i)
        {
            for (int j = 0; j < b.length; ++j)
            {
                c[i * a.length + j] = a[i] + b[j];
            }
        }
        
        Arrays.sort(c);
        System.out.println(Arrays.toString(c));
        return c;
    }
}
