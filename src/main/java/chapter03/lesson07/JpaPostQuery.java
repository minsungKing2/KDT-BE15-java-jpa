package chapter03.lesson07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JpaPostQuery {

    public static void main(String[] args) {

        String keyword = "JPA";
        String exactTitle = "JPA edited";
        String wantedLoginId = "jpa01";

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("kdt");
             EntityManager em = emf.createEntityManager()) {
            // 1. 전체 목록: 제목만 출력하므로 작성자 지연 로딩을 요청하지 않습니다.
            System.out.println("all");

            TypedQuery<JpaPost> allQuery = em.createQuery(
                    "select p from JpaPost p order by p.id", JpaPost.class);

            List<JpaPost> allPosts = allQuery.getResultList();
            System.out.println("allPosts.size() = " + allPosts.size());

            for (JpaPost post : allPosts) {
                System.out.println(post.getId() + " | " + post.getTitle());
            }

            // 2. 일반 조회: 검색 값을 매개변수로 전달하고 작성자까지 출력합니다.
            em.clear();
            System.out.println("normal");
            List<JpaPost> normalPosts = em.createQuery(
                            "select p from JpaPost p " + "where p.title like :keyword order by p.id"
                            , JpaPost.class)
                    .setParameter("keyword", "%" + keyword + "%") // .setParameter로 :keyword와 매핑
                    .getResultList();

            System.out.println("normalPosts.size() = " + normalPosts.size());
            printPosts(normalPosts);

            // 3. 일반 조인: 조인만 추가해도 작성자 로딩까지 보장하지는 않습니다.
            em.clear(); // clear() - 영속성 컨텍스트를 비워주는 용도다.
            System.out.println("join");
            List<JpaPost> joinPosts = em.createQuery(
                            "select p from JpaPost p join p.member where p.title like :keyword order by p.id"
                            , JpaPost.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();

            System.out.println("joinCount = " + joinPosts.size());
            printPosts(joinPosts);

            // 4. fetch join: 게시글과 작성자를 함께 조회합니다.
            em.clear();
            System.out.println("fetch");
            List<JpaPost> fetchPosts = em.createQuery(
                            "select p from JpaPost p join fetch p.member m where p.title like :keyword " +
                                    "order by  p.id"
                            , JpaPost.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();

            System.out.println("fetchJoinCount = " + fetchPosts.size());
            printPosts(fetchPosts);

            // 5. 제목 일치 검색: LIKE와 달리 제목 전체를 비교합니다.
            em.clear();
            System.out.println("exact");
            List<JpaPost> exactPosts = em.createQuery(
                            "select p from JpaPost p where p.title = :title order by p.id"
                            , JpaPost.class)
                    .setParameter("title", exactTitle)
                    .getResultList();

            System.out.println("exactCount = " + exactPosts.size());
            for (JpaPost post : exactPosts) {
                System.out.println(post.getId() + " | " + post.getTitle());
            }

            // 6. 회원 일치 검색: 엔티티, 필드, 결과 타입을 회원으로 맞춥니다.
            em.clear();
            System.out.println("member");
            List<JpaMember> members = em.createQuery(
                            "select m from JpaMember m where m.loginId = :loginId"
                            , JpaMember.class)
                    .setParameter("loginId", wantedLoginId)
                    .getResultList();
            System.out.println("memberCount = " + members.size());
            if (members.isEmpty()) {
                System.out.println("member not found");
            } else {
                JpaMember member = members.get(0);
                System.out.println(member.getLoginId());
            }

            // JPA - DELETE
            JpaPost post = em.find(JpaPost.class, 1); // 영속 상태
            if (post == null) {
                throw new IllegalArgumentException("post not found");
            }
            em.clear(); // clear()를 써서 준영속 상태
            em.remove(post); // 준영속 상태에서 remove를 쓰면 에러가 난다. remove는 영속 상태일때만 사용 가능

            // JPA - flush
        }

    }

    private static void printPosts(List<JpaPost> posts) {
        // main의 EntityManager가 아직 열려 있으므로 필요한 지연 로딩이 가능합니다.
        for (JpaPost post : posts) {
            System.out.println(post.getId() + " | "
                    + post.getMember().getLoginId() + " | " + post.getTitle());
        }

    }
}