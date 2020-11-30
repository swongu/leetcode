import java.util.ArrayList;
import java.util.List;

class Solution
{
   int n, m;
   char[][] board;
   String word;
   boolean found = false;

   public boolean exist(char[][] board, String word)
   {
      this.m = board.length;
      this.n = board[0].length;
      this.board = board;
      this.word = word;

      for (int[] start: start(word.charAt(0)))
      {
         // System.out.println("Starting position (" + start[0] + ", " + start[1] + ")");
         bt(0, start[0], start[1], visited());
      }

      return found;
   }

   // Find all starting positions of word.
   public List<int[]> start(char c)
   {
      List<int[]> list = new ArrayList<>();
      for (int i = 0; i < m; ++i)
      {
         for (int j = 0; j < n; ++j)
         {
            if (board[i][j] == c)
            {
               list.add(new int[]{ i, j });
            }
         }
      }

      return list;
   }

   // Create grid of visited positions.
   public boolean[][] visited()
   {
      boolean[][] visited = new boolean[m][];
      for (int i = 0; i < visited.length; ++i)
      {
         visited[i] = new boolean[n];
      }

      return visited;
   }

   public void bt(int index, int i, int j, boolean[][] visited)
   {   
      if (reject(index, i, j, visited))
      {
         return;
      }

      // System.out.println("Visting (" + i + ", " + j + ") with letter " + word.charAt(index));
      if (accept(index))
      {
         found = true;
         return;
      }
      
      visited[i][j] = true;

      bt(index + 1, i + 1, j, visited);
      bt(index + 1, i - 1, j, visited);
      bt(index + 1, i, j + 1, visited);
      bt(index + 1, i, j - 1, visited);
       
      visited[i][j] = false;
   }

   public boolean reject(int index, int i, int j, boolean[][] visited)
   {
      if (found) return true;
      if (i < 0 || i >= m) return true;
      if (j < 0 || j >= n) return true;
      if (visited[i][j]) return true;
      return board[i][j] != word.charAt(index);
   }

   public boolean accept(int index)
   {
      return index == word.length() - 1;
   }
}
