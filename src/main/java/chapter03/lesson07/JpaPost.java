package chapter03.lesson07;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Version
    @Column(name = "version")
    private Long version;

    // LAZY 중요, LAZY 가 없으면 관련된 테이블들을 다 가져온다. (불필요한 자원 사용) resource 아끼기 위해 사용한다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // N:1 관계
    @JoinColumn(name = "member_id", nullable = false)
    private JpaMember member;

    // mappedBy - 양방향 연결, orphanRemoval - 연결이 끊어진 행 삭제
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaComment> comments = new ArrayList<>();

    // Collections.unmodifiableList - 조회용
    public List<JpaComment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    // comment.getPost() != this - 현재 넘겨받은 포스트가 this 포스트가 아니면
    public void addComment(JpaComment comment) {
        if (comment == null || comment.getPost() != this) {
            throw new IllegalArgumentException("comment post mismatch");
        }
        comments.add(comment);
    }

    public void removeComment(JpaComment comment) {
        comments.remove(comment);
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostStatus status = PostStatus.DRAFT;

    public Long getVersion() {
        return version;
    }

    public void publish() {
        if (status != PostStatus.DRAFT) {
            throw new IllegalArgumentException("only draft can be published");
        }
        status = PostStatus.PUBLISHED;
    }

    public void archive() {
        if (status == PostStatus.ARCHIVED) {
            throw new IllegalArgumentException("already archived");
        }
        status = PostStatus.ARCHIVED;
    }

    public boolean canReceiveComment() {
        return status == PostStatus.PUBLISHED;
    }

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

    public PostStatus getStatus() {
        return status;
    }

    public void edit(String title, String body) {
        validate(title, body);
        this.title = title;
        this.body = body;
    }

}
