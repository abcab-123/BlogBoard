package com.abc.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blog.model.RoleType;
import com.abc.blog.model.User;
import com.abc.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입
	private UserRepository userRepository;

	// save 함수는 id를 전달하지 않으면 insert를 해준다
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해준다
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다
	// email. password

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}

		return "삭제 되었습니다. id: " + id;
	}

	@Transactional // 함수 종료 시에 자동 commit이 됨
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

		System.out.println("id: " + id);
		System.out.println("password: " + requestUser.getPassword());
		System.out.println("email: " + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		return user;

	}

	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {

		return userRepository.findAll();
	}

	// 한 페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public Page<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}

	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 요청한 사용자 id로 DB를 조회할 때 존재하지 않는다면 null이 반환될 수 있다.
		// null이 반환되면 프로그램에 문제가 발생할 수 있다.
		// 그래서 User를 Optional로 감싸서 null 여부를 판단하고 반환한다.

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 웹브라우저가 이해할 수 있는 데이터를 JSON (Gson 라이브러리 사용)으로 변환
		// 스프링부트의 MessageConverter가 응답 시 자동 실행
		// 자바 오브젝트를 리턴하면 MessageConverter의 Jackson 라이브러리가
		// user 오브젝트를 JSON으로 변환해서 브라우저에 전달
		return user;
	}

	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙)

		System.out.println("id: " + user.getId());
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
