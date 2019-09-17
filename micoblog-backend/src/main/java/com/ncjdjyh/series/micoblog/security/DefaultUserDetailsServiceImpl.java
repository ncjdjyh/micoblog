package com.ncjdjyh.series.micoblog.security;

import com.ncjdjyh.series.micoblog.entity.Auth;
import com.ncjdjyh.series.micoblog.entity.User;
import com.ncjdjyh.series.micoblog.service.IAuthService;
import com.ncjdjyh.series.micoblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            List<Auth> userPermission = authService.listByUserId(user.getUserid());
            userPermission.forEach(p -> {
                if (p != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getMd5password(), grantedAuthorities);
    }
}
