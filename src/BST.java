import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size = 0;

    private class Node {
        K key;
        V val;
        Node left, right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, val);
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, val);
                    size++;
                    return;
                }
                current = current.right;
            } else {
                current.val = val; // обновляем значение
                return;
            }
        }
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) return; // нет такого ключа

        // 1. Нет детей
        if (current.left == null && current.right == null) {
            if (parent == null) root = null;
            else if (parent.left == current) parent.left = null;
            else parent.right = null;
        }
        // 2. Один ребёнок
        else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (parent == null) root = child;
            else if (parent.left == current) parent.left = child;
            else parent.right = child;
        }
        // 3. Два ребёнка
        else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.key = successor.key;
            current.val = successor.val;

            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }
        size--;
    }

    public Iterable<Node> iterator() {
        return new Iterable<Node>() {
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {
                    private Stack<Node> stack = new Stack<>();
                    private Node current = root;

                    {
                        pushLeft(current);
                    }

                    private void pushLeft(Node node) {
                        while (node != null) {
                            stack.push(node);
                            node = node.left;
                        }
                    }

                    public boolean hasNext() {
                        return !stack.isEmpty();
                    }

                    public Node next() {
                        Node node = stack.pop();
                        pushLeft(node.right);
                        return node;
                    }
                };
            }
        };
    }

    public int size() {
        return size;
    }
}
