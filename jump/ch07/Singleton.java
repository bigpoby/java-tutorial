package ch07;

public class Singleton {
    private static Singleton one;
    private Singleton() {};

    public static Singleton getInstance() {
        if (one==null) {
            one = new Singleton();
        }
        System.out.println("id:" + one.toString());
        return one;
    }
}
