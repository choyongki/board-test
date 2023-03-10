package study.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MemberVO {
    private Long id;
    private String account;
    private String email;
    private String nickname;
    private String memberName;
    private String phone;
    private String memberRole;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static MemberVO of(String account,String email,String nickname,String memberName,String phone,String memberRole,String password){
        return new MemberVO(null,account,email,nickname,memberName,phone,memberRole,password,null,null);
    }
}
