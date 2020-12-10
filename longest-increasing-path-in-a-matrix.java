class Solution 
{
    int m, n;
    int[][] matrix;
    boolean[][] global;
    int length = 0;
    
    public int longestIncreasingPath(int[][] matrix) 
    {
        this.m = matrix.length;
        if (m == 0) return 0;
        this.n = matrix[0].length;
        this.matrix = matrix;
        this.global = new boolean[m][n];
        
        for (int i = 0; i < m*n; ++i)
        {
            if (global[x(i)][y(i)]) continue;
            
            System.out.print("Starting search at (" + x(i) + ", " + y(i) + ")... ");
            int desc = bfs(i, (l, r) -> matrix[x(l)][y(l)] > matrix[x(r)][y(r)]);
            int asc  = bfs(i, (l, r) -> matrix[x(l)][y(l)] < matrix[x(r)][y(r)]);
            System.out.print(desc + " + " + asc + " - 1 = " + (desc + asc - 1));
            length = Math.max(length, desc + asc - 1);
        }
        
        return length;
    }
    
    public int x(int i)
    {
        return i / n;
    }
    
    public int y(int i)
    {
        return i % n;
    }
    
    public int i(int x, int y)
    {
        return x * n + y;
    }
    
    public int bfs(int start, BiFunction<Integer, Integer, Boolean> function)
    {
        int maxDist = 0;
        int node = 0;
        
        Map<Integer, Integer> path = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{ start, 1 });
        while (!queue.isEmpty())
        {
            int[] n = queue.remove();
            int i = n[0];
            int dist = n[1];
         
            // System.out.println("..(" + x(i) + ", " + y(i) + "), dist = " + dist);
            if (dist > maxDist)
            {
                maxDist = dist;
                node = i;
            }

            neighbours(i)
                .filter(j -> function.apply(j, i))
                .peek(j -> path.put(j, i))
                .forEach(j -> queue.add(new int[]{ j, dist + 1 }));
        }
        
        int i = node;
        while (path.containsKey(i) && path.containsKey(path.get(i)))
        {
            global[x(i)][y(i)] = true;
            i = path.get(i);
        }

        return maxDist;
    }
    
    public IntStream neighbours(int i)
    {
        return IntStream.concat(
            IntStream.of(x(i) - 1, x(i) + 1)
                .filter(x -> x >= 0 && x <= m - 1)
                .map(x -> i(x, y(i))
            ),
            IntStream.of(y(i) - 1, y(i) + 1)
                .filter(y -> y >= 0 && y <= n - 1)
                .map(y -> i(x(i), y))
        );
    }
}
