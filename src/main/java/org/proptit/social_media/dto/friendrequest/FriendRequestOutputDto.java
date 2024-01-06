package org.proptit.social_media.dto.friendrequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FriendRequestOutputDto {
    private Long id;
    private SimpleUserOutputDto other;
}
