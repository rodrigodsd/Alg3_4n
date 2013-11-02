
enum Color { RED, BLACK }

class Node<K extends Comparable<? super K>,V>
{
    public K key;
    public String value;
    public Node<K,V> left;
    public Node<K,V> right;
    public Node<K,V> parent;
    public Color color;

    public Node(K key, String value, Color nodeColor, Node<K,V> left, Node<K,V> right) {
        this.key = key;
        this.value = value;
        this.color = nodeColor;
        this.left = left;
        this.right = right;
        if (left  != null)  left.parent = this;
        if (right != null) right.parent = this;
        this.parent = null;
    }
    public Node<K,V> grandparent() {
        assert parent != null; // Not the root node
        assert parent.parent != null; // Not child of root
        return parent.parent;
    }
    public Node<K,V> sibling() {
        assert parent != null; // Root node has no sibling
        if (this == parent.left)
            return parent.right;
        else
            return parent.left;
    }
    public Node<K,V> uncle() {
        assert parent != null; // Root node has no uncle
        assert parent.parent != null; // Children of root have no uncle
        return parent.sibling();
    }
}
