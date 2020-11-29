class Solution
{
   int i = 0;
   int k;
   TreeNode current;
   
   public int kthSmallest(TreeNode root, int k)
   {
      this.k = k;
      inorder(root);
      return current.val;
   }

   public void inorder(TreeNode node)
   {
      if (node.left != null)
      {
         inorder(node.left);
      }

      ++i;

      if (i == k)
      {
         current = node;
         return;
      }
      
      if (node.right != null)
      {
         inorder(node.right);
      }
   }
}
