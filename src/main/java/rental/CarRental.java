package rental;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarRental {
    private int idCar;
    private int idPerson;

    public CarRental(int idCar, int idPerson) {
        this.idCar = idCar;
        this.idPerson = idPerson;
    }
}
