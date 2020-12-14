class Node
{
    int val;
    List<Integer> indexes = new ArrayList<>();
    Node left, right;
    public Node(int val)
    {
        this.val = val;
    }
}

class Solution 
{
    public List<Integer> countSmaller(int[] nums) 
    {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        
        Node bst = createTree(sorted, 0, sorted.length);
        
        // Add index to BST
        for (int i = 0; i < nums.length; ++i)
        {
            add(i, nums[i], bst);
        }
        
        // print(bst);
        
        // Descend BST
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i)
        {
            result.add(find(i, nums[i], bst));
        }
        
        return result;
    }
    
    public Node createTree(int[] array, int start, int end)
    {
        if (end - start <= 0)
        {
            return null;
        }
                
        int mid = start + (end - start) / 2;
        int[] midRange = equalRange(array, mid);

        Node node = new Node(array[mid]);
        node.left = createTree(array, start, midRange[0]);
        node.right = createTree(array, midRange[1], end);
        return node;
    }
    
    public int[] equalRange(int[] array, int start)
    {
        int left = start;
        while (left >= 0 && array[left] == array[start])
        {
            --left;
        }
        
        int right = start;
        while (right < array.length && array[right] == array[start])
        {
            ++right;
        }
        
        return new int[]{ left + 1, right };
    }
    
    // public void print(Node node)
    // {
    //     if (node == null) return;
    //     System.out.println("At Node " + node.val + " with indexes " + node.indexes);
    //     System.out.println("Left for " + node.val + ": ");
    //     print(node.left);
    //     System.out.println("Right for " + node.val + ": ");
    //     print(node.right);
    // }
    
    public void add(int index, int value, Node tree)
    {
        if (value == tree.val)
        {
            tree.indexes.add(index);
        }
        else if (value < tree.val)
        {
            add(index, value, tree.left);
        }
        else
        {
            add(index, value, tree.right);
        }
    }
    
    public int find(int index, int value, Node tree)
    {
        if (tree == null) return 0;

        int pos = Collections.binarySearch(tree.indexes, index);
        int count = tree.indexes.size() - (-pos-1);
        
        int left = find(index, value, tree.left);
        int current = (value > tree.val) ? count : 0;
        int right = find(index, value, tree.right);
        
        // System.out.println("At node " + tree.val + " for value " + value + ": l = " + left + ", c = " + current + ", r = " + right);
        // System.out.println("pos " + pos + ", count = " + count);
        
        if (value <= tree.val)
        {
            return left;
        }
        else
        {
            return left + current + right;
        }
    }
} 
