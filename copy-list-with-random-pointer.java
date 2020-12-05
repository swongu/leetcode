/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution
{
    public Node copyRandomList(Node head) 
    {
        if (head == null)
        {
            return null;
        }
        
        Map<Node, Node> map = new HashMap<>();

        // Deep copy all nodes; random will be assigned later
        Node copy = new Node(head.val);
        map.put(head, copy);
        for (Node i = head.next, j = copy; i != null; i = i.next, j = j.next)
        {
            j.next = new Node(i.val);
            map.put(i, j.next);
        }
        
        // Assign random field with map from original to copied nodes
        for (Node i = head, j = copy; i != null && j != null; i = i.next, j = j.next)
        {
            j.random = (i.random == null) ? null : map.get(i.random);
        }
        
        return copy;
    }
}
