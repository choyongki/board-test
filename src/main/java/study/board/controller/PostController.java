package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.board.controller.request.PostCreateRequest;
import study.board.controller.request.PostUpdateRequest;
import study.board.controller.response.BoardResponse;
import study.board.controller.response.CommentResponse;
import study.board.controller.response.FileResponse;
import study.board.controller.response.PostResponse;
import study.board.domain.dto.CommentDTO;
import study.board.file.UploadFile;
import study.board.domain.dto.PostDTO;
import study.board.file.FileStore;
import study.board.service.BoardService;
import study.board.service.CommentService;
import study.board.service.FileService;
import study.board.service.PostService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final FileStore fileStore;
    private final FileService fileService;
    private final CommentService commentService;

    /**
     * 모든 게시글
     * @return
     */
    @GetMapping
    public String posts(Model model){
        return null;
    }

    /**
     * 게시글 상세 페이지
     * @param id
     * @param boardId
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String post(@PathVariable Long id, @RequestParam Long boardId, Model model){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        PostResponse post = PostResponse.fromPostDTO(postService.getPost(id));
        BoardResponse board = BoardResponse.fromBoardDTO(boardService.getBoard(boardId));
        // TODO : CommentDTO -> CommentResponse 변환 에러 발생 => 일단 DTO 로 처리하고 나중에 수정 예정
        // List<CommentResponse> commentList = commentService.getCommentList(id).stream().map(CommentResponse::fromCommentDTO).collect(Collectors.toList());
        List<CommentDTO> commentList = commentService.getCommentList(id);

        List<FileResponse> fileList  = fileService.getFileList(post.getFileGroup()).stream().map(FileResponse::fromFileDTO).collect(Collectors.toList());

        log.info("postController :: commentList : {}",commentList.toString());

        model.addAttribute("post", post);
        model.addAttribute("board", board);
        model.addAttribute("boardList", boardList);
        model.addAttribute("fileList", fileList);
        model.addAttribute("commentList", commentList);

        return "post/view";
    }

    /**
     * 게시글 작성 페이지
     * @param boardId
     * @param model
     * @return
     */
    @GetMapping("/form")
    public String postForm(@RequestParam Long boardId, Model model){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        BoardResponse board = BoardResponse.fromBoardDTO(boardService.getBoard(boardId));

        model.addAttribute("boardList", boardList);
        model.addAttribute("board", board);

        return "post/write";
    }

    /**
     * 게시글 생성
     * @param id
     * @param postCreateRequest
     * @return
     */
    @Transactional
    @PostMapping("/form/{id}")
    public String savePost(@PathVariable Long id, PostCreateRequest postCreateRequest) throws IOException {

        // 파일 그룹 key 생성
        String fileGroup = UUID.randomUUID().toString();

        PostDTO post = postCreateRequest.toPostDTO(fileGroup);
        postService.savePost(post);

        // 첨부파일이 존재한다면 Upload로 변환하여 처리
        if(postCreateRequest.getAttachFiles() != null){
            List<UploadFile> attachFiles = fileStore.storeFiles(postCreateRequest.getAttachFiles(),fileGroup);
            fileService.saveFile(attachFiles);
        }

        return "redirect:/board/" + id.toString();

    }

    /**
     * 게시글 수정 페이지
     * @param id
     * @param boardId
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,@RequestParam Long boardId,Model model){
        List<BoardResponse> boardList = boardService.getBoardList().stream().map(BoardResponse::fromBoardDTO).collect(Collectors.toList());
        PostResponse post = PostResponse.fromPostDTO(postService.getPost(id));
        BoardResponse board = BoardResponse.fromBoardDTO(boardService.getBoard(boardId));
        List<FileResponse> fileList  = fileService.getFileList(post.getFileGroup()).stream().map(FileResponse::fromFileDTO).collect(Collectors.toList());

        model.addAttribute("post", post);
        model.addAttribute("board", board);
        model.addAttribute("boardList", boardList);
        model.addAttribute("fileList", fileList);

        return "post/update";
    }

    @PostMapping("/update")
    public String updatePost(PostUpdateRequest postUpdateRequest){
        PostDTO postDTO = postUpdateRequest.toPostDTO();
        log.info("post : {}",postDTO.toString());

        // TODO : 첨부파일 변경했을 경우 처리해야함
        // TODO : 비밀번호 처리 해야함
        // TODO : 공지사항 처리 해야함


        postService.updatePost(postDTO);
        return "redirect:/post/" + postUpdateRequest.getId() + "?boardId=" + postUpdateRequest.getBoardId();

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,@RequestParam Long boardId){
        postService.deletePost(id);
        return "redirect:/board/posts/" + boardId.toString();
    }




}
