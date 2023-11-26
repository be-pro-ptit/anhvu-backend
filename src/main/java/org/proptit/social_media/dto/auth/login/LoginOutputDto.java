package org.proptit.social_media.dto.auth.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutputDto {
    private String accessToken;
}
