class Solution
{
   public List<Integer> inorderTraversal(TreeNode root)
   {
      if (root == null)
      {
         return Collections.emptyList();
      }
      
      List<Integer> list = new ArrayList<>();
      inorder(root, list);
      return list;
   }

   private void inorder(TreeNode current, List<Integer> list)
   {
      if (current.left != null)
      {
         inorder(current.left, list);
      }

      list.add(current.val);

      if (current.right != null)
      {
         inorder(current.right, list);
      }
   }
}
