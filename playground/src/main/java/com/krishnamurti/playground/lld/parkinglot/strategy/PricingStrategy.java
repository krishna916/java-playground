package com.krishnamurti.playground.lld.parkinglot.strategy;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.domain.SlotType;

public interface PricingStrategy {

    double calculate(long hours, SlotType type, PricingRule pricingRule);
}
