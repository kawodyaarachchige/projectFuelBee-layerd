package lk.ijse.fuelBee.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private double salary;
    private String jobTitle;
    private String email;
}
