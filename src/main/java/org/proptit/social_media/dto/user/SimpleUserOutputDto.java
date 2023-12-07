package org.proptit.social_media.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SimpleUserOutputDto {
    private Long userId;
    private String username;
    private String fullName;
}
