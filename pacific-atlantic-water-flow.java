class Solution
{
    int m, n;
    int[][] matrix;
    boolean[][] p, a;
    public List<List<Integer>> pacificAtlantic(int[][] matrix)
    {   
        this.m = matrix.length;
        if (m == 0) return Collections.emptyList();
        
        this.n = matrix[0].length;
        this.matrix = matrix;
        init();

        Queue<int[]> queue = new ArrayDeque<>();
        getPacificStart().forEach(queue::add);
        while (!queue.isEmpty())
        {
            int[] i = queue.remove();
            p[i[0]][i[1]] = true;
            getNeighbours(i).filter(i0 -> !p[i0[0]][i0[1]]).forEach(queue::add);
        }
        
        getAtlanticStart().forEach(queue::add);
        while (!queue.isEmpty())
        {
            int[] i = queue.remove();
            a[i[0]][i[1]] = true;
            getNeighbours(i).filter(i0 -> !a[i0[0]][i0[1]]).forEach(queue::add);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; ++i)
        {
            // System.out.println(Arrays.toString(p[i]));
            // System.out.println(Arrays.toString(a[i]));
            for (int j = 0; j < n; ++j)
            {
                if (p[i][j] && a[i][j])
                {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void init()
    {
        p = new boolean[m][];
        a = new boolean[m][];
        for (int i = 0; i < m; ++i)
        {
            p[i] = new boolean[n];
            a[i] = new boolean[n];
        }
    }
    
    private Stream<int[]> getPacificStart()
    {
        return Stream.concat(
            IntStream.range(0, m).boxed().map(i -> new int[]{ i, 0 }),
            IntStream.range(1, n).boxed().map(j -> new int[]{ 0, j })
        );
    }
    
    private Stream<int[]> getAtlanticStart()
    {
        return Stream.concat(
            IntStream.range(0, m).boxed().map(i -> new int[]{ i, n-1 }),
            IntStream.range(0, n-1).boxed().map(j -> new int[]{ m-1, j })
        );
    }
    
    private Stream<int[]> getNeighbours(int[] i0)
    {
        return Stream.of(
            new int[]{ i0[0]-1, i0[1] },
            new int[]{ i0[0], i0[1]-1 },
            new int[]{ i0[0]+1, i0[1] },
            new int[]{ i0[0], i0[1]+1 }
        )
            .filter(i -> i[0] >= 0 && i[0] < m)
            .filter(i -> i[1] >= 0 && i[1] < n)
            .filter(i -> matrix[i[0]][i[1]] >= matrix[i0[0]][i0[1]]);
    }
}
