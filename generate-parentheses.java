import java.util.ArrayList;
import java.util.List;

class Solution
{
   int n;
   List<String> s = new ArrayList<>();

   public List<String> generateParenthesis(int n)
   {
      this.n = n;
      bt(n * 2, new StringBuilder());
      return s;
   }

   public void bt(int i, StringBuilder builder)
   {
      // System.out.println("At i = " + i + " with builder = " + builder.toString());
      if (reject(i, builder))
      {
         return;
      }

      if (accept(i, builder))
      {
         s.add(builder.toString());
      }

      builder.append('(');
      bt(i - 1, builder);
      builder.deleteCharAt(builder.length() - 1);
      builder.append(')');
      bt(i - 1, builder);
      builder.deleteCharAt(builder.length() - 1);
   }

   public boolean reject(int i, StringBuilder builder)
   {
      int depth = depth(builder);
      return i < 0 || depth < 0 || depth > n;
   }

   public int depth(StringBuilder builder)
   {
      int depth = 0;
      int openCount = 0;
      for (int i = 0; i < builder.length(); ++i)
      {
         switch (builder.charAt(i))
         {
            case '(': depth++; openCount++; break;
            case ')': depth--; break;
         }

         if (depth < 0)
         {
            return -1;
         }
      }

      return openCount;
   }

   public boolean accept(int i, StringBuilder builder)
   {
      return i == 0 && depth(builder) == n;
   }
}
