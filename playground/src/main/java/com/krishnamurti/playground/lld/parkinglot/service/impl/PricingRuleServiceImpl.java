package com.krishnamurti.playground.lld.parkinglot.service.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;
import com.krishnamurti.playground.lld.parkinglot.service.PricingRuleService;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link PricingRuleService}.
 * Provides a management layer for defining and retrieving parking rates and their calculation types.
 */
public class PricingRuleServiceImpl implements PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;

    public PricingRuleServiceImpl(final PricingRuleRepository pricingRuleRepository) {
        this.pricingRuleRepository = pricingRuleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PricingRule> getById(UUID ruleId) {
        return pricingRuleRepository.fetchById(ruleId);
    }

    /**
     * {@inheritDoc}
     * Persists the pricing rule details into the repository.
     */
    @Override
    public PricingRule save(PricingRule pricingRule) {
        // Business logic: Ensure the rate and rule type are passed to the persistence layer.
        return pricingRuleRepository.save(pricingRule.getRate(), pricingRule.getType());
    }
}
