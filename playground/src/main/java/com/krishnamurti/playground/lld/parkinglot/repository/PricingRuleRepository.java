package com.krishnamurti.playground.lld.parkinglot.repository;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing PricingRule data access.
 * Defines operations for fetching and persisting pricing rules.
 */
public interface PricingRuleRepository {

    /**
     * Retrieves the default pricing rule.
     *
     * @return The default PricingRule.
     */
    PricingRule fetchDefault();

    /**
     * Fetches a pricing rule by its unique identifier.
     *
     * @param ruleId The UUID of the pricing rule to retrieve.
     * @return An Optional containing the PricingRule if found, or empty if not.
     */
    Optional<PricingRule> fetchById(UUID ruleId);

    /**
     * Creates and persists a new pricing rule.
     *
     * @param rate The rate associated with the pricing rule.
     * @param type The type of pricing (e.g., HOURLY, FLAT_RATE).
     * @return The newly created and persisted PricingRule.
     */
    PricingRule save(Double rate, PricingRule.PricingType type);

}
