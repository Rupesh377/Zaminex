package com.rupesh.Zaminex.Repository;

import com.rupesh.Zaminex.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User , Long> {

    Optional<User> findByName(String name);

    Optional<User> findByPhone(String phone);

    boolean existsByPhone(String phone);

}
