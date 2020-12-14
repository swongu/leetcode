class Solution
{
    int m, n;
    int[][] matrix, cache;
    int globalMaxLength = 0;
    
    public int longestIncreasingPath(int[][] matrix)
    {
        this.m = matrix.length;
        if (m == 0) return 0;
        this.n = matrix[0].length;
        this.matrix = matrix;

        cache = new int[m][];
        for (int i = 0; i < m; ++i)
        {
            cache[i] = new int[n];
        }
        
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                longestIncreasingPath(i, j, Long.MAX_VALUE);
            }
        }
        
        return globalMaxLength;
    }
    
    public int longestIncreasingPath(int i, int j, long previous)
    {
        // Skip out of bounds
        if (i < 0 || i >= m) return 0;
        if (j < 0 || j >= n) return 0;
        
        // Skip cells that aren't descreasing from previous
        int current = matrix[i][j];
        if (current >= previous) return 0;
        
        int length = cache[i][j];
        if (length > 0) return length;
        
        length = 1 + Math.max(
            Math.max(longestIncreasingPath(i+1, j, current), longestIncreasingPath(i-1, j, current)),
            Math.max(longestIncreasingPath(i, j+1, current), longestIncreasingPath(i, j-1, current))
        );
        
        cache[i][j] = length;
        globalMaxLength = Math.max(globalMaxLength, length);
        return length;
    }
}
