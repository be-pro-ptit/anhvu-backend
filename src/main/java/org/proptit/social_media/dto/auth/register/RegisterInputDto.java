package org.proptit.social_media.dto.auth.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RegisterInputDto {
    private String username;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
