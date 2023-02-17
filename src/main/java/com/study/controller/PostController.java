package com.study.controller;

import com.study.domain.*;
import com.study.file.FileStore;
import com.study.file.UploadFile;
import com.study.service.BoardService;
import com.study.service.CommentService;
import com.study.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final CommentService commentService;
    //private final FileService fileService;

    private final FileStore fileStore;

    // 게시판 별 게시글 리스트 페이지
    @GetMapping()
    public String posts(Model model) {
        List<PostResponse> postList = postService.findAllPost();
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("postList", postList);
        model.addAttribute("boardList",boardList);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String post(@PathVariable Long id , @RequestParam Long boardId, Model model) {
        List<BoardDTO> boardList = boardService.getBoardList();
        BoardDTO board = boardService.getBoardDetail(boardId);
        PostDTO post = postService.findById(id);
        CommentRequest commentRequest = CommentRequest.builder()
                .postId(id)
                .build();

        List<CommentDTO> commentList = commentService.getCommentList(commentRequest);
        model.addAttribute("post", post);
        model.addAttribute("board", board);
        model.addAttribute("boardList", boardList);
        model.addAttribute("commentList",commentList);
        return "post/view";
    }

    // 게시글 수정 페이지
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id , @RequestParam Long boardId, Model model) {
        log.info("updateController ::: ");
        List<BoardDTO> boardList = boardService.getBoardList();
        BoardDTO board = boardService.getBoardDetail(boardId);
        PostDTO post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("board", board);
        model.addAttribute("boardList", boardList);
        return "post/update";
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,@RequestParam Long boardId){
        log.info("deleteController");
        postService.deletePost(id);
        return "redirect:/board/" + boardId.toString();
    }


    // 게시글 작성 페이지
    @GetMapping("/form")
    public String postForm(@RequestParam final Long boardId, Model model) {
        log.info("boardId : ",boardId);
        List<BoardDTO> boardList = boardService.getBoardList();
        BoardDTO board = boardService.getBoardDetail(boardId);
        model.addAttribute("boardList", boardList);
        model.addAttribute("board", board);
        return "post/write";
    }

    // 신규 게시글 생성
    @PostMapping("/form/{id}")
    public String savePost(@PathVariable Long id , final PostRequest params) throws IOException {
        log.info("PostController :: savePost");
        PostDTO postDTO = PostDTO.builder()
                .writerId(params.getWriterId())
                .content(params.getContent())
                .title(params.getTitle())
                .password(params.getPassword())
                .noticeYn(params.getNoticeYn())
                .boardId(id)
                .build();

        // 첨부파일을 처리하는 부분
        log.info("file : {}",params.getAttachFile());


        UploadFile attachFile = fileStore.storeFile(params.getAttachFile());

        postDTO.setAttachFile(attachFile);
        postService.savePost(postDTO);

        return "redirect:/board/" + id.toString();
    }

    // 기존 게시글 수정
    @PostMapping("/update")
    public String updatePost(final PostRequest params) {
        log.info(params.toString());
        postService.updatePost(params);
        return "redirect:/post/" + params.getId() + "?boardId=" + params.getBoardId();
    }

}