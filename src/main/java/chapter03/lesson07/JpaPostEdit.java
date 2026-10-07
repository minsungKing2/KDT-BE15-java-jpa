package chapter03.lesson07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaPostEdit {

    public static void main(String[] args) {

        int postId = 1;

        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("kdt");
            EntityManager em = emf.createEntityManager()
        ) {
            em.getTransaction().begin();
            try {
                JpaPost post = em.find(JpaPost.class, postId); // select문 발생
                if (post == null) {
                    System.out.println("not found");
                    em.getTransaction().rollback();
                    return;
                }
                post.edit("JPA edited", "dirty checking");
                // commit()은 더티 체킹 발생한다. (DB에 완전히 반영), flush()도 터티 체킹 발생한다. (DB에 완전히 반영X)
                em.getTransaction().commit(); // update문 발생
                System.out.println(post.getTitle());
            } catch (RuntimeException e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.clear(); // clear() 혹은 EntityManager를 사라지게 만들었을때, 준영속 상태가 된다.
                throw e;
            }
        }

    }

}
