package org.proptit.social_media.service.auth;

import org.proptit.social_media.entity.AccountEntity;
import org.proptit.social_media.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByUsername(username)
                                                       .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                   .username(accountEntity.getUsername())
                   .password(accountEntity.getPassword())
                   .roles(accountEntity.getRole().name())
                   .build();
    }
}
