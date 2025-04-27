public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(i), "Value" + i);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            MyHashTable.HashNode head = getBucketHead(table, i);
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }

        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(2, "Two");
        tree.put(8, "Eight");
        tree.put(1, "One");
        tree.put(3, "Three");

        for (var elem : tree.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }

    private static MyHashTable.HashNode getBucketHead(MyHashTable table, int index) {
        try {
            java.lang.reflect.Field chainArrayField = MyHashTable.class.getDeclaredField("chainArray");
            chainArrayField.setAccessible(true);
            Object[] chainArray = (Object[]) chainArrayField.get(table);
            return (MyHashTable.HashNode) chainArray[index];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}