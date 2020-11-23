import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution
{
   enum Color { RED, BLUE };

   static class Edges
   {
      final Map<Integer, List<Integer>> map = new HashMap<>();

      Edges(int[][] input)
      {
         for (int[] edge: input)
         {
            map.computeIfAbsent(edge[0], i -> new ArrayList<>()).add(edge[1]);
         }
      }
   }

   public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges)
   {
      class Node
      {
         final int i;
         final Color from;
         final int length;

         Node(int i, Color from, int length)
         {
            this.i = i;
            this.from = from;
            this.length = length;
         }

         int getIndex()
         {
            return from == Color.RED ? i : i + n;
         }
      }

      int[] visited = new int[n*2];
      Arrays.fill(visited, Integer.MAX_VALUE);
      Edges red = new Edges(red_edges);
      Edges blue = new Edges(blue_edges);

      Deque<Node> visit = new ArrayDeque<>();
      visit.add(new Node(0, Color.RED, 0));
      visit.add(new Node(0, Color.BLUE, 0));
      while (!visit.isEmpty())
      {
         Node curr = visit.pop();
         if (curr.length >= visited[curr.getIndex()]) continue;

         visited[curr.getIndex()] = curr.length;
         System.out.println("Visiting " + curr.i + ", " + curr.from + ", " + curr.length);

         Edges color = (curr.from == Color.RED) ? blue : red;
         Color nextFrom = (curr.from == Color.RED) ? Color.BLUE : Color.RED;

         color.map.getOrDefault(curr.i, Collections.emptyList())
            .stream()
            .map(j -> new Node(j, nextFrom, curr.length + 1))
            .filter(next -> visited[next.getIndex()] == Integer.MAX_VALUE)
            .forEach(visit::add);
      }

      int[] result = new int[n];
      for (int i = 0; i < n; ++i)
      {
         System.out.println("Comparing " + visited[i] + " and " + visited[i+n]);

         result[i] = Math.min(visited[i], visited[i+n]);
         if (result[i] == Integer.MAX_VALUE)
         {
            result[i] = -1;
         }
      }

      return result;
   }
}
