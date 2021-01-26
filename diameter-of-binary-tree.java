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
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root)
    {
        height(root);
        return max;
    }
    
    public int height(TreeNode node)
    {
        if (node == null)
        {
            return 0;
        }
        
        int l = height(node.left);
        int r = height(node.right);
        max = Math.max(max, l + r);
        
        return l > r ? l + 1 : r + 1;
    }
}
