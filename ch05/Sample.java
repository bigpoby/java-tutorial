public class Sample {
    public static void main(String[] args) {
        ZooKeeper zooKeeper = new ZooKeeper();
        Tiger tiger = new Tiger();
        tiger.getFood();
        tiger.printFood();
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
        // Barkable t4 = new Tiger();
        // Lion t1 = new Lion();
        // Animal t2 = new Lion();
        // Predator t3 = new Lion();
        // Barkable t4 = new Lion();

        Bouncer bouncer = new Bouncer();
        bouncer.barkAnimal(tiger);
        bouncer.barkAnimal(lion);

        // 다형성
        Predator tiger2 = new Tiger();
        tiger2.getFood();
        tiger2.printFood();
        Predator lion2 = new Lion();
        lion2.getFood();
        lion2.printFood();
    }
}
