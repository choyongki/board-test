package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.dto.CommentDTO;
import study.board.domain.vo.CommentVO;
import study.board.mapper.CommentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentMapper commentMapper;

    @Transactional
    public void saveComment(CommentDTO commentDTO) {
        log.info("CommentVO : {}",commentDTO.toCommentVO().toString());
        commentMapper.save(commentDTO.toCommentVO());
    }

    public List<CommentDTO> getCommentList(Long postId){
        CommentDTO commentDTO = CommentDTO.of(postId, null);
        List<CommentDTO> commentList = commentMapper.findAllByPostId(commentDTO.toCommentVO()).stream().map(CommentDTO::fromCommentVO).collect(Collectors.toList());
        for (int i = 0; i < commentList.size(); i++) {
            commentDTO.setParentId(commentList.get(i).getId());
            commentList.get(i).setChildCommentList(commentMapper.findAllByPostId(commentDTO.toCommentVO()).stream().map(CommentDTO::fromCommentVO).collect(Collectors.toList()));
        }

        log.info("commentService ::: commentList : {}",commentList.toString());
        return commentList;
    }

}
