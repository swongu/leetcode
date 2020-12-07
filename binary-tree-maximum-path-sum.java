/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution 
{
    Map<TreeNode, Integer> map;
    int n;
    int[][] dp;
    int remain;
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) 
    {
        this.map = buildMapping(root);
        this.n = map.size();
        this.dp = new int[map.size()][map.size()];
        this.remain = n * n;
        
        initialize(root);
        
        // Floyd-Warshall
        for (int k = 0; k < n; ++k)
        {
            for (int i = 0; i < n; ++i)
            {
                if (dp[i][k] == Integer.MAX_VALUE)
                {
                    continue;
                }
                
                for (int j = i; j < n; ++j)
                {
                    if (dp[i][j] != Integer.MAX_VALUE || dp[j][k] == Integer.MAX_VALUE)
                    {
                        continue;
                    }
                    
                    // System.out.println("(i, j, k) = (" + i + ", " + j + ", " + k + "), " +
                                       // "dp[i][k] + dp[k][j] - dp[k][k] = " +
                                       // dp[i][k] + " + " + dp[k][j] + " - " + dp[k][k]);
                    set(i, j, dp[i][k] + dp[k][j] - dp[k][k]);
                    if (remain == 0)
                    {
                        // System.out.println("No more entries to fill");
                        return max;
                    }
                }
            }
        }
        
        return max;
    }
    
    public Map<TreeNode, Integer> buildMapping(TreeNode root)
    {
        Map<TreeNode, Integer> map = new HashMap<>();
        int i = 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove();
            map.put(node, i++);
            if (node.left != null)
            {
                queue.add(node.left);
            }
            
            if (node.right != null)
            {
                queue.add(node.right);
            }
        }
        
        return map;
    }
    
    public void initialize(TreeNode root)
    {
        for (int i = 0; i < n; ++i)
        {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.remove();
            int i = map.get(node);
            set(i, i, node.val);
            
            if (node.left != null)
            {
                int j = map.get(node.left);
                set(i, j, node.val + node.left.val);
                queue.add(node.left);
            }
            
            if (node.right != null)
            {
                int j = map.get(node.right);
                set(i, j, node.val + node.right.val);
                queue.add(node.right);
            }
        }
    }
    
    public void set(int i, int j, int val)
    {
        dp[i][j] = dp[j][i] = val;
        max = Math.max(max, val);
        remain -= (i == j) ? 1 : 2;
    }
}
