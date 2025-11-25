package ru.lounge.services;

import ru.lounge.dto.CouponDto;
import ru.lounge.dto.PhoneDto;
import ru.lounge.models.Coupon;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponService {
    CouponDto findByPhoneAndCode(String phone, String code);

    List<CouponDto> findAll();

    List<CouponDto> findAllByExpirationAt(LocalDateTime expirationAt);

    Coupon createCoupon(PhoneDto phone);

    Coupon activate(long id);

    Coupon save(CouponDto couponDto);
}
