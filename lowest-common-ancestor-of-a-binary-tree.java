/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class ListNode
{
    TreeNode node;
    ListNode next;
    ListNode(TreeNode node, ListNode next)
    {
        this.node = node;
        this.next = next;
    }
}

class Solution 
{
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {
        return intersect(bfs(root, p), bfs(root, q));
    }
    
    // Standard BFS to find a node
    public ListNode bfs(TreeNode root, TreeNode p)
    {
        Queue<ListNode> queue = new LinkedList<>();
        queue.add(new ListNode(root, null));
        while (!queue.isEmpty())
        {
            ListNode i = queue.remove();
            if (i.node == p)
            {
                return i;
            }
            
            if (i.node.left != null)
            {
                queue.add(new ListNode(i.node.left, i));
            }
            
            if (i.node.right != null)
            {
                queue.add(new ListNode(i.node.right, i));
            }
        }
        
        return null;
    }
    
    // Find intersection point using 'intersection-of-two-linked-lists.java'
    public TreeNode intersect(ListNode l, ListNode r)
    {
        int lSize = length(l);
        int rSize = length(r);
        if (lSize > rSize)
        {
            l = advance(l, lSize - rSize);
        }
        else if (rSize > lSize)
        {
            r = advance(r, rSize - lSize);
        }
        
        while (l.node != r.node)
        {
            l = l.next;
            r = r.next;
        }
        
        return l.node;
    }
    
    public int length(ListNode node)
    {
        int length = 0;
        while (node != null)
        {
            node = node.next;
            ++length;
        }
        
        return length;
    }
    
    public ListNode advance(ListNode node, int steps)
    {
        for (int i = 0; i < steps; ++i)
        {
            node = node.next;
        }
        
        return node;
    }
}
