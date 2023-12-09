package org.proptit.social_media.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.proptit.social_media.dto.user.SimpleUserOutputDto;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostOutputDto {
    private long id;
    private String content;
    private SimpleUserOutputDto user;
    private Timestamp timestamp;
}
