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
    boolean found = false;
    int sum = 0;
    List<Integer> path = new ArrayList<>();
    
    public boolean hasPathSum(TreeNode root, int targetSum)
    {
        dfs(root, targetSum);
        return found;
    }
    
    public void dfs(TreeNode node, int targetSum)
    {
        if (node == null) return;
        
        sum += node.val;
        path.add(node.val);
        
        if (sum == targetSum && node.left == null && node.right == null)
        {
            System.out.println(path.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(" ")));
            found = true;
        }
        
        dfs(node.left, targetSum);
        dfs(node.right, targetSum);
        
        sum -= node.val;
        path.remove(path.size() - 1);
    }
}
