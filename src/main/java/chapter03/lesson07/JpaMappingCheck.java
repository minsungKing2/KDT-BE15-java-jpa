package chapter03.lesson07;

public class JpaMappingCheck {

    public static void main(String[] args) {

        JpaMember jpaMember = new JpaMember("mapping01", "kim");

        String title200 = "a".repeat(200);

        JpaPost valid = new JpaPost(jpaMember, title200, "body");

        System.out.println("valid.getTitle() = " + valid.getTitle());
        System.out.println("titleLength = " + valid.getTitle().length());

        try {
            new JpaPost(jpaMember, "a".repeat(201), "body");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

}
