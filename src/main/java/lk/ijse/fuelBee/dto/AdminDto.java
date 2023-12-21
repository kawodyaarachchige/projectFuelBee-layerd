package lk.ijse.fuelBee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDto {
    public String email;
    public String password;
    public String username;
    public String type;
}
