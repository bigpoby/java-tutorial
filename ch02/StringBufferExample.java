public class StringBufferExample {
    public static void main(String[] args) {
        // int a = 1;
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(", Java");
        sb.insert(5, " World");
        sb.replace(0, 5, "Hi");
        sb.delete(3, 8);

        System.out.println(sb.toString());
    }
}
