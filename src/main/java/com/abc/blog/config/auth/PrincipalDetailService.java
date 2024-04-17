package com.abc.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abc.blog.model.User;
import com.abc.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌 때 , username, password 변수 2개를 가로챔
    // password 부분 처리는 알아서 함
    //username이 DB에 있는지만 확인해주면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
                });
        System.out.println("PrincipalDetailService에서 principal 값 확인용: "+principal);
        return new PrincipalDetail(principal);  //시큐리티의 세션에 유저 정보가 저장이 됨, 아이디 : user, 패스워드 : 콘솔창에 나오는 비번
    }
}