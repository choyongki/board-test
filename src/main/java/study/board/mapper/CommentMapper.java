package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.domain.vo.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper {
    public void save(CommentVO commentVO);

    List<CommentVO> findAllByPostId(CommentVO commentVO);
}
