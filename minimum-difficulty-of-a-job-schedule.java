class Solution
{
    int[][] dp;
    
    public int minDifficulty(int[] jobDifficulty, int d)
    {
        if (jobDifficulty.length < d)
        {
            return -1;
        }
        
        dp = new int[jobDifficulty.length][];
        
        for (int i = 0; i < dp.length; ++i)
        {
            dp[i] = new int[d];
        }
        
        for (int j = d - 1; j >= 0; --j)
        {
            for (int i = 0; i < jobDifficulty.length - (d - 1 - j); ++i)
            {
                if (j == d - 1)
                {
                    dp[i][j] = IntStream.range(i, jobDifficulty.length).map(l -> jobDifficulty[l]).max().getAsInt();
                    System.out.println("(" + i + ", " + j + ") = " + dp[i][j]);
                }
                else
                {
                    int min = Integer.MAX_VALUE;
                    for (int k = i + 1; k < jobDifficulty.length - (d - 2 - j); ++k)
                    {
                        // System.out.println(IntStream.range(i, k).map(l -> jobDifficulty[l]).max().getAsInt());
                        // System.out.println(dp[k][j+1]);
                        min = Math.min(min, IntStream.range(i, k).map(l -> jobDifficulty[l]).max().getAsInt() + dp[k][j+1]);
                    }
                    
                    dp[i][j] = min;
                    System.out.println("(" + i + ", " + j + ") = " + dp[i][j]);
                }
            }
        }
        
        return dp[0][0];
    }
}
