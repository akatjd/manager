package kr.co.manager.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.manager.domain.jpa.AccountBcrypt;
import kr.co.manager.exception.rdb.AccountBcryptRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private AccountBcryptRepository accountBcryptRepository;
    
    @Transactional
    public String joinUser(AccountBcrypt account) {
        
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountBcryptRepository.save(account).getAccountId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, BadCredentialsException{
        /* 1130: diff password
         * 1131: 없는 아이디
         * 1132: 미승인 아이디
         */
    	
    	// qg2sts용
    	AccountBcrypt account = accountBcryptRepository.findByAccountId(userId);

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(account != null) {
            if("req".equals(account.getApprovalStatus())) {
                throw new BadCredentialsException("1132");
            }else {
                authorities.add(new SimpleGrantedAuthority(account.getRole()));
            }
        }else {
            throw new BadCredentialsException("1131");
        }
        
        return new User(account.getAccountId(), account.getPassword(), authorities);
    }
}
