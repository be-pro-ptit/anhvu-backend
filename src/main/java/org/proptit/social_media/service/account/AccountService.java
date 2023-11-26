package org.proptit.social_media.service.account;

import org.proptit.social_media.entity.AccountEntity;

public interface AccountService {
    AccountEntity getAccountByUserId(long userId);

    AccountEntity getAccountByUsername(String username);

    AccountEntity createAccount(AccountEntity accountEntity);

    AccountEntity updateAccount(AccountEntity accountEntity);

    void deleteAccount(String username);

}
