package study.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.controller.request.PostUpdateRequest;
import study.board.domain.dto.PostDTO;
import study.board.domain.vo.PostVO;
import study.board.mapper.PostMapper;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostMapper postMapper;

    public List<PostDTO> getPostList(){
        postMapper.findAll();

        return null;
    }

    public PostDTO getPost(Long postId){
        PostDTO post = PostDTO.fromPostVO(postMapper.findById(postId));
        return post;
    }

    public List<PostDTO> getPostsListByBoardId(Long boardId){
        List<PostDTO> postList = postMapper.findPostByBoardId(boardId).stream().map(PostDTO::fromPostVO).collect(Collectors.toList());
        return postList;
    }

    public List<PostDTO> getPostsListByBoardIdAndNoticeYn(Long boardId){
        List<PostDTO> postList = postMapper.findPostsByBoardIdAndNoticeYn(boardId).stream().map(PostDTO::fromPostVO).collect(Collectors.toList());
        return postList;
    }

    @Transactional
    public void savePost(PostDTO postDTO){
        postMapper.save(postDTO.toPostVO());
        postDTO.getId();
    }

    public void updatePost(PostDTO postDTO) {
        log.info("postDTO.toPostUpdateVO() : {}",postDTO.toPostUpdateVO().toString());
        postMapper.update(postDTO.toPostUpdateVO());
    }


    public void deletePost(Long id) {
        postMapper.deleteById(id);
    }
}
