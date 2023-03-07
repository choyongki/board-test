package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.controller.request.CommentSaveRequest;
import study.board.service.BoardService;
import study.board.service.CommentService;
import study.board.service.PostService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    // 댓글 조회

    // 댓글 생성
    @PostMapping()
    public String saveComment(CommentSaveRequest commentSaveRequest, Model model) {
        commentService.saveComment(commentSaveRequest.toCommentDTO());

        return "redirect:/post/" + commentSaveRequest.getPostId() + "?boardId=" + commentSaveRequest.getBoardId();
    }

    // 댓글 수정

    // 댓글 삭제
}
