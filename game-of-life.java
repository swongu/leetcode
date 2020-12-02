class Solution 
{
    int m, n;
    int[][] board;
    
    public void gameOfLife(int[][] board) 
    {
        this.m = board.length;
        this.n = board[0].length;
        this.board = board;
        IntStream.range(0, m).forEach(i -> IntStream.range(0, n).forEach(j ->
            board[i][j] = update(alive(i, j), neighbours(i, j))
        ));
        IntStream.range(0, m).forEach(i -> IntStream.range(0, n).forEach(j ->
            board[i][j] = board[i][j] >> 5
        ));
    }
    
    public int alive(int i, int j)
    {
        return (board[i][j] & 0b1);
    }
    
    public int neighbours(int i, int j)
    {
        return (int)IntStream.rangeClosed(i - 1, i + 1).filter(x -> x >= 0 && x < m)
            .flatMap(x -> IntStream.rangeClosed(j - 1, j + 1).filter(y -> y >= 0 && y < n)
                .filter(y -> alive(x, y) == 1)
            )
            .count() - alive(i, j);
    }
    
    public int update(int alive, int neighbours)
    {
        // System.out.println("alive = " + alive + ", neighbours = " + neighbours);
        int nextAlive = (neighbours == 3) || (alive == 1 && neighbours == 2) ? 1 : 0;
        return (nextAlive << 5) + (neighbours << 1) + alive;
    }
}
