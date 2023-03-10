package study.board.controller.request;

import lombok.*;
import study.board.domain.dto.MemberDTO;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class signUpRequest {
    private String account;
    private String email;
    private String nickname;
    private String memberName;
    private String phone;
    private String memberRole;
    private String password;

    public MemberDTO toMemberDTO(String encodedPassword){
        return MemberDTO.of(account,email, nickname,memberName,phone,memberRole,encodedPassword);
    }
}
