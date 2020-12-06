class Solution 
{
    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {        
        if (!wordList.contains(endWord))
        {
            return 0;
        }

        Map<String, List<String>> adj = buildAdjacencyList(beginWord, wordList);
        Map<String, Integer> cost = new HashMap<>();
        cost.put(beginWord, 1);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!queue.isEmpty())
        {
            String word = queue.remove();
            if (visited.contains(word))
            {
                continue;
            }
            
            visited.add(word);
            int c = cost.get(word);
            
            if (word.equals(endWord))
            {
                System.out.println("Found length = " + c);
                return c;
            }
            
            Set<String> nexts = transformStates(word)
                .stream()
                .flatMap(state -> adj.get(state).stream())
                .collect(Collectors.toSet());
            
            nexts.stream()
                .filter(next -> !visited.contains(next))
                .peek(next -> cost.put(next, Math.min(cost.getOrDefault(next, Integer.MAX_VALUE), c + 1)))
                .forEach(queue::add);
        }
                         
        return 0;
    }
    
    public static Map<String, List<String>> buildAdjacencyList(String beginWord, List<String> wordList)
    {
        Map<String, List<String>> adj = new HashMap<>();
        transformStates(beginWord).forEach(state -> 
            adj.computeIfAbsent(state, k -> new ArrayList<>()).add(beginWord)
        );
        wordList.forEach(word -> transformStates(word).forEach(state ->
            adj.computeIfAbsent(state, k -> new ArrayList<>()).add(word)
        ));
            
        return adj;
    }
    
    public static List<String> transformStates(String word)
    {
        return IntStream.range(0, word.length())
            .boxed()
            .map(i -> word.substring(0, i) + "*" + word.substring(i + 1))
            .collect(Collectors.toList());
    }
    
    public boolean connected(String l, String r)
    {
        return dist(l, r) == 1;
    }
    
    public int dist(String l, String r)
    {
        return (int)IntStream.range(0, l.length()).filter(i -> l.charAt(i) != r.charAt(i)).count();
    }
}
