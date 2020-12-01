class Solution
{
   public ListNode addTwoNumbers(ListNode l1, ListNode l2)
   {
      ListNode node = addTwoNumbers(l1, l2, 0);
      return node != null ? node : new ListNode(0);
   }

   public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry)
   {
      int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;

      carry = val > 9 ? 1 : 0;
      val = val % 10;

      if (carry == 0 && val == 0 && l1 == null && l2 == null)
      {
         return null;
      }
      else
      {
         return new ListNode(val, addTwoNumbers(
            l1 != null ? l1.next : null,
            l2 != null ? l2.next : null,
            carry
         ));
      }
   }
}
