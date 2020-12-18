class Solution 
{
    boolean[][] dp;
    
    public boolean isMatch(String s, String p) 
    {
        this.dp = init(s.length(), p.length());
        
        for (int pi = p.length() - 1; pi >= 0; --pi)
        {
            for (int si = s.length(); si >= 0; --si)
            {
                switch (p.charAt(pi))
                {
                    case '?':
                        // Look down 1 and right 1, unless there are no more characters in S.
                        dp[si][pi] = (si != s.length() && dp[si+1][pi+1]);
                        break;
                    case '*':
                        // Look right 1 and sweep through all cells going down, looking for a true.
                        dp[si][pi] = matchWildcard(si, pi, s.length());
                        break;
                    default:
                        // Look down 1 and right 1 if characters match, unless there are no more characters in S.
                        dp[si][pi] = (si != s.length() && s.charAt(si) == p.charAt(pi) && dp[si+1][pi+1]);
                        break;
                }
            }
        }
        
        return dp[0][0];
    }
    
    public boolean matchWildcard(int si, int pi, int sLength)
    {
        return IntStream.rangeClosed(si, sLength).anyMatch(i -> dp[i][pi+1]);
    }
    
    public static boolean[][] init(int m, int n)
    {
        boolean[][] dp = new boolean[m+1][];
        for (int i = 0; i < m + 1; ++i)
        {
            dp[i] = new boolean[n+1];
            // dp[i][n] = true;
        }
        
        dp[m][n] = true;
        // Arrays.fill(dp[m], true);
        
        return dp;
    }
}
