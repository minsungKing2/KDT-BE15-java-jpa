package chapter03.lesson07;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaPing {

    public static void main(String[] args) {

        // try - with - resource
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("kdt")) {
            System.out.println("emf ok");
        }

    }

}
