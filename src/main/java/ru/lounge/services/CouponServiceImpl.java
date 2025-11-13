package ru.lounge.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lounge.dto.CouponDto;
import ru.lounge.dto.PhoneDto;
import ru.lounge.exceptions.BusinessLogicException;
import ru.lounge.exceptions.EntityNotFoundException;
import ru.lounge.models.Coupon;
import ru.lounge.models.CouponStatus;
import ru.lounge.repositories.CouponRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    @Override
    public CouponDto findByPhoneAndCode(String phone, String code) {
        Coupon targetCoupon = couponRepository.findByPhoneAndCode(phone, code)
                .orElseThrow(() -> new EntityNotFoundException("Coupon with phone %s and code %s not found".formatted(phone, code)));

        return CouponDto.fromDomainObject(targetCoupon);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CouponDto> findAll() {
        return couponRepository.findAll().stream()
                .map(CouponDto::fromDomainObject)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CouponDto> findAllByExpirationAt(Date expirationAt) {
        return couponRepository.findAllByExpirationAt(expirationAt).stream()
                .map(CouponDto::fromDomainObject)
                .toList();
    }

    @Transactional
    @Override
    public Coupon save(PhoneDto phone) {
        if (phone.getPhone().length() != 11) {
            throw new BusinessLogicException("The phone number must be 11 characters long!");
        }

        return couponRepository.save(
                new Coupon(
                    null,
                    phone.getPhone(),
                    generateCode(),
                    CouponStatus.CREATED.name(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(7)
                )
        );
    }

    @Transactional
    @Override
    public Coupon activate(long id) {
        Coupon currentCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coupon with id %d not found".formatted(id)));
        currentCoupon.setStatus(CouponStatus.ACTIVATED.name());
        return couponRepository.save(currentCoupon);
    }

    private String generateCode() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                stringBuilder.append(random.nextInt(10));
                continue;
            }
            stringBuilder.append((char) (random.nextInt(26) + 'A'));
        }

        return stringBuilder.toString();
    }
}
