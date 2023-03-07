package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.controller.request.BoardCreateRequest;
import study.board.controller.response.BoardResponse;
import study.board.controller.response.PostResponse;
import study.board.service.BoardService;
import study.board.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    /**
     * 메인 페이지
     * 게시판 리스트
     * @param model
     * @return
     */
    @GetMapping
    public String boardList(Model model){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        model.addAttribute("boardList",boardList);
        return "board/list";
    }

    /**
     * 게시판 상세 페이지
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        BoardResponse board = BoardResponse.fromBoardDTO(boardService.getBoard(id));
        model.addAttribute("boardList",boardList);
        model.addAttribute("board",board);

        return "board/view";
    }

    /**
     * 게시판 생성 페이지
     * @param model
     * @param boardCreateRequest
     * @return
     */
    @GetMapping("/write")
    public String boardCreate(Model model, BoardCreateRequest boardCreateRequest){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        model.addAttribute("boardList",boardList);
        return "board/write";
    }

    /**
     * 게시판 생성
     * @param boardCreateRequest
     * @param model
     * @return
     */
    @PostMapping("/write")
    public String boardCreate(BoardCreateRequest boardCreateRequest, Model model){
        boardService.saveBoard(boardCreateRequest.toBoardDTO());

        return "redirect:/board";
    }

    /**
     * 게시판 별 게시글 리스트
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/posts/{id}")
    public String postsByBoardId(@PathVariable Long id,Model model) {
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        List<PostResponse> postList = postService.getPostsListByBoardId(id).stream().map(PostResponse::fromPostDTO).collect(Collectors.toList());
        List<PostResponse> postNoticeList = postService.getPostsListByBoardIdAndNoticeYn(id).stream().map(PostResponse::fromPostDTO).collect(Collectors.toList());
        BoardResponse board = BoardResponse.fromBoardDTO(boardService.getBoard(id));

        model.addAttribute("board",board);
        model.addAttribute("boardList",boardList);
        model.addAttribute("postList",postList);
        model.addAttribute("postNoticeList",postNoticeList);

        return "board/view";
    }
}
