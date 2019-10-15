package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Car {
    private int id;
    private String brand;
    private String model;
    private int age;

    public Car(int id, String brand, String model, int age) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.age = age;
    }


}
