class Solution
{
    List<String> list = new ArrayList<>();
    public String numberToWords(int num)
    {
        if (num == 0)
        {
            return "Zero";
        }
        else
        {
            billions(num);
            return String.join(" ", list).trim();
        }
    }
    
    public void billions(int i)
    {
        if (i >= 1000000000)
        {
            thousands(i / 1000000000);
            list.add("Billion");
        }
        
        millions(i % 1000000000);
    }
    
    public void millions(int i)
    {
        if (i >= 1000000)
        {
            thousands(i / 1000000);
            list.add("Million");
        }
        
        thousands(i % 1000000);
    }
    
    public void thousands(int i)
    {
        if (i >= 1000)
        {
            hundreds(i / 1000);
            list.add("Thousand");
        }
        
        hundreds(i % 1000);
    }
    
    public void hundreds(int i)
    {
        if (i >= 100)
        {
            tens(i / 100);
            list.add("Hundred");
        }
        
        tens(i % 100);
    }
    
    public void tens(int i)
    {
        int j = i % 10;
        switch (i / 10)
        {
            case 0: ones(j); return;
            case 1: list.add(teens(i)); return;
            case 2: list.add("Twenty"); ones(j); return;
            case 3: list.add("Thirty"); ones(j); return;
            case 4: list.add("Forty"); ones(j); return;
            case 5: list.add("Fifty"); ones(j); return;
            case 6: list.add("Sixty"); ones(j); return;
            case 7: list.add("Seventy"); ones(j); return;
            case 8: list.add("Eighty"); ones(j); return;
            case 9: 
            default: list.add("Ninety"); ones(j); return;
        }
    }
    
    public void ones(int i)
    {
        switch (i % 10)
        {
            case 0: return;
            case 1: list.add("One"); return;
            case 2: list.add("Two"); return;
            case 3: list.add("Three"); return;
            case 4: list.add("Four"); return;
            case 5: list.add("Five"); return;
            case 6: list.add("Six"); return;
            case 7: list.add("Seven"); return;
            case 8: list.add("Eight"); return;
            case 9:
            default:list.add("Nine"); return;
        }
    }
    
    public String teens(int i)
    {
        switch (i)
        {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19:
            default: return "Nineteen";
        }
    }
}
