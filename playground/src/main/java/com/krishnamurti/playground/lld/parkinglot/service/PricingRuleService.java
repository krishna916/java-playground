package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing pricing rules.
 * Defines how parking rates are stored and retrieved based on different strategies.
 */
public interface PricingRuleService {
    /**
     * Retrieves a pricing rule by its unique identifier.
     *
     * @param ruleId The UUID of the pricing rule.
     * @return An {@link Optional} containing the pricing rule if found, or empty otherwise.
     */
    Optional<PricingRule> getById(UUID ruleId);

    /**
     * Saves or updates a pricing rule in the system.
     *
     * @param pricingRule The pricing rule to be saved.
     * @return The persisted {@link PricingRule}.
     */
    PricingRule save(PricingRule pricingRule);
}
