class Solution 
{
    int m, n;
    
    public void solve(char[][] board) 
    {
        this.m = board.length;
        if (m == 0) return;
        this.n = board[0].length;
        
        Set<Integer> set = IntStream.range(0, m*n)
            .filter(i -> board[x(i)][y(i)] == 'O')
            .boxed()
            .collect(Collectors.toSet());
        
        Set<Integer> visited = new HashSet<>();
        
        Queue<Integer> queue = new LinkedList<>();
        ring(board).forEach(queue::add);
        while (!queue.isEmpty())
        {
            int i = queue.remove();
            if (visited.contains(i)) continue;
            
            // System.out.println("Visited (" + x(i) + ", " + y(i) + ")");
            visited.add(i);
            neighbours(i, set).forEach(queue::add);
        }
        
        set.removeAll(visited);
        set.forEach(i -> board[x(i)][y(i)] = 'X');
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
        return x*n + y;
    }
    
    public IntStream ring(char[][] board)
    {
        return IntStream.concat(
            IntStream.of(0, n - 1)
                .flatMap(y -> IntStream.range(0, m)
                    .filter(x -> board[x][y] == 'O')
                    .map(x -> i(x, y))
                ),
            IntStream.of(0, m - 1)
                .flatMap(x -> IntStream.range(1, n - 1)
                    .filter(y -> board[x][y] == 'O')
                    .map(y -> i(x, y))
                )
            );
    }
    
    public IntStream neighbours(int i, Set<Integer> set)
    {
        return IntStream.concat(
            IntStream.of(x(i) - 1, x(i) + 1)
                .filter(x -> x >= 0 && x < m)
                .map(x -> i(x, y(i))),
            IntStream.of(y(i) - 1, y(i) + 1)
                .filter(y -> y >= 0 && y < n)
                .map(y -> i(x(i), y))
            )
            .filter(set::contains);
    }
}
