import java.util.Arrays;

class Solution
{
   int[][] matrix;

   public int uniquePaths(int m, int n)
   {
      matrix = new int[m][];
      for (int i = 0; i < m; ++i)
      {
         matrix[i] = new int[n];
         matrix[i][0] = 1;
      }

      Arrays.fill(matrix[0], 1);

      for (int i = 1; i < m; ++i)
      {
         for (int j = 1; j < n; ++j)
         {
            matrix[i][j] = matrix[i-1][j] + matrix[i][j-1];
         }
      }

      return matrix[m-1][n-1];
   }
}
