class Solution 
{
    int n;
    Map<Integer, List<Integer>> adj;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) 
    {    
        this.n = numCourses;
        
        buildAdjacencyList(prerequisites);
        
        // Order by first course to last course, e.g., 3 -> 0 -> 1 -> 2
        int[] order = topSort();
        
        // Position of course in order, e.g., { 2, 3, 4, 1 }
        int[] position = new int[n];
        IntStream.range(0, n).forEach(i -> position[order[i]] = i);
        
        // Validate topological sort with adjacency list. If failure, then a cycle must have existed
        boolean valid = Arrays.stream(prerequisites)
            .allMatch(i -> position[i[0]] > position[i[1]]);
        
        return valid ? order : new int[0];
    }
    
    public void buildAdjacencyList(int[][] prerequisites)
    {
        this.adj = Arrays.stream(prerequisites)
            .collect(Collectors.groupingBy(
                i -> i[1],
                Collectors.mapping(i -> i[0], Collectors.toList())
            ));
    }
    
    public int[] topSort()
    {
        Deque<Integer> list = new ArrayDeque<>();
        Set<Integer> unvisited = IntStream.range(0, n).boxed().collect(Collectors.toSet());
        while (!unvisited.isEmpty())
        {
            int i = unvisited.stream().findAny().get();
            // System.out.println("Visiting " + i);
            
            dfs(i, unvisited, list);
            // System.out.println("List: " + list);
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    public void dfs(int i, Set<Integer> unvisited, Deque<Integer> list)
    {
        unvisited.remove(i);
        
        for (int j: adj.getOrDefault(i, Collections.emptyList()))
        {
            if (unvisited.contains(j))
            {
                dfs(j, unvisited, list);
            }
        }
        
        list.addFirst(i);
    }
}
