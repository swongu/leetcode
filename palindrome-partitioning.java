class Solution 
{   
    String s;
    int n;
    List<List<String>> solution = new ArrayList<>();
    
    public List<List<String>> partition(String s) 
    {
        this.s = s;
        this.n = s.length();
        bt(new ArrayList<>(), 0, n);
        return solution;
    }
    
    public void bt(List<String> words, int start, int end)
    {
        if (!words.isEmpty() && reject(words.get(words.size() - 1)))
        {
            return;
        }

        // System.out.println(words + " with " + (end - start) + " letters remaining");
        if (accept(start, end))
        {
            // System.out.println("Accepted!");
            solution.add(new ArrayList<>(words));
            return;
        }
        
        for (int i = start + 1; i <= n; ++i)
        {
            words.add(s.substring(start, i));
            // System.out.println("Adding " + words.get(words.size() - 1));
            bt(words, i, end);
            // System.out.println("Removing " + words.get(words.size() - 1));
            words.remove(words.size() - 1);
        }
    }
    
    // Checks if word is a palindrome
    public static boolean reject(String word)
    {
        if (word.length() == 1)
        {
            return false;
        }
        
        for (int i = 0, j = word.length() - 1; i < j; ++i, --j)
        {
            if (word.charAt(i) != word.charAt(j))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean accept(int start, int end)
    {
        return start == n && end == n;
    }
}
