package ru.lounge.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.lounge.dto.CouponDto;
import ru.lounge.dto.PhoneDto;
import ru.lounge.models.Coupon;
import ru.lounge.services.CouponService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    private final CouponService couponService;

    @GetMapping("/api/v1/public/coupons")
    public List<CouponDto> findAll() {
        logger.info("Method called - GET /api/v1/public/coupons");
        return couponService.findAll();
    }

    @GetMapping("/api/v1/admin/coupons/find")
    public CouponDto findByPhoneAndCode(
            @RequestParam("phone") String phone,
            @RequestParam("code") String code
            ) {
        logger.info("Method called - GET /api/v1/admin/coupons/find");
        return couponService.findByPhoneAndCode(phone, code);
    }

    @PostMapping("/api/v1/admin/coupons")
    public Coupon save(@RequestBody PhoneDto phone) {
        logger.info("Method called - POST /api/v1/admin/coupons");
        return couponService.createCoupon(phone);
    }

    @PutMapping("/api/v1/admin/coupons/activate/{id}")
    public Coupon activate(@PathVariable long id) {
        logger.info("Method called - PUT /api/v1/admin/coupons/activate/{id}");
        return couponService.activate(id);
    }
}
