class Solution 
{
    boolean[][] dp; // (i,j) is true if p.substring(j) matches s.substring(i)
    
    public boolean isMatch(String s, String p) 
    {
        this.dp = init(s.length(), p.length());
        
        boolean isWildcard = false;
        for (int j = p.length() - 1; j >= 0; --j)
        {
            if (p.charAt(j) == '*')
            {
                isWildcard = true;
                continue;
            }
            
            for (int i = s.length(); i >= 0; --i)
            {
                switch (p.charAt(j))
                {
                    case '.':
                        dp[i][j] = isWildcard
                            // Look right 2 and sweep through all cells going down, looking for a true.
                            ? anyTrue(i, s.length(), j+2)
                            // Look down 1 and right 1, unless there are no more characters in S.
                            : (i != s.length() && dp[i+1][j+1]);
                        break;
                    default:
                        dp[i][j] = isWildcard
                            // Look right 2 and sweep through all cells going down, looking for a true when all preceding characters match.
                            ? anyTrue(i, s.length(), j+2, s, p.charAt(j))
                            // Look down 1 and right 1 if characters match, unless there are no more characters in S.
                            : (i != s.length() && dp[i+1][j+1] && s.charAt(i) == p.charAt(j));
                        
                }

                // System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j]);
            }
                            
            isWildcard = false;
        }
        
        return dp[0][0];
    }
    
    public boolean anyTrue(int i0, int i1, int j)
    {
        return IntStream.rangeClosed(i0, i1).anyMatch(i -> dp[i][j]);
    }
    
    public boolean anyTrue(int i0, int i1, int j, String s, char c)
    {
        return IntStream.rangeClosed(i0, i1).anyMatch(i -> dp[i][j]
            // Every character up to position i must match the first character.
            && IntStream.range(i0, i).allMatch(ii -> s.charAt(ii) == c)
        );
    }
    
    public static boolean[][] init(int m, int n)
    {
        boolean[][] dp = new boolean[m+1][];
        for (int i = 0; i < m + 1; ++i)
        {
            dp[i] = new boolean[n+1];
        }
        
        dp[m][n] = true;
        return dp;
    }
}
