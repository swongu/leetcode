import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

static class Solution
{
   class Grid
   {
      final int[][] grid;
      final int m;
      final int n;

      Grid(int[][] grid)
      {
         this.grid = grid;
         this.m = grid.length;
         this.n = grid[0].length;
      }

      Grid(int m, int n, int fill)
      {
         this.grid = new int[m][n];
         this.m = m;
         this.n = n;
         for (int[] row: grid)
         {
            Arrays.fill(row, fill);
         }
      }

      int get(int i)
      {
         return grid[x(i)][y(i)];
      }

      void set(int i, int value)
      {
         grid[x(i)][y(i)] = value;
      }

      int x(int i)
      {
         return i/m;
      }

      int y(int i)
      {
         return i%m;
      }

      int i(int x, int y)
      {
         return x*m + y;
      }

      int distance(int i)
      {
         return Math.max(Math.abs(x(i) - (m-1)), Math.abs(y(i) - (n-1)));
      }

      boolean isGoal(int i)
      {
         return x(i) == m-1 && y(i) == n-1;
      }

      IntStream surrounding(int i)
      {
         return IntStream.rangeClosed(-1, 1)
            .map(x -> x(i) + x)
            .filter(x -> x >= 0 && x < m)
            .flatMap(x -> IntStream.rangeClosed(-1, 1)
               .map(y -> y(i) + y)
               .filter(y -> y >= 0 && y < n)
               .map(y -> i(x, y))
            );
      }
   }

   public int shortestPathBinaryMatrix(int[][] grid)
   {
      if (grid[0][0] == 1)
      {
         return -1;
      }

      Grid matrix = new Grid(grid);

      // Create and initialize cost grid
      Grid cost = new Grid(matrix.m, matrix.n, Integer.MAX_VALUE);
      cost.set(0, 1);

      Set<Integer> visited = new HashSet<>();
      Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> cost.get(i) + cost.distance(i)));
      queue.add(0);
      while (!queue.isEmpty())
      {
         int i = queue.remove();
         visited.add(i);

         System.out.println("Visiting " + i + " (" + matrix.x(i) + ", " + matrix.y(i) + ") with cost " + cost.get(i));
         if (matrix.isGoal(i))
         {
            return cost.get(i);
         }

         matrix.surrounding(i)
            .filter(i0 -> matrix.get(i0) == 0)
            .peek(i0 -> cost.set(i0, Math.min(cost.get(i0), cost.get(i) + 1)))
            .filter(i0 -> !visited.contains(i0))
            .forEach(queue::add);
      }

      return -1;
   }
}
