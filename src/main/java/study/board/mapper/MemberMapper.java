package study.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.board.domain.dto.MemberDTO;
import study.board.domain.vo.MemberVO;

@Mapper
public interface MemberMapper {
    void save(MemberVO memberVO);

    MemberVO findByAccount(String account);

}
