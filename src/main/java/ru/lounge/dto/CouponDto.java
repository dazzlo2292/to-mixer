package ru.lounge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lounge.models.Coupon;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CouponDto {
    private Long id;

    private String phone;

    private String code;

    private String status;

    private String bonus;

    private LocalDateTime createdAt;

    private LocalDateTime expirationAt;

    public Coupon toDomainObject() {
        return new Coupon(
                id,
                phone,
                code,
                status,
                bonus,
                createdAt,
                expirationAt
        );
    }

    public static CouponDto fromDomainObject(Coupon coupon) {
        return new CouponDto(
                coupon.getId(),
                coupon.getPhone(),
                coupon.getCode(),
                coupon.getStatus(),
                coupon.getBonus(),
                coupon.getCreatedAt(),
                coupon.getExpirationAt());
    }
}
