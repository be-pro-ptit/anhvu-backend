package org.proptit.social_media.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
    private Long userId;
    private String username;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
