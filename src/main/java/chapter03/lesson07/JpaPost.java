package chapter03.lesson07;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_post")
public class JpaPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "body", length = 1000, nullable = false)
    private String body;

    // LAZY 중요, LAZY 가 없으면 관련된 테이블들을 다 가져온다. (불필요한 자원 사용) resource 아끼기 위해 사용한다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // N:1 관계
    @JoinColumn(name = "member_id", nullable = false)
    private JpaMember member;

    protected JpaPost() {
    }

    public JpaPost(JpaMember member, String title, String body) {
        if (member == null) throw new IllegalArgumentException("member required");
        validate(title, body);
        this.member = member;
        this.title = title;
        this.body = body;
    }

    private static void validate(String title, String body) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title required");
        if (title.length() > 200) throw new IllegalArgumentException("title too long");
        if (body == null || body.isBlank()) throw new IllegalArgumentException("body required");
        if (body.length() > 1000) throw new IllegalArgumentException("body too long");
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public JpaMember getMember() {
        return member;
    }

    public void edit(String title, String body) {
        validate(title, body);
        this.title = title;
        this.body = body;
    }

}
