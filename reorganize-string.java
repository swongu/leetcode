class Solution
{
    public String reorganizeString(String S)
    {
        Map<Integer, Long> map = S.chars()
            .boxed()
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        
        long max = map.values().stream().mapToLong(i -> i).max().getAsLong();
        if (max > (S.length() + 1) / 2)
        {
            return "";
        }
        
        StringBuilder s = new StringBuilder();
        
        Queue<Map.Entry<Integer, Long>> queue = new PriorityQueue<>(Map.Entry.<Integer, Long>comparingByValue().reversed());
        map.entrySet().forEach(queue::add);
        while (!queue.isEmpty())
        {
            Map.Entry<Integer, Long> entry = queue.remove();
            if (entry.getValue() > 0)
            {
                // Don't repeat
                if (s.length() > 0 && s.charAt(s.length() - 1) == (char)((int)entry.getKey()))
                {
                    Map.Entry<Integer, Long> entry2 = queue.remove();
                    queue.add(entry);
                    entry = entry2;
                }
                
                s.append((char)((int)entry.getKey()));
                entry.setValue(entry.getValue() - 1);
                queue.add(entry);
            }
        }
        
        return s.toString();
    }
}
