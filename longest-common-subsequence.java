class Solution
{
    String text1, text2;
    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
    
    public int longestCommonSubsequence(String text1, String text2)
    {
        this.text1 = text1;
        this.text2 = text2;
        return lcs(text1.length() - 1, text2.length() - 1);
    }
    
    public int lcs(int end1, int end2)
    {
        Map<Integer, Integer> map = cache.computeIfAbsent(end1, k -> new HashMap<>());
        if (map.containsKey(end2))
        {
            return map.get(end2);
        }
        else
        {
            int value = lcsHelper(end1, end2);
            map.put(end2, value);
            return value;
        }
    }
    
    public int lcsHelper(int end1, int end2)
    {
        if (end1 == -1 || end2 == -1)
        {
            return 0;
        }
        
        //System.out.println("Computing new LCS for " + text1.substring(0, end1 + 1) + " and " + text2.substring(0, end2 + 1));
        
        char c1 = text1.charAt(end1);
        char c2 = text2.charAt(end2);
        if (c1 == c2)
        {
            return 1 + lcs(end1-1, end2-1);
        }
        else
        {
            return Math.max(lcs(end1-1, end2), lcs(end1, end2-1));
        }
    }
}
