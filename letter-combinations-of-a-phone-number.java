import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution
{
   int n;
   String digits;
   List<String> list = new ArrayList<>();

   public List<String> letterCombinations(String digits)
   {
      this.n = digits.length();
      this.digits = digits;
      bt(0, new StringBuilder());
      return list;
   }

   public void bt(int i, StringBuilder builder)
   {
      // No solutions are rejected
      boolean reject = false;

      // Check if we've reached the end of candidate string
      boolean accept = accept(i, builder);
      if (accept)
      {
         list.add(builder.toString());
      }

      String chars = chars(i);

      // Generate first extension
      int j = first(chars, builder);

      while (j != -1)
      {
         bt(i + 1, builder);

         // Generate next extension
         j = next(j, chars, builder);
      }
   }

   public boolean accept(int i, StringBuilder b)
   {
      return i == n && b.length() > 0;
   }

   public int first(String chars, StringBuilder builder)
   {
      if (chars.length() == 0)
      {
         return -1;
      }

      builder.append(chars.charAt(0));
      return 1;
   }

   public int next(int j, String chars, StringBuilder builder)
   {
      builder.deleteCharAt(builder.length() - 1);

      if (chars.length() == j)
      {
         return -1;
      }

      builder.append(chars.charAt(j));
      return j + 1;
   }

   public String chars(int i)
   {
      if (i >= digits.length())
      {
         return "";
      }

      switch (digits.charAt(i))
      {
         case '2': return "abc";
         case '3': return "def";
         case '4': return "ghi";
         case '5': return "jkl";
         case '6': return "mno";
         case '7': return "prqs";
         case '8': return "tuv";
         case '9': return "wxyz";
         default:  return "";
      }
   }
}
