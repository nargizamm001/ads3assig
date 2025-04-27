public class BSTMain {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(2, "Two");
        tree.put(8, "Eight");
        tree.put(1, "One");
        tree.put(3, "Three");

        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.key + " and value is " + elem.val);
        }
    }
}

