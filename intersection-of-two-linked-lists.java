public class Solution
{
   public ListNode getIntersectionNode(ListNode headA, ListNode headB)
   {
      int lengthA = length(headA);
      int lengthB = length(headB);
      if (lengthA > lengthB)
      {
         headA = advance(headA, lengthA - lengthB);
      }
      else if (lengthB > lengthA)
      {
         headB = advance(headB, lengthB - lengthA);
      }
      
      while (true)
      {
         if (headA == headB)
         {
            return headA;
         }
         
         headA = headA.next;
         headB = headB.next;
      }
   }
   
   public int length(ListNode list)
   {
      int length = 0;
      for (ListNode i = list; i != null; i = i.next)
      {
         ++length;
      }
      
      return length;
   }
   
   public ListNode advance(ListNode node, int count)
   {
      for (int i = 0; i < count; ++i)
      {
         node = node.next;
      }
      
      return node;
   }
}
