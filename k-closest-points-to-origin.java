class Point
{
    int x, y, dist;
    public Point(int[] p)
    {
        x = p[0];
        y = p[1];
        dist = x*x + y*y;
    }
}

class Solution
{
    public int[][] kClosest(int[][] points, int K)
    {
        List<Point> list = new ArrayList<>();
        for (int[] p: points)
        {
            list.add(new Point(p));
        }
        
        list.sort(Comparator.comparingInt(p -> p.dist));
        
        int[][] result = new int[K][];
        for (int i = 0; i < K; ++i)
        {
            result[i] = new int[]{ list.get(i).x, list.get(i).y };
        }
        
        return result;
    }
}
