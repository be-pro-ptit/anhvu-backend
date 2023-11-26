package org.proptit.social_media.dto.auth.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegisterOutputDto {
    private Long userId;
    private String username;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
