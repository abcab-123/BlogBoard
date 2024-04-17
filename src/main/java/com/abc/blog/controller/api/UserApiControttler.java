package com.abc.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blog.config.auth.PrincipalDetail;
import com.abc.blog.dto.ResponseDto;
import com.abc.blog.model.RoleType;
import com.abc.blog.model.User;
import com.abc.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserApiControttler {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email

		System.out.println("UserApiController : save 호출됨");

		userService.회원가입(user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		// Jackson을 사용하여 자바 오브젝트를 JSON으로 변환하여 상태코드와 함께 클라이언트에 반환
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) { // key= value , x-www-form-urlencoded
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되기 떄문에 DB에 값은 변경이 됐음
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.

		// 세션 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}