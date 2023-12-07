package org.proptit.social_media.dto.post;

import org.proptit.social_media.dto.user.SimpleUserOutputDto;

public class PostOutputDto {
    private long id;
    private String content;
    private SimpleUserOutputDto user;
    private String timestamp;
}
