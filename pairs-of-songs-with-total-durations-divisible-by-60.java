class Solution
{
    Map<Integer, List<Integer>> map = new HashMap<>(); // Seconds -> index
    public int numPairsDivisibleBy60(int[] time)
    {
        for (int i = 0; i < time.length; ++i)
        {
            int mod = time[i] % 60;
            map.computeIfAbsent(mod, k -> new ArrayList<>()).add(i);
        }
        
        long count = 0;
        for (int i = 0; i < time.length; ++i)
        {
            int k = i;
            int remain = (60 - (time[i] % 60)) % 60;
            count += map.getOrDefault(remain, Collections.emptyList())
                .stream()
                .filter(j -> k < j)
                .count();
        }
        
        return (int)count;
    }
}
