package com.krishnamurti.playground.lld.parkinglot.strategy.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategy;

public class HourlyPricingStrategy implements PricingStrategy {

    private static final PricingStrategy INSTANCE = new HourlyPricingStrategy();

    public static PricingStrategy getInstance() {
        return INSTANCE;
    }


    @Override
    public double calculate(long hours, SlotType type, PricingRule rule) {
        return hours * (rule.getRate() * type.getSize());
    }
}
