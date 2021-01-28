class MedianFinder
{
    Queue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
    Queue<Integer> upper = new PriorityQueue<>();

    /** initialize your data structure here. */
    public MedianFinder() 
    {
        
    }
    
    public void addNum(int num) 
    {
        if (lower.size() == 0)
        {
            lower.add(num);
            return;
        }
        
        if (lower.peek() >= num)
        {
            lower.add(num);
            
            // Ensure lower has {0,1} elements more than upper
            while (lower.size() - upper.size() > 1)
            {
                upper.add(lower.remove());
            }
        }
        else
        {
            upper.add(num);

            // Ensure lower has {0,1} elements more than upper
            while (upper.size() > lower.size())
            {
                lower.add(upper.remove());
            }
        }
    }
    
    public double findMedian() 
    {
        if (isEven())
        {
            return (lower.peek() + upper.peek()) / 2.0;
        }
        else
        {
            return lower.peek();
        }
    }
    
    public boolean isEven()
    {
        return (lower.size() + upper.size()) % 2 == 0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
