package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.controller.request.PostUpdateRequest;
import study.board.domain.dto.PostDTO;
import study.board.domain.vo.PostVO;

import java.util.List;

@Mapper
public interface PostMapper {

    List<PostVO> findAll();

    void save(PostVO postVO);

    PostVO findById(Long postId);

    List<PostVO> findPostByBoardId(Long boardId);
    List<PostVO> findPostsByBoardIdAndNoticeYn(Long boardId);


    void update(PostVO postVO);

    void deleteById(Long id);
}
