public class TreeNodeLevel extends TreeNode
{
   int level;

   TreeNodeLevel(TreeNode node, int level)
   {
      super(node.val, node.left, node.right);
      this.level = level;
   }
}

class Solution
{
   public List<List<Integer>> zigzagLevelOrder(TreeNode root)
   {
      List<List<Integer>> list = new ArrayList<>();

      if (root == null)
      {
         return Collections.emptyList();
      }

      Deque<TreeNodeLevel> queue = new LinkedList<>();
      queue.add(new TreeNodeLevel(root, 0));
      while (!queue.isEmpty())
      {
         int level = Math.min(queue.peekFirst().level, queue.peekLast().level);
         TreeNodeLevel node = (level % 2 == 0) ? queue.removeFirst() : queue.removeLast();
         if (node.level > list.size() - 1)
         {
            list.add(new ArrayList<>());
         }

         list.get(node.level).add(node.val);

         if (level % 2 == 0)
         {
            if (node.left != null)
            {
               queue.addLast(new TreeNodeLevel(node.left, node.level + 1));
            }

            if (node.right != null)
            {
               queue.addLast(new TreeNodeLevel(node.right, node.level + 1));
            }
         }
         else
         {
            if (node.right != null)
            {
               queue.addFirst(new TreeNodeLevel(node.right, node.level + 1));
            }

            if (node.left != null)
            {
               queue.addFirst(new TreeNodeLevel(node.left, node.level + 1));
            }
         }
      }

      return list;
   }
}
