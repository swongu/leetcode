class Solution
{
    public String minWindow(String s, String t)
    {
        int[] tt = dissect(t);
        
        int i = 0, j = -1, ws = -1, wt = s.length() + 1, remain = t.length();
        while (true)
        {
            // System.out.print("Remain = " + remain +", Window is " + s.substring(i, j+1));
            if (remain == 0)
            {
                // System.out.println("\tGood");
                if (j - i < wt - ws)
                {
                    ws = i;
                    wt = j + 1;
                }
                
                // Shrink valid window
                if (++tt[s.charAt(i++)] > 0) ++remain;
            }
            else
            {
                // System.out.println("\tBad");
                // Terminal case
                if (j == s.length() - 1)
                {
                    return ws < 0 ? "" : s.substring(ws, wt);
                }
                
                if (--tt[s.charAt(++j)] >= 0) --remain;
            }
        }
    }
    
    public int[] dissect(String t)
    {
        int[] tt = new int[128];
        for (int i = 0; i < t.length(); ++i)
        {
            tt[t.charAt(i)]++;
        }

        return tt;
    }
}
