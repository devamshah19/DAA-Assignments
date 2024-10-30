class BinarySearchTree {
    class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    public boolean search(int value) {
        return searchRec(root, value) != null;
    }

    private Node searchRec(Node root, int value) {
        if (root == null || root.value == value) {
            return root;
        }
        if (value < root.value) {
            return searchRec(root.left, value);
        }
        return searchRec(root.right, value);
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node root, int value) {
        if (root == null) return root;
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    private int minValue(Node root) {
        int minVal = root.value;
        while (root.left != null) {
            minVal = root.left.value;
            root = root.left;
        }
        return minVal;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(52);
        bst.insert(42);
        bst.insert(40);
        bst.insert(73);
        bst.insert(72);
        bst.insert(66);
        bst.insert(89);

        System.out.println("Inorder traversal:");
        bst.inorder();

        System.out.println("\nSearch for 40: " + bst.search(40));
        System.out.println("Search for 60: " + bst.search(60));

        System.out.println("Delete 40:");
        bst.delete(40);
        bst.inorder();

        System.out.println("\nDelete 52:");
        bst.delete(52);
        bst.inorder();
    }
}
