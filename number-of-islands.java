class Solution
{
   int m, n;

   public int numIslands(char[][] grid)
   {
      m = grid.length;
      n = grid[0].length;

      int count = 0;
      for (int i = 0; i < m; ++i)
      {
         for (int j = 0; j < n; ++j)
         {
            if (grid[i][j] == '1')
            {
               count++;
               zero(grid, i, j);
            }
         }
      }

      return count;
   }

   public void zero(char[][] grid, int i, int j)
   {
      if (i < 0 || i > m - 1) return;
      if (j < 0 || j > n - 1) return;
      if (grid[i][j] == '0') return;
      
      grid[i][j] = '0';
      zero(grid, i - 1, j);
      zero(grid, i + 1, j);
      zero(grid, i, j - 1);
      zero(grid, i, j + 1);
   }
}
