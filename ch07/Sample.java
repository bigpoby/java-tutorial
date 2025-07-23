package ch07;

import ch07.house.*;

public class Sample {
    public static void main(String[] args) {
        HouseKim kim = new HouseKim();
        HousePark park = new HousePark();

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println(singleton1 == singleton2);
    }
}
