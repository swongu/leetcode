import java.util.Arrays;

class Solution
{
   int[] coins;
   int s = Integer.MAX_VALUE;

   public int coinChange(int[] coins, int amount)
   {
      this.coins = coins;
      Arrays.sort(coins);

      bt(coins.length - 1, 0, amount);
      return s == Integer.MAX_VALUE ? -1 : s;
   }

   public void bt(int index, int used, int amount)
   {
      if (reject(index, used, amount))
      {
         return;
      }

      if (accept(amount))
      {
         // System.out.println("Found answer using " + used + " coins");
         s = Math.min(used, s);
         return;
      }

      for (int i = amount / coins[index]; i >= 0 && used + i < s; --i)
      {
         // System.out.println("Using " + i + " " + coins[index] + "-coins, " + amount + " reduced to " + (amount - coins[index] * i));
         used += i;
         bt(index - 1, used, amount - coins[index] * i);
         used -= i;
      }
   }

   public boolean reject(int index, int used, int amount)
   {
      return (index < 0 && amount > 0) || used >= s;
   }

   public boolean accept(int amount)
   {
      return amount == 0;
   }
}
