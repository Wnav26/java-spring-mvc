package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User wnav);

    List<User> findByEmail(String email);

    List<User> findAll();

    boolean existsByEmail(String email);

    User findById(long id);

    void deleteById(long id);

    User findOneByEmail(String email);
}
