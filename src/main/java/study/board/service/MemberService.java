package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.board.domain.dto.MemberDTO;
import study.board.mapper.MemberMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberDTO getMember(String account){
        return MemberDTO.fromMemberVO(memberMapper.findByAccount(account));
    }
}
