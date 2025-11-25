package ru.lounge.processors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lounge.dto.CouponDto;
import ru.lounge.models.CouponStatus;
import ru.lounge.services.CouponService;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CouponExpirationProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CouponExpirationProcessor.class);

    private final CouponService couponService;

    @Scheduled(cron = "0 0 * * * *")
    public void expirationCouponsTask() {
        List<CouponDto> expiredCoupons = couponService.findAllByExpirationAt(LocalDateTime.now());

        for (CouponDto c : expiredCoupons) {
            c.setStatus(CouponStatus.EXPIRED.name());
            couponService.save(c);
            logger.info("Expiration task completed for coupon: {} — {}", c.getPhone(), c.getCode());
        }
    }
}
