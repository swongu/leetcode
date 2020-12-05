class Solution
{
    public String minWindow(String s, String t)
    {        
        char[] ss = new char[52];
        char[] tt = dissect(t);
        
        int i = 0, j = -1, ws = -1, wt = s.length() + 1;
        while (true)
        {
            // System.out.print("Window is " + s.substring(i, j+1));
            if (isValidWindow(ss, tt))
            {
                // System.out.println("\tGood");
                if (j - i < wt - ws)
                {
                    ws = i;
                    wt = j + 1;
                }
                
                // Shrink valid windows
                ss[getPosition(s.charAt(i++))]--;
            }
            else
            {
                // System.out.println("\tBad");
                // Terminal case
                if (j == s.length() - 1)
                {
                    return ws < 0 ? "" : s.substring(ws, wt);
                }
                
                ss[getPosition(s.charAt(++j))]++;
            }
        }
    }
    
    public static int getPosition(char c)
    {
        return (c <= 'Z') ? c - 'A' : c - 'a' + 26;
    }
    
    public char[] dissect(String t)
    {
        char[] tt = new char[52];
        for (int i = 0; i < t.length(); ++i)
        {
            tt[getPosition(t.charAt(i))]++;
        }
        
        return tt;
    }
    
    public boolean isValidWindow(char[] ss, char[] tt)
    {
        return IntStream.range(0, ss.length).allMatch(i -> ss[i] >= tt[i]);
    }
}
