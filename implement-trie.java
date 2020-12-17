class Node
{
    Node[] children = new Node[26];
    boolean end = false;
}

class Trie {
    Node root;
    
    /** Initialize your data structure here. */
    public Trie()
    {
        this.root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word)
    {
        Node current = root;
        for (int i = 0; i < word.length(); ++i)
        {
            int pos = (int)(word.charAt(i) - 'a');
            if (current.children[pos] == null)
            {
                current.children[pos] = new Node();
            }
            
            current = current.children[pos];
        }
        
        current.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) 
    {
        Node current = root;
        for (int i = 0; i < word.length(); ++i)
        {
            int pos = (int)(word.charAt(i) - 'a');
            if (current.children[pos] == null)
            {
                return false;
            }
            
            current = current.children[pos];
        }
        
        return current.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix)
    {
        Node current = root;
        for (int i = 0; i < prefix.length(); ++i)
        {
            int pos = (int)(prefix.charAt(i) - 'a');
            if (current.children[pos] == null)
            {
                return false;
            }
            
            current = current.children[pos];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
