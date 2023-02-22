package com.study.controller;

import com.study.domain.BoardCreateDTO;
import com.study.domain.BoardDTO;
import com.study.domain.PostDTO;
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
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    /*
    게시판 리스트
     */
    @GetMapping()
    public String boardList(Model model) {
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList",boardList);
        log.trace(boardList.toString());
        return "board/list";
    }

    @GetMapping(value = "/{id}")
    public String boardDetail(@PathVariable Long id, Model model){
        log.info("boardDetail");
        BoardDTO board = boardService.getBoardDetail(id);
        List<BoardDTO> boardList = boardService.getBoardList();
        List<PostDTO> postList = postService.findByBoardId(id);
        List<PostDTO> postNoticeList= postService.findByBoardIdAndNotice(id);
        model.addAttribute("board",board);
        model.addAttribute("postList",postList);
        model.addAttribute("boardList",boardList);
        model.addAttribute("postNoticeList",postNoticeList);

        return "board/view";
    }

    /*
    게시판 생성 화면
     */
    @GetMapping("/write")
    public String boardCreate(Model model){
        log.info("boardWrite");
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("boardList",boardList);

        return "board/write";
    }

    /*
    게시판 생성 처리
     */
    @PostMapping("/write")
    public String boardCreate(BoardCreateDTO boardCreateDTO, Model model){
        log.info("boardWrite : ", boardCreateDTO.toString());
        boardService.saveBoard(boardCreateDTO);

        return "redirect:/";
    }

}
