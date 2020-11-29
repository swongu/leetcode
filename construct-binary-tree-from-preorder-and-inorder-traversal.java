
public class TreeNodeParent extends TreeNode
{
   TreeNodeParent parent;
   TreeNodeParent(int val, TreeNodeParent parent)
   {
      super(val);
      this.parent = parent;
   }

   TreeNodeParent add(int val, boolean left)
   {
      TreeNodeParent node = new TreeNodeParent(val, this);
      if (left)
      {
         this.left = node;
      }
      else
      {
         this.right = node;
      }

      System.out.println("Added " + (left ? "left" : "right") + " node " + val + " for parent " + this.val);
      return node;
   }
}

class Solution
{
   public TreeNode buildTree(int[] preorder, int[] inorder)
   {
      if (preorder.length == 0)
      {
         return null;
      }

      TreeNodeParent node = null;
      Map<Integer, TreeNodeParent> map = new HashMap<>();
      int p = 0;
      int i = 0;
      boolean left = true;

      while (true)
      {
         while (true)
         {
            node = add(preorder[p], left, node);
            map.put(node.val, node);
            
            boolean match = (preorder[p] == inorder[i]);
            ++p; if (p == preorder.length) { return root(node); }

            if (match) break;

            // Next node is left, since we're in preorder position.
            left = true;
         }

         // Next node is right, since we reached inorder position.
         left = false;

         while (true)
         {
            ++i; if (i == inorder.length) { return root(node); }

            TreeNodeParent seen = map.get(inorder[i]);
            if (seen == null) break;

            // Next node is parent, since we're in inorder position.
            node = seen;
         }
      }
   }

   public TreeNodeParent add(int val, boolean left, TreeNodeParent parent)
   {
      if (parent == null)
      {
         System.out.println("Added node " + val + " as root");
         return new TreeNodeParent(val, null);
      }
      else
      {
         return parent.add(val, left);
      }
   }

   public TreeNodeParent root(TreeNodeParent node)
   {
      while (node.parent != null)
      {
         node = node.parent;
      }

      return node;
   }
}
