package study.board.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import study.board.domain.dto.MemberDTO;
import study.board.domain.vo.MemberVO;
import study.board.mapper.MemberMapper;
import study.board.service.MemberService;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername ::: {}",username);
        UserDetails findMember = (UserDetails) memberService.getMember(username);
        log.info("findMember : {}",findMember.toString());

        // 사용자 정보가 존재하지 않는 경우
        if(findMember==null){
            throw new UsernameNotFoundException("존재하지 않는 유저입니다.");
        }
        // 비밀번호가 맞지 않는 경우
        log.info("loadUserByUsername ::: member.id = {}",findMember.getUsername());

        return findMember;
    }
}
