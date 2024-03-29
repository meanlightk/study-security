package com.study.security1.auth;


/**
 * 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료가 되면 시큐리티 session 을 만들어준다. (Security ContextHolder: 키값에 세션 정보 저장)
 * 오브젝트 => Authentication 타입 객체
 * Authentication 안에 User 정보가 있어야 함.
 * User 오브젝트타입 => UserDetails 타입 객체
 *
 * Security Session => Authentication(객체) => UserDetails(타입)
 */

import com.study.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;  // 콜포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 것!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();   //ArrayList는 Collection의 자식임
        collect.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료?
    @Override
    public boolean isAccountNonExpired() {
        return true;    // 아니요
    }

    // 계정 잠겼나?
    @Override
    public boolean isAccountNonLocked() {
        return true; // 아니요
    }

    // 비밀번호 너무 오래사용?
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 아니요
    }

    // 계정 활성화 되어있?
    @Override
    public boolean isEnabled() {
        // 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면회원이 되는 것임
        // 현재시간 - 로그인시간 => 1년을 초과하면 return false


        return true;    // 아니요
    }
}
