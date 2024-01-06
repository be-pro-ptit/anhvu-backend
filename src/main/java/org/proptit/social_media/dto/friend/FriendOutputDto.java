package org.proptit.social_media.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FriendOutputDto {
    private Long id;
    private SimpleUserOutputDto user;
    private OffsetDateTime timestamp;
}
