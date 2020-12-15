class Solution
{
    int n;
    int[] nums;
    int[] indexes;
    int[] counts;
    
    public List<Integer> countSmaller(int[] nums)
    {
        this.n = nums.length;
        this.nums = nums;
        this.indexes = new int[n];
        this.counts = new int[n];
        IntStream.range(0, n).forEach(i -> indexes[i] = i);
        mergeSort(0, n);
        return IntStream.of(counts).boxed().collect(Collectors.toList());
    }
    
    public void mergeSort(int start, int end)
    {
        int mid = start + (end - start) / 2;
        if (end - start > 2)
        {
            mergeSort(start, mid);
            mergeSort(mid, end);
        }
        
        // Merge left and right arrays
        int[] temp = new int[end - start];
        int i = start;
        int j = mid;
        int k = 0;
        int r = 0;
        while (i < mid || j < end)
        {
            // if (i != mid) System.out.println("Left = " + indexes[i] + ", " + nums[indexes[i]]);
            // if (j != end) System.out.println("Right = " + indexes[j] + ", " + nums[indexes[j]]);
            if (i == mid || (j != end && nums[indexes[i]] > nums[indexes[j]]))
            {
                // Since right is bigger than left, every remaining item in left array should
                // increase its count by one.
                ++r;

                temp[k++] = indexes[j++];
            }
            else
            {
                // Use the stored count value for this left array item (see above).
                counts[indexes[i]] += r;

                temp[k++] = indexes[i++];
            }
        }
        
        for (int l = 0; l < temp.length; ++l)
        {
            indexes[start + l] = temp[l];
        }
        
        // print(start, end);
    }
    
    // public void print(int start, int end)
    // {
    //     System.out.print("Start = " + start + ", End = " + end);
    //     System.out.print(" [ ");
    //     for (int i = start; i < end; ++i)
    //     {
    //         System.out.print(indexes[i] + " ");
    //     }
    //     System.out.println("]");
    // }
}
