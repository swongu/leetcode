class Solution
{
    public int calPoints(String[] ops)
    {
        List<Integer> scores = new ArrayList<>();
        for (String op: ops)
        {
            switch (op)
            {
                case "+":
                    scores.add(scores.get(scores.size() - 1) + scores.get(scores.size() - 2));
                    break;
                case "D":
                    scores.add(scores.get(scores.size() - 1) * 2);
                    break;
                case "C":
                    scores.remove(scores.size() - 1);
                    break;
                default:
                    scores.add(Integer.parseInt(op));
                    break;
            }
        }
        
        return scores.stream().mapToInt(i -> i).sum();
    }
}
