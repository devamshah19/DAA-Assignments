class AvlTree {
    class Node {
        int value, height;
        Node left, right;

        Node(int value) {
            this.value = value;
            height = 1;
        }
    }

    private Node root;

    private int height(Node N) {
        return (N == null) ? 0 : N.height;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    // Insertion in AVL Tree
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        // Normal BST insertion
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        } else {
            // Duplicate values not allowed
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // Get the balance factor to check if this node became unbalanced
        int balance = getBalance(node);

        // If the node becomes unbalanced, 4 cases:

        // Left Left Case
        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the unchanged node
        return node;
    }

    // Delete a node from AVL Tree
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node root, int value) {
        // Standard BST delete
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // Node with two children: Get the inorder successor
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's value to this node
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteRec(root.right, temp.value);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // Update height of the current node
        root.height = max(height(root.left), height(root.right)) + 1;

        // Get the balance factor
        int balance = getBalance(root);

        // If the node becomes unbalanced, 4 cases:

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Search a value in AVL Tree
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

    // Inorder traversal of the AVL Tree
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.value + " ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        AvlTree avl = new AvlTree();

        // Insertion
        avl.insert(52);
        avl.insert(42);
        avl.insert(40);
        avl.insert(73);
        avl.insert(72);
        avl.insert(66);
        avl.insert(89);

        System.out.println("Inorder traversal after insertion:");
        avl.inorder();

        // Search
        System.out.println("\nSearch for 30: " + avl.search(66));
        System.out.println("Search for 60: " + avl.search(60));

        // Deletion
        avl.delete(40);
        System.out.println("Inorder traversal after deleting 40:");
        avl.inorder();
    }
}
