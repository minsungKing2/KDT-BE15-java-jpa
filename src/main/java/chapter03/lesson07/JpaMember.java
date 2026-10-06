package chapter03.lesson07;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_member")
public class JpaMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // strategy - 전략을 IDENTITY - 1씩 증가시킨다.
    private Integer id;

    @Column(name = "login_id", nullable = false, unique = true, length = 30) // unique - unique key
    private String loginId;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    protected JpaMember() {
    }

    public JpaMember(String loginId, String nickname) {
        if (loginId == null || loginId.isBlank()) {
            throw new IllegalArgumentException("loginId required");
        }
        if (loginId.length() > 30) {
            throw new IllegalArgumentException("loginId too long");
        }
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("nickname required");
        }
        if (nickname.length() > 30) {
            throw new IllegalArgumentException("nickname too long");
        }

        this.loginId = loginId;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getNickname() {
        return nickname;
    }

    public void changeNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("nickname required");
        }

        if(nickname.length() > 30){
            throw new IllegalArgumentException("nickname too long");
        }

        this.nickname = nickname;
    }

}
