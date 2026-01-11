package com.rupesh.Zaminex.Repository;

import com.rupesh.Zaminex.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OTP , Long> {

    Optional<OTP> findByPhone(String phone);

    void deleteByPhone(String phone);
}
