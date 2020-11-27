class Solution
{
   public int lengthOfLongestSubstring(String s)
   {
      StringBuilder builder = new StringBuilder();
      int length = 0;
      for (int i = 0; i < s.length(); ++i)
      {
         char c = s.charAt(i);
         int index = builder.lastIndexOf(String.valueOf(c));
         if (index != -1)
         {
            builder.delete(0, index + 1);
         }

         builder.append(c);
         length = Math.max(length, builder.length());
      }

      return length;
   }
}
