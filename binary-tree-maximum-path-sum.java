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
    public int maxPathSum(TreeNode root) 
    {
        int leftSum = (root.left != null) ? maxPathSum(root.left) : Integer.MIN_VALUE;
        int rightSum = (root.right != null) ? maxPathSum(root.right) : Integer.MIN_VALUE;
        
        int leftGain = (root.left != null) ? gain(root.left) : 0;
        int rightGain = (root.right != null) ? gain(root.right) : 0;

        int sum_1 = leftSum;
        int sum_2 = leftGain + root.val + rightGain;
        int sum_3 = rightSum;
        return Math.max(sum_1, Math.max(sum_2, sum_3));    
    }
    
    public int gain(TreeNode root)
    {
        int left = (root.left != null) ? gain(root.left) : 0;
        int right = (root.right != null) ? gain(root.right) : 0;
        
        int gain = Math.max(left + root.val, right + root.val);
        return Math.max(0, gain);
    }
}
