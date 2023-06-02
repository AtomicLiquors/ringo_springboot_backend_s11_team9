package com.project.ringo.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.sql.Date;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.Board;
import com.project.ringo.model.service.BoardService;
import com.project.ringo.model.service.BoardServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/boards")
@RestController
public class BoardController {

	@Autowired
	BoardService boardService;
	// ResponseEntity<Board>

	@GetMapping("/{board_id}")
	protected ResponseEntity<Board> getBoardDetail(@PathVariable int board_id, HttpServletRequest request,
			HttpServletResponse response, @CookieValue(value = "REMEMBER", required = false) Cookie loginCookie)
			throws Exception {
		Board board = boardService.getBoardDetail(board_id);
		if (board != null) {
			if (loginCookie != null) {
				// 받은 쿠키가 있으면

				System.out.println("쿠키가 있음");
				System.out.println(loginCookie.getValue());
			} else if (loginCookie == null) {
				// 받은 쿠키가 없으면
				System.out.println("쿠키가 없음. 쿠키 생성");

				// front에서 쿠키 응답받기 checkBox를 체크 했으면 쿠키 생성

				String cookie_value = "쿠키값_테스트";
				cookie_value = URLEncoder.encode(cookie_value, "utf-8");
				Cookie rememberCookie = new Cookie("REMEMBER", cookie_value);
				rememberCookie.setPath("http://127.0.0.1:9000");
				rememberCookie.setMaxAge(30);
				response.addCookie(rememberCookie);
				System.out.println(rememberCookie.getPath());
				boardService.updateHit(board_id);

			}
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/list/{board_type}")
	protected ResponseEntity<List<Board>> getBoardList(@PathVariable String board_type) throws Exception {
		return new ResponseEntity<List<Board>>(boardService.getBoardList(board_type), HttpStatus.OK);
	}

	@PostMapping
	protected ResponseEntity<?> registerBoard(@RequestBody Board board) throws Exception {
		boolean flag = boardService.insertBoard(board);
		if (flag) {
			return ResponseEntity.created(URI.create("/api/boards/" + board.getBoard_id())).build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{board_id}")
	protected ResponseEntity<?> updateBoard(@RequestBody Board board) throws Exception {
		boardService.updateBoard(board);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{board_id}")
	protected ResponseEntity<?> deleteBoard(@PathVariable("board_id") int board_id) throws Exception {
		Board board = boardService.getBoardDetail(board_id);
		if (board != null) {
			boardService.deleteBoard(board_id);
			;
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
