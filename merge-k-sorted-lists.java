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
    public ListNode mergeKLists(ListNode[] lists) 
    {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        Arrays.stream(lists).filter(Objects::nonNull).forEach(queue::add);
        
        if (queue.isEmpty())
        {
            return null;
        }

        ListNode head = queue.remove();
        if (head.next != null)
        {
            queue.add(head.next);
        }
        
        ListNode previous = head;
        while (!queue.isEmpty())
        {
            ListNode current = queue.remove();
            previous.next = current;
            if (current.next != null)
            {
                queue.add(current.next);
            }
            
            previous = current;
        }
        
        return head;
    }
}
