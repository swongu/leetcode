import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

class Solution
{
   class Path
   {
      int node;
      int visited; // bitmask
      int length;

      Path(int node)
      {
         this.node = node;
         this.visited = 1 << node;
         this.length = 0;
      }

      Path(int node, Path previous)
      {
         this.node = node;
         this.visited = previous.visited | (1 << node);
         this.length = previous.length + 1;
      }

      @Override
      public boolean equals(Object o)
      {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Path path = (Path) o;
         return node == path.node && visited == path.visited;
      }

      @Override
      public int hashCode()
      {
         return Objects.hash(node, visited);
      }
   }

   public int shortestPathLength(int[][] graph)
   {
//      System.out.println("Stopping when mask = " + ((1 << graph.length) - 1));
      Set<Path> visited = new HashSet<>();

      Deque<Path> queue = new LinkedList<>();
      IntStream.range(0, graph.length)
         .boxed()
         .map(i -> new Path(i))
         .forEach(queue::add);
      while (!queue.isEmpty())
      {
         Path path = queue.pop();
//         System.out.println("Visiting " + path.node + " with length " + path.length + " and visited " + Integer.toBinaryString(path.visited));

         if (path.visited == (1 << graph.length) - 1)
         {
            return path.length;
         }

         visited.add(path);

         IntStream.of(graph[path.node])
            .boxed()
            .map(i -> new Path(i, path))
            .filter(next -> !visited.contains(next))
            .forEach(queue::add);
      }

      return -1;
   }
}
