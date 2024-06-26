package rs.raf.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String salt;

    private String password;

    private Long roleId;

    private Boolean enabled;

}
