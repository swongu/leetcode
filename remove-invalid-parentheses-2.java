class Solution
{
    List<String> solution = new ArrayList<>();
    Set<String> visited = new HashSet<>();
    int max = 0;
    
    public List<String> removeInvalidParentheses(String s)
    {
        bt(s);
        
        return solution.stream().filter(word -> word.length() == max).collect(Collectors.toList());
    }
    
    public void bt(String word)
    {
        if (visited.contains(word))
        {
            return;
        }
        
        visited.add(word);
        btHelper(word);
    }
    
    public void btHelper(String word)
    {
        int depth = depth(word);
        // System.out.println("At " + word + " with depth " + depth);
        
        if (reject(word))
        {
            return;
        }
                
        if (accept(word, depth))
        {
            solution.add(word);
            max = Math.max(max, word.length());
        }
        
        for (int i = 0; i < word.length(); ++i)
        {
            if (word.charAt(i) == '(' || word.charAt(i) == ')')
            {
                bt(word.substring(0, i) + word.substring(i + 1));
            }
        }
    }
    
    public boolean reject(String word)
    {
        if (word.length() < max)
        {
            return true;
        }
        
        return false;
    }
    
    public boolean accept(String word, int depth)
    {
        return depth == 0;
    }
    
    public int depth(String word)
    {
        int depth = 0;
        for (int i = 0; i < word.length(); ++i)
        {
            switch (word.charAt(i))
            {
                case '(': ++depth; break;
                case ')': --depth; break;
            }
            
            if (depth < 0)
            {
                return -1;
            }
        }
        
        return depth;
    }
}
