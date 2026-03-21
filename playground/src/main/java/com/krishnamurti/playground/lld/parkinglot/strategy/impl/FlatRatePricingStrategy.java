package com.krishnamurti.playground.lld.parkinglot.strategy.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;
import com.krishnamurti.playground.lld.parkinglot.strategy.PricingStrategy;

public class FlatRatePricingStrategy implements PricingStrategy {

    private static final PricingStrategy INSTANCE = new FlatRatePricingStrategy();

    public static PricingStrategy getInstance() {
        return INSTANCE;
    }


    @Override
    public double calculate(long hours, SlotType type, PricingRule pricingRule) {

        return (pricingRule.getRate() * type.getSize());
    }
}
