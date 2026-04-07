package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the PricingRuleRepository.
 * Manages pricing rules in a thread-safe local cache.
 */
public class PricingRuleInMemoryRepository implements PricingRuleRepository {

    // Thread-safe in-memory cache to store pricing rules by their UUID.
    private static final Map<UUID, PricingRule> pricingRuleCache = new ConcurrentHashMap<>();


    @Override
    public PricingRule fetchDefault() {
        // Return the first available pricing rule as the default.
        // Throws exception if no pricing rules have been defined.
        Optional<PricingRule> rule = pricingRuleCache.values().stream().findFirst();
        if (rule.isEmpty()) {
            throw  new IllegalStateException("No Default Pricing defined");
        }
        return rule.get();
    }

    @Override
    public Optional<PricingRule> fetchById(UUID ruleId) {
        // Retrieve the pricing rule from the cache if it exists.
        return Optional.ofNullable(pricingRuleCache.get(ruleId));
    }

    @Override
    public PricingRule save(Double rate, PricingRule.PricingType type) {
        // Create a new PricingRule instance and persist it in the cache.
        PricingRule rule = new PricingRule(rate, type);
        pricingRuleCache.put(rule.getRuleId(), rule);
        return rule;
    }
}
