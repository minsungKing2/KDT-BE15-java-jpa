package chapter03.lesson07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaSeed {

    public static void main(String[] args) {

        // try - with - resource
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("kdt");
            EntityManager em = emf.createEntityManager();
        ){
            em.getTransaction().begin(); // 트랜잰셕 시작

            // try - catch
            try {
                JpaMember member = new JpaMember("jpa01", "kim"); // 비영속 상태
                em.persist(member); // 영속 상태

                JpaPost post = new JpaPost(member, "JPA start", "pure java"); // 비영속 상태
                em.persist(post); // 영속 상태

                em.getTransaction().commit(); // 실제 DB에 반영 // 트랜잭션 끝

                System.out.println("memberId = " + member.getId());
                System.out.println("postId = " + post.getId());
            } catch (RuntimeException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            }
        }

    }

}
