package kr.co.manager.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.co.manager.domain.jpa.AccountBcrypt;

public class UserDetailsImpl extends User {
    
    public UserDetailsImpl(AccountBcrypt accountBcrypt) {
        super(accountBcrypt.getAccountId(), accountBcrypt.getPassword(), authorities(accountBcrypt));
    }
    
    private static Collection<? extends GrantedAuthority> authorities(AccountBcrypt accountBcrypt){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(accountBcrypt.getRole()));
        return authorities;
    }
}
