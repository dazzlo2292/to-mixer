package ru.lounge.services;

import ru.lounge.dto.CouponDto;
import ru.lounge.dto.PhoneDto;
import ru.lounge.models.Coupon;

import java.util.Date;
import java.util.List;

public interface CouponService {
    CouponDto findByPhoneAndCode(String phone, String code);

    List<CouponDto> findAll();

    List<CouponDto> findAllByExpirationAt(Date expirationAt);

    Coupon save(PhoneDto phone);

    Coupon activate(long id);
}
