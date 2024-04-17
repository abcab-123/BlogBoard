package com.abc.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.blog.config.auth.PrincipalDetail;
import com.abc.blog.dto.ReplySaveRequestDto;
import com.abc.blog.dto.ResponseDto;
import com.abc.blog.model.Board;
import com.abc.blog.model.Reply;
import com.abc.blog.service.BoardService;

@RestController
public class BoardApiControttler {

	@Autowired
	private BoardService boardService;

	// '/api/board' 경로로 오는 POST 요청을 처리하여 새 게시글을 저장.
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// '/api/board/{id}' 경로로 오는 DELETE 요청을 처리하여 해당 ID의 게시글을 삭제.
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 사용자가 '/api/board/{id}' 경로로 PUT 요청을 할 때, 게시글을 찾아 내용을 업데이트.
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 사용자가 '/api/board/{boardId}/reply' 경로로 댓글을 POST 요청할 때, 해당 댓글을 게시판에 저장.
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}