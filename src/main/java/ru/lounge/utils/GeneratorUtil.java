package ru.lounge.utils;

import org.springframework.stereotype.Component;
import ru.lounge.models.CouponBonus;

import java.util.Random;

@Component
public class GeneratorUtil {
    public String generateCode() {
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

    public String generateBonus() {
        Random random = new Random();

        return switch (getRange(random.nextInt(100) + 1)) {
            case 1 -> CouponBonus.MINUS_10.getDescription();
            case 2 -> CouponBonus.MINUS_20.getDescription();
            case 3 -> CouponBonus.FREE_TEA.getDescription();
            case 4 -> CouponBonus.FREE_HOOKAH.getDescription();
            default -> "Bad generation!";
        };
    }

    public int getRange(int number) {
        if (number >= 1 && number <= 50) return 1;
        if (number >= 51 && number <= 80) return 2;
        if (number >= 81 && number <= 95) return 3;
        if (number >= 96 && number <= 100) return 4;
        return -1;
    }
}
