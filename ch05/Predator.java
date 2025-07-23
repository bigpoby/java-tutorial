abstract class Predator extends Animal {
    abstract String getFood();

    void printFood() {
        System.out.printf("my food is %s", getFood());
    }
}
