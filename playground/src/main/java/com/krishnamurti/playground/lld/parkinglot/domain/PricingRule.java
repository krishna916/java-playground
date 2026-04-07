package com.krishnamurti.playground.lld.parkinglot.domain;

import java.util.UUID;

/**
 * Defines a rule for calculating the parking fee.
 * Pricing can be based on different strategies such as hourly rates or flat fees.
 */
public class PricingRule {

    /**
     * Enum for different types of pricing models.
     */
    public enum PricingType {
        // Charging based on the number of hours parked
        HOURLY,
        // A single fixed charge regardless of duration
        FLAT_RATE,
        // Charging based on the number of days parked
        DAYS;
    }

    // Unique identifier for the pricing rule
    private UUID ruleId;
    
    // The numerical rate to be applied (e.g., price per hour)
    private double rate;
    
    // The strategy type for this rule
    private PricingType type;

    /**
     * Constructs a PricingRule.
     *
     * @param rate The rate to charge.
     * @param type The pricing model to use.
     */
    public PricingRule(double rate, PricingType type) {
        this.rate = rate;
        this.type = type;
        this.ruleId = UUID.randomUUID();
    }

    /**
     * @return The unique rule identifier.
     */
    public UUID getRuleId() {
        return ruleId;
    }

    /**
     * @return The charging rate.
     */
    public double getRate() {
        return rate;
    }

    /**
     * @return The pricing model type.
     */
    public PricingType getType() {
        return type;
    }
}
