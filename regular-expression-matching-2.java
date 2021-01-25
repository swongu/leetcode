class Solution
{
    String s, p;
    Map<Integer, Map<Integer, Boolean>> cache = new HashMap<>();
    
    public boolean isMatch(String s, String p)
    {
        this.s = s;
        this.p = p;
        
        return bt(0, 0);
    }
    
    private boolean btHelper(int is, int ip)
    {
        if (reject(is, ip))
        {
            return false;
        }
        
        if (accept(is, ip))
        {
            return true;
        }
        
        String token = nextToken(ip);
        
        if (token.length() == 2) // *
        {
            int i = is;
            while (true)
            {
                boolean result = bt(i, ip + 2);
                if (result)
                {
                    return result;
                }
                
                if (i == s.length() || (token.charAt(0) != '.' && s.charAt(i) != token.charAt(0)))
                {
                    break;
                }
                
                ++i;
            }
        }
        else if (is != s.length())
        {
            char cs = s.charAt(is);
            if (token.equals("."))
            {
                return bt(is + 1, ip + 1);
            }
            else if (cs == token.charAt(0))
            {
                return bt(is + 1, ip + 1);
            }
        }
        
        return false;
    }
    
    private boolean reject(int is, int ip)
    {
        boolean send = (is == s.length());
        boolean pend = (ip == p.length());
        return !send && pend;
    }
    
    private boolean accept(int is, int ip)
    {
        boolean send = (is == s.length());
        boolean pend = (ip == p.length());
        return send && pend;
    }
    
    private String nextToken(int ip)
    {
        if (ip + 1 == p.length())
        {
            return p.substring(ip);
        }
        
        if (p.charAt(ip + 1) == '*')
        {
            return p.substring(ip, ip + 2);
        }
        else
        {
            return p.substring(ip, ip + 1);
        }
    }
    
    private boolean bt(int is, int ip)
    {
        if (cache.containsKey(is) && cache.get(is).containsKey(ip))
        {
            System.out.println("(" + is + ", " + ip + ")... cached = " + cache.get(is).get(ip));
            return cache.get(is).get(ip);
        }
        
        boolean result = btHelper(is, ip);
        System.out.println("(" + is + ", " + ip + ")... not cached = " + result);
        cache.computeIfAbsent(is, k -> new HashMap<>()).put(ip, result);
        return result;
    }
}
