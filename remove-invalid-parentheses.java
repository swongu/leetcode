class Solution 
{
    int length = 0;
    Set<String> solution = new HashSet<>();
    Set<String> visited = new HashSet<>();
    
    public List<String> removeInvalidParentheses(String s) 
    {
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty())
        {
            String next = queue.remove();
            if (visited.contains(next))
            {
                continue;
            }
            
            visited.add(next);
            
            // System.out.println("Trying " + next + "...");
            
            if (next.length() < length)
            {
                // System.out.println("skipping");
                continue;
            }
            
            if (isValid(next))
            {
                // System.out.println("valid");
                solution.add(next);
                length = Math.max(length, next.length());
                continue;
            }
            
            for (int i = 0; i < next.length(); ++i)
            {
                if (next.charAt(i) == '(' || next.charAt(i) == ')')
                {
                    queue.add(next.substring(0, i) + next.substring(i + 1));
                }
            }
        }
        
        return new ArrayList<>(solution);
    }
    
    public boolean isValid(String s)
    {
        int depth = 0;
        for (int i = 0; i < s.length(); ++i)
        {
            switch (s.charAt(i))
            {
                case '(': depth++; break;
                case ')': depth--; break;
            }
            
            if (depth < 0)
            {
                return false;
            }
        }
        
        return depth == 0;
    }
}
