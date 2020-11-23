import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution
{
   static class Edges
   {
      final Map<Integer, List<Integer>> map = new HashMap<>();

      Edges(int n, int[][] redEdges, int[][] blueEdges)
      {
         for (int[] red: redEdges)
         {
            map.computeIfAbsent(red[0], i -> new ArrayList<>()).add(red[1] + n);
         }

         for (int[] blue: blueEdges)
         {
            map.computeIfAbsent(blue[0] + n, i -> new ArrayList<>()).add(blue[1]);
         }
      }
   }

   public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges)
   {
      // Build graph of combined red & blue edges (0..n-1 = red, n..2n-1 = blue)
      Edges edges = new Edges(n, red_edges, blue_edges);

      // Lowest cost starting with red edge
      int[] red = shortestPath(n, edges, n);

      // Lowest cost starting with blue edge
      int[] blue = shortestPath(n, edges, 0);

      for (int i = 0; i < n; ++i)
      {
         red[i] = Math.min(red[i], blue[i]);
         red[i] = (red[i] == Integer.MAX_VALUE) ? -1 : red[i];
      }

      return red;
   }

   public int[] shortestPath(int n, Edges edges, int start)
   {
      // Build cost array
      int[] cost = new int[n*2];
      Arrays.fill(cost, Integer.MAX_VALUE);
      cost[start] = 0;

      Set<Integer> visited = new HashSet<>();
      Deque<Integer> queue = new ArrayDeque<>();
      queue.add(start);
      while (!queue.isEmpty())
      {
         int i = queue.pop();
         visited.add(i);

         if (i >= n)
         {
            System.out.println("Visiting " + (i - n) + " with blue edge");
         }
         else
         {
            System.out.println("Visiting " + i + " with red edge");
         }

         edges.map.getOrDefault(i, Collections.emptyList())
            .stream()
            .peek(j -> cost[j] = Math.min(cost[j], cost[i] + 1))
            .filter(j -> !visited.contains(j))
            .forEach(queue::add);
      }

      for (int i = 0; i < n; ++i)
      {
         System.out.println("Comparing " + cost[i] + " and " + cost[i+n]);
         cost[i] = Math.min(cost[i], cost[i+n]);
      }

      return Arrays.copyOf(cost, n);
   }
}
