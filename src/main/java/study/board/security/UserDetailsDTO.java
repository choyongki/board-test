package study.board.security;

import lombok.AllArgsConstructor;
import lombok.Delegate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.board.domain.dto.MemberDTO;

import java.util.Collection;

@Slf4j
@AllArgsConstructor
@Getter
public class UserDetailsDTO {

    private MemberDTO memberDTO;
    private Collection<? extends GrantedAuthority> authorities;


}
