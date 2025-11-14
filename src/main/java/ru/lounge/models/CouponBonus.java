package ru.lounge.models;

import lombok.Getter;

@Getter
public enum CouponBonus {
    MINUS_10("-10%"),
    MINUS_20("-20%"),
    FREE_TEA("Free Tea"),
    FREE_HOOKAH("Free Hookah");

    private final String description;

    CouponBonus(String description) {
        this.description = description;
    }
}
