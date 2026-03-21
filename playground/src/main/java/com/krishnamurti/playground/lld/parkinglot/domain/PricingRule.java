package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

public class PricingRule {

    public enum PricingType {
        HOURLY,
        FLAT_RATE,
        DAYS;
    }

    private UUID ruleId;
    private double rate;
    private PricingType type;

    public PricingRule(double rate, PricingType type) {
        this.rate = rate;
        this.type = type;
        this.ruleId = UUID.randomUUID();
    }

    public UUID getRuleId() {
        return ruleId;
    }

    public double getRate() {
        return rate;
    }

    public PricingType getType() {
        return type;
    }
}
