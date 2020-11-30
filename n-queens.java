import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution
{
   int n;
   List<List<Integer>> s = new ArrayList<>();

   public List<List<String>> solveNQueens(int n)
   {
      this.n = n;
      bt(new ArrayList<>());
      return print(s);
   }

   public void bt(List<Integer> rows)
   {
      if (reject(rows))
      {
         return;
      }

      if (accept(rows))
      {
         s.add(new ArrayList<>(rows));
      }

      for (int i = 0; i < n; ++i)
      {
         rows.add(i);
         bt(rows);
         rows.remove(rows.size() - 1);
      }
   }

   public boolean reject(List<Integer> rows)
   {
      if (rows.size() > n)
      {
         return true;
      }

      for (int i = 0; i < rows.size(); ++i)
      {
         for (int j = i + 1; j < rows.size(); ++j)
         {
            if (rows.get(i).equals(rows.get(j)))
            {
//               System.out.println("Vertical rejection " + rows + ";\t rows[" + i + "] = " + rows.get(i) + ", rows[" + j + "] = " + rows.get(j));
               return true;
            }

            if (Math.abs(j - i) == Math.abs(rows.get(j) - rows.get(i)))
            {
//               System.out.println("Diagonal rejection " + rows + ";\t rows[" + i + "] = " + rows.get(i) + ", rows[" + j + "] = " + rows.get(j));
               return true;
            }
         }
      }

      return false;
   }

   public boolean accept(List<Integer> rows)
   {
      return rows.size() == n;
   }

   public List<List<String>> print(List<List<Integer>> s)
   {
      return s.stream()
         .map(list -> list.stream().map(this::print).collect(Collectors.toList()))
         .collect(Collectors.toList());
   }

   private String print(int queen)
   {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < n; ++i)
      {
         builder.append((queen == i) ? 'Q' : '.');
      }

      return builder.toString();
   }
}
