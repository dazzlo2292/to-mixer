package ru.lounge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lounge.models.Coupon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("SELECT c FROM Coupon c WHERE c.phone = :phone and c.code = :code")
    Optional<Coupon> findByPhoneAndCode(@Param("phone")String phone, @Param("code")String code);

    @Query("SELECT c FROM Coupon c WHERE c.expirationAt <= :expirationAt")
    List<Coupon> findAllByExpirationAt(@Param("expirationAt") LocalDateTime expirationAt);
}
