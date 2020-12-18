class Solution 
{
    String s, p;
    
    public boolean isMatch(String s, String p) 
    {
        this.s = s;
        this.p = p;
        return bt(0, 0, fixedPatterns(p));
    }
    
    public boolean bt(int si, int pi, int f)
    {
        // System.out.print("At si = " + si + ", pi = " + pi + ", f = " + f + "...");
        if (reject(si, pi))
        {
            System.out.print("At si = " + si + ", pi = " + pi + ", f = " + f + "...");
            System.out.println("reject");
            return false;
        }
        
        if (accept(si, pi))
        {
            // System.out.print("At si = " + si + ", pi = " + pi + ", f = " + f + "...");
            // System.out.println("accept");
            return true;
        }
        
        // System.out.println("sub-patterns");
        switch (p.charAt(pi))
        {
            case '?':
            {
                if (bt(si + 1, pi + 1, f)) return true;
                break;
            }
            case '*':
            {
                int offset = 1;
                while (pi + offset < p.length() && p.charAt(pi + offset) == '*')
                {
                    ++offset;
                }
                
                for (int i = s.length() - f; i >= si; --i)
                {
                    if (bt(i, pi + offset, f)) return true;
                }
                break;
            }
            default:
            {
                if (bt(si + 1, pi + 1, f - 1)) return true;
                break;
            }
        }
        
        return false;
    }
    
    public boolean reject(int si, int pi)
    {
        boolean noMoreLetters = (si > s.length() - 1);
        boolean noMorePattern = (pi > p.length() - 1);
        
        if (noMoreLetters && noMorePattern) return false;
        if (noMorePattern) return true;
        
        switch (p.charAt(pi))
        {
            case '?': return noMoreLetters;
            case '*': return false;
        }
        
        return noMoreLetters || s.charAt(si) != p.charAt(pi);
    }
    
    public boolean accept(int si, int pi)
    {
        boolean noMoreLetters = (si > s.length() - 1);
        boolean noMorePattern = (pi > p.length() - 1);
        boolean lastLetter  = (si == s.length() - 1);
        boolean lastPattern = (pi == p.length() - 1);
        
        if (noMoreLetters && noMorePattern) return true;
        if (!lastPattern) return false;
        
        switch (p.charAt(pi))
        {
            case '?': return lastLetter;
            case '*': return true;
        }
        
        return lastLetter && s.charAt(si) == p.charAt(pi);
    }
    
    public int fixedPatterns(String p)
    {
        return (int)p.chars().filter(i -> i != '?' && i != '*').count();
    }
}
