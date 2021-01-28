class Solution 
{
    char[][] board;
    
    public char[][] updateBoard(char[][] board, int[] click) 
    {
        this.board = board;
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(click);
        
        while (!queue.isEmpty())
        {
            int[] p0 = queue.remove();
            switch (board[p0[0]][p0[1]])
            {
                case 'M':
                    board[p0[0]][p0[1]] = 'X';
                    return board;
                case 'E':
                    board[p0[0]][p0[1]] = 'B';
                    int count = getAdjacentMines(p0);
                    if (count > 0)
                    {
                        board[p0[0]][p0[1]] = Integer.toString(count).charAt(0);
                    }
                    else
                    {
                        // Add unrevealed neighbouring squares
                        getUnrevealedNeighbours(p0).forEach(queue::add);
                    }
            }
        }
        
        return board;
    }
    
    private int getAdjacentMines(int[] p0)
    {
        return (int)getNeighbours(p0).filter(p -> board[p[0]][p[1]] == 'M').count();
    }
    
    private Stream<int[]> getUnrevealedNeighbours(int[] p0)
    {
        return getNeighbours(p0).filter(p -> board[p[0]][p[1]] == 'E');
    }
    
    private Stream<int[]> getNeighbours(int[] p0)
    {
        return IntStream.rangeClosed(-1, 1)
            .boxed()
            .flatMap(i -> IntStream.rangeClosed(-1, 1).boxed().map(j -> new int[]{ p0[0] + i, p0[1] + j }))
            .filter(p -> p[0] >= 0 && p[0] < board.length)
            .filter(p -> p[1] >= 0 && p[1] < board[0].length)
            .filter(p -> p[0] != p0[0] || p[1] != p0[1]);
    }
}
