package com.study.controller;

import com.study.domain.CommentSaveRequest;
import com.study.service.BoardService;
import com.study.service.CommentService;
import com.study.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String saveComment(CommentSaveRequest commentSaveRequest){
        log.info("commentController ::");
        commentService.saveComment(commentSaveRequest);

        return "redirect:/post/" + commentSaveRequest.getPostId();
    }


    /*
    댓글 수정
     */

    /*
    댓글 삭제
     */


}