package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class EmployeeDto {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
    private double salary;
    private String jobTitle;
    private String email;
}
