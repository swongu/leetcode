class Solution
{
   public void setZeroes(int[][] matrix)
   {
      List<Integer> rows = new ArrayList<>();
      List<Integer> cols = new ArrayList<>();

      int m = matrix.length;
      int n = matrix[0].length;

      IntStream.range(0, m).forEach(i ->
         IntStream.range(0, n).forEach(j ->
         {
            if (matrix[i][j] == 0)
            {
               rows.add(i);
               cols.add(j);
            }
         })
      );

      rows.forEach(i -> IntStream.range(0, n).forEach(j -> matrix[i][j] = 0));
      cols.forEach(j -> IntStream.range(0, m).forEach(i -> matrix[i][j] = 0));
   }
}
