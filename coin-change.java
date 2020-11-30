import java.util.Arrays;

class Solution
{
   int[] coins;
   int[] dp;
   int s = Integer.MAX_VALUE;

   public int coinChange(int[] coins, int amount)
   {
      this.coins = coins;
      this.dp = new int[amount + 1];
      Arrays.fill(dp, Integer.MAX_VALUE);
      dp[0] = 0;

      for (int current = 1; current < amount + 1; ++current)
      {
         for (int coin: coins)
         {
            int previous = current - coin;
            if (previous < 0) continue;
            if (dp[previous] == -1) continue;

            dp[current] = Math.min(dp[current], dp[previous] + 1);
         }
          
         if (dp[current] == Integer.MAX_VALUE)
         {
            dp[current] = -1;
         }
      }
      
      return dp[amount];
   }
}
