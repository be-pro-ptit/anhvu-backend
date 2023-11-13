package org.proptit.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDto {
    private String username;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
