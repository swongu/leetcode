class Solution
{
   public ListNode oddEvenList(ListNode head)
   {
      if (head == null)
      {
         return null;
      }

      if (head.next == null)
      {
         return head;
      }

      final ListNode oddHead  = head;
      final ListNode evenHead = head.next;
      ListNode oddTail  = head;
      ListNode evenTail = head.next;
      ListNode current  = head.next;
      
      boolean odd = true;
      while (current.next != null)
      {
         current = current.next;
         
         if (odd)
         {
            System.out.println(oddTail.val + " -> " + current.val);
            oddTail.next = current;
            oddTail = current;
         }
         else
         {
            System.out.println(evenTail.val + " -> " + current.val);
            evenTail.next = current;
            evenTail = current;
         }
         
         odd = !odd;
      }
      
      oddTail.next = evenHead;
      evenTail.next = null;
      return oddHead;
   }
}
