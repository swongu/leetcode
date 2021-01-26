class Solution
{
    public String mostCommonWord(String paragraph, String[] banned)
    {
        Map<String, Long> histogram = Arrays.stream(paragraph.split("[ !?',;.]+"))
            .map(word -> word.toLowerCase())
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        
        for (String word: banned)
        {
            histogram.remove(word.toLowerCase());
        }
        
        return histogram.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .get()
            .getKey();
    }
}
