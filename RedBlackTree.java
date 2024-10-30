class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node {
        int value;
        Node left, right, parent;
        boolean color;

        Node(int value) {
            this.value = value;
            this.color = RED;
        }
    }

    private Node root, TNULL;

    public Main() {
        TNULL = new Node(0);
        TNULL.color = BLACK;
        root = TNULL;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != TNULL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null) root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;
                if (u.color == RED) {
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) break;
        }
        root.color = BLACK;
    }

    public void insert(int value) {
        Node node = new Node(value);
        node.parent = null;
        node.left = TNULL;
        node.right = TNULL;

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            if (node.value < x.value) x = x.left;
            else x = x.right;
        }

        node.parent = y;
        if (y == null) root = node;
        else if (node.value < y.value) y.left = node;
        else y.right = node;

        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        if (node.parent.parent == null) return;

        fixInsert(node);
    }

    public void inorder() {
        inorderHelper(this.root);
    }

    private void inorderHelper(Node node) {
        if (node != TNULL) {
            inorderHelper(node.left);
            System.out.print(node.value + " ");
            inorderHelper(node.right);
        }
    }

    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert(52);
        rbt.insert(42);
        rbt.insert(40);
        rbt.insert(73);
        rbt.insert(72);
        rbt.insert(66);
        rbt.insert(89);

        System.out.println("Inorder traversal:");
        rbt.inorder();
    }
}
