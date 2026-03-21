package com.krishnamurti.playground.lld.parkinglot.repository.impl;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;
import com.krishnamurti.playground.lld.parkinglot.repository.PricingRuleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PricingRuleInMemoryRepository implements PricingRuleRepository {

    private static final Map<UUID, PricingRule> pricingRuleCache = new ConcurrentHashMap<>();


    @Override
    public PricingRule fetchDefault() {
        Optional<PricingRule> rule = pricingRuleCache.values().stream().findFirst();
        if (rule.isEmpty()) {
            throw  new IllegalStateException("No Default Pricing defined");
        }
        return rule.get();
    }

    @Override
    public Optional<PricingRule> fetchById(UUID ruleId) {
        return Optional.ofNullable(pricingRuleCache.get(ruleId));
    }

    @Override
    public PricingRule save(Double rate, PricingRule.PricingType type) {
        PricingRule rule = new PricingRule(rate, type);
        pricingRuleCache.put(rule.getRuleId(), rule);
        return rule;
    }
}
