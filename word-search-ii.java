class Node
{
    Node parent;
    char c;
    Node[] children = new Node[26];
    boolean end = false;
    // boolean found = false;
    
    Node(Node parent, char c)
    {
        this.parent = parent;
        this.c = c;
    }
}

class Trie
{
    Node root = new Node(null, '\0');
    
    public void add(String word)
    {
        Node current = root;
        for (int i = 0; i < word.length(); ++i)
        {
            int pos = (int)(word.charAt(i) - 'a');
            // System.out.println(word.charAt(i) + ".." + pos);
            if (current.children[pos] == null)
            {
                current.children[pos] = new Node(current, word.charAt(i));
            }
            
            current = current.children[pos];
        }
        
        current.end = true;
    }
}

class Solution 
{
    Trie trie;
    Set<String> solution = new HashSet<>();
    char[][] board;
    int m, n;
    boolean[][] visited;
    
    public List<String> findWords(char[][] board, String[] words) 
    {
        this.trie = new Trie();
        for (String word: words)
        {
            trie.add(word);
        }
        
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][];
        for (int i = 0; i < m; ++i)
        {
            visited[i] = new boolean[n];
        }
        
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                bt(i, j, trie.root);
            }
        }
        
        return new ArrayList<>(solution);
    }
    
    public void bt(int i, int j, Node node)
    {
        // System.out.println("At (" + i + ", " + j + ")");
        if (reject(i, j, node))
        {
            return;
        }

        int pos = (int)(board[i][j] - 'a');
        node = node.children[pos];

        // System.out.println("Currently " + buildWord(node));

        if (node.end)
        {
            solution.add(buildWord(node));
        }
        
        visited[i][j] = true;
        bt(i + 1, j, node);
        bt(i - 1, j, node);
        bt(i, j + 1, node);
        bt(i, j - 1, node);
        visited[i][j] = false;
        // System.out.println("Backing out " + buildWord(node));
    }
    
    public boolean reject(int i, int j, Node node)
    {
        if (i < 0 || i >= m) return true;
        if (j < 0 || j >= n) return true;
        if (visited[i][j]) return true;

        int pos = (int)(board[i][j] - 'a');
        // System.out.println("(" + i + ", " + j + ") = " + board[i][j] + "..");
        if (node.children[pos] == null) return true;
        
        return false;
    }
    
    public String buildWord(Node node)
    {
        StringBuilder b = new StringBuilder();
        while (node.parent != null)
        {
            b.append(node.c);
            node = node.parent;
        }
        
        return b.reverse().toString();
    }
}
