package study.board.domain;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("관리자"),
    ROLE_MANAGER("매니저"),
    ROLE_MEMBER("유저");

    private String description;

    Role(String description){
        this.description= description;
    }
}
