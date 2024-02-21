package severino.com.severino.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

public class
RegistrationDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String CPF;
    @NotEmpty
    private String UF;
    @NotEmpty
    private String city;
    @NotEmpty
    private String birthday;
    private String worker;
    private String gender;
}
