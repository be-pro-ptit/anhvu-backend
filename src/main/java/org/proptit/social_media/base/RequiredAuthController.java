package org.proptit.social_media.base;

import org.proptit.social_media.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequiredAuthController {
    protected UserEntity getUserEntityFromContext() {
        return (UserEntity) SecurityContextHolder.getContext()
                                                 .getAuthentication()
                                                 .getCredentials();
    }
}
