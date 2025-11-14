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
import ru.lounge.utils.GeneratorUtil;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    private final GeneratorUtil generatorUtil;

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
    public List<CouponDto> findAllByExpirationAt(LocalDateTime expirationAt) {
        return couponRepository.findAllByExpirationAt(expirationAt).stream()
                .map(CouponDto::fromDomainObject)
                .toList();
    }

    @Transactional
    @Override
    public Coupon createCoupon(PhoneDto phone) {
        if (phone.getPhone().length() != 11) {
            throw new BusinessLogicException("The phone number must be 11 characters long!");
        }

        return couponRepository.save(
                new Coupon(
                    null,
                    phone.getPhone(),
                    generatorUtil.generateCode(),
                    CouponStatus.CREATED.name(),
                    generatorUtil.generateBonus(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(7)
                )
        );
    }

    @Transactional
    @Override
    public Coupon save(CouponDto couponDto) {
        return couponRepository.save(couponDto.toDomainObject());
    }

    @Transactional
    @Override
    public Coupon activate(long id) {
        Coupon currentCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coupon with id %d not found".formatted(id)));

        if (currentCoupon.getStatus().equals(CouponStatus.CREATED.name())) {
            currentCoupon.setStatus(CouponStatus.ACTIVATED.name());
            return couponRepository.save(currentCoupon);
        }

        throw new BusinessLogicException("Coupon already ACTIVATED or EXPIRED!");
    }
}
