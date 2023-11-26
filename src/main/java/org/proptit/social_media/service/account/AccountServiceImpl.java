package org.proptit.social_media.service.account;

import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.exeption.ExistsException;
import org.proptit.social_media.exeption.NotFoundException;
import org.proptit.social_media.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity getAccountByUserId(long userId) {
        return accountRepository.findByUserId(userId)
                                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        return accountRepository.findByUsername(username)
                                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Override
    public AccountEntity createAccount(AccountEntity accountEntity) {
        Optional<AccountEntity> accountExist = accountRepository.findByUsername(accountEntity.getUsername());
        if (accountExist.isPresent()) {
            throw new ExistsException("Account already exists");
        }
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity updateAccount(AccountEntity accountEntity) {
        Optional<AccountEntity> accountExist = accountRepository.findByUsername(accountEntity.getUsername());
        if (accountExist.isEmpty()) {
            throw new NotFoundException("Account not found");
        }
        return accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(String username) {
        Optional<AccountEntity> accountExist = accountRepository.findByUsername(username);
        if (accountExist.isEmpty()) {
            throw new NotFoundException("Account not found");
        }
        accountRepository.delete(accountExist.get());

    }
}
