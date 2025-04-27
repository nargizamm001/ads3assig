public class MainTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();

        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(i), "Value" + i);
        }

        // печать количества элементов в каждом бакете
        for (int i = 0; i < 11; i++) {
            int count = 0;
            var head = getBucketHead(table, i);
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
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
