import java.util.Optional;

class Solution 
{
    int n;
    
    public int kthSmallest(int[][] matrix, int k) 
    {
        this.n = matrix.length;
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> matrix[x(i)][y(i)]));
        queue.add(0);
        while (!queue.isEmpty())
        {
            int i = queue.remove();
            if (visited.contains(i))
            {
                continue;
            }
            
            // System.out.print(i + "..");
            visited.add(i);
            if (visited.size() == k)
            {
                return matrix[x(i)][y(i)];
            }
            
            i(x(i) + 1, y(i)).ifPresent(queue::add);
            i(x(i), y(i) + 1).ifPresent(queue::add);
        }
        
        // Shouldn't reach here
        return matrix[n-1][n-1];
    }
    
    public int x(int i)
    {
        return i / n;
    }
    
    public int y(int i)
    {
        return i % n;
    }
    
    public Optional<Integer> i(int x, int y)
    {
        return (x < n && y < n) ? Optional.of(x*n + y) : Optional.empty();
    }
}
