class Solution 
{
    public List<Integer> spiralOrder(int[][] matrix) 
    {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        
        int rf = 0;
        int rb = m-1;
        int cf = n-1;
        int cb = 0;
        
        while (true)
        {
            // Top left to top right
            for (int i = cb; i <= cf; ++i)
            {
                list.add(matrix[rf][i]);
            }
            
            if (m * n == list.size()) return list;
            ++rf;
            
            // Top right to bottom right
            for (int i = rf; i <= rb; ++i)
            {
                list.add(matrix[i][cf]);
            }
            
            if (m * n == list.size()) return list;
            --cf;
            
            // Bottom right to bottom left
            for (int i = cf; i >= cb; --i)
            {
                list.add(matrix[rb][i]);
            }
            
            if (m * n == list.size()) return list;
            --rb;
            
            // Bottom right to top left
            for (int i = rb; i >= rf; --i)
            {
                list.add(matrix[i][cb]);
            }
            
            if (m * n == list.size()) return list;
            ++cb;
        }
    }
}
