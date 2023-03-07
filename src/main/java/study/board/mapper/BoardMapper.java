package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.domain.vo.BoardVO;
import study.board.domain.vo.PostVO;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVO> findAll();

    BoardVO findById(Long id);

    void saveBoard(BoardVO boardVO);

    List<PostVO> findPostsByBoardId(Long boardId);
}
