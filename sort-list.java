/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution 
{
    public ListNode sortList(ListNode head) 
    {
        if (head == null)
        {
            return null;
        }
        
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode i = head; i != null; i = i.next)
        {
            queue.add(i);
        }
        
        head = queue.remove();
        ListNode previous = head;
        while (!queue.isEmpty())
        {
            ListNode current = queue.remove();
            previous.next = current;
            previous = current;
        }
        
        previous.next = null;
        return head;
    }
}
