class Solution 
{
    public int[] maxSlidingWindow(int[] nums, int k) 
    {
        int n = nums.length;
        int[] window = new int[n - k + 1];
        
        // Maintain head to be maximum of window
        Deque<Integer> queue = new ArrayDeque<>();
        
        // i: Start of window, j: End of window
        for (int i = -k + 1, j = 0; j < n; ++i, ++j)
        {
            // Add num[j] to window: remove anything from end that is less than number
            while (!queue.isEmpty() && queue.peekLast() < nums[j])
            {
                queue.removeLast();
            }
            
            queue.addLast(nums[j]);
            
            if (i >= 0)
            {
                // System.out.println(queue);
                window[i] = queue.peekFirst();
                
                // Remove num[i] from window: remove if head equals number
                if (nums[i] == queue.peekFirst())
                {
                    queue.removeFirst();
                }
            }            
        }
        
        return window;
    }
}
