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
    public void flatten(TreeNode root)
    {
        if (root == null) return;
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNode prev = null;
        while (!queue.isEmpty())
        {
            TreeNode node = queue.removeLast();
            
            if (prev != null)
            {
                prev.left = null;
                prev.right = node;
            }
            
            if (node.right != null)
            {
                queue.addLast(node.right);
            }

            if (node.left != null)
            {
                queue.addLast(node.left);
            }
            
            prev = node;
        }
        
        prev.left = null;
        prev.right = null;
    }
}
