package fatk.idelka.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Integer id,
            userStatus;
    private String username,
            firstName,
            lastName,
            email,
            password,
            phone;
}