class Solution
{
    // dp[i][j] = true if p.substring(j) is a regex for s.substring(i)
    boolean[][] dp;
    
    public boolean isMatch(String s, String p)
    {
        dp = new boolean[s.length() + 1][];
        for (int i = 0; i < dp.length; ++i)
        {
            dp[i] = new boolean[p.length() + 1];
        }
        
        dp[s.length()][p.length()] = true;
        
        int j = p.length() - 1;
        while (j >= 0)
        {
            if (p.charAt(j) == '*') // Zero or more case
            {
                --j;
                
                for (int i = s.length(); i >= 0; --i)
                {
                    dp[i][j] = p.charAt(j) == '.'
                        ? dp[i][j+2] || (i != s.length() && dp[i+1][j])
                        : dp[i][j+2] || (i != s.length() && dp[i+1][j] && s.charAt(i) == p.charAt(j));
                    System.out.println("(" + i + ", " + j + ") = " + s.substring(i) + ", " + p.substring(j) + " = " + dp[i][j]);
                }
            }
            else // Single character case
            {
                for (int i = s.length(); i >= 0; --i)
                {
                    dp[i][j] = p.charAt(j) == '.'
                        ? i != s.length() && dp[i+1][j+1]
                        : i != s.length() && dp[i+1][j+1] && s.charAt(i) == p.charAt(j);
                    System.out.println("(" + i + ", " + j + ") = " + s.substring(i) + ", " + p.substring(j) + " = " + dp[i][j]);
                }
            }
            
            --j;
        }
        
        return dp[0][0];
    }
}
