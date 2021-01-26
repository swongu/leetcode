class Solution 
{
    public int findCircleNum(int[][] M) 
    {
        int n = M.length;
        
        // Friend {i} is at index i. Value is parent node
        int[] f = new int[n];
        IntStream.range(0, n).forEach(i -> f[i] = i);
        
        for (int i = 0; i < n; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                if (M[i][j] == 1)
                {
                    // i and j are friends. Find their roots...
                    int root_i = f[i]; while (root_i != f[root_i]) root_i = f[root_i];
                    int root_j = f[j]; while (root_j != f[root_j]) root_j = f[root_j];
                    // ... and make root_j's root root_i (union-find)
                    f[root_j] = root_i;
                }
            }

            // System.out.println(Arrays.toString(f));
        }

        // Make every node point to root (path compression)
        for (int i = 0; i < n; ++i)
        {
            int root_i = f[i]; while (root_i != f[root_i]) root_i = f[root_i];
            // Make every node point to root_i
            int j = i;
            while (j != root_i)
            {
                int k = f[j];
                f[j] = root_i;
                j = k;
            }
        }

        // System.out.println(Arrays.toString(f));
        return Arrays.stream(f).boxed().collect(Collectors.toSet()).size();
    }
}
