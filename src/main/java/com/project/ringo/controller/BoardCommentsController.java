package com.project.ringo.controller;

import java.net.URI;
import java.sql.Date;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.Board;
import com.project.ringo.model.dto.BoardComments;
import com.project.ringo.model.service.BoardCommentsService;
import com.project.ringo.model.service.BoardService;
import com.project.ringo.model.service.BoardServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/api/comments")
@RestController
public class BoardCommentsController{
	
	@Autowired
	BoardCommentsService boardCommentsService; 
	//ResponseEntity<Board>
	
	@GetMapping("/{board_comments_id}")
	protected ResponseEntity<BoardComments> getBoardComment(@PathVariable int board_comments_id) throws Exception {
		BoardComments boardComments = boardCommentsService.getBoardComment(board_comments_id);
		if(boardComments!=null) {
			return new ResponseEntity<BoardComments>(boardComments,HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/list/{board_id}")
	protected ResponseEntity<List<BoardComments>> getBoardCommentsByBoardId(@PathVariable int board_id) throws Exception {
		return new ResponseEntity<List<BoardComments>>(boardCommentsService.getBoardCommentsByBoardId(board_id),HttpStatus.OK);
	}
	
	@PostMapping
	protected ResponseEntity<?> addBoardComment(@RequestBody BoardComments boardComments) throws Exception {
		boolean flag = boardCommentsService.addBoardComment(boardComments);
		if(flag) {
			return ResponseEntity.created(URI.create("/api/comments/"+boardComments.getBoard_comments_id())).build();
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/{board_comments_id}")
	protected ResponseEntity<?> updateBoardComments(@RequestBody BoardComments boardComments) throws Exception {
		boardCommentsService.updateBoardComments(boardComments);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{board_comments_id}")
	protected ResponseEntity<?> deleteBoardComment(@PathVariable("board_comments_id") int board_comments_id) throws Exception {
		BoardComments boardComments = boardCommentsService.getBoardComment(board_comments_id);
		if(boardComments!=null) {
			boardCommentsService.deleteBoardComment(board_comments_id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}

