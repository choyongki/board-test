package study.board.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import study.board.domain.dto.MemberDTO;


@ToString
@NoArgsConstructor
@Service
@Getter
public class signInRequest {
    private String account;
    private String password;

    public MemberDTO toMemberDTO(String encode) {
        return MemberDTO.of(account,password);
    }
}
