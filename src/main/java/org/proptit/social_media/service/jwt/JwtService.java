package org.proptit.social_media.service.jwt;

import org.proptit.social_media.entity.AccountEntity;

public interface JwtService {
    String generateAccessToken(AccountEntity accountEntity);

    void validateAccessToken(String accessToken);

    AccountEntity getAccountFromAccessToken(String accessToken);
}
