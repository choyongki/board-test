package com.study.controller;

import com.study.domain.BoardDTO;
import com.study.domain.PostDTO;
import com.study.domain.PostRequest;
import com.study.domain.PostResponse;
import com.study.service.BoardService;
import com.study.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;
    private final BoardService boardService;

    // 게시판 별 게시글 리스트 페이지
    @GetMapping()
    public String postList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("posts", posts);
        model.addAttribute("boardList",boardList);
        return "post/list";
    }

    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam final Long boardId, Model model) {
        if (boardId != null) {
            List<BoardDTO> boardList = boardService.getBoardList();
            model.addAttribute("boardList", boardList);
        }
        //BoardDTO board = boardService.getBoardDetail(boardId);
        //model.addAttribute("board",board);
        return "post/write";
    }



    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String postDetail(
            @PathVariable Long id ,
            @RequestParam Long boardId, Model model) {
        log.info("id : ",id);
        log.info("boardId : ",boardId);
        List<BoardDTO> boardList = boardService.getBoardList();
        BoardDTO board = boardService.getBoardDetail(boardId);
        PostDTO post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("board", board);
        model.addAttribute("boardList", boardList);

        return "post/view";
    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list.do";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params) {
        postService.updatePost(params);
        return "redirect:/post/list.do";
    }

}