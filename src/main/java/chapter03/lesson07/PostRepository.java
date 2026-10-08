package chapter03.lesson07;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    void save(JpaPost post);

    // Optional - 있거나 없거나
    Optional<JpaPost> findById(int id);

    List<JpaPost> findAll();

    List<JpaPost> findByTitleContaining(String keyword);

    List<JpaPost> findPage(int offset, int size);

}
