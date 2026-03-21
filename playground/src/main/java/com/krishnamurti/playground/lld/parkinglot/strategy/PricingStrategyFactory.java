package com.krishnamurti.playground.lld.parkinglot.strategy;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.strategy.impl.DayPricingStrategy;
import com.krishnamurti.playground.lld.parkinglot.strategy.impl.FlatRatePricingStrategy;
import com.krishnamurti.playground.lld.parkinglot.strategy.impl.HourlyPricingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PricingStrategyFactory {

    private static final Map<PricingRule.PricingType, PricingStrategy> PRICING_TYPE_PRICING_STRATEGY_MAP = new HashMap<>();

    public PricingStrategyFactory() {
        PRICING_TYPE_PRICING_STRATEGY_MAP.put(PricingRule.PricingType.FLAT_RATE, FlatRatePricingStrategy.getInstance());
        PRICING_TYPE_PRICING_STRATEGY_MAP.put(PricingRule.PricingType.HOURLY, HourlyPricingStrategy.getInstance());
        PRICING_TYPE_PRICING_STRATEGY_MAP.put(PricingRule.PricingType.DAYS, DayPricingStrategy.getInstance());
    }

    public Optional<PricingStrategy> getStrategy(PricingRule.PricingType pricingType) {
        return Optional.ofNullable(PRICING_TYPE_PRICING_STRATEGY_MAP.get(pricingType));
    }
}
