package study.board.domain.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import study.board.domain.vo.MemberVO;
import study.board.security.UserDetailsDTO;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class MemberDTO implements UserDetails {
    private String account;
    private String email;
    private String nickname;
    private String memberName;
    private String phone;
    private String memberRole;
    private String password;

    public static MemberDTO of(String account,String email,String nickname,String memberName,String phone,String memberRole,String password){
        return new MemberDTO(account,email,nickname,memberName,phone,memberRole,password);
    }

    public static MemberDTO of(String account,String password){
        return new MemberDTO(account,null,null,null,null,null,password);
    }

    public MemberVO toMemberVO(){
        return MemberVO.of(account,email, nickname,memberName,phone,memberRole,password);
    }

    public static MemberDTO fromMemberVO(MemberVO memberVO){
        return new MemberDTO(
                memberVO.getAccount(),
                memberVO.getEmail(),
                memberVO.getNickname(),
                memberVO.getMemberName(),
                memberVO.getPhone(),
                memberVO.getMemberRole(),
                memberVO.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.memberRole);
    }

    @Override
    public String getUsername() {
        return this.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
