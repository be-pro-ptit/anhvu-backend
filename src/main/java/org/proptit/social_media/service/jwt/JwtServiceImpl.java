package org.proptit.social_media.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.service.account.AccountService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET = "VuNAProPTIT";
    private final AccountService accountService;

    public JwtServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String generateAccessToken(AccountEntity accountEntity) {
        Date now = new Date();
        long exp = now.getTime() + 15 * 60 * 1000;
        return Jwts.builder()
                   .setSubject(accountEntity.getUserId()
                                            .toString())
                   .setIssuedAt(now)
                   .setExpiration(new Date(exp))
                   .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                   .compact();
    }

    @Override
    public void validateAccessToken(String accessToken) {
        Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(accessToken);
    }

    @Override
    public AccountEntity getAccountFromAccessToken(String accessToken) {
        String userId = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(accessToken)
                            .getBody()
                            .getSubject();
        long id = Long.parseLong(userId);
        return accountService.getAccountByUserId(id);
    }
}
