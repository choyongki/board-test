package study.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.dto.MemberDTO;
import study.board.domain.vo.MemberVO;
import study.board.mapper.MemberMapper;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignService {
    private final MemberMapper memberMapper;

    /**
     * 회원가입
     * @param memberDTO
     */
    @Transactional
    public void signUp(MemberDTO memberDTO) {
        log.info("memberDTO.toMemberVO() : {}",memberDTO.toMemberVO().toString());
        memberMapper.save(memberDTO.toMemberVO());
    }

    public void signIn(MemberDTO memberDTO) {
        log.info("memberDTO.toMemberVO() : {}",memberDTO.toMemberVO().toString());
        MemberVO memberVO = memberMapper.findByAccount(memberDTO.getAccount());
    }

}
