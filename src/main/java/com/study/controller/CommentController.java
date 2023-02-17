package com.study.controller;

import com.study.domain.CommentDeleteRequest;
import com.study.domain.CommentSaveRequest;
import com.study.service.BoardService;
import com.study.service.CommentService;
import com.study.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    /*
    댓글 조회
     */


    /*
    댓글 생성
     */
    @PostMapping()
    public String saveComment(CommentSaveRequest commentSaveRequest, Model model){
        log.info("commentController ::");
        log.info("postid : " ,commentSaveRequest.getPostId());
        commentService.saveComment(commentSaveRequest);
//        model.addAttribute("boardId",commentSaveRequest.getBoardId());


        return "redirect:/post/" + commentSaveRequest.getPostId() + "?boardId=" + commentSaveRequest.getBoardId();
    }


    /*
    댓글 수정
     */

    /*
    댓글 삭제
     */
    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable int id, CommentDeleteRequest commentDeleteRequest, Model model){
        log.info("deleteCommentController :: ");
        commentDeleteRequest.setCommentId(id);
        commentService.deleteComment(commentDeleteRequest);
        return "redirect:/post/" + commentDeleteRequest.getPostId() + "?boardId=" + commentDeleteRequest.getBoardId();
    }


}
