package com.krishnamurti.playground.lld.parkinglot.service;

import com.krishnamurti.playground.lld.parkinglot.domain.PricingRule;

import java.util.Optional;
import java.util.UUID;

public interface PricingRuleService {
    Optional<PricingRule> getById(UUID ruleId);
    PricingRule save(PricingRule pricingRule);
}
