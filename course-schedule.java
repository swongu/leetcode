class Solution 
{
    Map<Integer, List<Integer>> adj;
    public boolean canFinish(int numCourses, int[][] prerequisites) 
    {
        this.adj = buildAdjacencyList(numCourses, prerequisites);
        System.out.println(adj);

        // Detect cycles using DFS
        return adj.keySet().stream().allMatch(i -> !hasCycle(i));
    }
    
    public Map<Integer, List<Integer>> buildAdjacencyList(int numCourses, int[][] prerequisites)
    {
        return Arrays.stream(prerequisites)
            .collect(Collectors.groupingBy(
                i -> i[1],
                Collectors.mapping(i -> i[0], Collectors.toList())
            ));
    }
    
    public boolean hasCycle(int i)
    {
        Set<Integer> stack = new HashSet<>();
        stack.add(i);
        return dfs(i, stack);
    }
    
    public boolean dfs(int i, Set<Integer> stack)
    {
        for (int j: adj.getOrDefault(i, Collections.emptyList()))
        {
            if (stack.contains(j))
            {
                System.out.println("CYCLE: Visiting " + j + " with stack " + stack);
                return true;
            }
            stack.add(j);
            if (dfs(j, stack)) return true;
            stack.remove(j);
        }
        
        return false;
    }
}
