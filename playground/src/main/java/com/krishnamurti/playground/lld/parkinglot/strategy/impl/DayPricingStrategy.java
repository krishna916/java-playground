package com.krishnamurti.playground.lld.parkinglot.strategy.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategy;

public class DayPricingStrategy implements PricingStrategy {

    private static final  PricingStrategy INSTANCE = new DayPricingStrategy();

    public static PricingStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public double calculate(long hours, SlotType type, PricingRule pricingRule) {
        int days = (int)(Math.ceil(hours/24.0));
        return days * (pricingRule.getRate() * type.getSize());
    }
}
