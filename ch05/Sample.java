public class Sample {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        zooKeeper.feed(tiger);
        Lion lion = new Lion();
        zooKeeper.feed(lion);
        Crocodile crocodile = new Crocodile();
        zooKeeper.feed(crocodile);
        Leopard leopard = new Leopard();
        zooKeeper.feed(leopard);

        // Tiger t1 = new Tiger();
        // Animal t2 = new Tiger();
        // Predator t3 = new Tiger();
    }
}
