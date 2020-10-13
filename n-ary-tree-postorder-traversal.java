/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution
{
    public List<Integer> postorder(Node root)
    {
        return root == null
            ? Collections.emptyList()
            : iterate(root).collect(Collectors.toList());
    }
    
    public Stream<Integer> iterate(Node node)
    {
        return Stream.concat(
            node.children.stream().flatMap(it -> iterate(it)), 
            Stream.of(node.val)
        );
    }
}
