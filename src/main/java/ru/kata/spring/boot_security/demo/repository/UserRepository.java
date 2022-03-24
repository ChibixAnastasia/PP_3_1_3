package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

        @Modifying
    @Query(value = "update users set name = :name , last_name = :last_name where id = :id",nativeQuery = true)
    void updateUserById(@Param("id") Long id, @Param("name") String name, @Param("last_name") String lastName);

    User findByName(String name);

}
