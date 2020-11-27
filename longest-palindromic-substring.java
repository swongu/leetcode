class Solution
{
  public String longestPalindrome(String s)
  {
     int n = s.length();

     // matrix[i][j] holds whether (s_i...s_j) is a palindrome
     // matrix[i][j] is a palindrome if s[i] == s[j] and (s_i+1...s_j-1) is a palindrome
     boolean[][] matrix = new boolean[n][n];

     String palindrome = "";

     // Go backwards to build matrix, since we need s[i+1] to get s[i].
     for (int i = n - 1; i >= 0; --i)
     {
        for (int j = i; j < n; ++j)
        {
           // True if same position (base case); or if matches above statement.
           matrix[i][j] = (i == j) || (s.charAt(i) == s.charAt(j) && (i + 1 == j || matrix[i+1][j-1]));
           if (matrix[i][j] && (j + 1 - i) > palindrome.length())
           {
              palindrome = s.substring(i, j + 1); 
           }
        }
     }

     return palindrome;
  }
}
