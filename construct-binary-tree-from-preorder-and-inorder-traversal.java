class Solution
{
   int[] preorder;
   int[] inorder;
   Map<Integer, Integer> iIndex = new HashMap<>();

   public TreeNode buildTree(int[] preorder, int[] inorder)
   {
      this.preorder = preorder;
      this.inorder = inorder;

      if (preorder.length == 0)
      {
         return null;
      }

      IntStream.range(0, inorder.length).forEach(i -> iIndex.put(inorder[i], i));
      return buildNode(0, 0, preorder.length);
   }

   // https://www.programcreek.com/2014/06/leetcode-construct-binary-tree-from-preorder-and-inorder-traversal-java/
   //  in-order: [4 2 5] (1) [6 7 3 8]
   // pre-order: (1) [2 4 5] [3 7 6 8]
   // Example:
   //        p = 0, i = 0, length = 8, pivot = 3
   // Left:  p = 1, i = 0, length = 3
   // Right: p = 4, i = 4, length = 4
   public TreeNode buildNode(int p, int i, int length)
   {
      if (length == 0)
      {
         return null;
      }
         
      int val = preorder[p];
      int pivot = iIndex.get(val);
      
      int leftLength = pivot - i;
      
      return new TreeNode(
         val,
         buildNode(p + 1, i, leftLength),
         buildNode(p + 1 + leftLength, pivot + 1, length - leftLength - 1) 
      );
   }
}
