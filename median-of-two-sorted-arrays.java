import java.util.Optional;

class Solution 
{
    int[] nums1, nums2;
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        this.nums1 = nums1;
        this.nums2 = nums2;

        int length = nums1.length + nums2.length;
        if (length % 2 == 0)
        {
            // System.out.println("Finding positions " + (length/2 - 1) + " and " + (length/2));
            return (findTarget(length/2 - 1) + findTarget(length/2)) / 2.0;
        }
        else
        {
            // System.out.println("Finding position " + (length/2));
            return findTarget(length/2);
        }
    }
    
    public int findTarget(int target)
    {
        return findTarget(nums1, target).orElseGet(() -> findTarget(nums2, target).get());
    }
    
    // Returns the value in the array containing the nth position in the merged array.
    public Optional<Integer> findTarget(int[] array, int target)
    {
        // System.out.println("Looking for position " + target + " in array " + Arrays.toString(array));
        int start = 0;
        int end = array.length;
        while (start != end)
        {
            int mid = (start + end) / 2;
            int[] range = equalRange(array[mid]);
            // System.out.println("Equal range for " + array[mid] + " is [" + range[0] + ", " + range[1] + ")");
            if (range[0] <= target && target < range[1])
            {
                // System.out.println("Ended at index " + mid);
                return Optional.of(mid).filter(i -> i != array.length).map(i -> array[mid]);
            }
            else if (range[0] < target)
            {
                start = mid + 1;
            }
            else
            {
                end = mid;
            }
        }
        
        return Optional.empty();
    }
    
    // Find start, end index of value in combined array. The start and end differs when a value
    // is found in more than one index in the array.
    public int[] equalRange(int value)
    {
        int[] range1 = new int[] { lowerBound(nums1, value), upperBound(nums1, value) };
        int[] range2 = new int[] { lowerBound(nums2, value), upperBound(nums2, value) };
        return new int[]{ range1[0] + range2[0], range1[1] + range2[1] };
    }
    
    public static int lowerBound(int[] array, int value)
    {
        int start = 0;
        int end = array.length;
        while (start != end)
        {
            int mid = (start + end) / 2;
            if (array[mid] < value) { start = mid + 1; } else { end = mid; }
        }
        
        return start; // same as end
    }
    
    public static int upperBound(int[] array, int value)
    {
        int start = 0;
        int end = array.length;
        while (start != end)
        {
            int mid = (start + end) / 2;
            if (array[mid] <= value) { start = mid + 1; } else { end = mid; }
        }
        
        return start; // same as end
    }
}
