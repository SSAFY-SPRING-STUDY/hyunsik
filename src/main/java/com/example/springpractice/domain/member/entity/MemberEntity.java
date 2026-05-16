package com.example.springpractice.domain.member.entity;

import lombok.Getter;

@Getter
public class MemberEntity {
    private static long AUTO_INCREMENT = 1L;

    private Long id;
    private String username;
    private String password;
    private String nickname;

    // 외부에서 new MemberEntity()로 직접 못 만들게 private으로 막아둠
    // 아래 create() 를 통해서만 생성하도록 강제하는 방식임
    private MemberEntity(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    // 정적 팩토리 메서드 - 호출할 때마다 AUTO_INCREMENT가 1씩 올라가면서 id가 자동 부여됨
    public static MemberEntity create(String username, String password, String nickname) {
        return new MemberEntity(AUTO_INCREMENT++, username, password, nickname);
    }

    // 입력된 비밀번호가 저장된 비밀번호랑 같은지 확인해주는 메서드
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
