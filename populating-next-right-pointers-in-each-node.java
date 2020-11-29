class NodeWithLevel
{
   public Node node;
   public int level;
   public NodeWithLevel(Node node, int level)
   {
      this.node = node;
      this.level = level;
   }
}

class Solution
{
   public Node connect(Node root)
   {
      if (root == null)
      {
         return root;
      }

      // BFS
      Queue<NodeWithLevel> queue = new ArrayDeque<>();
      queue.add(new NodeWithLevel(root, 0));
      NodeWithLevel previous = null;
      while (!queue.isEmpty())
      {
         NodeWithLevel current = queue.remove();
         if (previous != null && previous.level == current.level)
         {
            previous.node.next = current.node;
         }

         if (current.node.left != null)
         {
            queue.add(new NodeWithLevel(current.node.left, current.level + 1));
         }

         if (current.node.right != null)
         {
            queue.add(new NodeWithLevel(current.node.right, current.level + 1));
         }

         previous = current;
      }
      
      return root;
   }
}
