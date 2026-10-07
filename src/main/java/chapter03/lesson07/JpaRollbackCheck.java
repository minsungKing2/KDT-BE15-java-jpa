package chapter03.lesson07;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaRollbackCheck {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("kdt")) {
            Integer assignId;

            // TODO 1: 첫 번째 EntityManager에서 트랜잭션을 시작하고 회원을 저장한 뒤
            //         assignedId= 형식으로 할당된 ID를 출력합니다.
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();

                try {
                    JpaMember member = new JpaMember("mission-01-rollback", "rollbackUser");
                    em.persist(member);
                    assignId = member.getId();
                    System.out.println("assignId = " + assignId);

                    // TODO 2: 트랜잭션을 롤백하고 idAfterRollback= 형식으로 객체의 ID를 다시 출력합니다.
                    em.getTransaction().rollback();
                    System.out.println("idAfterRollback = " + member.getId());

                } catch (RuntimeException e) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    throw e;
                }
            }

            // TODO 3: 새 EntityManager로 같은 ID의 회원을 조회하고
            //         existsAfterRollback= 형식으로 존재 여부(boolean)를 출력합니다.
            try (EntityManager verifyEm = emf.createEntityManager()) {
                JpaMember found = verifyEm.find(JpaMember.class, assignId);
                System.out.println("existsAfterRollback = " + (found != null));
            }

        }
    }
}
