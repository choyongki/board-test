package study.board.domain.dto;

import lombok.*;
import study.board.domain.vo.MemberVO;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class MemberDTO {
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
}
