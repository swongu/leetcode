import java.util.Optional;

class Node
{
    Node parent;
    char c;
    Node[] next = new Node[26];
    boolean end = false;

    Node (Node parent, char c)
    {
        this.parent = parent;
        this.c = c;
    }
}

class Trie
{
    Node root = new Node(null, '\0');
    
    Trie()
    {
        
    }
    
    void add(String word)
    {
        Node current = root;
        for (int i = 0; i < word.length(); ++i)
        {
            int j = (int)(word.charAt(i) - 'a');
            if (current.next[j] == null)
            {
                current.next[j] = new Node(current, word.charAt(i));
            }
            
            current = current.next[j];
        }
        
        current.end = true;
    }
    
    boolean search(String word)
    {
        Queue<Pos> queue = new ArrayDeque<>();

        if (word.charAt(0) == '.')
        {
            Arrays.stream(root.next).filter(node -> node != null).forEach(node -> queue.add(new Pos(node, 1)));
        }
        else
        {
            int j = (int)(word.charAt(0) - 'a');
            Optional.ofNullable(root.next[j]).ifPresent(node -> queue.add(new Pos(root.next[j], 1)));
        }
        
        while (!queue.isEmpty())
        {
            Pos pos = queue.remove();
            
            if (pos.i == word.length())
            {
                if (pos.n.end) return true;
                continue;
            }

            if (word.charAt(pos.i) == '.')
            {
                Arrays.stream(pos.n.next).filter(node -> node != null).forEach(node -> queue.add(new Pos(node, pos.i+1)));
            }
            else
            {
                int j = (int)(word.charAt(pos.i) - 'a');
                Optional.ofNullable(pos.n.next[j]).ifPresent(node -> queue.add(new Pos(pos.n.next[j], pos.i+1)));
            }        
        }
                                                 
        return false;
    }
}

class Pos
{
    Node n;
    int i;
    Pos(Node n, int i)
    {
        this.n = n;
        this.i = i;
    }
}

class WordDictionary 
{
    Trie trie = new Trie();
    
    /** Initialize your data structure here. */
    public WordDictionary() 
    {
        
    }
    
    public void addWord(String word) 
    {
        trie.add(word);
    }
    
    public boolean search(String word) 
    {
        return trie.search(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
